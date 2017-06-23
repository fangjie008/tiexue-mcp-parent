package com.tiexue.mcp.task.online;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.tiexue.mcp.core.entity.McpBook;
import com.tiexue.mcp.core.entity.McpChapter;
import com.tiexue.mcp.core.entity.McpCommitRecord;
import com.tiexue.mcp.core.entity.McpConstants;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.entity.WxChapter;
import com.tiexue.mcp.core.service.IMcpBaseInfoService;
import com.tiexue.mcp.core.service.IMcpBookService;
import com.tiexue.mcp.core.service.IMcpChapterService;
import com.tiexue.mcp.core.service.IMcpCommitRecordService;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxChapterService;
import com.tiexue.mcp.core.service.IWxChapterSubService;
import com.tiexue.mcp.task.support.SyncNovelManage;

import weixin.popular.bean.qrcode.Wxaqrcode;
@Service
public class OnlineHelper {
	private static Logger logger=Logger.getLogger(OnlineHelper.class);
	@Resource
	IMcpCommitRecordService iMcpCommitRecordService;
	@Resource
	IMcpBookService iMcpBookService;
	@Resource
    IMcpChapterService iMcpChapterService;
	@Resource
	IWxBookService iWxBookService;
	@Resource
	IWxChapterService iWxChapterService;
	@Resource
	IWxChapterSubService iWxChapterSubService;
	private  Date lastUpdateTime;
	/**
	 * 程序入口(同步包括mcpbook和mcpchapter同步到wxbook和wxchapter)
	 */
	public void init(){
		try {
			lastUpdateTime=new Date();
			logger.info(" OnlineHelper start: "+new Date()+" . ");
			Thread.sleep(20000);
			McpCommitRecord mcpCommitRecord= iMcpCommitRecordService.getNewest();
			if(mcpCommitRecord!=null&&mcpCommitRecord.getCommittime()!=null){
				lastUpdateTime=mcpCommitRecord.getCommittime();
			}
			insertExecute();
			updateExecute();
			insertCommitRecord();
			logger.info(" OnlineHelper end: "+new Date()+" . ");
		} catch (Exception e) {
			logger.error("数据同步到线上wxbook,wxchapter.出错。错误信息："+e.getMessage());
		}
		
	}
	
	/**
	 * 新增小说
	 * @throws InterruptedException
	 */
	private void insertExecute() throws InterruptedException
	{
		logger.info("开始新增操作!");
		//获取未上架的小说
		List<McpBook> mcpBooks= iMcpBookService.getUnCommitBook();
		if(mcpBooks!=null&&!mcpBooks.isEmpty()&&mcpBooks.size()>0){
			for (McpBook mcpBook : mcpBooks) {
				Thread.sleep(100);
				WxBook tempWxBook=new WxBook();
				//新增到wxBook
				int tempInsertWxBook= iWxBookService.insertToWxBook(tempWxBook,mcpBook.getId(),mcpBook.getUniqueflag());
				if(tempInsertWxBook>0&&tempWxBook!=null)
				{
					int wxBookId=tempWxBook.getId();
					logger.info("新增小说Id:"+wxBookId);
					//获取未上架的章节
					List<McpChapter> mcpChapters= iMcpChapterService.getUnCommitChapter(mcpBook.getId());
					if(mcpChapters!=null&&!mcpChapters.isEmpty()&&mcpChapters.size()>0){
						for (McpChapter mcpChapter : mcpChapters) {
							WxChapter wxChapter=new WxChapter();
							int tempInsertWxChapter= iWxChapterService.insertToWxChapter(wxChapter,mcpChapter.getId()
									, wxBookId, mcpChapter.getUniqueflag());
							if(tempInsertWxChapter==0){
								wxChapter=null;
								wxChapter=iWxChapterService.selectByUniqueFlag(mcpChapter.getUniqueflag());
							}
							if(wxChapter!=null){
								int wxChapterId=wxChapter.getId();
								if(tempInsertWxChapter>0)
									logger.info("新增章节Id:"+wxChapterId);
								int tempId=iWxChapterSubService.insertToWxChapterSub(mcpChapter.getId(),wxChapterId);
								if(tempId>0){
									logger.info("新增章节内容Id:"+wxChapterId);
								}
							}
						}
					}
				}
				iMcpBookService.updatePutAwayStatus(mcpBook.getId(),mcpBook.getUniqueflag());
				
			}
		}
	}
	
