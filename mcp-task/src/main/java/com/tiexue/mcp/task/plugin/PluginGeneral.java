package com.tiexue.mcp.task.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Var;

import com.tiexue.mcp.core.entity.McpBaseInfo;
import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpChapter;
import com.tiexue.mcp.core.service.IMcpBookService;
import com.tiexue.mcp.core.service.IMcpChapterService;
import com.tiexue.mcp.task.entity.McpTaskConstants;
import com.tiexue.mcp.task.entity.TaskBook;
import com.tiexue.mcp.task.entity.TaskChapter;
import com.tiexue.mcp.task.util.ConvertBook;
import com.tiexue.mcp.task.util.ConvertChapter;


/**
 * 同步小说接口执行操作类
 * 
 * @author zhangxiaowei
 *
 */
public class PluginGeneral {
	private static Logger logger = Logger.getLogger(PluginGeneral.class);
	// 采集终止符号
	public final String endFlag = "single_complete";
	// 调用类的全称
	private String pageName;
	// 接口地址
	public String interfaceUrl;
	// 当前采集的站点
	protected PageBase currentTask;
	// 存放地址
	protected List<String> scheduleList;
	// 存放数据信息
	protected McpBaseInfo baseInfo;
	// 接口秘钥
	protected String appkey;
	// 合作方Id
	protected Integer cpId;
	// 合作名称
	protected String cpName;
	// 存放书籍列表信息
	protected HashMap<String, TaskBook> bookList = null;
	// 存放章节列表信息
	protected HashMap<String, TaskChapter> chapterList = null;
	// 存放当前采集的书籍信息
	protected McpBook tempBook = null;
	// 保存书籍的方法
	IMcpBookService iMcpBookService;
	// 保存章节的方法
	IMcpChapterService iMcpChapterService;
	// 当前采集的url
	private String currentUrl;
	//当前采集失败的页面
	private String currentErrorUrl="";
	private int errorCount=0;
	//章节采集成功的个数
	private int collectionChapterCount=0;

	/**
	 * 构造方法
	 * 
	 * @param pageNme
	 * @param baseInfo
	 */
	public PluginGeneral(String pageNme, McpBaseInfo baseInfo) {
		scheduleList = new ArrayList<>();
		this.pageName = pageNme;
		this.baseInfo = baseInfo;
		if (baseInfo != null) {
			this.interfaceUrl = baseInfo.getInterfaceurl1();
			this.appkey = baseInfo.getAppkey();
			this.cpId = baseInfo.getCpid();
			this.cpName = baseInfo.getName();
		}
	}

	/**
	 * 执行
	 * 
	 * @param inteval
	 *            间隔时间
	 */
	public void execute(int inteval) {
		try {
			scheduleList.add(interfaceUrl);
			while (scheduleList.size() > 0) {
				currentUrl = scheduleList.get(0);
				// 开始任务
				startTask(currentUrl);
				// 加载完成
				buildModel();
				//移除加载列表
				removeScheduleList();
				// 休息n秒钟
				Thread.sleep(10);
				// 如果本条内容采集完毕
				if (scheduleList != null && scheduleList.size() > 0 && scheduleList.get(0) == endFlag) {
					if (tempBook != null){
						int totalCount=chapterList.size();
						if(collectionChapterCount>=totalCount){
							iMcpBookService.updateCollectionStatus(tempBook.getId());
							logger.info("bookID:"+tempBook.getId()+"所有章节采集完成！更新采集完成状态");
						}
						else{
							logger.info("bookID:"+tempBook.getId()+"有章节没有保存成功。不能更新采集完成状态");
						}
						
					}
					scheduleList.remove(0);
				}
			}
		} catch (Exception e) {
			logger.debug("PluginGeneral.execute error. url: " + interfaceUrl + e);
			// e.printStackTrace();
		}
	}

