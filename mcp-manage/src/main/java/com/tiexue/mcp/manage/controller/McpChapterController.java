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
import com.tiexue.mcp.core.dto.McpChapterDto;
import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpChapter;
import com.tiexue.mcp.core.service.IMcpChapterService;

@Controller
@RequestMapping("mcpchapter")
public class McpChapterController {
	// 日志
	private static Logger logger = Logger.getLogger(McpChapterController.class);

	@Resource
	IMcpChapterService mcpChapterSer;
	
	/**
	 * 获取章节信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list")
	public String getChapterList(HttpServletRequest request, HttpServletResponse response) {
		// 根据传入的小说id,查找章节列表信息,显示到页面中
		// todo:权限判断
		String bookIdStr = "1";// request.getParameter("cpId");
		if (bookIdStr != "") {
			int bookId = 1;
			try {
				bookId = Integer.parseInt(bookIdStr);
			} catch (NumberFormatException e) {
				bookId = 1;
			}

			List<McpChapter> mcpChapters = mcpChapterSer.selectList(bookId);

			List<McpChapterDto> mcpChapterDtos = toMcpChapterListDto(mcpChapters);
			request.setAttribute("mcpChapters", mcpChapterDtos);
		}

		return "mcpChapter/list";
	}

	@RequestMapping("detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		//todo:权限判断,是否有查看此书的权限,管理员及拥有此图书的合作方
		String chapterIdStr = "1";// request.getParameter("bookid");
		if(chapterIdStr !=""){
			int chapterId = 1;
			try{
				chapterId = Integer.parseInt(chapterIdStr);
			} catch(NumberFormatException e){
				chapterId = 1;
			}
			
			McpChapter chapter = mcpChapterSer.selectByPrimaryKey(chapterId);
			McpChapterDto mcpChapterDto = toMcpChapterDto(chapter);
			request.setAttribute("mcpChapter", mcpChapterDto);
		}
		return "mcpChapter/detail";	
	}
	
	
	
	
	/*
	 * to mcp dto
	 * */
	private List<McpChapterDto> toMcpChapterListDto(List<McpChapter> mcpChapters) {
		ArrayList<McpChapterDto> mcpChapterDtoList = new ArrayList<McpChapterDto>();
		if (mcpChapters != null) {
			for(McpChapter chapter:mcpChapters){
				McpChapterDto mcpChapterDto= toMcpChapterDto(chapter);
				mcpChapterDtoList.add(mcpChapterDto);
			}
		}
		return mcpChapterDtoList;
	}
	
	private McpChapterDto toMcpChapterDto(McpChapter chapter) {

		McpChapterDto mcpChapterDto=new McpChapterDto();
		if (chapter != null) {
				mcpChapterDto.setId(chapter.getId());
				mcpChapterDto.setAuditinfo(chapter.getAuditinfo());
				mcpChapterDto.setAuditstatus(chapter.getAuditstatus());
				mcpChapterDto.setBookid(chapter.getBookid());
				mcpChapterDto.setBookname(chapter.getBookname());
				mcpChapterDto.setCpbookid(chapter.getCpbookid());
				mcpChapterDto.setCpchapterid(chapter.getCpchapterid());
				mcpChapterDto.setCpid(chapter.getCpid());
				mcpChapterDto.setCreatetime(chapter.getCreatetime());
				mcpChapterDto.setIsvip(chapter.getIsvip());
				mcpChapterDto.setMd5(chapter.getMd5());
				mcpChapterDto.setName(chapter.getName());
				mcpChapterDto.setOrder(chapter.getOrder());
				mcpChapterDto.setPrice(chapter.getPrice());
				mcpChapterDto.setUpdatetime(chapter.getUpdatetime());
				mcpChapterDto.setWords(chapter.getWords());		
				mcpChapterDto.setContent(chapter.getContent());		
			}
		return mcpChapterDto;
	}
	
	
	
}
