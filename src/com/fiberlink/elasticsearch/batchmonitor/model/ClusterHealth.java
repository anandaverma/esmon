package com.fiberlink.elasticsearch.batchmonitor.model;


public class ClusterHealth {
	private String cluster_name;
	private String status;
	private String timed_out;
	private String number_of_nodes;
	private String number_of_data_nodes;
	private String active_primary_shards;
	private String active_shards;
	private String relocating_shards;
	private String initializing_shards;
	private String unassigned_shards;
	
	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimed_out() {
		return timed_out;
	}

	public void setTimed_out(String timed_out) {
		this.timed_out = timed_out;
	}

	public String getNumber_of_nodes() {
		return number_of_nodes;
	}

	public void setNumber_of_nodes(String number_of_nodes) {
		this.number_of_nodes = number_of_nodes;
	}

	public String getNumber_of_data_nodes() {
		return number_of_data_nodes;
	}

	public void setNumber_of_data_nodes(String number_of_data_nodes) {
		this.number_of_data_nodes = number_of_data_nodes;
	}

	public String getActive_primary_shards() {
		return active_primary_shards;
	}

	public void setActive_primary_shards(String active_primary_shards) {
		this.active_primary_shards = active_primary_shards;
	}

	public String getActive_shards() {
		return active_shards;
	}

	public void setActive_shards(String active_shards) {
		this.active_shards = active_shards;
	}

	public String getRelocating_shards() {
		return relocating_shards;
	}

	public void setRelocating_shards(String relocating_shards) {
		this.relocating_shards = relocating_shards;
	}

	public String getInitializing_shards() {
		return initializing_shards;
	}

	public void setInitializing_shards(String initializing_shards) {
		this.initializing_shards = initializing_shards;
	}

	public String getUnassigned_shards() {
		return unassigned_shards;
	}

	public void setUnassigned_shards(String unassigned_shards) {
		this.unassigned_shards = unassigned_shards;
	}

	public void printInfo() {
		System.out.println("------Cluster Heath------");
		System.out.println("Cluster Name: " + cluster_name);
		System.out.println("status: " + status);
		System.out.println("Timed Out: " + timed_out);
		System.out.println("Number of Nodes: " + number_of_nodes);
		System.out.println("Number of Data Nodes: " + number_of_data_nodes);
		System.out.println("Active Primary Shards: "+ active_primary_shards);
		System.out.println("Active Shards: "+ active_shards);
		System.out.println("Relocating Shards:" + relocating_shards);
		System.out.println("Initializing Shards: "+ initializing_shards);
		System.out.println("Unaasigned Shards: "+ unassigned_shards);
	}
}