	/**
	 * 开始任务
	 * 
	 * @param baseUrl
	 */
	private void startTask(String baseUrl) {

		try {
			currentTask = (PageBase) Class.forName(pageName).newInstance();
			currentTask.setAppkey(appkey);
			currentTask.setUrl(baseUrl);
			currentTask.load(true);
			
		} catch (Exception e) {
			logger.error("startTask error." + e.getMessage() + ". url:" + baseUrl);

		}

	}

	/**
	 * 加载完成
	 */
	private void buildModel() {
		switch (currentTask.pageType) {
		case McpTaskConstants.PageType_BookList:
			parseBookList(currentTask.bookList);
			break;
		case McpTaskConstants.PageType_BookInfo:
			parseBookInfo(currentTask.currentBook);
			break;
		case McpTaskConstants.PageType_ChapterList:
			parseChapterList(currentTask.chapterList);
			break;
		case McpTaskConstants.PageType_ChapterInfo:
			if(currentTask.collectionStatus)
				++collectionChapterCount;
			parseChapterInfo(currentTask.currentChapter);
			break;
		default:
			break;
		}
	}

	/**
	 *  如果采集成功或者采集失败超过三次则，把采集的url从加载列表移除
	 */
	private void removeScheduleList(){
		//如果采集成功
		if(currentTask.collectionStatus){
			errorCount=0;
			currentErrorUrl="";
		}
		//采集失败
		else{
			if(!currentErrorUrl.isEmpty()&&currentErrorUrl==currentUrl){
				errorCount++;
			}
			else{
				currentErrorUrl=currentUrl;
				errorCount=1;
			}
		}
		if(currentTask.collectionStatus||errorCount>=3){
			// 移除加载列表
			scheduleList.remove(0);
		}
	}
	
	/**
	 * 解析获取到的书籍列表
	 * 
	 * @param bookMap
	 */
	private void parseBookList(List<TaskBook> bookMap) {
		bookList = new HashMap<String, TaskBook>();
		if (bookMap == null)
			return;
		for (TaskBook taskBook : bookMap) {
			// 添加采集书籍内容的url地址
			scheduleList.add(taskBook.getUrl());
			taskBook.setCpid(this.cpId);
			taskBook.setCpname(this.cpName);
			bookList.put(taskBook.getUrl(), taskBook);
		}

	}

	/**
	 * 解析书籍信息
	 * 
	 * @param taskbook
	 */
	private void parseBookInfo(TaskBook taskbook) {
		taskbook.setCpid(this.cpId);
		taskbook.setCpname(this.cpName);
		boolean isCollenctionChapter = false;
		int index = scheduleList.indexOf(currentUrl) + 1;
		if (taskbook == null || taskbook.getCpbid() == null)
			return;
		// 如果在书籍列表中已经采集过一部分书籍信息，则需要合并两次采集的书籍信息
		if (bookList != null) {
			TaskBook tempTaskBook = bookList.get(currentUrl);
			if (tempTaskBook != null) {
				taskbook = ConvertBook.fillTaskBook(tempTaskBook, taskbook);
			}
		}
		McpBook mcpBook = iMcpBookService.selectByCpBId(taskbook.getUniqueflag());
		// 书籍已经采集
		if (mcpBook != null) {
			if (!currentTask.isUpdateBook(mcpBook, taskbook)) {
				logger.info("书籍 bookId:" + mcpBook.getId() + "不需要更新");
				tempBook = mcpBook;
				return;
			}
			// 有更新
			mcpBook = ConvertBook.toMcpBookDaoForUpdate(mcpBook, taskbook);
			int returnId = iMcpBookService.taskUpdate(mcpBook);
			logger.info("更新书籍信息  bookId:" + mcpBook.getId());
			isCollenctionChapter = true;

		}
		// 书籍未采集，需要保存到数据库中
		else {
			mcpBook = ConvertBook.toMcpBookDaoForInsert(taskbook);
			mcpBook = iMcpBookService.taskInsert(mcpBook);
			logger.info("新增书籍信息  bookId:" + mcpBook.getId());
			if (mcpBook != null && mcpBook.getId() != null && mcpBook.getId() > 0) {
				isCollenctionChapter = true;
			} else {
				// 插入失败，不进行下边的操作
			}
		}
		// 添加采集章节的url
		// if (isCollenctionChapter) {
		if (taskbook.getChaptersurl() != null) {
			// 添加采集章节列表的url地址
			scheduleList.add(index++, taskbook.getChaptersurl());
			// 插入采集终止符号
			scheduleList.add(index++, endFlag);
		}
		// }
		// 给存放临时书籍的字段赋值
		tempBook = mcpBook;
	}

