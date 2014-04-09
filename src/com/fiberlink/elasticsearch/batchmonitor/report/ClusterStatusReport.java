package com.fiberlink.elasticsearch.batchmonitor.report;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import com.fiberlink.elasticsearch.batchmonitor.util.MailClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyUtils;

public class ClusterStatusReport implements Report {

	private static Logger logger = Logger.getLogger(ClusterStatusReport.class);
	private final String REPORT_NAME = "LogAnalyzer ES Cluster Status";


	public ClusterStatusReport() {
		MyUtils.loadProperties();
	}

	public void execute(JobExecutionContext context) {
		if (!MyUtils.isClusterUp()) {
			createReport();
			mailReport();
		}
	}

	public void mailReport() {
		MailClient.sendHTMLEmail("your_mail_id", REPORT_NAME,
				MailClient.formatMail("ES Cluster is down"));
		logger.info("clsuter is down");
	}

	public void createReport() {
		
	}
}
