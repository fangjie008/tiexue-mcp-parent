package com.tiexue.mcp.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiexue.mcp.base.util.CookieUtils;
import com.tiexue.mcp.core.dto.PageUserDto;
import com.tiexue.mcp.core.dto.ResultMsg;
import com.tiexue.mcp.core.dto.WxChapterSubDto;
import com.tiexue.mcp.core.entity.EnumType;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.entity.WxChapter;
import com.tiexue.mcp.core.entity.WxChapterSub;
import com.tiexue.mcp.core.service.IUserConsService;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxBookrackService;
import com.tiexue.mcp.core.service.IWxChapterService;
import com.tiexue.mcp.core.service.IWxChapterSubService;
import com.tiexue.mcp.core.service.IWxUserService;


@Controller
@RequestMapping("wxChapterSub")
public class WxChapterSubController {
	private static Logger logger=Logger.getLogger(WxChapterController.class);
	int pageSize=20;
	@Resource
	IWxChapterSubService chapterSubSer;
	@Resource
	IWxChapterService chapterService;
	@Resource
	IWxBookService bookService;
	@Resource
	IUserConsService userConsService;
	@Resource
	IWxUserService userSer;
	@Resource
	IWxBookrackService bookrackService;
	/**
	 * 获取免费章节的内容信息
	 * @param request
	 * @param attr
	 * @param rackCookie
	 * @param wx_gzh_token
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/index")
	public String getContent(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attr,
			@CookieValue(value = "defaultbookrack", required = true, defaultValue = "") String rackCookie,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token
			,@CookieValue(value ="from_name",required = true, defaultValue = "")String from_name)
			throws UnsupportedEncodingException {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String bookIdStr = request.getParameter("bookId");
		String chapterIdStr = request.getParameter("chapterId");
		String fm = request.getParameter("fm");
		String bookName = "";
		String chapterTitle="";
		int userId = 0;
		int bookId = 0;
		int chapterId = 0;
		String tag="";
		if (chapterIdStr != null && !chapterIdStr.isEmpty()) {
		    chapterId = Integer.parseInt(chapterIdStr);
			if (bookIdStr != null && !bookIdStr.isEmpty()) {
				bookId = Integer.parseInt(bookIdStr);
			}
			if (userIdStr != null && !userIdStr.isEmpty()) {
				userId = Integer.parseInt(userIdStr);
			}
			// 获取图书信息
			WxBook book = bookService.selectByPrimaryKey(bookId);
			if (book != null) {
				bookName = book.getName();
				tag=book.getTag();
			}
			// 章节数据
			WxChapter chapterModel = chapterService.selectByPrimaryKey(chapterId, EnumType.ChapterStatus_OnLine);
			if (chapterModel == null)
				return "wxChapterSub/index";
			else
				chapterTitle=chapterModel.getTitle();
			// 付费章节操作
			if (chapterModel.getChaptertype() == 1) {
				ResultMsg resultMsg = userConsService.consDeal(userId, bookId, bookName, chapterModel);
				if (!resultMsg.getStatus()) {
					switch (resultMsg.getNum()) {
					case EnumType.ResultNum_Login:
						return "redirect:/wxUser/login";
					case EnumType.ResultNum_Pay:
						attr.addAttribute("chapterId", chapterId);
						attr.addAttribute("bookId", bookId);
						attr.addAttribute("fm", fm);
						return "redirect:/wxPay/pay";
					case EnumType.ResultNum_Cons:
						attr.addAttribute("chapterId", chapterId);
						attr.addAttribute("bookId", bookId);
						attr.addAttribute("fm", fm);
						return "redirect:/wxConsume/subscribe";
					}
				}
				logger.error(resultMsg.getMsg());
			}
			// 获取章节信息
			WxChapterSubDto chapSubDto = getCahperDto(bookId, bookName, chapterId, chapterModel,tag);
			request.setAttribute("wxChapterSub", chapSubDto);
			if(chapterModel!=null)
			{
				int pageNo=0;
				if(chapterModel.getSortorder()%pageSize==0){
					if(chapterModel.getSortorder()>0)
						pageNo=chapterModel.getSortorder()-pageSize;
				}
				else
					pageNo=chapterModel.getSortorder()-(chapterModel.getSortorder()%pageSize);
				request.setAttribute("pageNo",pageNo);
			}
			request.setAttribute("fromurl", fm);
		}
		//保存书架
        if(bookId>0&&userId>0){
        	saveBookrack(bookId,userId,bookName,chapterId,chapterTitle);
        }
        //把小说来源公共号信息放到cookie中
		if((from_name==null||from_name.isEmpty())&&fm!=null&&!fm.isEmpty()){
			CookieUtils.addcookie("from_name", 1*365*24*60*60, response,fm);
		}
		return "wxChapterSub/index";
	}
	
	
	 /**
	  * 获取付费章节内容信息
	  * @param request
	  * @param attr
	  * @param rackCookie
	  * @param wx_gzh_token
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 @RequestMapping("/vip")
	 public String getVipContent(HttpServletRequest request, RedirectAttributes attr,HttpServletResponse response,
				@CookieValue(value = "defaultbookrack", required = true, defaultValue = "") String rackCookie,
				@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token
				,@CookieValue(value ="from_name",required = true, defaultValue = "")String from_name)
				throws UnsupportedEncodingException {
			String userIdStr = "";
			if (wx_gzh_token != "") {
				PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
				if (pageUser != null) {
					userIdStr = pageUser.getId();
				}
			}
			String bookIdStr = request.getParameter("bookId");
			String chapterIdStr = request.getParameter("chapterId");
			String fm = request.getParameter("fm");
			String bookName = "";
			String chapterTitle="";
			int userId = 0;
			int bookId = 0;
			int chapterId = 0;
			String tag="";
			if (chapterIdStr != null && !chapterIdStr.isEmpty()) {
			    chapterId = Integer.parseInt(chapterIdStr);
				if (bookIdStr != null && !bookIdStr.isEmpty()) {
					bookId = Integer.parseInt(bookIdStr);
				}
				if (userIdStr != null && !userIdStr.isEmpty()) {
					userId = Integer.parseInt(userIdStr);
				}
				// 获取图书信息
				WxBook book = bookService.selectByPrimaryKey(bookId);
				if (book != null) {
					bookName = book.getName();
					tag=book.getTag();
				}
				
				// 章节数据
				WxChapter chapterModel = chapterService.selectByPrimaryKey(chapterId, EnumType.ChapterStatus_OnLine);
				if (chapterModel == null)
					return "wxChapterSub/index";
				else
					chapterTitle=chapterModel.getTitle();
				// 付费章节操作
				if (chapterModel.getChaptertype() == 1) {
					ResultMsg resultMsg = userConsService.consDeal(userId, bookId, bookName, chapterModel);
					if (!resultMsg.getStatus()) {
						switch (resultMsg.getNum()) {
						case EnumType.ResultNum_Login:
							return "redirect:/wxUser/login";
						case EnumType.ResultNum_Pay:
							attr.addAttribute("chapterId", chapterId);
							attr.addAttribute("bookId", bookId);
							attr.addAttribute("fm", fm);
							return "redirect:/wxPay/pay";
						case EnumType.ResultNum_Cons:
							attr.addAttribute("chapterId", chapterId);
							attr.addAttribute("bookId", bookId);
							attr.addAttribute("fm", fm);
							return "redirect:/wxConsume/subscribe";
						}
					}
					logger.error(resultMsg.getMsg());
				}
				// 获取章节信息
				WxChapterSubDto chapSubDto = getCahperDto(bookId, bookName, chapterId, chapterModel,tag);
				if(chapterModel!=null)
				{
					int pageNo=0;
					if(chapterModel.getSortorder()%pageSize==0){
						if(chapterModel.getSortorder()>0)
							pageNo=chapterModel.getSortorder()-pageSize;
					}
					else
						pageNo=chapterModel.getSortorder()-(chapterModel.getSortorder()%pageSize);
					request.setAttribute("pageNo",pageNo);
				}
				request.setAttribute("wxChapterSub", chapSubDto);
				request.setAttribute("fromurl", fm);
			}
			//保存书架
	        if(bookId>0&&userId>0){
	        	saveBookrack(bookId,userId,bookName,chapterId,chapterTitle);
	        }
	        //把小说来源公共号信息放到cookie中
			if((from_name==null||from_name.isEmpty())&&fm!=null&&!fm.isEmpty()){
				CookieUtils.addcookie("from_name", 1*365*24*60*60, response,fm);
			}
			return "wxChapterSub/index";
		}
	

	/**
	 *  获取默认章节的内容信息
	 * @param request
	 * @param attr
	 * @param response
	 * @param wx_gzh_token
	 * @param from_name
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/defualt")
	public String getContentByBookId(HttpServletRequest request, RedirectAttributes attr,HttpServletResponse response,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token
			,@CookieValue(value ="from_name",required = true, defaultValue = "")String from_name)
			throws UnsupportedEncodingException {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String bookIdStr = request.getParameter("bookId");
		String fm = request.getParameter("fm");
		String bookName = "";
		int userId = 0;
		String tag="";
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
			int bookId = Integer.parseInt(bookIdStr);
			int chapterId = 0;
			// 获取图书信息
			WxBook book = bookService.selectByPrimaryKey(bookId);
			if (book != null) {
				bookName = book.getName();
				tag=book.getTag();
			}
			if (userIdStr != null && !userIdStr.isEmpty()) {
				userId = Integer.parseInt(userIdStr);
			}

			// 获取第一章数据
			WxChapter chapterModel = chapterService.getFirstChapter(bookId, EnumType.ChapterStatus_OnLine);
			if (chapterModel != null) {
				chapterId = chapterModel.getId();
				// 付费章节操作
				if (chapterModel.getChaptertype() == 1) {
					ResultMsg resultMsg = userConsService.consDeal(userId, bookId, bookName, chapterModel);
					if (!resultMsg.getStatus()) {
						switch (resultMsg.getNum()) {
						case EnumType.ResultNum_Login:
							return "wxUser/login";
						case EnumType.ResultNum_Pay:
							return "WxPay/pay";
						case EnumType.ResultNum_Cons:
							return "wxConsume/subscribe";
						}
					}
					logger.error(resultMsg.getMsg());
				}
			}
			// 获取章节信息
			WxChapterSubDto chapSubDto = getCahperDto(bookId, bookName, chapterId, chapterModel,tag);
			if(chapterModel!=null)
			{
				int pageNo=0;
				if(chapterModel.getSortorder()%pageSize==0){
					if(chapterModel.getSortorder()>0)
						pageNo=chapterModel.getSortorder()-pageSize;
				}
				else
					pageNo=chapterModel.getSortorder()-(chapterModel.getSortorder()%pageSize);
				request.setAttribute("pageNo",pageNo);
			}
			request.setAttribute("wxChapterSub", chapSubDto);
			request.setAttribute("fromurl", fm);
		}
		 //把小说来源公共号信息放到cookie中
		if((from_name==null||from_name.isEmpty())&&fm!=null&&!fm.isEmpty()){
			CookieUtils.addcookie("from_name", 1*365*24*60*60, response,fm);
		}
		return "wxChapterSub/index";
	}
	

	@RequestMapping("/show")
	public String getContentUnlogin(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attr,
			@CookieValue(value = "defaultbookrack", required = true, defaultValue = "") String rackCookie,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token
			,@CookieValue(value ="from_name",required = true, defaultValue = "")String from_name)
			throws UnsupportedEncodingException {
		String userIdStr = "";
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String bookIdStr = request.getParameter("bookId");
		String chapterIdStr = request.getParameter("chapterId");
		String fm = request.getParameter("fm");
		String bookName = "";
		String chapterTitle="";
		int userId = 0;
		int bookId = 0;
		int chapterId = 0;
		String tag="";
		WxChapter chapterModel=null;
		if (chapterIdStr != null && !chapterIdStr.isEmpty()) {
		    chapterId = Integer.parseInt(chapterIdStr);
			if (bookIdStr != null && !bookIdStr.isEmpty()) {
				bookId = Integer.parseInt(bookIdStr);
			}
			if (userIdStr != null && !userIdStr.isEmpty()) {
				userId = Integer.parseInt(userIdStr);
			}
			
			// 获取图书信息
			WxBook book = bookService.selectByPrimaryKey(bookId);
			if (book != null) {
				bookName = book.getName();
				tag=book.getTag();
			}
			// 章节数据
		    chapterModel = chapterService.selectByPrimaryKey(chapterId, EnumType.ChapterStatus_OnLine);
			if (chapterModel == null)
				return "wxChapterSub/index";
			else
				chapterTitle=chapterModel.getTitle();
			// 付费章节操作
			if (chapterModel.getChaptertype() == 1) {
				ResultMsg resultMsg = userConsService.consDeal(userId, bookId, bookName, chapterModel);
				if (!resultMsg.getStatus()) {
					switch (resultMsg.getNum()) {
					case EnumType.ResultNum_Login:
						return "redirect:/wxUser/login";
					case EnumType.ResultNum_Pay:
						attr.addAttribute("chapterId", chapterId);
						attr.addAttribute("bookId", bookId);
						attr.addAttribute("fm", fm);
						return "redirect:/wxPay/pay";
					case EnumType.ResultNum_Cons:
						attr.addAttribute("chapterId", chapterId);
						attr.addAttribute("bookId", bookId);
						attr.addAttribute("fm", fm);
						return "redirect:/wxConsume/subscribe";
					}
				}
				logger.error(resultMsg.getMsg());
			}
			// 获取章节信息
			WxChapterSubDto chapSubDto = getCahperDto(bookId, bookName, chapterId, chapterModel,tag);
			request.setAttribute("wxChapterSub", chapSubDto);
			if(chapterModel!=null)
			{
				int pageNo=0;
				if(chapterModel.getSortorder()%pageSize==0){
					if(chapterModel.getSortorder()>0)
						pageNo=chapterModel.getSortorder()-pageSize;
				}
				else
					pageNo=chapterModel.getSortorder()-(chapterModel.getSortorder()%pageSize);
				request.setAttribute("pageNo",pageNo);
			}
			request.setAttribute("fromurl", fm);
		}
		//保存书架
        if(bookId>0&&userId>0){
        	saveBookrack(bookId,userId,bookName,chapterId,chapterTitle);
        }
        //把小说来源公共号信息放到cookie中
		if((from_name==null||from_name.isEmpty())&&fm!=null&&!fm.isEmpty()){
			CookieUtils.addcookie("from_name", 1*365*24*60*60, response,fm);
		}//逍遥医圣在都市||监狱风云||苗疆蛊事||1980之他来自未来
//		if((bookId==26795&&chapterId>=67995)||(bookId==26794&&chapterId>=67678)||(bookId==26779&&chapterId>=49561)||(bookId==26777&&chapterId>=17553)){
//			String url="/wxChapterSub/index?bookId="+bookId+"&chapterId="+chapterId+"&fm="+fm;
//			CookieUtils.addcookie("readMark_Show", 1*365*24*60*60, response,url);
//			return "/wxChapterSub/focusQR";
//		}
		if(chapterModel!=null&&chapterModel.getShowtype()==1){
			String url="/wxChapterSub/index?bookId="+bookId+"&chapterId="+chapterId+"&fm="+fm;
			CookieUtils.addcookie("readMark_Show", 1*365*24*60*60, response,url);
			return "/wxChapterSub/focusQR";
		}
		return "wxChapterSub/show";
	}
	
	@RequestMapping("/readContinue")
	public String readContinue(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attr,
			@CookieValue(value = "defaultbookrack", required = true, defaultValue = "") String rackCookie,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token
			,@CookieValue(value ="readMark_Show",required = true, defaultValue = "")String readMark_Show){
		if(!readMark_Show.isEmpty()&&readMark_Show.contains("/wxChapterSub/index?bookId=")){
			return "redirect:"+readMark_Show;
		}
		return "redirect:/wxBookrack/list";
	}
	

	/**
	 * 获取章节内容信息
	 * @param bookName
	 * @param chapterId
	 * @param chapterModel
	 * @return
	 */
	private WxChapterSubDto getCahperDto(int bookId,String bookName, int chapterId,WxChapter chapterModel,String tag) {
		int preId = 0;
		int nextId = 0;
		int preType = 0;
		int nextType = 0;
        int sortOrder=0;
        if(chapterModel!=null)
        	sortOrder=chapterModel.getSortorder();
		// 章节内容数据
		WxChapterSub chapSub = chapterSubSer.selectByChapterId(chapterId);
		// 上一章数据
		WxChapter preChap = chapterService.getPreChapter(bookId,sortOrder, EnumType.ChapterStatus_OnLine);
		// 下一章数据
		WxChapter nextChap = chapterService.getNextChapter(bookId,sortOrder, EnumType.ChapterStatus_OnLine);
		if (preChap != null) {
			preId = preChap.getId();
			preType=preChap.getChaptertype();
		}
		if (nextChap != null) {
			nextId = nextChap.getId();
			nextType=nextChap.getChaptertype();
		}
		WxChapterSubDto chapSubDto = wxChapterSubDtoFill(chapSub, chapterModel, preId, nextId,preType,nextType,tag);
		chapSubDto.setBookName(bookName);
		return chapSubDto;
	}


	

	
	private WxChapterSubDto wxChapterSubDtoFill(WxChapterSub chapSub,WxChapter chapterModel,int preId,int nextId,int preType,int nextType,String tag){
		WxChapterSubDto chapSubDto=new WxChapterSubDto();
		if(chapterModel!=null){
			
		    chapSubDto.setTitle(chapterModel.getTitle());
			chapSubDto.setBookId(chapterModel.getBookid());
		}
		if(chapSub!=null){
			chapSubDto.setId(chapSub.getId());
			String content= chapSub.getContent();
			if(tag!=null&&tag.contains("军事")){
				content=addP(content);
			}
			chapSubDto.setContent(content);
		}
		chapSubDto.setPreId(preId);
		chapSubDto.setNextId(nextId);
		chapSubDto.setPreType(preType);
		chapSubDto.setNextType(nextType);
		return chapSubDto;
	}

	/**
	 * 格式化内容
	 */
	private static String addP(String str)
    {
        if (str==null||str.isEmpty())
        {
            return "";
        }
        else
        {
            str = str.replace("\r\n　　", "\r\n");
            str = str.replace("\r", "");
            return "<p style='text-indent: 2em;'>" + str.replace("\n", "</p>\n<p style='text-indent: 2em;'>") + "</p>";

        }
    }
	
	
	/**
	 * 用户阅读小说时直接加到书架中
	 * @param bookId
	 * @param userId
	 */
	private void saveBookrack(int bookId,int userId,String bookName,int chapterId,String chapterTitle){
		bookrackService.saveBookrack(userId, bookId, bookName, chapterId, chapterTitle);
	}

}