	/**
	 * 解析获取到的书籍列表
	 * 
	 * @param bookMap
	 */
	private void parseChapterList(List<TaskChapter> chapters) {
		chapterList = new HashMap<String, TaskChapter>();
		int index = scheduleList.indexOf(currentUrl) + 1;
		if (chapters == null)
			return;
		for (TaskChapter taskChapter : chapters) {
			// 添加采集书籍内容的url地址
			scheduleList.add(index++, taskChapter.getChapterUrl());
			// 填充书籍信息
			if (tempBook != null) {
				taskChapter.setBookid(tempBook.getId());
				taskChapter.setBookname(tempBook.getName());
				taskChapter.setCpbookid(tempBook.getCpbid());
			}
			taskChapter.setCpid(this.cpId);
			chapterList.put(taskChapter.getChapterUrl(), taskChapter);
		}
	}

	/**
	 * 解析书籍信息
	 * 
	 * @param taskbook
	 */
	private void parseChapterInfo(TaskChapter taskChapter) {
		if (taskChapter == null)
			return;
		taskChapter.setCpid(this.cpId);
		// 如果在书籍列表中已经采集过一部分书籍信息，则需要合并两次采集的书籍信息
		if (chapterList != null) {
			TaskChapter tempTaskChapter = chapterList.get(currentUrl);
			if (tempTaskChapter != null) {
				taskChapter = ConvertChapter.fillTaskChapter(tempTaskChapter, taskChapter);
			}
		}
		String cpbookid = taskChapter.getCpbookid() == null ? "" : taskChapter.getCpbookid();
		McpChapter mcpChapter = iMcpChapterService.selectByCpBId(taskChapter.getUniqueflag());
		// 书籍已经采集
		if (mcpChapter != null) {
			if (!currentTask.isUpdateChapter(mcpChapter, taskChapter)) {
				logger.info("章节 chapterId:" + mcpChapter.getId() + "不需要更新");
				return;
			}
			mcpChapter = ConvertChapter.toMcpChapterDaoForUpdate(mcpChapter, taskChapter);
			// 内容有更新
			iMcpChapterService.taskUpdate(mcpChapter);
			logger.info("更新章节信息  chapterId:" + mcpChapter.getId());

		}
		// 书籍未采集，需要保存到数据库中
		else {
			mcpChapter = ConvertChapter.toMcpChapterDaoForInsert(taskChapter);
			McpChapter bMcpChapter = iMcpChapterService.taskInsert(mcpChapter);
			logger.info("新增章节信息  chapterId:" + mcpChapter.getId());
			if (bMcpChapter != null && bMcpChapter.getId() != null && bMcpChapter.getId() > 0) {
				// 章节采集完成
			} else {
				// 插入失败，不进行下边的操作
			}
		}
	}

	public IMcpBookService getiMcpBookService() {
		return iMcpBookService;
	}

	public void setiMcpBookService(IMcpBookService iMcpBookService) {
		this.iMcpBookService = iMcpBookService;
	}

	public IMcpChapterService getiMcpChapterService() {
		return iMcpChapterService;
	}

	public void setiMcpChapterService(IMcpChapterService iMcpChapterService) {
		this.iMcpChapterService = iMcpChapterService;
	}
}
