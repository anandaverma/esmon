package com.fiberlink.elasticsearch.batchmonitor.model;

public class Cluster {
	private String ok;
	private String name;
	private String status;
	private String tagline;
	private Version version;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
	
    public void printInfo(){
    	System.out.println("------ElasticSearch Information------");
    	System.out.println("Name: " + name);
    	System.out.println("Ok: " + ok);
    	System.out.println("Tagline: " + tagline);
    	version.printInfo();
    }
}

class Version {
	private String number;
	private String build_hash;
	private String build_timestamp;
	private String build_snapshot;
	private String lucene_version;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBuild_hash() {
		return build_hash;
	}

	public void setBuild_hash(String build_hash) {
		this.build_hash = build_hash;
	}

	public String getBuild_timestamp() {
		return build_timestamp;
	}

	public void setBuild_timestamp(String build_timestamp) {
		this.build_timestamp = build_timestamp;
	}

	public String getBuild_snapshot() {
		return build_snapshot;
	}

	public void setBuild_snapshot(String build_snapshot) {
		this.build_snapshot = build_snapshot;
	}

	public String getLucene_version() {
		return lucene_version;
	}

	public void setLucene_version(String lucene_version) {
		this.lucene_version = lucene_version;
	}

	public void printInfo() {
		System.out.println("--Version--");
		System.out.println("Version: " + number);
    	System.out.println("Build Hash: " + build_hash);
    	System.out.println("Build TimeStamp: " + build_timestamp);
    	System.out.println("Build Snapshot: " + build_snapshot);
    	System.out.println("Lucene_version: " + lucene_version);
	}
}