	/**
	 * 更新小说
	 * 
	 * @throws InterruptedException
	 */
	private void updateExecute() throws InterruptedException {
		logger.info("开始更新操作!");
		// 获取未更新的小说
		List<McpBook> mcpBooks = iMcpBookService.getNeedUpdateMcpBook(lastUpdateTime);
		if (mcpBooks != null && !mcpBooks.isEmpty() && mcpBooks.size() > 0) {
			for (McpBook mcpBook : mcpBooks) {
				Thread.sleep(100);
				WxBook tempWxBook = new WxBook();
				// 更新wxBook
				int tempInsertWxBook = iWxBookService.updateToWxBook(mcpBook.getId(), mcpBook.getUniqueflag());
				tempWxBook = iWxBookService.selectByUniqueFlag(mcpBook.getUniqueflag());
				
				if (tempWxBook != null) {
					int wxBookId = tempWxBook.getId();
					if(tempInsertWxBook > 0)
						logger.info("更新小说Id:" + wxBookId);
					// 获取未上架的章节
					List<McpChapter> mcpChapters = iMcpChapterService.getUnCommitChapter(mcpBook.getId());
					if (mcpChapters != null && !mcpChapters.isEmpty() && mcpChapters.size() > 0) {
						for (McpChapter mcpChapter : mcpChapters) {
							WxChapter wxChapter = new WxChapter();
							int tempInsertWxChapter = iWxChapterService.insertToWxChapter(wxChapter, mcpChapter.getId(),
									wxBookId, mcpChapter.getUniqueflag());
							if (tempInsertWxChapter == 0) {
								wxChapter = null;
								wxChapter = iWxChapterService.selectByUniqueFlag(mcpChapter.getUniqueflag());
							}

							if (wxChapter != null) {
								int wxChapterId = wxChapter.getId();
								if (tempInsertWxChapter > 0)
									logger.info("新增章节Id:" + wxChapterId);
								int tempId = iWxChapterSubService.insertToWxChapterSub(mcpChapter.getId(), wxChapterId);
								if (tempId > 0) {
									logger.info("新增章节内容Id:" + wxChapterId);
								}
							}
						}
					}
					// 获取需要更新的章节
					mcpChapters = iMcpChapterService.getNeedUpdateMcpChapter(mcpBook.getId(), lastUpdateTime);
					if (mcpChapters != null && !mcpChapters.isEmpty() && mcpChapters.size() > 0) {
						for (McpChapter mcpChapter : mcpChapters) {
							WxChapter wxChapter = null;
							wxChapter = iWxChapterService.selectByUniqueFlag(mcpChapter.getUniqueflag());
							if (wxChapter != null) {
								int tempInsertWxChapter = iWxChapterService.updateToWxChapter(mcpChapter.getId(),
										mcpChapter.getUniqueflag());
								if (tempInsertWxChapter > 0)
									logger.info("更新章节Id:" + wxChapter.getId());
								int wxChapterId = wxChapter.getId();
								int tempId = iWxChapterSubService.updateToWxChapterSub(wxChapterId,
										mcpChapter.getUniqueflag());
								if (tempId > 0) {
									logger.info("更新章节内容Id:" + wxChapterId);
								}
							
							}
						}
					}
				}
				iMcpBookService.updatePutAwayStatus(mcpBook.getId(), mcpBook.getUniqueflag());
			}
		}
	}
	
	private void insertCommitRecord(){
		McpCommitRecord mcpCommitRecord=new McpCommitRecord();
		mcpCommitRecord.setCommitstatus(McpConstants.CommitRecordStatus_Success==1?true:false);
		mcpCommitRecord.setCommittime(new Date());
		mcpCommitRecord.setCreatetime(new Date());
		int res= iMcpCommitRecordService.insert(mcpCommitRecord);
		if(res>0){
			logger.info("发布到线上成功,新增发布到线上的记录");
		}
	}
}
