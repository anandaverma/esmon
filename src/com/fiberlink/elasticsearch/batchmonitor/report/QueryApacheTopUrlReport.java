package com.fiberlink.elasticsearch.batchmonitor.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import com.fiberlink.elasticsearch.batchmonitor.query.FacetQuery;
import com.fiberlink.elasticsearch.batchmonitor.query.Term;
import com.fiberlink.elasticsearch.batchmonitor.util.ElasticSearchSettings;
import com.fiberlink.elasticsearch.batchmonitor.util.JsonClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MailClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyHttpClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyUtils;

public class QueryApacheTopUrlReport implements Report {

	private static Logger logger = Logger
			.getLogger(QueryApacheTopUrlReport.class);
	private final String REPORT_NAME = "Apache Service Tier - Top 20 Urls";
	private final File REPORT_SUBSCRIPTION_FILE = new File(
			"resources//report-subscription//query-apache-top-url-report.properties");
	private Properties subscriptionProp = new Properties();
	private String msg = null;
	private Properties prop = new Properties();
	private MyHttpClient httpclient = new MyHttpClient();

	public QueryApacheTopUrlReport() {
		prop = MyUtils.loadProperties();
	}
	
	public String doQuery(String env) {
		String queryString = "{\r\n  \"facets\": {\r\n    \"terms\": {\r\n      \"terms\": {\r\n        \"field\": \"request.raw\",\r\n        \"size\": 20,\r\n        \"order\": \"count\",\r\n        \"exclude\": []\r\n      },\r\n      \"facet_filter\": {\r\n        \"fquery\": {\r\n          \"query\": {\r\n            \"filtered\": {\r\n              \"query\": {\r\n                \"bool\": {\r\n                  \"should\": [\r\n                    {\r\n                      \"query_string\": {\r\n                        \"query\": \"*\"\r\n                      }\r\n                    }\r\n                  ]\r\n                }\r\n              },\r\n              \"filter\": {\r\n                \"bool\": {\r\n                  \"must\": [\r\n                    {\r\n                      \"range\": {\r\n                        \"@timestamp\": {\r\n                          \"from\": 1397036476331,\r\n                          \"to\": \"now\"\r\n                        }\r\n                      }\r\n                    },\r\n                    {\r\n                      \"terms\": {\r\n                        \"type\": [\r\n                          \""+ env +"\"\r\n                        ]\r\n                      }\r\n                    }\r\n                  ],\r\n                  \"must_not\": [\r\n                    {\r\n                      \"fquery\": {\r\n                        \"query\": {\r\n                          \"query_string\": {\r\n                            \"query\": \"tags:true\"\r\n                          }\r\n                        },\r\n                        \"_cache\": true\r\n                      }\r\n                    }\r\n                  ]\r\n                }\r\n              }\r\n            }\r\n          }\r\n        }\r\n      }\r\n    }\r\n  },\r\n  \"size\": 0\r\n}";
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		String date = dateFormat.format(c.getTime());
		String indexName = "apache-logstash-" + date;
		FacetQuery topUrl  = (FacetQuery) JsonClient.readJson(httpclient.sendPostRequestAndGetResponse(
				prop.getProperty("ES_HOST_ADDR"),
				prop.getProperty("ES_HOST_PORT"),
				indexName + "/" + ElasticSearchSettings.searchEndPoint, queryString),FacetQuery.class);
		try {
				msg = msg + "<h3> Date: "
						+ date + ", Env: " + env + "</h3> <table border=\"1\" style=\"width:640px;cellpadding:10;table-layout:fixed\"><thead><tr><th>URL</th><th>Hits</th></tr></thead><tbody>";
				List<Term> term = topUrl.getFacets().getTerms().getTerms();
				for(int i =0; i < term.size(); i++) {
					msg = msg + "<tr><td width=\"400\">" + term.get(i).getTerm() + "</td><td  width=\"240\">" + term.get(i).getCount() + "</td></tr>";  
				}
				msg = msg + "</tbody></table>";				
			
		}catch (Exception e) {
			logger.error("uable to construct message", e);
		}
		return msg;
	}
	@Override
	public void createReport() {
		msg = "Hi, <br />Daily report for top 20 URLs on apache tier <hr />";
		doQuery("m1");
		doQuery("m2");
		doQuery("m3");
	}
	
	public void query(String params) {
		
	}
	@Override
	public void execute(JobExecutionContext context) {
		if (MyUtils.isClusterUp()) {
			createReport();
			loadSubscriptionFile();
			mailReport();
		} else {
			// send alert for cluster is down
			logger.error("cluster is down");
		}
	}

	@Override
	public void mailReport() {
		if (msg != null && subscriptionProp.getProperty("subscribers") != null) {
			MailClient.sendHTMLEmail(subscriptionProp.getProperty("subscribers"), REPORT_NAME,
					MailClient.formatMail(msg));

		} else {
			logger.error("message body is null, something wrong in parsing Json response");
		}

	}

	@Override
	public void loadSubscriptionFile() {
		try {
			subscriptionProp
					.load(new FileInputStream(REPORT_SUBSCRIPTION_FILE));
		} catch (FileNotFoundException e) {
			logger.error("subscription file not found ", e);
		} catch (IOException e) {
			logger.error("error reading subscription file", e);
		}
	}

}
