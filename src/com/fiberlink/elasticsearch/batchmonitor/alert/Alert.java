package com.fiberlink.elasticsearch.batchmonitor.alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.fiberlink.elasticsearch.batchmonitor.model.ClusterStats;
import com.fiberlink.elasticsearch.batchmonitor.util.ElasticSearchSettings;
import com.fiberlink.elasticsearch.batchmonitor.util.JsonClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MailClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyHttpClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyUtils;

public class Alert implements Job {

	private final File ALERT_FOLDER = new File("resources//alert");
	private static Logger logger = Logger.getLogger(Alert.class);

	// alert values
	private static String alertOwner;
	private static String alertName;
	private static String alertDescription;
	private static String alertType;
	private static String alertNumber;
	private static String alertCompare;

	private Properties esprop = new Properties();
	private MyHttpClient httpclient = new MyHttpClient();

	public Alert() {
		esprop = MyUtils.loadProperties();
	}

	public void readAlertFiles(File file) {
		for (File f : file.listFiles()) {
			if (f.isDirectory()) {
				readAlertFiles(f);
			} else {
				try {
					if (isValidateAlertFile(f)) {
						Properties prop = new Properties();
						prop.load(new FileInputStream(f));
						buildAlerts(prop);
					} else {
						logger.info("Alert File format is not valid.");
					}
				} catch (FileNotFoundException e) {
					logger.error("File not found", e);
				} catch (IOException e) {
					logger.error("I/O error in reading file", e);
				}
			}
		}
	}

	public boolean isValidateAlertFile(File f) {
		return true;
	}

	public void buildAlerts(Properties prop) {
		alertOwner = prop.getProperty("alert.Owner");
		alertName = prop.getProperty("alert.Name");
		alertDescription = prop.getProperty("alert.Description");
		alertType = prop.getProperty("alert.Type");
		alertCompare = prop.getProperty("alert.Compare");
		alertNumber = prop.getProperty("alert.Number");
		fireAlerts();
	}

	public void fireAlerts() {
		if (alertType.equalsIgnoreCase("IndexSize")) {
			ClusterStats clusterstats = (ClusterStats) JsonClient.readJson(
					httpclient.sendRequestAndGetResponse(
							esprop.getProperty("ES_HOST_ADDR"),
							esprop.getProperty("ES_HOST_PORT"),
							ElasticSearchSettings.clusterStatsEndPoint),
					ClusterStats.class);
			Pattern p = Pattern.compile("(\\d+\\.?\\d+)");
			Matcher m1 = p.matcher(clusterstats.getIndices().getStore()
					.getSize());
			Matcher m2 = p.matcher(alertNumber);
			m1.find();
			m2.find();
			// System.out.println("m1: " + m1.group(0) + " m2: " + m2.group(0));
			if (alertCompare.equalsIgnoreCase("GreaterThan")) {
				if (Float.parseFloat(m1.group(0)) > Float.parseFloat(m2
						.group(0))) {
					String msg = "Hi, " + "<br />Index Size Limit Reached"
							+ "<br />Index Size: <b><span style='color:red'>"
							+ clusterstats.getIndices().getStore().getSize()
							+ "</span></b><br />" + "Expected: <b><span style='color:green'>"
							+ alertNumber + "</span><b><br />";
					MailClient.sendHTMLEmail(alertOwner, alertName + " - "
							+ alertDescription, MailClient.formatMail(msg));
				}
			}
		} else if (alertType.equalsIgnoreCase("NodeStatus")) {
			ClusterStats clusterstats = (ClusterStats) JsonClient.readJson(
					httpclient.sendRequestAndGetResponse(
							esprop.getProperty("ES_HOST_ADDR"),
							esprop.getProperty("ES_HOST_PORT"),
							ElasticSearchSettings.clusterStatsEndPoint),
					ClusterStats.class);
			if (alertCompare.equalsIgnoreCase("LessThan")) {
				if (Integer.parseInt(clusterstats.getNodes().getCount()
						.getTotal()) < Integer.parseInt(alertNumber)) {
					String msg = "Hi, "
							+ "<br /> Some node(s) on ES cluster are down"
							+ "<br />Nodes Up: <b><span style='color:red'>"
							+ clusterstats.getNodes().getCount().getTotal()
							+ "</span></b><br />" + "Expected: <b><span style='color:green'>"
							+ alertNumber + "</span></b><br />";
					MailClient.sendHTMLEmail(alertOwner, alertName + " - "
							+ alertDescription, MailClient.formatMail(msg));
				}
			}
		} else {
			logger.error("Alert type not found");
		}
	}

	public void execute(JobExecutionContext context) {
		if (MyUtils.isClusterUp()) {
			logger.info("connceted to host");
			readAlertFiles(ALERT_FOLDER);
		} else {
			logger.error("cluster is down");
		}
	}
}
