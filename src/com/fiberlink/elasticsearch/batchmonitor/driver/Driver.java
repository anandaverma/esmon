package com.fiberlink.elasticsearch.batchmonitor.driver;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.fiberlink.elasticsearch.batchmonitor.alert.Alert;
import com.fiberlink.elasticsearch.batchmonitor.report.ClusterHealthReport;
import com.fiberlink.elasticsearch.batchmonitor.report.ClusterNodesInfoReport;
import com.fiberlink.elasticsearch.batchmonitor.report.ClusterStatusReport;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class Driver {

	private static Logger logger = Logger.getLogger(Driver.class);

	public static void main(String[] args) {

		try {
			SchedulerFactory schedularfactory = new StdSchedulerFactory();
			Scheduler scheduler = schedularfactory.getScheduler();

			// define jobs and tie to your class
			JobDetail jobClusterHealth = newJob(ClusterHealthReport.class)
					.withIdentity("ClusterHealth", "Report").build();
			JobDetail jobClusterStatus = newJob(ClusterStatusReport.class)
					.withIdentity("ClusterStatus", "Report").build();
			JobDetail jobNodeInfo = newJob(ClusterNodesInfoReport.class)
					.withIdentity("ClusterNodeStatus", "Report").build();
			JobDetail jobAlert = newJob(Alert.class).withIdentity("Alert",
					"Alert").build();

			// Trigger the job to run now, and then repeat every 1 hour
			Trigger triggerClusterStatus = newTrigger()
					.withIdentity("triggerClusterStatus", "Cluster")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInHours(1)
									.repeatForever()).build();
			// Trigger the job to run now, and then repeat every 6 hour
			Trigger triggerClusterHealth = newTrigger()
					.withIdentity("triggerClusterHealth", "Cluster")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInHours(6)
									.repeatForever()).build();
			// Trigger the job to run now, and then repeat every 6 hour
			Trigger triggerClusterNodeInfo = newTrigger()
					.withIdentity("triggerClusterNodeInfo", "Node")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInHours(6)
									.repeatForever()).build();
			// Trigger the job to run now, and then repeat every 6 hour
			Trigger triggerAlert = newTrigger()
					.withIdentity("triggerAlert", "Alert")
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInHours(1)
									.repeatForever()).build();

			// Tell quartz to schedule the job using our trigger
			scheduler.start();
			logger.info("schedular started");
			scheduler.scheduleJob(jobClusterStatus, triggerClusterStatus);
			scheduler.scheduleJob(jobClusterHealth, triggerClusterHealth);
			scheduler.scheduleJob(jobNodeInfo, triggerClusterNodeInfo);
			scheduler.scheduleJob(jobAlert, triggerAlert);
		} catch (SchedulerException se) {
			logger.error("Schedular failed", se);
		}
	}
}
