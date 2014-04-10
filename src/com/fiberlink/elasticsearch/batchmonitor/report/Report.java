package com.fiberlink.elasticsearch.batchmonitor.report;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public interface Report extends Job {
	public void createReport();
	public void execute(JobExecutionContext context);
	public void mailReport();
	public void loadSubscriptionFile();
}
