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
import com.tiexue.mcp.manage.dto.Paging;

@Controller
@RequestMapping("mcpchapter")
public class McpChapterController {
	// 日志
	private static Logger logger = Logger.getLogger(McpChapterController.class);

	@Resource
	IMcpChapterService mcpChapterSer;
	private static final int psize=10;
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
		String bookIdStr = request.getParameter("bookId");
		if (bookIdStr != "") {
			int bookId = 0;
			try {
				bookId = Integer.parseInt(bookIdStr);
			} catch (NumberFormatException e) {
				bookId = 0;
			}
			//分页信息
			int pindex=1;
			String pindexStr=request.getParameter("pindex");
			if(pindexStr!=null&&!pindexStr.isEmpty()){
				pindex=Integer.parseInt(pindexStr);
			}
			int pStart=(pindex-1)*psize;
			int pcount=mcpChapterSer.getCount(" BookId="+bookId);
			//如果最后一页只有一条数据，则在删除时取上一页数据
 			if(pStart>0&&pStart>=pcount){
				pStart=pStart-psize;
			}
			Paging paging=new Paging();
			paging.setPcount(pcount);
			paging.setPsize(psize);
			paging.calcPtotalpages();
			if(pindex>paging.getPtotalpages()){
				pindex=paging.getPtotalpages();
			}
			paging.setPindex(pindex);
			List<McpChapter> mcpChapters = mcpChapterSer.selectList(bookId,pStart,psize);
			List<McpChapterDto> mcpChapterDtos = toMcpChapterListDto(mcpChapters);
			request.setAttribute("mcpChapters", mcpChapterDtos);
			request.setAttribute("paging",paging);
			request.setAttribute("bookId",bookId);
		}

		return "mcpChapter/list";
	}

	@RequestMapping("detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		//todo:权限判断,是否有查看此书的权限,管理员及拥有此图书的合作方
		String chapterIdStr =request.getParameter("chapterId");
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
