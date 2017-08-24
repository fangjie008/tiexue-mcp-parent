package com.tiexue.mcp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.tiexue.mcp.base.util.CookieUtils;
import com.tiexue.mcp.core.dto.PageUserDto;
import com.tiexue.mcp.core.dto.WxBookDto;
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
@RequestMapping("/wxbook")
public class WxBookController {
	// 日志
	private Logger logger = Logger.getLogger(WxBookController.class);

	@Resource
	IWxBookService wxBookService;
	
	@Resource
	IWxBookrackService bookrackService;
	//获取章节信息的服务
	@Resource
	IWxChapterService wxChapterService;
	
	@Resource
	IWxUserService userSer;
	
	/**
	 * 首页入口
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response
			,@CookieValue(value ="defaultbookrack",required = true, defaultValue = "")String rackCookie
			,@CookieValue(value ="wx_gzh_token",required = true, defaultValue = "")String wx_gzh_token
			,@CookieValue(value ="from_name",required = true, defaultValue = "")String from_name) {
		String userIdStr="";
		try {
			String fm = request.getParameter("fm");
			logger.error("获取 wx_gzh_token："+wx_gzh_token);
			if(wx_gzh_token!=""){
				PageUserDto pageUser= userSer.getPageUserDto(wx_gzh_token);
				if(pageUser!=null){
				 userIdStr=pageUser.getId();
				 if(pageUser.getName().length()>3){
					 pageUser.setName(pageUser.getName().substring(0,3)+"...");
				 }
				 request.setAttribute("pageUser", pageUser);
				}
				logger.error("获取 pageUser.getId："+userIdStr);
			}
			String status = EnumType.BookStatus_Finish + "," + EnumType.BookStatus_Update;
			String strWhere=" Status in ("+status+")"+" and tag='军事'";
			List<WxBook> wxBooks = this.wxBookService.getList(strWhere, "ViewCount",80);
			List<WxBookDto> wxBookDtos = toWxBookListDto(wxBooks);
			request.setAttribute("wxBooks", wxBookDtos);
			WxBookrack rack=new WxBookrack();
			if (userIdStr != null && !userIdStr.isEmpty()) {
				int userId = Integer.parseInt(userIdStr);
			    rack = bookrackService.getModelByUserId(userId);
			}else{
				rack=getBookrackByCookie(rackCookie);
			}
			request.setAttribute("bookrack", rack);
			request.setAttribute("fromurl", fm);
			//把小说来源公共号信息放到cookie中
			if((from_name==null||from_name.isEmpty())&&fm!=null&&!fm.isEmpty()){
				CookieUtils.addcookie("from_name", 1*365*24*60*60, response,fm);
			}
		} catch (Exception e) {
			logger.error("首页获取数据异常"+e.getMessage());
		}
		return "/booklist";
	}

	
	@RequestMapping("/detail")
	public String detailInfo(HttpServletRequest request,
			@CookieValue(value = "wx_gzh_token", required = true, defaultValue = "") String wx_gzh_token,
			@CookieValue(value = "wx_gzh_sign", required = true, defaultValue = "") String wx_gzh_sign) {
		String userIdStr = "";
		String fm = request.getParameter("fm");
		if (wx_gzh_token != "") {
			PageUserDto pageUser = userSer.getPageUserDto(wx_gzh_token);
			if (pageUser != null) {
				userIdStr = pageUser.getId();
			}
		}
		String bookIdStr = request.getParameter("id");
		int bookId=0;
		int userId=0;
		String bookName="";
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
		    bookId = Integer.parseInt(bookIdStr);
			WxBook wxBook = this.wxBookService.selectByPrimaryKey(bookId);
			WxBookDto wxBookDto = toWxBookDto(wxBook);
			request.setAttribute("wxBook", wxBookDto);
			if (userIdStr != null && !userIdStr.isEmpty()) {
			    userId = Integer.parseInt(userIdStr);
				WxBookrack rack = bookrackService.getModelByBookId(userId, bookId);
				request.setAttribute("bookrack", rack);
			} else {

			}
			if(wxBook!=null){
				bookName=wxBook.getName();
			}

		}
		request.setAttribute("fromurl", fm);
		//保存书架
        if(bookId>0&&userId>0){
        	saveBookrack(bookId,userId,bookName,wx_gzh_sign);
        }
		return "bookdetail";
	}
	
	/**
	 * 搜索入口
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchlist")
	public String searchlist(HttpServletRequest request,HttpServletResponse response
			,@CookieValue(value ="defaultbookrack",required = true, defaultValue = "")String rackCookie
			,@CookieValue(value ="wx_gzh_token",required = true, defaultValue = "")String wx_gzh_token
			,@CookieValue(value ="from_name",required = true, defaultValue = "")String from_name) {
		String userIdStr="";
		try {
			String fm = request.getParameter("fm");
			String iptsearch=request.getParameter("iptsearch");
			String status = EnumType.BookStatus_Finish + "," + EnumType.BookStatus_Update;
			String strWhere=" Status in ("+status+")"+" and tag='军事'";
			if(iptsearch!=null&&!iptsearch.isEmpty()){
				strWhere+=" and name like  '%"+iptsearch+"%'";
				List<WxBook> wxSearchBooks = this.wxBookService.getSearchList(strWhere);
				List<WxBookDto> wxBookDtos = toWxBookListDto(wxSearchBooks);
				request.setAttribute("wxSearchBooks", wxBookDtos);
			}
			else{
				List<WxBook> wxBooks = this.wxBookService.getList(strWhere, "ViewCount",3);
				List<WxBookDto> wxBookDtos = toWxBookListDto(wxBooks);
				request.setAttribute("wxBooks", wxBookDtos);
			}
			request.setAttribute("fromurl", fm);
			request.setAttribute("iptsearch", iptsearch);
		} catch (Exception e) {
			logger.error("首页搜索的小说数据异常"+e.getMessage());
		}
		return "/searchlist";
	}
	
	/**
	 * 根据cookie获取收藏的书架
	 * @param rackCookie
	 * @return
	 */
	private WxBookrack getBookrackByCookie(String rackCookie){
		WxBookrack rack = new WxBookrack();
		 List<bookrackCookieDto> cookies=JSON.parseArray(rackCookie, bookrackCookieDto.class);
		 if(cookies!=null&&cookies.size()>0){
				 WxChapter curChap = null;
				 WxBook book= wxBookService.selectByPrimaryKey(cookies.get(cookies.size()-1).getBookid());
				 if(cookies.get(cookies.size()-1).getChapterid()>0){
					 curChap=wxChapterService.selectByPrimaryKey(cookies.get(cookies.size()-1).getChapterid(), EnumType.ChapterStatus_OnLine);
				 }
				 rack = bookrackDtoFill(book,curChap);
		 }
		 return rack;
	}
	
	
	private WxBookrack bookrackDtoFill(WxBook book,WxChapter curChap){
		WxBookrack rack = new WxBookrack();
		if (book != null) {
			rack.setBookid(book.getId());
			rack.setBookname(book.getName());
			rack.setLocation(0);
			rack.setUserid(0);
		}
		if (curChap != null) {
			rack.setChapterid(curChap.getId());
			rack.setChaptertitle(curChap.getTitle());
		}

		return rack;
	}
	
	
	private WxBookDto toWxBookDto(WxBook wxBook) {
		WxBookDto wxBookDto = new WxBookDto();
		if (wxBook != null) {
			wxBookDto.setId(wxBook.getId());
			wxBookDto.setName(wxBook.getName());
			wxBookDto.setCoverImgs(wxBook.getCoverimgs());
			wxBookDto.setTag(wxBook.getTag());
			wxBookDto.setStatus(EnumType.BookStatus.get(wxBook.getStatus()));
			if(wxBook.getContentlen()!=null){
				if(wxBook.getContentlen()>10000){
					String conlen=(wxBook.getContentlen()/10000+wxBook.getContentlen()%10000*0.0001)+"万";
					wxBookDto.setContentLen(conlen);
				}
				else{
					wxBookDto.setContentLen(wxBook.getContentlen()+"");
				}
			}
			
			wxBookDto.setIntr(wxBook.getIntr());
		}
		return wxBookDto;
	}
	
