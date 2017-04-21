package com.tiexue.mcp.core.service.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tiexue.mcp.core.dto.WxBookrackDto;
import com.tiexue.mcp.core.entity.WxBookrack;
import com.tiexue.mcp.core.mapper.WxBookrackMapper;
import com.tiexue.mcp.core.service.IWxBookrackService;

@Service("wxBookrack")
public class WxBookrackServiceImpl implements IWxBookrackService {
	private static Logger _logger = Logger.getLogger(WxBookrackServiceImpl.class);
	@Resource
	WxBookrackMapper bookrackMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bookrackMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WxBookrack record) {
		return bookrackMapper.insert(record);
	}

	@Override
	public int insertSelective(WxBookrack record) {
		return bookrackMapper.insertSelective(record);
	}

	@Override
	public WxBookrack selectByPrimaryKey(Integer id) {
		return bookrackMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxBookrack record) {
		return bookrackMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WxBookrack record) {
		return bookrackMapper.updateByPrimaryKey(record);
	}

	@Override
	public WxBookrack getModelByBookId(Integer userId,Integer bookId) {
		return bookrackMapper.getModelByBookId(userId,bookId);
	}
	
	@Override
	public WxBookrack getModelByUserId(Integer userId) {
		return bookrackMapper.getModelByUserId(userId);
	}


	@Override
	public List<WxBookrackDto> getListByUserId(Integer userId, Integer size) {
		return bookrackMapper.getListByUserId(userId, size);
	}
	@Override
	public boolean saveBookrack(int userId,int bookId,String bookName,Integer chapterId,String chapterTitle){
		try {
			if(bookName==null||bookName.isEmpty())
				bookName="";
			if(chapterId==null)
				chapterId=0;
			if(chapterTitle==null||chapterTitle.isEmpty())
				chapterTitle="";
			 WxBookrack rack= getModelByBookId(userId,bookId);
			 if(rack==null){
				 rack=new WxBookrack();
				 rack.setUserid(userId);
				 rack.setBookid(bookId);
				 rack.setBookname(bookName);
				 rack.setChapterid(chapterId);
				 rack.setChaptertitle(chapterTitle);
				 rack.setCreatetime(new Date());
				 rack.setLocation(0);
				 rack.setUserid(userId);
				 return insert(rack)>0?true:false;
			 }else{
				 rack.setCreatetime(new Date());
				 if(bookName!="")
					 rack.setBookname(bookName);
				 if(chapterId!=0)
					 rack.setChapterid(chapterId);
				 if(chapterTitle!="")
					 rack.setChaptertitle(chapterTitle);
				 return updateByPrimaryKey(rack)>0?true:false;
			 }
		} catch (Exception e) {
			_logger.error("保存书架失败！"+e.getMessage());
		}
		return false;
		
		 
	}
	
}
