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

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.tiexue.mcp.base.util.DateUtil;
import com.tiexue.mcp.base.util.Md5Utils;
import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpChapter;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.task.entity.TaskBook;
import com.tiexue.mcp.task.entity.McpTaskConstants;

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
		String sign;
		switch (pageType) {
		case McpTaskConstants.PageType_BookList:
			sign = currentDate + "#booklist#" + getAppkey();
			try {
				sign = Md5Utils.ToBit32(sign, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tempUrl = tempUrl + "&sign=" + sign;
			modifyInternalUrl(tempUrl);
			break;
		case McpTaskConstants.PageType_BookInfo:
			String bid = "";
			Pattern tempPat = Pattern.compile("bid=(\\d+)");
			Matcher matcher = tempPat.matcher(getUrl());
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
			tempUrl = tempUrl + "&sign=" + sign;
			modifyInternalUrl(tempUrl);
			break;
		}
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
			bookMap=parseBookList(xmlstring);
			break;
		case McpTaskConstants.PageType_BookInfo:
			currentBook=parseBookInfo(xmlstring);
		default:
			break;
		}
	}

	/**
	 * 解析书籍列表
	 * @param htmlstring
	 * @return
	 */
	private HashMap<String, TaskBook> parseBookList(String xmlstring) {
 		Document doc = null;
 		HashMap<String,TaskBook> returnMap=new HashMap<>();
		try {
			doc = DocumentHelper.parseText(xmlstring);
		} catch (DocumentException e1) {
			logger.error("parseBookList DocumentHelper.parseText error url:"+internalUrl);
			e1.printStackTrace();
		}
		String xpath = "//document/items/item";
		@SuppressWarnings("unchecked")
		List<Element> list = doc.selectNodes(xpath);
		for (Element e : list) {
			TaskBook book=new TaskBook();
			Element bookidEle= e.element("bookid");
			if(bookidEle!=null){
				String bookid= bookidEle.getStringValue();
				book.setCpbid(bookid);
			}
			Element titleEle=e.element("title");
			if(titleEle!=null){
				String title=titleEle.getStringValue();
				book.setCpname(title);;
			}
			Element urlEle=e.element("url");
			if(urlEle!=null){
				String url=urlEle.getStringValue();
				book.setUrl(url);
			}
			if(book.getUrl()==null||book.getUrl().isEmpty())
				continue;
			returnMap.put(book.getUrl(),book);
		}
		return returnMap;
	}
	/**
	 * 解析书籍信息
	 * @param xmlstring
	 * @return
	 */
	private TaskBook parseBookInfo(String xmlstring){
		TaskBook bookInfo=new TaskBook();
		Document doc=null;
		try {
			doc=DocumentHelper.parseText(xmlstring);
			
		} catch (DocumentException e) {
			logger.error(" parseBookInfo DocumentHelper.parseText error url:"+internalUrl);
			e.printStackTrace();
		}
		Node tempNode= doc.selectSingleNode("//document/info");
		if(tempNode==null)
			return null;
		String title=tempNode.valueOf("title");
		if(title!=null)
			bookInfo.setName(title);
		String chaptersurl=tempNode.valueOf("chaptersurl");
		if(chaptersurl!=null)
			bookInfo.setChaptersurl(chaptersurl);
		String bookid=tempNode.valueOf("bookid");
		if(bookid!=null)
			bookInfo.setCpbid(bookid);
		String category=tempNode.valueOf("category");
		if(category!=null){
			bookInfo.setClassify(getClassify(category));
		}
		String image_big=tempNode.valueOf("image_big");
		if(image_big!=null)
			bookInfo.setCoverimg(image_big);
		String comment=tempNode.valueOf("comment");
		if(comment!=null)
			bookInfo.setIntro(comment);
		String author=tempNode.valueOf("author");
		if(author!=null)
			bookInfo.setAuthor(author);
		String postdate=tempNode.valueOf("postdate");
		if(postdate!=null){
			Date date= DateUtil.timestamp2Date(postdate);
			bookInfo.setCreatetime(date);
			bookInfo.setUpdatetime(date);
		}
		String size=tempNode.valueOf("size");
		if(size!=null){
			int words=0;
			try {
				int wwords=Integer.parseInt(size);
			} catch (Exception e) {
			}
			bookInfo.setWords(words);
		}
		
		//铁血的书默认为男生频道
	    bookInfo.setChanneltype(McpConstants.McpChannelType_Body);
		//fullflag 1完本    0 更新
		String fullflag=tempNode.valueOf("fullflag");
		if(fullflag!=null&&fullflag=="0"){
			bookInfo.setBookstatus(McpConstants.BookStatus_Update);
		}
		//isvip 1 vip   0免费小说
		String isvip=tempNode.valueOf("isvip");
        if(isvip!=null||isvip=="0"){
        	bookInfo.setChargemode(McpConstants.ChargeMode_Free);
        }
        bookInfo.setCpid(getCpId());
	 	return bookInfo;
	}

	
	
	@Override
	boolean isUpdateBook(McpBook book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isUpdateChapter(McpChapter chapter) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private int getClassify(String category){
		//默认为军事
		int returnInt=McpConstants.Classify_Junshi;
		switch (category) {
		case "军事科幻":
		case "中短篇集":
			returnInt=McpConstants.Classify_Junshi;
			break;
		case "仙侠":
			returnInt=McpConstants.Classify_Xuanhuan;
			break;
		case "都市":
			returnInt=McpConstants.Classify_Dushi;
			break;
		case "历史架空":
			returnInt=McpConstants.Classify_Lishi;
			break;
		case "推理":
		case "悬疑":
			returnInt=McpConstants.Classify_Xuanyi;
			break;
		case "情感":
			returnInt=McpConstants.Classify_Yanqing;
			break;
		case "玄幻":
			returnInt=McpConstants.Classify_Xuanhuan;
			break;
		}
		return returnInt;
	}

}
