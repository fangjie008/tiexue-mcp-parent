package com.tiexue.mcp.core.entity;

import java.util.HashMap;
import java.util.Map;

public class EnumType {
	// 小说状态常量
    public static final int BookStatus_Offline = 0;
    public static final int BookStatus_Update = 1;
    public static final int BookStatus_Finish = 2;
    public static final int BookStatus_Delete = 3;
    /**
     * 小说状态
     */
    public static Map<Integer, String> BookStatus = new HashMap<Integer,String>(){
	{
    	put(BookStatus_Offline, "未上线");
    	put(BookStatus_Update, "连载中");
    	put(BookStatus_Finish, "完结");
    	put(BookStatus_Delete, "删除");
    }};

    //小说标识常量
    public static final int BookMark_Normal=0;
    public static final int BookMark_Recomment=1;
    public static final int BookMark_Hot=2;
    public static final int BookMark_Essence=3;
    /**
     * 小说标识
     */
    public static Map<Integer,String> BookMark=new HashMap<Integer,String>(){
    	{
    		put(BookMark_Normal,"正常");
    		put(BookMark_Recomment,"推荐");
    		put(BookMark_Hot,"热门");
    		put(BookMark_Essence,"精华");
    	}
    };
    	
    //章节类型常量
    public static final int ChapterType_Free=0;
    public static final int ChapterType_Pay=1;
    /**
     * 章节类型
     */
    public static Map<Integer,String> ChapterType=new HashMap<Integer,String>(){
    	{
    		put(ChapterType_Free,"免费");
    		put(ChapterType_Pay,"付费");
    	}
    };

    //章节状态常量
    public static final int ChapterStatus_Offline=0;
    public static final int ChapterStatus_OnLine=1;
    public static final int ChapterStatus_Delete=2;
    /**
     * 章节状态
     */
    public static Map<Integer,String> ChapterStatus=new HashMap<Integer,String>(){
    	{
    		put(ChapterStatus_Offline,"未上线");
    		put(ChapterStatus_OnLine,"上线");
    		put(ChapterStatus_Delete,"删除");
    	}
    };

    //评论状态常量
    public static final int CommentStatus_Normal=0;
    public static final int CommentStatus_Delete=1;
    /**
     * 评论状态
     */
    public static Map<Integer,String> CommentStatus=new HashMap<Integer,String>(){
    	{
    		put(CommentStatus_Normal,"正常");
    		put(CommentStatus_Delete,"删除");
    	}
    };

    //充值类型常量
    public static final int PayType_NovelCurr=1;
    public static final int PayType_Time=2;
    /**
     * 充值类型
     */
    public static Map<Integer,String> PayType=new HashMap<Integer,String>(){
    	{
    		put(PayType_NovelCurr,"小说币");
    		put(PayType_Time,"包月(年)");
    	}
    };
    
    //订单支付状态
    public static final int OrderStatus_WaitPay=1;
    public static final int OrderStatus_Error=2;
    public static final int OrderStatus_Success=3;
    /**
     * 订单支付状态
     */
    public static Map<Integer,String> OrderStatus=new HashMap<Integer,String>(){
    	{
    		put(OrderStatus_WaitPay,"待支付");
    		put(OrderStatus_Error,"支付失败");
    		put(OrderStatus_Success,"支付成功");
    		
    	}
    };
    
    //支付渠道
    public static final int PayChannel_Wx=1;
    public static final int PayChannel_ZhiFuBao=2;
    /**
     * 支付渠道
     */
    public static Map<Integer,String> PayChannel=new HashMap<Integer,String>(){
    	{
    		put(PayChannel_Wx,"微信");
    		put(PayChannel_ZhiFuBao,"支付宝");
    	}
    };
    
    //包年(月)充值的单位常量
    public static final int PayUnit_Year=1;
    public static final int PayUnit_Month=2;
    public static final int PayUnit_Quarter=3;
    /**
     * 包年(月)充值的单位
     */
    public static Map<Integer,String> PayUnit=new HashMap<Integer,String>(){
    	{
    		put(PayUnit_Year,"年");
    		put(PayUnit_Month,"月");
    		put(PayUnit_Quarter,"季度");
    	}
    };
    
    //用户类型常量
    public static final int UserType_Normal=0;
    public static final int UserType_VIP=1;
    /**
     * 用户类型
     */
    public static Map<Integer,String> UserType=new HashMap<Integer,String>(){
    	{
    		put(UserType_Normal,"普通用户");
    		put(UserType_VIP,"VIP用户");
    	}
    };

    //用户性别
    public static final int UserSex_Male=1;
    public static final int UserSex_Female=2;
    /**
     * 用户性别
     */
    public static Map<Integer,String> UserSex=new HashMap<Integer,String>(){
    	{
    		put(UserSex_Male,"男性");
    		put(UserSex_Female,"女性");
    	}
    };
    
    //用户状态常量
    public static final int UserStatus_Normal=0;
    public static final int UserStatus_Delete=1;
    /**
     * 用户状态
     */
    public static Map<Integer,String> UserStatus=new HashMap<Integer,String>(){
    	{
    		put(UserStatus_Normal,"正常");
    		put(UserStatus_Delete,"删除");
    	}
    };

    
    /**
     * 跳转到登录页面
     */
	public static final int ResultNum_Login=101;	
    /**
     * 跳转到支付页面的值
     */
    public static final int ResultNum_Pay=102;
    /**
     * 跳转到消费页面
     */
	public static final int ResultNum_Cons=103;	

	
	public static final String Des_Key="abc123bca321";
	
			
}
