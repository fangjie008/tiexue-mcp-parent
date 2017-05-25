package com.tiexue.mcp.task.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.tiexue.mcp.core.entity.McpBaseInfo;
import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.task.entity.McpTaskConstants;
import com.tiexue.mcp.task.entity.TaskBook;
import com.tiexue.mcp.task.entity.TaskChapter;
import com.tiexue.mcp.task.service.ITaskBookService;
import com.tiexue.mcp.task.service.ITaskChapterService;


/**
 * 同步小说接口执行操作类
 * 
 * @author zhangxiaowei
 *
 */
public class PluginGeneral {
	private static Logger logger=Logger.getLogger(PluginGeneral.class);
	//调用类的全称
	private String pageName;
	//接口地址
	public String interfaceUrl;
	//当前采集的站点
	protected PageBase currentTask;
	// 存放地址
	protected List<String> scheduleList;
	//存放数据信息
	protected McpBaseInfo baseInfo;
	//接口秘钥
	protected String appkey;
	//合作方Id
	protected Integer cpId;
	//合作名称
	protected String cpName;
	//存放书籍列表信息		
	protected HashMap<String,TaskBook> bookMap=null;
	//存放章节列表信息
	protected HashMap<String,TaskChapter> chapterMap=null;
	//存放当前采集的书籍信息
	protected McpBook tempBook=null;
	//存放当前采集的章节信息
	protected TaskChapter currentChapter=null;
	@Resource
	ITaskBookService taskBookService;
	@Resource
	ITaskChapterService iTaskChapterService;
	
	/**
	 * 构造方法
	 * @param pageNme
	 * @param baseInfo
	 */
	public PluginGeneral(String pageNme, McpBaseInfo baseInfo) {
		scheduleList = new ArrayList<>();
		this.pageName = pageNme;
		this.baseInfo = baseInfo;
		if(baseInfo!=null){
			this.interfaceUrl=baseInfo.getInterfaceurl1();
			this.appkey=baseInfo.getAppkey();
			this.cpId=baseInfo.getCpid();
			this.cpName=baseInfo.getName();
		}
	}

	/**
	 * 执行
	 * @param inteval 间隔时间
	 */
	public void execute(int inteval) {
		scheduleList.add(interfaceUrl);
		while (scheduleList.size() > 0) {
			String url = scheduleList.get(0);
			//开始任务
			startTask(url);
			//加载完成
			buildModel();
		    //移除加载列表
			scheduleList.remove(0);
		}
	}

	/**
	 * 开始任务
	 * @param baseUrl
	 */
	private void startTask(String baseUrl) {

		try {
			currentTask=(PageBase)Class.forName(pageName).newInstance();
			currentTask.setAppkey(appkey);
			currentTask.setUrl(baseUrl);
			currentTask.setCpId(cpId);
			currentTask.setCpName(cpName);
			currentTask.load(true);
		} catch (Exception e) {
			logger.error("startTask error."+e.getMessage()+". url:"+baseUrl);
			
		}

	}
	
	/**
	 * 加载完成
	 */
	private void buildModel(){
		switch (currentTask.pageType) {
		case McpTaskConstants.PageType_BookList:
			parseBookList(currentTask.bookMap);
			break;
		case McpTaskConstants.PageType_BookInfo:
			parseBookInfo(currentTask.currentBook);
			break;
		default:
			break;
		}
	}
	
	/**
	 * 解析获取到的书籍列表
	 * @param bookMap
	 */
	private void parseBookList(HashMap<String, TaskBook> bookMap){
		if(bookMap==null)
			return;
		for(Map.Entry<String, TaskBook> entry: bookMap.entrySet()){
			//添加采集书籍内容的url地址
			scheduleList.add(entry.getKey());
		}
		this.bookMap=bookMap;
	}
	/**
	 * 解析书籍信息
	 * @param taskbook
	 */
	private void parseBookInfo(TaskBook taskbook){
		if(taskbook==null||taskbook.getCpbid()==null)
			return;
		McpBook mcpBook= taskBookService.selectByCpBId(taskbook.getCpbid());
		//书籍已经采集且有更新
		if(mcpBook!=null&&!currentTask.isUpdateBook(mcpBook)){
			taskBookService.update(mcpBook,taskbook);
		}
		//书籍未采集，需要保存到数据库中
		else{
			McpBook bMcpBook=taskBookService.insert(taskbook);
			if(bMcpBook!=null&&bMcpBook.getId()!=null&&bMcpBook.getId()>0){
				if(taskbook.getChaptersurl()!=null){
					//添加采集章节列表的url地址
					scheduleList.add(taskbook.getChaptersurl());
				}
				//给存放临时书籍的字段赋值
				tempBook=bMcpBook;
			}else{
				//插入失败，不进行下边的操作
			}
		}
	}
}
