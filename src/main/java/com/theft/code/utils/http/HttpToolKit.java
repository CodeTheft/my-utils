package com.theft.code.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.theft.code.utils.string.StringUtil;

/**
 * HttpClient模拟请求HttpGet和HttpPost
 * 
 * @author chufei
 * @date 2017年7月3日
 */
public class HttpToolKit {

	/**
	 * 模拟http get 请求
	 * @param uri 请求地址
	 * @param params 请求参数
	 * @param charset 请求编码
	 * @return 返回请求体
	 */
	public static String doGet(String uri, Map<String, String> params, String userAgent, String charset) {
		CloseableHttpClient client = HttpClients.createDefault();
		StringBuffer queryString = new StringBuffer();
		if (params != null && !params.isEmpty()) {
			queryString.append("?");
			for (Map.Entry<String, String> entry : params.entrySet()) {
				queryString.append(entry.getKey());
				queryString.append("=");
				try {
					queryString.append(URLEncoder.encode(entry.getValue(), charset));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				queryString.append("&");
			}
		}
		uri = uri + queryString.toString();
		HttpGet get = new HttpGet(uri);
		
		if (!StringUtil.strIsNull(userAgent)) {
			get.setHeader("User-Agent", userAgent);
		}
		
		try {
			CloseableHttpResponse response = client.execute(get);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity, charset);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "";
	}
	
	/**
	 * 模拟http post 请求
	 * @param uri 请求地址
	 * @param params 请求参数
	 * @param charset 请求编码
	 * @return 返回请求体
	 */
	public static String doPost(String uri, Map<String, String> params, String userAgent, String charset) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(uri);
		
		if (!StringUtil.strIsNull(userAgent)) {
			post.setHeader("User-Agent", userAgent);
		}
		
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity urlEncodedFormEntity;
			try {
				urlEncodedFormEntity = new UrlEncodedFormEntity(paramList, charset);
				post.setEntity(urlEncodedFormEntity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		try {
			CloseableHttpResponse response = client.execute(post);
			try {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						return EntityUtils.toString(entity, charset);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
}
