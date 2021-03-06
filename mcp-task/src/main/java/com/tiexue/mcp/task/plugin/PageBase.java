package com.tiexue.mcp.task.plugin;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Hash;

import com.tiexue.mcp.base.util.HttpUtils;
import com.tiexue.mcp.core.dto.bookrackCookieDto;
import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpChapter;
import com.tiexue.mcp.task.entity.TaskBook;
import com.tiexue.mcp.task.entity.TaskChapter;
/**
 * 获取接口小说数据的基类
 * @author zhangxiaowei
 *
 */
public abstract class PageBase {

	private static Logger logger = Logger.getLogger(PageBase.class);
	// 真实的url地址
	protected String internalUrl;
	// 页面匹配规则
	protected PageFeature pageFeature;
	// 页面类型
	public int pageType;
	// 接口地址
	private String url;
	// 接口秘钥
	private String appkey;
	//存放书籍列表信息
	protected List<TaskBook> bookList=null;
	//存放章节列表信息
	protected List<TaskChapter> chapterList=null;
	//存放当前采集的书籍信息
	protected TaskBook currentBook=null;
	//存放当前采集的章节信息
	protected TaskChapter currentChapter=null;
	//采集成功的状态
	public boolean collectionStatus=true;
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (internalUrl == null)
			this.internalUrl = url;
		if (pageFeature == null)
			pageFeature = new PageFeature();
		buildPageFeatureRules(pageFeature);
		pageType = pageFeature.matchUrl(url);
		this.url = url;
	}
	

	/**
	 * 修改接口地址
	 * 
	 * @param url
	 */
	public void modifyInternalUrl(String url) {
		this.internalUrl = url;
	}


	/**
	 * 创建接口地址的规则
	 */
	abstract void buildPageFeatureRules(PageFeature pageFeature);

	
	
	/**
	 * 获取接口数据入口
	 * 
	 * @param isUTF8
	 */
	public void load(boolean isUTF8) {
		try {
			//获取接口内容
			String result = HttpUtils.HttpRequest(internalUrl, "post", "", isUTF8);
			parseXmlString(result);

		} catch (Exception e) {
			logger.error("collection data error.url:" + url + ".msg:" + e.getMessage());
			collectionStatus=false;
		}

	}

	/**
	 * 解析获取的内容
	 * 
	 * @param Xmlstring
	 */
	 abstract void parseXmlString(String xmlstring);
	 /**
	  * 判断书籍是否需要更新
	  * @return
	  */
	 abstract boolean isUpdateBook(McpBook book,TaskBook taskBook);
	 /**
	  * 判断章节是否需要更新
	  * @param chapter
	  * @return
	  */
	 abstract boolean isUpdateChapter(McpChapter chapter,TaskChapter taskChapter);

}
