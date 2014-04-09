package com.fiberlink.elasticsearch.batchmonitor.model;

import java.util.ArrayList;
import java.util.List;

public class ClusterPendingTasks {
	private List<Tasks> tasks = new ArrayList<Tasks>();
	
	public void printInfo() {
		System.out.println("------Cluster Pending Tasks------");
		for (Tasks t : tasks) {
			t.printInfo();
		}
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}
	
	public static class Tasks {
		private String insert_order;
		private String priority;
		private String source;
		private String time_in_queue_millis;		
		private String time_in_queue;
		
		public void printInfo() {
			System.out.println("Insert Order: " + insert_order);
			System.out.println("Priority: " + priority);
			System.out.println("Source: " + source);
			System.out.println("Time in Queue Millis: " + time_in_queue_millis);
			System.out.println("Time in Queue: " + time_in_queue);
		}

		public String getInsert_order() {
			return insert_order;
		}

		public void setInsert_order(String insert_order) {
			this.insert_order = insert_order;
		}

		public String getPriority() {
			return priority;
		}

		public void setPriority(String priority) {
			this.priority = priority;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getTime_in_queue_millis() {
			return time_in_queue_millis;
		}

		public void setTime_in_queue_millis(String time_in_queue_millis) {
			this.time_in_queue_millis = time_in_queue_millis;
		}

		public String getTime_in_queue() {
			return time_in_queue;
		}

		public void setTime_in_queue(String time_in_queue) {
			this.time_in_queue = time_in_queue;
		}
		
	}
}


