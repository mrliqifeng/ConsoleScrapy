package com.njci.student.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * TaskInfo entity. @author MyEclipse Persistence Tools
 */

public class TaskInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private String hostname;
	private String taskname;
	private String url;
	private String rulename;
	private String rulecontent;
	private String uuid;
	private Set taskStatuses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TaskInfo() {
	}

	/** minimal constructor */
	public TaskInfo(Users users, String hostname, String taskname, String url, String rulename, String rulecontent,
			String uuid) {
		this.users = users;
		this.hostname = hostname;
		this.taskname = taskname;
		this.url = url;
		this.rulename = rulename;
		this.rulecontent = rulecontent;
		this.uuid = uuid;
	}

	/** full constructor */
	public TaskInfo(Users users, String hostname, String taskname, String url, String rulename, String rulecontent,
			String uuid, Set taskStatuses) {
		this.users = users;
		this.hostname = hostname;
		this.taskname = taskname;
		this.url = url;
		this.rulename = rulename;
		this.rulecontent = rulecontent;
		this.uuid = uuid;
		this.taskStatuses = taskStatuses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getTaskname() {
		return this.taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRulename() {
		return this.rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}

	public String getRulecontent() {
		return this.rulecontent;
	}

	public void setRulecontent(String rulecontent) {
		this.rulecontent = rulecontent;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Set getTaskStatuses() {
		return this.taskStatuses;
	}

	public void setTaskStatuses(Set taskStatuses) {
		this.taskStatuses = taskStatuses;
	}

}