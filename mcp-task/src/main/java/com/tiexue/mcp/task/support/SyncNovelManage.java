package com.tiexue.mcp.task.support;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tiexue.mcp.core.entity.McpBaseInfo;
import com.tiexue.mcp.core.service.IMcpBaseInfoService;
import com.tiexue.mcp.core.service.IMcpBookService;
import com.tiexue.mcp.core.service.IMcpChapterService;
import com.tiexue.mcp.task.online.OnlineHelper;
import com.tiexue.mcp.task.plugin.PluginGeneral;


/**
 * 同步小说入口
 * @author zhangxiaowei
 *
 */
public class SyncNovelManage {

	private static Logger logger=Logger.getLogger(SyncNovelManage.class);
	

	
	@Resource
	IMcpBaseInfoService iMcpBaseInfoService;
	@Resource
	IMcpBookService iMcpBookService;
	@Resource
    IMcpChapterService iMcpChapterService;
	@Resource
	OnlineHelper onlineHelper;
	private static List<McpBaseInfo> mcpBaseInfos;
	/**
	 * 启动同步小说,从第三方同步小说
	 */
	public void init(){
		try {
			logger.info(" SyncNovelManage start: "+new Date()+"sleep 20 second. ");
			//暂停20秒
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mcpBaseInfos=  iMcpBaseInfoService.getAllList(" 1=1 ");
		start();
		logger.info(" SyncNovelManage end: "+new Date()+"");
		//logger.info(" onlineHelper start: "+new Date()+"");
		onlineHelper.init();
		//logger.info(" onlineHelper end: "+new Date()+"");
	}
	
	/**
	 * 开始同步小说。
	 */
	@SuppressWarnings("unused")
	private  void start(){
		if(mcpBaseInfos!=null){
			for(McpBaseInfo mcpBaseInfo:mcpBaseInfos){
				if(mcpBaseInfo.getInterfaceurl1()!=null&&!mcpBaseInfo.getInterfaceurl1().isEmpty()){
					
					if(mcpBaseInfo.getInterfaceurl1().contains("junshishu.com")
							||mcpBaseInfo.getInterfaceurl1().contains("hezuo.book.tiexue.net")){
						executeTieXueDuShu(mcpBaseInfo);
					}
					else {
						executeDefault(mcpBaseInfo);
					}
					
				}
			}
		}
	}

	
	
	
	@SuppressWarnings("unused")
	private  void executeTieXueDuShu(McpBaseInfo mcpBaseInfo) {

		logger.info(" EPageTieXueDuShu start: "+new Date());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PluginGeneral pgGeneral = new PluginGeneral("com.tiexue.mcp.task.plugin.EPageTieXueDuShu",
					mcpBaseInfo);
			pgGeneral.setiMcpBookService(iMcpBookService);
			pgGeneral.setiMcpChapterService(iMcpChapterService);
			pgGeneral.execute(1);
		} catch (Exception e) {
			logger.debug("executeTieXueDuShu error. url: "+mcpBaseInfo.getInterfaceurl1()+e.getMessage());
			e.printStackTrace();
		}
	

	}
	
	@SuppressWarnings("unused")
	private  void executeDefault(McpBaseInfo mcpBaseInfo) {
//		Thread thread=new Thread(mcpBaseInfo.getName());
//		thread.start();
	}


}
