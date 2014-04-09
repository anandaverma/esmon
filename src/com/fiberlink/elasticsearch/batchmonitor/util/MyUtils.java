package com.fiberlink.elasticsearch.batchmonitor.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MyUtils {
	private static Logger logger = Logger.getLogger(MyUtils.class);
	private static Properties prop = new Properties();
	private static MyHttpClient httpclient = new MyHttpClient();
	
	public static Properties loadProperties() {
		// load es-batch-monitor properties file
				try {
					prop.load(new FileInputStream(
							"resources//es-batch-monitor.properties"));
				} catch (FileNotFoundException e) {
					logger.error("es-batch-monitor.properties file not found ", e);
				} catch (IOException e) {
					logger.error("error reading properties file", e);
				}
				return prop;
	}
	
	public static boolean isClusterUp() {
		if (httpclient.sendRequestAndGetResponse(
				prop.getProperty("ES_HOST_ADDR"),
				prop.getProperty("ES_HOST_PORT"),
				ElasticSearchSettings.clusterInfoEndPoint).equalsIgnoreCase(
				"Request Failed")) {
			return false;	
		}
		else {
			return true;
		}
	}
}
