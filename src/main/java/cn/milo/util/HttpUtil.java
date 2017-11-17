package cn.milo.util;

import java.io.*;
import java.net.URLDecoder;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/******************************************************
 ****** @ClassName   : HttpUtil.java            
 ****** @illustration: illustration                                 
 ****** @author      : 商明涛 ^ ^                     
 ****** @date        : 2017-1-10 下午12:09:28      
 ****** @version     : v5.0.x                      
 *******************************************************/
public class HttpUtil {
	
	static Logger log = Logger.getLogger(HttpUtil.class);
	
	/***          
	 *** @illustration: 获取request中信息                 
	 *** @Description : Description                  
	 *** @author      : 商明涛 ^ ^                     
	 *** @date        : 2016-11-28 下午4:44:51      
	 *** @version     : v1.0.x                  
	 ***/
	public static String getJsonFromRequest(HttpServletRequest request) {
		StringBuffer info = new StringBuffer();
		try {
			InputStream in = request.getInputStream();
			BufferedInputStream buf = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int iRead;
			while ((iRead = buf.read(buffer)) != -1) {
				info.append(new String(buffer, 0, iRead, "UTF-8"));
			}
		} catch (Exception ee) {
		}
		try {
			return URLDecoder.decode(info.toString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void sendAppMessage(String message, HttpServletResponse response) {
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.println(message);
		} catch (Exception ee) {
		} finally {
			out.close();
		}
	}

	/***
	 *** @illustration: POST 请求
	 *** @Description : Description
	 *** @author      : 商明涛 ^ ^
	 *** @date        : 2016-11-28 下午4:56:15
	 *** @version     : v1.0.x
	 ***/
	public static String doPost(String url, String json) throws Exception {
		String responseText = null;
		CloseableHttpClient closeableHttpClient = createHttpsClient();
		HttpPost method = new HttpPost(url);

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
		method.setConfig(requestConfig);

		StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		method.setEntity(entity);
		HttpResponse httpResponse = closeableHttpClient.execute(method);
		HttpEntity httpEntity2 = httpResponse.getEntity();
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(httpEntity2);
			responseText = result;
		}
		closeableHttpClient.close();
		return responseText;
	}

	/**
	 * doGet方式访问URL
	 *
	 * @param url
	 * @return OutputStream
	 */
	public static String doGet(String url) throws Exception {
		String responseText = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(entity);
			responseText = result;
		}
		return responseText;
	}

	public static CloseableHttpClient createHttpsClient() throws Exception {
		X509TrustManager x509mgr = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] xcs, String string) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] xcs, String string) {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, new TrustManager[] { x509mgr }, new java.security.SecureRandom());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
}
