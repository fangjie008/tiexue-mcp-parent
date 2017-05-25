package com.tiexue.mcp.base.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 时间戳与字符串转换
 * 
 */
public class DateUtil {

	/**
	 * 默认日期格式
	 */
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	
	public static final String DATE_FORMAT_YYYYMMDDHHmmdd = "yyyyMMddHHmmssSSS";

	/**
	 * 默认构造函数
	 */
	private DateUtil() {
	}

	/**
	 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            日期格式
	 * @return 日期
	 * @throws java.text.ParseException
	 */
	public static Date str2Date(String str, String format) {
		if (null == str || "".equals(str)) {
			return null;
		}
		// 如果没有指定字符串转换的格式，则用默认格式进行转换
		if (null == format || "".equals(format)) {
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, String format) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		return sdf.format(date);
	}
	public static String date2StrDetail(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHmmdd);
		return sdf.format(date);
	}
	
	
	/**
	 * 字符串型时间戳转换日期
	 * @param timestamp
	 * @return
	 */
	public static String time2str(long timestamp){
		Timestamp ts = new Timestamp(timestamp);   
		return timestamp2Str(ts);
	}
	
	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamp2Str(Timestamp time) {
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date2Str(date, DEFAULT_FORMAT);
	}
	
	/**
	 * 时间戳转换为日期
	 * 
	 * @param time
	 * @return
	 */
	public static Date timestamp2Date(String time) {
		Date date = null;
		if (null != time) {
			try {
				long lg=new Long(time);
				date = new Date(lg);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		return date;
	}
	
	
	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamp2DateStr(Timestamp time) {
		Date date = null;
		if (null != time) {
			date = new Date(time.getDate());
		}
		return date2Str(date, DATE_FORMAT_YYYY_MM_DD);
	}
	/**
	 * 字符串转换时间戳  字符串格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, DEFAULT_FORMAT);
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 字符串转换时间戳 字符串格式yyyy-MM-dd
	 * 
	 * @param str
	 * @return
	 */
	public static String str2str(String str)
	{
		Date date = str2Date(str, DATE_FORMAT_YYYY_MM_DD);
	    long a =date.getTime()/1000;
	    String b = a+"";
		return b;
	}
	
	/**
	 * 字符串转换时间戳 字符串格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static String str2straa(String str)
	{
		Date date = str2Date(str, DEFAULT_FORMAT);
	    long a =date.getTime()/1000;
	    String b = a+"";
		return b;
	}
	/**
	 * 取得系统时间
	 * @return yyyy-mm-dd hh:mm:ss
	 */
	public static String getDateTime(){
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		return df.format(date);
	}
	

	public static long getCurrentLong(){
		return System.currentTimeMillis()/1000;
	}
	//取得当前时间
	public static String getCurrentTime(){
		return long2String(getCurrentLong());
	}

	/**
	 * long转换String日期
	 * @param date 为空，返回当前时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String long2String(long date){
		if(date > 0)
			return new java.text.SimpleDateFormat(DEFAULT_FORMAT).format(new java.util.Date(date * 1000));
		else
			return getDateTime();
	}
	
	//linu获取当前时间
	public static String getLinuxTime2(){
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");//设置日期格式
			return df.format(new Date());// 
		} catch (Exception e) {
		}
		return null;
	}

	public static String getCurrentDate(){
		Date date = new Date();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(date);
	}
	/**
	 * 把时间转换为long
	 */
	public static long getDate2Long(String date) throws Exception{
		SimpleDateFormat sdf  = new SimpleDateFormat(DEFAULT_FORMAT);
		Date date2 = sdf.parse(date);
		return date2.getTime();
	}
	/**
	 * 把当前时间转换为long
	 * @return
	 * @throws Exception
	 */
	public static long getDate2Long(){
		SimpleDateFormat sdf  = new SimpleDateFormat(DEFAULT_FORMAT);
		try {
			Date date = sdf.parse(getDateTime());
			 return date.getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	//linu获取当前时间
	public static String getLinuxTime(){
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			return df.format(new Date());// 
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 获取系统时间 
	 * 格式yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws 
	 * @see getSystemTime
	 */
	public static String getSystemTime(){
		return new java.text.SimpleDateFormat(DEFAULT_FORMAT).format(new java.util.Date( System.currentTimeMillis()));
	}
	
	/** 
     * 判断日期格式:yyyy-mm-dd 
     *  
     * @param sDate 
     * @return 
     */  
    public static boolean isValidDate(String sDate) {  
        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";  
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"  
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"  
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"  
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("  
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"  
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";  
        if ((sDate != null)) {  
            Pattern pattern = Pattern.compile(datePattern1);  
            Matcher match = pattern.matcher(sDate);  
            if (match.matches()) {  
                pattern = Pattern.compile(datePattern2);  
                match = pattern.matcher(sDate);  
                return match.matches();  
            } else {  
                return false;  
            }  
        }  
        return false;  
    }
    
    /**
	 * 获取当前时间
	 * @return
	 */
	public static String getDays(String format){
		return new SimpleDateFormat(format).format(new Date());
	}
	
	/**
	 * 格式化当前日期
	 * @return
	 */
	public static Date fomatCurrentDate(String format){
		
		DateFormat fm = new SimpleDateFormat(format);
		try {
			return fm.parse(getDays(format));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 校验日期
	 */
	// public static String checkDate(String endTime){
	// boolean flage =false;
	// String regex =
	// "^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))(\s(([01]\d{1})|(2[0123])):([0-5]\d):([0-5]\d))?$";
	// Pattern pattern = Pattern.compile(regex);
	// Matcher match = pattern.matcher(endTime);
	// if(match.matches()){
	// long now = System.currentTimeMillis();
	// long end = new SimpleDateFormat("yyyy-MM-dd
	// hh:mm:ss").parse(endTime).getTime();//根据字符串time得到毫秒数。
	// if (end>now) {
	// flage=true;
	// }
	// }
	// return
	// }

	/**
	 * 日期加day天
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date AddDays(Date date, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return date;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getCurrentTime());
	}
}