	private List<WxBookDto> toWxBookListDto(List<WxBook> wxBooks) {
		ArrayList<WxBookDto> wxBookDtoList = new ArrayList<WxBookDto>();
		if (wxBooks != null) {
			for(WxBook book:wxBooks){
				WxBookDto wxBookDto=new WxBookDto();
				wxBookDto.setId(book.getId());
				wxBookDto.setName(book.getName());
				wxBookDto.setTag(book.getTag());
				wxBookDto.setCoverImgs(book.getCoverimgs());
				wxBookDto.setStatus(EnumType.BookStatus.get(book.getStatus()));
				if(book.getContentlen()!=null){
					if(book.getContentlen()>10000){
						String conlen=(book.getContentlen()/10000+book.getContentlen()%10000*0.0001)+"万";
						wxBookDto.setContentLen(conlen);
					}
					else{
						wxBookDto.setContentLen(book.getContentlen()+"");
					}
				}
				wxBookDto.setIntr(book.getIntr());
				wxBookDtoList.add(wxBookDto);
			}
			
			
		}
		return wxBookDtoList;
	}

	/**
	 * 用户阅读小说时直接加到书架中
	 * @param bookId
	 * @param userId
	 */
	private void saveBookrack(int bookId,int userId,String bookName,String wx_gzh_sign){
		bookrackService.saveBookrack(userId, bookId, bookName, 0, "",wx_gzh_sign);
	}
	

}
