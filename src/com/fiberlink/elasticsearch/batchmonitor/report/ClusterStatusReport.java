package com.fiberlink.elasticsearch.batchmonitor.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import com.fiberlink.elasticsearch.batchmonitor.util.MailClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyUtils;

public class ClusterStatusReport implements Report {

	private static Logger logger = Logger.getLogger(ClusterStatusReport.class);
	private final String REPORT_NAME = "IMP-Elastic Search Cluster is down";
	private final File REPORT_SUBSCRIPTION_FILE = new File("resources//report-subscription//cluster-status.properties");
	private Properties subscriptionProp = new Properties();
	private String msg = null;

	public ClusterStatusReport() {
		MyUtils.loadProperties();
	}
	
	@Override
	public void loadSubscriptionFile() {
		try {
			subscriptionProp.load(new FileInputStream(
					REPORT_SUBSCRIPTION_FILE));
		} catch (FileNotFoundException e) {
			logger.error("subscription file not found ", e);
		} catch (IOException e) {
			logger.error("error reading subscription file", e);
		}
	}
	
	public void execute(JobExecutionContext context) {
		if (!MyUtils.isClusterUp()) {
			createReport();
			loadSubscriptionFile();
			mailReport();
		}
	}

	public void mailReport() {
		if (msg != null && subscriptionProp.getProperty("subscribers") != null)
		MailClient.sendHTMLEmail(subscriptionProp.getProperty("subscribers"), REPORT_NAME,
				MailClient.formatMail(msg));
		logger.info("clsuter is down");
	}

	public void createReport() {
		msg = "<font size=\"3\" color=\"red\">I am unable to ping ES Cluster. It might be down</font>";
	}
}
