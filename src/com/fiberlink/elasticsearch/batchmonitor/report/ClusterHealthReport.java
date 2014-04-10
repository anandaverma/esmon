package com.fiberlink.elasticsearch.batchmonitor.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import com.fiberlink.elasticsearch.batchmonitor.model.ClusterHealth;
import com.fiberlink.elasticsearch.batchmonitor.model.ClusterStats;
import com.fiberlink.elasticsearch.batchmonitor.util.JsonClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MailClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyHttpClient;
import com.fiberlink.elasticsearch.batchmonitor.util.ElasticSearchSettings;
import com.fiberlink.elasticsearch.batchmonitor.util.MyUtils;

public class ClusterHealthReport implements Report {

	private ClusterHealth myClusterHealth;
	private ClusterStats myClusterStats;
	private final String REPORT_NAME = "LogAnalyzer ES Health Report";
	private final File REPORT_SUBSCRIPTION_FILE = new File("resources//report-subscription//cluster-health.properties");
	private Properties subscriptionProp = new Properties();
	private static Logger logger = Logger.getLogger(ClusterHealthReport.class);
	private MyHttpClient httpclient = new MyHttpClient();
	private Properties prop = new Properties();
	private String msg = null;
	public ClusterHealthReport() {
		prop = MyUtils.loadProperties();
	}

	
	@Override
	public void createReport() {
		// get Cluster Health
		// check cluster is up
		myClusterHealth = (ClusterHealth) JsonClient.readJson(httpclient
				.sendRequestAndGetResponse(prop.getProperty("ES_HOST_ADDR"),
						prop.getProperty("ES_HOST_PORT"),
						ElasticSearchSettings.clusterHealthEndPoint),
				ClusterHealth.class);

		// get cluster stats
		myClusterStats = (ClusterStats) JsonClient.readJson(httpclient
				.sendRequestAndGetResponse(prop.getProperty("ES_HOST_ADDR"),
						prop.getProperty("ES_HOST_PORT"),
						ElasticSearchSettings.clusterStatsEndPoint),
				ClusterStats.class);
		
		// build health report
				try {
					msg = "Hi,<br />" + "Here is the health report of ES cluster on "
							+ new SimpleDateFormat("dd MMM, yyyy HH:MM:ss")
									.format((new Timestamp(Long
											.parseLong(myClusterStats.getTimestamp()))))
							+ "<br /><br />"
							+ "<hr />Cluster Summary<br />"
							+ "<b>Cluster Name:</b> "
							+ myClusterStats.getCluster_name()
							+ "<br />"
							+ "<b>Cluster Status:</b> "
							+ myClusterStats.getStatus()
							+ "<br />"
							+ "<b>Timeout:</b> "
							+ myClusterHealth.getTimed_out()
							+ "<br />"
							+ "<b>Total Nodes Up:</b> "
							+ myClusterHealth.getNumber_of_nodes()
							+ "<br />"
							+ "<b>Total Data Nodes Up:</b> "
							+ myClusterHealth.getNumber_of_data_nodes()
							+ "<br />"
							+ "<b>Active  Nodes Up:</b> "
							+ myClusterHealth.getNumber_of_nodes()
							+ "<br />"
							+ "<b>Total Document Indexed:</b>"
							+ myClusterStats.getIndices().getDocs().getCount()
							+ "<br />"
							+ "<b>Index Size:</b> "
							+ myClusterStats.getIndices().getStore().getSize()
							+ "<br />"
							+ "<b>Throttle Time:</b> "
							+ myClusterStats.getIndices().getStore().getThrottle_time()
							+ "<br />"
							+ "<b>Active Primary Shards:</b> "
							+ myClusterHealth.getActive_primary_shards()
							+ "<br />"
							+ "<b>Active Shards:</b> "
							+ myClusterHealth.getActive_shards()
							+ "<br />"
							+ "<b>Relocating Shards:</b> "
							+ myClusterHealth.getRelocating_shards()
							+ "<br />"
							+ "<b>Initializing Shards:</b> "
							+ myClusterHealth.getInitializing_shards()
							+ "<br />"
							+ "<b>Unassigned Shards:</b> "
							+ myClusterHealth.getUnassigned_shards()
							+ "<br />"
							+ "<hr />"
							+ "Diagnostic Details<br />"
							+ "os<br />"
							+ "<b>Available Processor:</b> "
							+ myClusterStats.getNodes().getOs()
									.getAvailable_processors()
							+ "<br />"
							+ "jvm<br />"
							+ "<b>Max Up-Time</b> "
							+ myClusterStats.getNodes().getJvm().getMax_uptime()
							+ "<br />"
							+ "<b>Heap Used</b> "
							+ myClusterStats.getNodes().getJvm().getMem()
									.getHeap_used() + "<br />" + "<b>Heap Max</b> "
							+ myClusterStats.getNodes().getJvm().getMem().getHeap_max()
							+ "<br />" + "<b>Total Threads</b> "
							+ myClusterStats.getNodes().getJvm().getThreads()
							+ "<br />" + "";
				} catch (Exception e) {
					logger.error("unable to construct message", e);
				}
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

}
