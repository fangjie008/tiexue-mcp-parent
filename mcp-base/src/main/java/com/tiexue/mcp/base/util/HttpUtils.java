package com.tiexue.mcp.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.log4j.Logger;


public class HttpUtils {

	private static Logger logger = Logger.getLogger(HttpUtils.class);
	public static final String UTF_8 = "UTF-8";
	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";
	public static final String GET = "GET";
	public static final String POST = "POST";
	// 请求超时时间
	public static final int SEND_REQUEST_TIME_OUT = 60000;
	// 将读超时时间
	public static final int READ_TIME_OUT = 60000;

	/**
	 * get方式获取
	 * 
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String HttpRequest(String requestUrl, String requestMethod, String outputStr, Boolean isUTF8) {
		String charset = "";
		String result = "";
		StringBuffer sBuffer = new StringBuffer();
		OutputStream outputStream = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferdReader = null;
		try {
			if (isUTF8)
				charset = UTF_8;
			else
				charset = GB2312;
			URL url = new URL(requestUrl);
			HttpURLConnection hConnection = (HttpURLConnection) url.openConnection();
			hConnection.setDoOutput(true);// true
											// 以后就可以使用hConnection.getOutputStream().write()
			hConnection.setDoInput(true);// true
											// 以后就可以使用hConnection.getInputStream().read()
			hConnection.setUseCaches(false);// 请求不使用缓存
			hConnection.setConnectTimeout(SEND_REQUEST_TIME_OUT);
			hConnection.setReadTimeout(READ_TIME_OUT);
			// 设置通用属性
			hConnection.setRequestProperty("accept", "*/*");
			hConnection.setRequestProperty("connection", "Keep-Alive");
			hConnection.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36");
			// 设置请求方式(get,post)

			if (GET.equalsIgnoreCase(requestMethod)) {
				hConnection.setRequestMethod(GET);
				hConnection.connect();
			}
			if (POST.equalsIgnoreCase(requestMethod)) {
				hConnection.setRequestMethod(POST);
				hConnection.connect();
			}

			// 提交数据
			if (outputStr != null) {
				outputStream = hConnection.getOutputStream();
				outputStream.write(outputStr.getBytes(charset));
				outputStream.close();
			}
			// 将返回的输入流转化成字符串
			inputStream = hConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, charset);
			bufferdReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferdReader.readLine()) != null) {
				sBuffer.append(str);
			}

			hConnection.disconnect();
			// 赋返回值
			result = sBuffer.toString();
		} catch (IOException e) {
			logger.error("httpUtils err. url:" + requestUrl);
			e.printStackTrace();
		} finally {
			// 释放资源
			try {
				bufferdReader.close();
			} catch (IOException e) {
				logger.error("httpUtils bufferdReader err. url:" + requestUrl);
				e.printStackTrace();
			}
			try {
				inputStreamReader.close();
			} catch (IOException e) {
				logger.error("httpUtils inputStreamReader err. url:" + requestUrl);
				e.printStackTrace();
			}
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("httpUtils inputStream err. url:" + requestUrl);
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String httpClientDoGet(String url, String queryString) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			if (StringUtils.isNotBlank(queryString))
				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (URIException e) {
			logger.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			logger.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String httpClientDoPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new PostMethod(url);
		for (Iterator it = params.entrySet().iterator(); it.hasNext();) {

		}
		// 设置Http Post数据
		if (params != null) {
			HttpMethodParams p = new HttpMethodParams();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				p.setParameter(entry.getKey(), entry.getValue());
			}
			method.setParams(p);
		}
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (IOException e) {
			logger.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}

		return response;
	}
	
	 

}
