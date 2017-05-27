package com.tiexue.mcp.core.entity;

import java.util.HashMap;
import java.util.Map;

public class McpConstants {

	/**
     * 男频女频
     */
    public static final int McpChannelType_Body=0;
    public static final int McpChannelType_Girl=1;
    
    public static Map<Integer,String> McpChannelTypeList=new HashMap<Integer,String>(){
    	{
    		put(McpChannelType_Body,"男生");
    		put(McpChannelType_Girl,"女生");
    	}
    };
    
    
    /**
     * mcp小说状态
     */
    public static final int BookStatus_Update = 1;
    public static final int BookStatus_Finish = 2;
    public static final int BookStatus_Stop = 3;
    
    public static Map<Integer, String> BookStatusList = new HashMap<Integer,String>(){
	{
    	put(BookStatus_Update, "连载中");
    	put(BookStatus_Finish, "完结");
    	put(BookStatus_Stop, "停止更新");
    }};
    
    /**
     * mcp收费模式
     */
    public static final int ChargeMode_Free = 1;
    public static final int ChargeMode_Whole = 2;
    public static final int ChargeMode_Chapter = 3;
    
    public static Map<Integer, String> ChargeModeList = new HashMap<Integer,String>(){
	{
    	put(ChargeMode_Free, "免费");
    	put(ChargeMode_Whole, "整本收费");
    	put(ChargeMode_Chapter, "章节收费");
    }};
    
    
    /**
     * 上架状态
     */
    public static final int PutAwayStatus_UnPut = 1;
    public static final int PutAwayStatus_HadPut = 2;
    public static final int PutAwayStatus_HadDown = 3;
    
    public static Map<Integer, String> PutAwayStatusList = new HashMap<Integer,String>(){
	{
    	put(PutAwayStatus_UnPut, "未上架");
    	put(PutAwayStatus_HadPut, "已上架");
    	put(PutAwayStatus_HadDown, "已下架");
    }};
    
    
    /**
     * 审核状态
     */
    public static final int AuditStatus_UnAudit = 1;
    public static final int AuditStatus_Pass = 2;
    public static final int AuditStatus_Reject = 3;
    
    public static Map<Integer, String> AuditStatusList = new HashMap<Integer,String>(){
	{
    	put(AuditStatus_UnAudit, "未审核");
    	put(AuditStatus_Pass, "审核通过");
    	put(AuditStatus_Reject, "驳回");
    }};
    
    
    /**
     * 小说分类
     */
    public static final int Classify_Wuxia= 1001; //武侠
    public static final int Classify_Kehuan = 1002;   //科幻
    public static final int Classify_Xuanhuan = 1003; //玄幻
    public static final int Classify_Xuanyi = 1004; //悬疑
    public static final int Classify_Kongbu = 1005; //恐怖/历险
    public static final int Classify_Yanqing = 1006;  //言情
    public static final int Classify_Xiaoyuan = 1007;  //校园青春
    public static final int Classify_Dushi = 1008;  //都市
    public static final int Classify_Lishi = 1009;  //历史
    public static final int Classify_Junshi = 1010;  //军事
    public static final int Classify_Xiangtu = 1014;  //乡土
    public static final int Classify_Yingshi = 1018;  //影视
    
    public static Map<Integer, String> ClassifyList = new HashMap<Integer,String>(){
	{
    	put(Classify_Wuxia, "武侠");
    	put(Classify_Kehuan, "科幻");
    	put(Classify_Xuanhuan, "玄幻");
    	put(Classify_Xuanyi, "悬疑");
    	put(Classify_Kongbu, "恐怖/历险");
    	put(Classify_Yanqing, "言情");
    	put(Classify_Xiaoyuan, "校园青春");
    	put(Classify_Dushi, "都市");
    	put(Classify_Lishi, "历史");
    	put(Classify_Junshi, "军事");
    	put(Classify_Xiangtu, "乡土");
    	put(Classify_Yingshi, "影视");	
    }};
    
    
    
    /**
     * 章节收费类型
     */
    public static final int Chapter_Free = 0;
    public static final int Chapter_pay = 1;
    public static Map<Integer, String> ChapterTypeList = new HashMap<Integer,String>(){
	{
    	put(Chapter_Free, "免费");
    	put(Chapter_pay, "付费");
    }};
    
    
    /**
     * Des加密秘钥
     */
	public static final String Mcp_Des_Key="abc123bca321";
	 /**
     * Md5加密秘钥
     */
	public static final String Mcp_Md5_Key="0505bcaasd";
}
