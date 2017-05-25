package com.tiexue.mcp.task.support;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tiexue.mcp.core.entity.McpBaseInfo;
import com.tiexue.mcp.core.service.IMcpBaseInfoService;
import com.tiexue.mcp.task.plugin.PluginGeneral;

/**
 * 同步小说入口
 * @author zhangxiaowei
 *
 */
@Component("syncNovelManage")
public class SyncNovelManage {

	private static Logger logger=Logger.getLogger(SyncNovelManage.class);
	
	@Resource
	IMcpBaseInfoService iMcpBaseInfoService;
	
	private static List<McpBaseInfo> mcpBaseInfos;
	/**
	 * 启动同步小说
	 */
	public void init(){
		mcpBaseInfos=  iMcpBaseInfoService.getAllList(" 1=1 ");
		start();
	}
	
	/**
	 * 开始同步小说。每个站点用一个线程
	 */
	@SuppressWarnings("unused")
	private static void start(){
		if(mcpBaseInfos!=null){
			for(McpBaseInfo mcpBaseInfo:mcpBaseInfos){
				if(mcpBaseInfo.getInterfaceurl1()!=null&&!mcpBaseInfo.getInterfaceurl1().isEmpty()){
					
					if(mcpBaseInfo.getInterfaceurl1().contains("junshishu.com")){
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
	private static void executeTieXueDuShu(McpBaseInfo mcpBaseInfo) {

		logger.info("EPageTieXueDuShu start"+new Date());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PluginGeneral pgGeneral = new PluginGeneral("com.tiexue.mcp.task.plugin.EPageTieXueDuShu",
				mcpBaseInfo);
		pgGeneral.execute(1);

	}
	
	@SuppressWarnings("unused")
	private static void executeDefault(McpBaseInfo mcpBaseInfo) {
//		Thread thread=new Thread(mcpBaseInfo.getName());
//		thread.start();
	}


}
