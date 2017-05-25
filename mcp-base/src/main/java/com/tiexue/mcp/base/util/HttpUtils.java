package com.tiexue.mcp.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;






public class HttpUtils {

	private static Logger logger=Logger.getLogger(HttpUtils.class);
	public static final String UTF_8 = "UTF-8";
	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";
	public static final String GET = "GET";
	public static final String POST = "POST";
	// 请求超时时间
    public static final int SEND_REQUEST_TIME_OUT = 50000;
    // 将读超时时间
    public static final int READ_TIME_OUT = 50000;
	/**
	 * get方式获取
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String HttpRequest(String requestUrl,String requestMethod,String outputStr,Boolean isUTF8){
		String charset="";
		String result="";
		StringBuffer sBuffer=new StringBuffer();
		OutputStream outputStream=null;
		InputStream inputStream=null;
		InputStreamReader inputStreamReader=null;
		BufferedReader bufferdReader=null;
		try {
			if(isUTF8)
				charset=UTF_8;
			else
				charset=GB2312;
			URL url=new URL(requestUrl);
			HttpURLConnection hConnection= (HttpURLConnection)url.openConnection();
			hConnection.setDoOutput(true);//true 以后就可以使用hConnection.getOutputStream().write()  
			hConnection.setDoInput(true);//true 以后就可以使用hConnection.getInputStream().read()  
			hConnection.setUseCaches(false);//请求不使用缓存
			//设置通用属性
			hConnection.setRequestProperty("accept", "*/*");  
			hConnection.setRequestProperty("connection", "Keep-Alive");  
			hConnection.setRequestProperty("user-agent",  
	                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36");
			//设置请求方式(get,post)
			
			if(GET.equalsIgnoreCase(requestMethod))
			{
				hConnection.setRequestMethod(GET);
				hConnection.connect();
			}
			if(POST.equalsIgnoreCase(requestMethod))
			{
				hConnection.setRequestMethod(POST);
				hConnection.connect();
			}
				
			//提交数据
			if(outputStr!=null){
			    outputStream=hConnection.getOutputStream();
				outputStream.write(outputStr.getBytes(charset));
				outputStream.close();
			}
			//将返回的输入流转化成字符串
			inputStream=hConnection.getInputStream();
			inputStreamReader=new InputStreamReader(inputStream,charset);
		    bufferdReader=new BufferedReader(inputStreamReader);
			String str=null;
			while((str=bufferdReader.readLine())!=null){
				sBuffer.append(str);
			}
			
			hConnection.disconnect();
			//赋返回值
			result=sBuffer.toString();
		} catch (IOException e) {
			logger.error("httpUtils err. url:"+requestUrl);
			e.printStackTrace();
		}
		finally {
			//释放资源
			try {
				bufferdReader.close();
			} catch (IOException e) {
				logger.error("httpUtils bufferdReader err. url:"+requestUrl);
				e.printStackTrace();
			}
			try {
				inputStreamReader.close();
			} catch (IOException e) {
				logger.error("httpUtils inputStreamReader err. url:"+requestUrl);
				e.printStackTrace();
			}
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("httpUtils inputStream err. url:"+requestUrl);
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
