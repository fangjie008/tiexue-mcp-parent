package com.tiexue.mcp.task.plugin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.base.util.Md5Utils;
import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpChapter;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.task.entity.McpTaskConstants;
import com.tiexue.mcp.task.entity.TaskBook;
import com.tiexue.mcp.task.entity.TaskChapter;




/**
 * 获取铁血读书接口的小说
 * 
 * @author zhangxiaowei
 *
 */
public class EPageTieXueDuShu extends PageBase {

	private static Logger logger = Logger.getLogger(PluginGeneral.class);

	private static String currentDate;

	public void load(boolean isUTF8) {
		currentDate = DateUtil.date2Str(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
		String tempUrl = getUrl();
		String sign = "";
		String bid = "";
		String cid = "";
		Pattern tempPat;
		Matcher matcher;
		switch (pageType) {
		case McpTaskConstants.PageType_BookList:
			sign = currentDate + "#booklist#" + getAppkey();
			try {
				sign = Md5Utils.ToBit32(sign, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case McpTaskConstants.PageType_BookInfo:
			tempPat = Pattern.compile("bid=(\\d+)");
			matcher = tempPat.matcher(getUrl());
			if (matcher.find()) {
				bid = matcher.group(1);
			}
			sign = currentDate + "#bookinfo#" + bid + "#" + getAppkey();
			try {
				sign = Md5Utils.ToBit32(sign, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case McpTaskConstants.PageType_ChapterList:
			tempPat = Pattern.compile("bid=(\\d+)");
			matcher = tempPat.matcher(getUrl());
			if (matcher.find()) {
				bid = matcher.group(1);
			}
			sign = currentDate + "#chapterlist#" + bid + "#" + getAppkey();
			try {
				sign = Md5Utils.ToBit32(sign, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case McpTaskConstants.PageType_ChapterInfo:
			tempPat = Pattern.compile("bid=(\\d+)");
			matcher = tempPat.matcher(getUrl());
			if (matcher.find()) {
				bid = matcher.group(1);
			}
			tempPat = Pattern.compile("cid=(\\d+)");
			matcher = tempPat.matcher(getUrl());
			if (matcher.find()) {
				cid = matcher.group(1);
			}
			sign = currentDate + "#chapter#" + bid + "#" + cid + "#" + getAppkey();
			try {
				sign = Md5Utils.ToBit32(sign, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		tempUrl = tempUrl + "&sign=" + sign;
		modifyInternalUrl(tempUrl);
		super.load(isUTF8);
	}

	@Override
	void buildPageFeatureRules(PageFeature pageFeature) {
		pageFeature.injectUrlRule(McpTaskConstants.PageType_BookList, "method=booklist");
		pageFeature.injectUrlRule(McpTaskConstants.PageType_BookInfo, "method=bookinfo");
		pageFeature.injectUrlRule(McpTaskConstants.PageType_ChapterList, "method=chapterlist");
		pageFeature.injectUrlRule(McpTaskConstants.PageType_ChapterInfo, "method=chapter", "cid=");
	}

	@Override
	void parseXmlString(String xmlstring) {
		if (xmlstring == null) {
			logger.error(" htmlstring is null。url:" + getUrl());
		}
		switch (pageType) {
		case McpTaskConstants.PageType_BookList:
			bookList = parseBookList(xmlstring);
			break;
		case McpTaskConstants.PageType_BookInfo:
			currentBook = parseBookInfo(xmlstring);
			break;
		case McpTaskConstants.PageType_ChapterList:
			chapterList = parseChapterList(xmlstring);
			break;
		case McpTaskConstants.PageType_ChapterInfo:
			currentChapter = parseChapterInfo(xmlstring);
			break;
		default:
			break;
		}
	}

	/**
	 * 解析书籍列表
	 * 
	 * @param htmlstring
	 * @return
	 */
	private List<TaskBook> parseBookList(String xmlstring) {
		Document doc = null;
		List<TaskBook> returns = new ArrayList<TaskBook>();
		try {
			doc = DocumentHelper.parseText(xmlstring);
		} catch (DocumentException e1) {
			logger.error("parseBookList DocumentHelper.parseText error url:" + internalUrl);
			e1.printStackTrace();
		}
		String xpath = "//document/items/item";
		@SuppressWarnings("unchecked")
		List<Element> list = doc.selectNodes(xpath);
		for (Element e : list) {
			TaskBook book = new TaskBook();
			Element bookidEle = e.element("bookid");
			if (bookidEle != null) {
				String bookid = bookidEle.getStringValue();
				book.setCpbid(bookid);
			}
			Element titleEle = e.element("title");
			if (titleEle != null) {
				String title = titleEle.getStringValue();
				book.setName(title);
				;
			}
			Element urlEle = e.element("url");
			if (urlEle != null) {
				String url = urlEle.getStringValue();
				book.setUrl(url);
			}
			if (book.getUrl() == null || book.getUrl().isEmpty())
				continue;
			returns.add(book);
		}
		return returns;
	}

	/**
	 * 解析书籍信息
	 * 
	 * @param xmlstring
	 * @return
	 */
	private TaskBook parseBookInfo(String xmlstring) {
		TaskBook bookInfo = new TaskBook();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlstring);

		} catch (DocumentException e) {
			logger.error(" parseBookInfo DocumentHelper.parseText error url:" + internalUrl);
			e.printStackTrace();
		}
		Node tempNode = doc.selectSingleNode("//document/info");
		if (tempNode == null)
			return null;
		String title = tempNode.valueOf("title");
		if (title != null)
			bookInfo.setName(title);
		String chaptersurl = tempNode.valueOf("chaptersurl");
		if (chaptersurl != null)
			bookInfo.setChaptersurl(chaptersurl);
		String bookid = tempNode.valueOf("bookid");
		if (bookid != null)
			bookInfo.setCpbid(bookid);
		String category = tempNode.valueOf("category");
		if (category != null) {
			bookInfo.setClassify(getClassify(category));
		}
		String image_big = tempNode.valueOf("image_big");
		if (image_big != null)
			bookInfo.setCoverimg(image_big);
		String comment = tempNode.valueOf("comment");
		if (comment != null)
			bookInfo.setIntro(comment);
		String author = tempNode.valueOf("author");
		if (author != null)
			bookInfo.setAuthor(author);
		String postdate = tempNode.valueOf("postdate");
		if (postdate != null) {
			Date date = DateUtil.secondTimestamp2Date(postdate);
//			bookInfo.setCreatetime(date);
			bookInfo.setUpdatetime(date);
		}
		String size = tempNode.valueOf("size");
		if (size != null) {
			int words = 0;
			try {
				words = Integer.parseInt(size);
			} catch (Exception e) {
			}
			bookInfo.setWords(words);
		}

		// 铁血的书默认为男生频道
		bookInfo.setChanneltype(McpConstants.McpChannelType_Body);
		// fullflag 1完本 0 更新
		String fullflag = tempNode.valueOf("fullflag");
		if (fullflag != null && fullflag.equals("0")) {
			bookInfo.setBookstatus(McpConstants.BookStatus_Update);
		}
		else{
			bookInfo.setBookstatus(McpConstants.BookStatus_Finish);
		}
		// isvip 1 vip 0免费小说
		String isvip = tempNode.valueOf("isvip");
		if (isvip != null && isvip.equals("1")) {
			bookInfo.setChargemode(McpConstants.ChargeMode_Chapter);
		}else{
			bookInfo.setChargemode(McpConstants.ChargeMode_Free);
		}
		//铁血的书默认是军事类的
		bookInfo.setTags("军事");
		bookInfo.setAuditstatus(McpConstants.AuditStatus_Pass);
		return bookInfo;
	}

	/**
	 * 解析章节列表
	 * 
	 * @param htmlstring
	 * @return
	 */
	private List<TaskChapter> parseChapterList(String xmlstring) {
		Document doc = null;
		List<TaskChapter> returns = new ArrayList<TaskChapter>();
		try {
			doc = DocumentHelper.parseText(xmlstring);
		} catch (DocumentException e1) {
			logger.error("parseChapterList DocumentHelper.parseText error url:" + internalUrl);
			e1.printStackTrace();
		}
		String xpath = "//document/items/item";
		@SuppressWarnings("unchecked")
		List<Element> list = doc.selectNodes(xpath);
		TaskChapter chapter;
		int order = 0;
		for (Element e : list) {
			chapter = new TaskChapter();
			Element chapternameEle = e.element("chaptername");
			if (chapternameEle != null) {
				String chaptername = chapternameEle.getStringValue();
				chapter.setName(chaptername);
			}
			Element chapterurlEle = e.element("chapterurl");
			if (chapterurlEle != null) {
				String chapterurl = chapterurlEle.getStringValue();
				chapter.setChapterUrl(chapterurl);
			}
			Element cid = e.element("cid");
			if (cid != null) {
				String cpchapterid = cid.getStringValue();
				chapter.setCpchapterid(cpchapterid);
			}
			Element isvipEle = e.element("isvip");
			String isvip = isvipEle.getStringValue();
			if (isvip != null && isvip.equals("1")) {
				chapter.setIsvip(McpConstants.Chapter_pay);
			} else {
				chapter.setIsvip(McpConstants.Chapter_Free);
			}
			String postdate = e.valueOf("postdate");
			if (postdate != null) {
				Date date = DateUtil.secondTimestamp2Date(postdate);
				chapter.setUpdatetime(date);
			}
			if (chapter.getChapterUrl() == null || chapter.getChapterUrl().isEmpty())
				continue;
			chapter.setOrder(++order);
			returns.add(chapter);
		}
		return returns;
	}

	/**
	 * 解析章节信息
	 * 
	 * @param xmlstring
	 * @return
	 */
	private TaskChapter parseChapterInfo(String xmlstring) {
		TaskChapter chapterInfo = new TaskChapter();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlstring);
		} catch (DocumentException e) {
			logger.error(" parseChapterInfo DocumentHelper.parseText error url:" + internalUrl);
			e.printStackTrace();
		}
		Node tempNode = doc.selectSingleNode("//document");
		if (tempNode == null)
			return null;
		String title = tempNode.valueOf("chaptertitle");
		if (title != null)
			chapterInfo.setName(title);
		String content = tempNode.valueOf("chaptercontent");
		if (content != null) {
			chapterInfo.setContent(content);
			// 铁血读书接口没有字数
			int words = content.length();
			chapterInfo.setWords(words);
			// 铁血读书默认千字四分钱
			int price = words / 250;
			chapterInfo.setPrice(price);
		}
		//铁血的书默认审核通过
		chapterInfo.setAuditstatus(McpConstants.AuditStatus_Pass);
		return chapterInfo;
	}

	@Override
	boolean isUpdateBook(McpBook book,TaskBook taskBook) {
		if(book!=null&&taskBook!=null){
			//如果采集状态为0即上次采集没有完成而异常中断，则需要继续采集
			if(book.getCollectionstatus()==McpConstants.Book_OnCollection){
				return true;
			}
			//如果采集的更新日期大于数据库中的更新日期则说明书籍有更新(精确到秒)
			if(taskBook.getUpdatetime()!=null&&book.getUpdatetime()!=null
					&&((taskBook.getUpdatetime().getTime()/1000*1000)>book.getUpdatetime().getTime())){
				return true;
			}
		}
		return false;
	}

	@Override
	boolean isUpdateChapter(McpChapter chapter,TaskChapter taskChapter) {
		if(chapter!=null&&taskChapter!=null&&taskChapter.getUpdatetime()!=null&&chapter.getUpdatetime()!=null){
			//如果采集章节的更新日期大于数据库中保存的章节的更新日期则说明章节有更新(精确到秒)
			if((taskChapter.getUpdatetime().getTime()/1000*1000)>chapter.getUpdatetime().getTime()){
				return true;
			}
		}
		return false;
	}

	private int getClassify(String category) {
		// 默认为军事
		int returnInt = McpConstants.Classify_Junshi;
		switch (category) {
		case "军事科幻":
		case "中短篇集":
			returnInt = McpConstants.Classify_Junshi;
			break;
		case "仙侠":
			returnInt = McpConstants.Classify_Xuanhuan;
			break;
		case "都市":
			returnInt = McpConstants.Classify_Dushi;
			break;
		case "历史架空":
			returnInt = McpConstants.Classify_Lishi;
			break;
		case "推理":
		case "悬疑":
			returnInt = McpConstants.Classify_Xuanyi;
			break;
		case "情感":
			returnInt = McpConstants.Classify_Yanqing;
			break;
		case "玄幻":
			returnInt = McpConstants.Classify_Xuanhuan;
			break;
		}
		return returnInt;
	}

}
