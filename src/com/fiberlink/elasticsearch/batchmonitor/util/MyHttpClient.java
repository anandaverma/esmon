package com.fiberlink.elasticsearch.batchmonitor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class MyHttpClient {
	private final static int TIMEOUT = 60000; // 60sec
	private HttpURLConnection conn = null;
	private Logger logger = Logger.getLogger(MyHttpClient.class);
			
	public HttpURLConnection getHttpConnection(URL url) throws IOException {
		conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(TIMEOUT);
		conn.setReadTimeout(TIMEOUT);
		return conn;
	}
	
	public void closeConnection() {
		if(conn != null) {
			conn.disconnect();
		}
	}
	
	public String sendRequestAndGetResponse(String host, String port, String endPoint) {
		String responseData = new String();
		try {
			URL url = new URL(host + ":" + port + "/" + endPoint);
			getHttpConnection(url);
			// check if response code is 200
			if (conn instanceof HttpURLConnection) {
				int code = ((HttpURLConnection) conn).getResponseCode();
				if (code != HttpURLConnection.HTTP_OK) {
					logger.error("Request falied with error code " + code);
					return "Request Failed";
				}
				
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(((URLConnection) conn).getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				responseData = responseData + line;
			}
		} catch (Exception e) {
			logger.error("conection failed", e);
		} finally {
			closeConnection();
		}
		logger.debug("recieved" + responseData);
		return responseData;
	}
	
	public String sendPostRequestAndGetResponse(String host, String port, String endPoint, String request) {
		String responseData = new String();
		try {
			URL url = new URL(host + ":" + port + "/" + endPoint);
			getHttpConnection(url);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// check if response code is 200
			byte[] outputBytes = request.getBytes("UTF-8");
			OutputStream os = conn.getOutputStream();
			os.write(outputBytes);
			os.close();
			if (conn instanceof HttpURLConnection) {
				int code = ((HttpURLConnection) conn).getResponseCode();
				if (code != HttpURLConnection.HTTP_OK) {
					logger.error("Request falied with error code " + code);
					return "Request Failed";
				}
				
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(((URLConnection) conn).getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				responseData = responseData + line;
			}
		} catch (Exception e) {
			logger.error("conection failed", e);
		} finally {
			closeConnection();
		}
		logger.debug("recieved" + responseData);
		return responseData;
	}
}