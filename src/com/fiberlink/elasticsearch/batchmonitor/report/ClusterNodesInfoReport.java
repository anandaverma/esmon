package com.fiberlink.elasticsearch.batchmonitor.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import com.fiberlink.elasticsearch.batchmonitor.model.NodeInfo;
import com.fiberlink.elasticsearch.batchmonitor.model.NodeInfo.Nodes.NodeObject;
import com.fiberlink.elasticsearch.batchmonitor.util.ElasticSearchSettings;
import com.fiberlink.elasticsearch.batchmonitor.util.JsonClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MailClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyHttpClient;
import com.fiberlink.elasticsearch.batchmonitor.util.MyUtils;

public class ClusterNodesInfoReport implements Report {
	private NodeInfo myNodeInfo;
	private final String REPORT_NAME = "LogAnalyzer Node Information Report";
	private final File REPORT_SUBSCRIPTION_FILE = new File("resources//report-subscription//cluster-node-info.properties");
	private Properties subscriptionProp = new Properties();
	private static Logger logger = Logger
			.getLogger(ClusterNodesInfoReport.class);
	private MyHttpClient httpclient = new MyHttpClient();

	private Properties prop = new Properties();

	public ClusterNodesInfoReport() {
		prop = MyUtils.loadProperties();
	}

	@Override
	public void execute(JobExecutionContext context) {
		// check cluster is up
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
	public void mailReport() {
		// build health report
		String msg = null;
		try {
			msg = "Hi,<br />"
					+ "Here is the Node Status on "
					+ new SimpleDateFormat("dd MMM, yyyy HH:MM:ss")
							.format((new Timestamp(new Date().getTime())))
					+ "<br /><br />" + "<b>Cluster Name: </b> "
					+ myNodeInfo.getCluster_name() + "<br />"
					+ "<b>Total Nodes: </b>"
					+ myNodeInfo.getNodes().any().size() + "<hr />";
			Map<String, NodeInfo.Nodes.NodeObject> map = myNodeInfo.getNodes()
					.any();
			for (Entry<String, NodeObject> entry : map.entrySet()) {
				msg = msg + "<b>Node Name: </b> " + entry.getValue().getName()
						+ "<br />" + "<b>Node Type: </b>" + "client: "
						+ entry.getValue().getAttributes().getClient()
						+ ", data: "
						+ entry.getValue().getAttributes().getData()
						+ ", master: "
						+ entry.getValue().getAttributes().getMaster()
						+ "<br />" + "<b>Jvm Pid: </b>"
						+ entry.getValue().getJvm().getPid() + "<br />"
						+ "<b>Heap Init: </b>"
						+ entry.getValue().getJvm().getMem().getHeap_init()
						+ "<br />" + "<b>Heap Max: </b>"
						+ entry.getValue().getJvm().getMem().getHeap_max()
						+ "<br />" + "<b>Non Heap Init: </b>"
						+ entry.getValue().getJvm().getMem().getNon_heap_init()
						+ "<br />" + "<b>Non Heap Max: </b>"
						+ entry.getValue().getJvm().getMem().getNon_heap_max()
						+ "<br />" + "<b>Direct Max: </b>"
						+ entry.getValue().getJvm().getMem().getDirect_max()
						+ "<br />" + "<br /><br />";
			}

		} catch (Exception e) {
			logger.error("unable to construct message", e);
		}
		if (msg != null && subscriptionProp.getProperty("subscribers") != null) {
			MailClient.sendHTMLEmail(subscriptionProp.getProperty("subscribers"), REPORT_NAME,
					MailClient.formatMail(msg));
		} else {
			logger.error("message body is null, something wrong in parsing Json response");
		}

	}

	@Override
	public void createReport() {
		// get Node Stats

		myNodeInfo = (NodeInfo) JsonClient
				.readJson(httpclient.sendRequestAndGetResponse(
						prop.getProperty("ES_HOST_ADDR"),
						prop.getProperty("ES_HOST_PORT"),
						ElasticSearchSettings.nodeInfoEndPoint), NodeInfo.class);
	}
}
