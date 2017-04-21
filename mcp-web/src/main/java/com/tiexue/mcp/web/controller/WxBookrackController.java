package com.tiexue.mcp.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tiexue.mcp.base.util.CookieUtils;
import com.tiexue.mcp.core.dto.PageUserDto;
import com.tiexue.mcp.core.dto.WxBookrackDto;
import com.tiexue.mcp.core.dto.bookrackCookieDto;
import com.tiexue.mcp.core.entity.EnumType;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.entity.WxBookrack;
import com.tiexue.mcp.core.entity.WxChapter;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxBookrackService;
import com.tiexue.mcp.core.service.IWxChapterService;
import com.tiexue.mcp.core.service.IWxUserService;

@Controller
@RequestMapping("wxBookrack")
public class WxBookrackController {
	// 日志
	private Logger logger = Logger.getLogger(WxBookrackController.class);
	@Resource
	IWxBookrackService bookrackService;
	@Resource
	IWxBookService bookService;
	//获取章节信息的服务
	@Resource
	IWxChapterService wxChapterService;
	@Resource
	IWxUserService userSer;
	
	@RequestMapping("addBookrack")
	@ResponseBody
	public String insertBookrack(HttpServletRequest request, Integer bookId, String bookName,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token)
			throws UnsupportedEncodingException {
		String userIdStr = "";
		int userId = 0;
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
				if (userIdStr != null && !userIdStr.isEmpty())
					userId = Integer.parseInt(userIdStr);
			}
		}
		JSONObject getObj = new JSONObject();
		WxBookrack rack;
		if (bookId > 0 && userId > 0) {
			rack = bookrackService.getModelByBookId(userId, bookId);
			if (rack != null && rack.getBookid() > 0) {
				getObj.put("ok", false);
				getObj.put("msg", "已收藏此书");
			} else {
				if (bookName == null) {
					WxBook book = bookService.selectByPrimaryKey(bookId);
					if (book != null)
						bookName = book.getName();
				}

				Date time = new Date();
				rack = new WxBookrack();
				rack.setBookid(bookId);
				rack.setBookname(bookName);
				rack.setUserid(userId);
				rack.setChapterid(0);
				rack.setLocation(0);
				rack.setCreatetime(time);
				rack.setChaptertitle("");
				int res = bookrackService.insert(rack);
				getObj.put("ok", res > 0 ? true : false);
				getObj.put("msg", "收藏成功");
			}
		}
		return getObj.toString();
	}
	
	@RequestMapping("updateBookrack")
	@ResponseBody
	public String updateBookrack(HttpServletRequest request, Integer bookId, String bookName, Integer chapterId,
			String chapterName,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token)
			throws UnsupportedEncodingException {
		String userIdStr = "";
		int userId = 0;
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
				if (userIdStr != null && !userIdStr.isEmpty())
					userId = Integer.parseInt(userIdStr);
			}
		}
		JSONObject getObj = new JSONObject();
		WxBookrack rack;
		if (bookId > 0&&userId>0) {
			rack = bookrackService.getModelByBookId(userId, bookId);
			if (rack != null && rack.getBookid() > 0) {
				Date time = new Date();
				rack.setBookid(bookId);
				rack.setUserid(userId);
				rack.setChapterid(chapterId);
				rack.setCreatetime(time);
				rack.setChaptertitle(chapterName);
				int res = bookrackService.updateByPrimaryKey(rack);
				getObj.put("ok", res > 0 ? true : false);
				getObj.put("msg", "收藏成功");
			} else {

				Date time = new Date();
				rack = new WxBookrack();
				rack.setBookid(bookId);
				rack.setBookname(bookName);
				rack.setUserid(userId);
				rack.setChapterid(chapterId);
				rack.setLocation(0);
				rack.setCreatetime(time);
				rack.setChaptertitle(chapterName);
				int res = bookrackService.insert(rack);
				getObj.put("ok", res > 0 ? true : false);
				getObj.put("msg", "收藏成功");
			}
		}
		return getObj.toString();
	}
	
	/**
	 * 获取书架信息 最多获取20条书架
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping("list")
	public String getBookrackList(HttpServletRequest request,HttpServletResponse response,
			@CookieValue(value = "defaultbookrack", required = true, defaultValue = "") String rackCookie,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token
			,@CookieValue(value ="from_name",required = true, defaultValue = "")String from_name) {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String fm = request.getParameter("fm");
		//把小说来源公共号信息放到cookie中
		if((from_name==null||from_name.isEmpty())&&fm!=null&&!fm.isEmpty()){
			CookieUtils.addcookie("from_name", 1*365*24*60*60, response,fm);
		}
		int userId = 0;
		WxChapter chap;
		List<WxBookrackDto> racks = new ArrayList<WxBookrackDto>();
		try {
			// 未登录
			if (userIdStr == null || userIdStr.isEmpty()) {
				if (rackCookie != null && !rackCookie.isEmpty()) {
					List<bookrackCookieDto> cookies = JSON.parseArray(rackCookie, bookrackCookieDto.class);
					if (cookies != null && cookies.size() > 0) {
						for (int i = cookies.size() - 1; i >= 0; i--) {
							// 最多取20条记录
							if (racks.size() >= 20)
								break;
							WxChapter curChap = null;
							WxChapter lastChap = null;
							WxBook book = bookService.selectByPrimaryKey(cookies.get(i).getBookid());
							lastChap = wxChapterService.getLastChapter(cookies.get(i).getBookid(),
									EnumType.ChapterStatus_OnLine);
							if (cookies.get(i).getChapterid() > 0) {
								curChap = wxChapterService.selectByPrimaryKey(cookies.get(i).getChapterid(),
										EnumType.ChapterStatus_OnLine);
							}else{
								curChap = wxChapterService.getFirstChapter(cookies.get(i).getBookid(),EnumType.ChapterStatus_OnLine);
							}
							racks.add(bookrackDtoFill(book, curChap, lastChap));
						}
					}

				}
			} // 已登录
			else {
				userId = Integer.parseInt(userIdStr);
				racks = bookrackService.getListByUserId(userId, 20);
				if (racks != null) {
					for (WxBookrackDto rackDto : racks) {
						chap = new WxChapter();
						chap = wxChapterService.getLastChapter(rackDto.getBookid(), EnumType.ChapterStatus_OnLine);
						if (chap != null) {
							rackDto.setLastchapterid(chap.getId());
							rackDto.setLastchaptertitle(chap.getTitle());
							rackDto.setLastsortorder(chap.getSortorder());
						}
						
						if(rackDto.getChapterid()==null||rackDto.getChapterid()<=0){
							WxChapter curChap = wxChapterService.getFirstChapter(rackDto.getChapterid(),EnumType.ChapterStatus_OnLine);
							if(curChap!=null){
								rackDto.setChapterid(curChap.getId());
								rackDto.setChaptertitle(curChap.getTitle());
								rackDto.setSortorder(curChap.getSortorder());
							}
						}

					}
				}
			}
			//未登录 cookie中也没有保存书架 则从数据库中获取默认的几本书
			if(racks==null||racks.isEmpty())
			{
				String status = EnumType.BookStatus_Finish + "," + EnumType.BookStatus_Update;
				String strWhere=" Status in ("+status+")"+" and tag='军事'";
				List<WxBook> books=bookService.getList(strWhere, "CreateTime",3);
				if(books!=null){
					for (WxBook wxBook : books) {
						WxChapter curChap = null;
						WxChapter lastChap = null;
					    curChap = wxChapterService.getFirstChapter(wxBook.getId(),EnumType.ChapterStatus_OnLine);
						lastChap = wxChapterService.getLastChapter(wxBook.getId(),
								EnumType.ChapterStatus_OnLine);
						racks.add(bookrackDtoFill(wxBook, curChap, lastChap));
					}
				}
			}
			request.setAttribute("bookracks", racks);
			request.setAttribute("fromurl", fm);
		} catch (Exception e) {
			logger.error("获取书架信息失败:" + e.getMessage());
		}

		return "/wxBookrack/index";

	}
	
	private WxBookrackDto bookrackDtoFill(WxBook book,WxChapter curChap,WxChapter lastChap){
		WxBookrackDto rack = new WxBookrackDto();
		if (book != null) {
			rack.setBookid(book.getId());
			rack.setBookname(book.getName());
			rack.setCoverimgs(book.getCoverimgs());
			rack.setIntr(book.getIntr());
			rack.setLocation(0);
			rack.setUserid(0);
		}
		if (curChap != null) {
			rack.setChapterid(curChap.getId());
			rack.setChaptertitle(curChap.getTitle());
			rack.setSortorder(curChap.getSortorder());
		}
		if (lastChap != null) {
			rack.setLastchapterid(lastChap.getId());
			rack.setLastchaptertitle(lastChap.getTitle());
			rack.setLastsortorder(lastChap.getSortorder());
		}
		return rack;
	}
}
