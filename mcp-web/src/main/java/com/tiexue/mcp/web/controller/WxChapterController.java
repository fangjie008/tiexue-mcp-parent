package com.tiexue.mcp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.mcp.core.dto.Pager;
import com.tiexue.mcp.core.dto.WxChapterDto;
import com.tiexue.mcp.core.dto.WxChapterNaviDto;
import com.tiexue.mcp.core.entity.EnumType;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.entity.WxChapter;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxChapterService;

@Controller
@RequestMapping("wxChapter")
public class WxChapterController {
	private static Logger logger=Logger.getLogger(WxChapterController.class);
	int pageSize=20;
	//获取章节信息的服务
	@Resource
	IWxChapterService wxChapterService;
	//获取小说信息的服务
	@Resource
	IWxBookService wxBook;
	//获取章节列表信息
	@RequestMapping("/index")
	public String getList(HttpServletRequest request) {
		
		String bookIdStr = request.getParameter("bookId");
		String pageNoStr = request.getParameter("pageNo");
		String jumpPage = request.getParameter("jumpPage");
		String fm = request.getParameter("fm");
		if (bookIdStr != null && !bookIdStr.isEmpty()) {
			int bookId = 0;
			bookId = Integer.parseInt(bookIdStr);
			int pageNo = 0;
			if(pageNoStr != null && !pageNoStr.isEmpty()){
				pageNo = Integer.parseInt(pageNoStr);
			}
			List<WxChapter> wxChapters = wxChapterService.selectByBookId(bookId, EnumType.ChapterStatus_OnLine, pageNo,
					pageSize);
			WxBook wxBookModel=wxBook.selectByPrimaryKey(bookId);
			int totalRecord=wxChapterService.getCountByBookId(bookId, EnumType.ChapterStatus_OnLine);
			//获取分页数据
			Pager pagerModel=new Pager().getPager(pageNo,pageSize,totalRecord);
			//获取导航
			List<WxChapterNaviDto> chapNaviDtos=wxChapterNaviDtoFill(bookId,totalRecord,pageSize,pageNo,fm);
			request.setAttribute("wxBook", wxBookModel);
			request.setAttribute("bookId", bookId);
		    request.setAttribute("chapNaviDtos", chapNaviDtos);
		    request.setAttribute("wxChapters",wxChapterDtoFill(wxChapters,pageNo));
			request.setAttribute("pager", pagerModel);
			request.setAttribute("jumpPage", jumpPage);
			int count=totalRecord/pageSize;
			if(totalRecord%pageSize>0)
				count++;
			request.setAttribute("totalRecord", count);
			request.setAttribute("fromurl", fm);
		}

		return "/wxChapter/index";
	}
	
	private List<WxChapterDto> wxChapterDtoFill(List<WxChapter> wxChapters,int pageNo){
		ArrayList<WxChapterDto> resultData=new ArrayList<WxChapterDto>();
		int i=0;
		if(wxChapters!=null){
			for(WxChapter chap: wxChapters){
				i++;
				WxChapterDto chaDto=new WxChapterDto();
				chaDto.setId(chap.getId());
				chaDto.setBookid(chap.getBookid());
				chaDto.setTitle(chap.getTitle());
				chaDto.setContentlen(chap.getContentlen());
				chaDto.setPirce(chap.getPirce());
				chaDto.setSortorder(chap.getSortorder());
				chaDto.setChaptertype(chap.getChaptertype());
				chaDto.setChaptertypeName(EnumType.ChapterType.get(chap.getChaptertype()));
				resultData.add(chaDto);
			}
		}
		return resultData;
	}
	//绑定小说章节导航的数据
	private List<WxChapterNaviDto> wxChapterNaviDtoFill(int bookId,int totalRecord,int pageSize,int pageNo,String fm)
	{
		ArrayList<WxChapterNaviDto> resultData=new ArrayList<WxChapterNaviDto>();
		if(totalRecord<=0)
			return resultData;
		if(pageSize>=totalRecord){
			WxChapterNaviDto chapNavi=new WxChapterNaviDto();
			chapNavi.setName("1-"+pageSize+"章");
			chapNavi.setUrl("/wxChapter/index?bookId="+bookId+"&pageNo="+pageNo+"&fm="+fm);
			chapNavi.setOrder(0);
			chapNavi.setIsActive(true);
			resultData.add(chapNavi);
		}
		else{
			int count=totalRecord/pageSize;
			if(totalRecord%pageSize>0)
				count++;
			for(int i=0;i<count;i++){
				int prePage=(pageSize*i+1);
				int lastPage=(pageSize*(i+1))>totalRecord?totalRecord:(pageSize*(i+1));
				WxChapterNaviDto chapNavi=new WxChapterNaviDto();
				chapNavi.setName(prePage+"-"+lastPage+"章");
				chapNavi.setUrl("/wxChapter/index?bookId="+bookId+"&pageNo="+(pageSize*i)+"&fm="+fm);
				chapNavi.setOrder(i);
				chapNavi.setIsActive((pageSize*i)==pageNo?true:false);
				resultData.add(chapNavi);
			}
			
		}
			
		return resultData;
	}
	

}
