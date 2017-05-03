package com.tiexue.mcp.manage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.mcp.core.dto.McpBookDto;
import com.tiexue.mcp.core.dto.PageUserDto;
import com.tiexue.mcp.core.dto.WxBookDto;
import com.tiexue.mcp.core.entity.EnumType;
import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.service.IMcpBookService;
import com.tiexue.mcp.core.service.IWxBookService;

@Controller
@RequestMapping("mcpbook")
public class McpBookController {
	//日志
	private static Logger logger=Logger.getLogger(McpBookController.class);
	
	@Resource
	IMcpBookService mcpBookSer;
	
	/**
	 * 获取第三方的图书信息
	 * 逻辑:需要判断用户是否登录,登录的话需要拿到用户的Id信息,然后根据id查询对应的图书列表数据
	 * 前期先不分页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list")
	public String getMcpBookList(HttpServletRequest request,HttpServletResponse response){
		//todo:权限判断
		//每个合作方可以查看自己的作品列表
		String cpIdStr = "1";//request.getParameter("cpId");
		if(cpIdStr!=""){
			int cpId = 1;
			try {
				cpId = Integer.parseInt(cpIdStr);
			} catch (NumberFormatException e) {
				cpId =1;
			}
			
			List<McpBook> mcpBooks= mcpBookSer.getList(cpId);
			
			List<McpBookDto> mcpBooksDtos = toMcpBookListDto(mcpBooks);
			request.setAttribute("mcpBooks", mcpBooksDtos);
		}
		
		return "mcpBook/list";
	}
	
	/**
	 * 图书详情列表, 展示图书详情信息
	 * */
	@RequestMapping("detail")
	public String mcpBookDetail(HttpServletRequest request, HttpServletResponse response){
		//todo:权限判断,是否有查看此书的权限,管理员及拥有此图书的合作方
		String bookIdStr = "1";// request.getParameter("bookid");
		if(bookIdStr !=""){
			int bookId = 1;
			try{
				bookId = Integer.parseInt(bookIdStr);
			} catch(NumberFormatException e){
				bookId = 1;
			}
			
			McpBook book = mcpBookSer.selectByPrimaryKey(bookId);
			McpBookDto mcpBooksDto = toMcpBookDto(book);
			request.setAttribute("mcpBook", mcpBooksDto);
		}
		
		return "mcpBook/detail";
	}
	
	
	/*
	 * to mcp dto
	 * */
	private List<McpBookDto> toMcpBookListDto(List<McpBook> mcpBooks) {
		ArrayList<McpBookDto> mcpBookDtoList = new ArrayList<McpBookDto>();
		if (mcpBooks != null) {
			for(McpBook book:mcpBooks){
				McpBookDto mcpBookDto=new McpBookDto();
				mcpBookDto.setId(book.getId());
				mcpBookDto.setName(book.getName());
				mcpBookDto.setAuthor(book.getAuthor());
				mcpBookDto.setBookstatus(book.getBookstatus());
				mcpBookDto.setChargemode(book.getChargemode());
				mcpBookDto.setChaptercount(book.getChaptercount());
				mcpBookDto.setAuditstatus(book.getAuditstatus());
				mcpBookDto.setAuditinfo(book.getAuditinfo());
				mcpBookDto.setPutawaystatus(book.getPutawaystatus());
				mcpBookDtoList.add(mcpBookDto);
			}
		}
		return mcpBookDtoList;
	}
	
	
	/*
	 * book to dto
	 * */
	private McpBookDto toMcpBookDto(McpBook book){
		McpBookDto mcpBookDto = new  McpBookDto();
		if (book != null) {
			mcpBookDto.setId(book.getId());
			mcpBookDto.setActors(book.getActors());
			mcpBookDto.setChanneltype(book.getChanneltype());
			mcpBookDto.setCoverimg(book.getCoverimg());
			mcpBookDto.setCpbid(book.getCpbid());
			mcpBookDto.setCpid(book.getCpid());
			mcpBookDto.setCpname(book.getCpname());
			mcpBookDto.setCreatetime(book.getCreatetime());
			mcpBookDto.setFeechapter(book.getFeechapter());
			mcpBookDto.setIntro(book.getIntro());
			mcpBookDto.setKeywords(book.getKeywords());
			mcpBookDto.setPrice(book.getPrice());
			mcpBookDto.setPublishtime(book.getPublishtime());
			mcpBookDto.setPutawaytime(book.getPutawaytime());
			mcpBookDto.setSubhead(book.getSubhead());
			mcpBookDto.setTags(book.getTags());
			mcpBookDto.setUpdatetime(book.getUpdatetime());
			mcpBookDto.setWords(book.getWords());
			mcpBookDto.setName(book.getName());
			mcpBookDto.setAuthor(book.getAuthor());
			mcpBookDto.setBookstatus(book.getBookstatus());
			mcpBookDto.setChargemode(book.getChargemode());
			mcpBookDto.setChaptercount(book.getChaptercount());
			mcpBookDto.setAuditstatus(book.getAuditstatus());
			mcpBookDto.setAuditinfo(book.getAuditinfo());
			mcpBookDto.setPutawaystatus(book.getPutawaystatus());
		}
		return mcpBookDto;
	}
	
	
	
	
}
