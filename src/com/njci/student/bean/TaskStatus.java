package com.njci.student.bean;

/**
 * TaskStatus entity. @author MyEclipse Persistence Tools
 */

public class TaskStatus implements java.io.Serializable {

	// Fields

	private Integer id;
	private TaskInfo taskInfo;
	private Integer pid;
	private Integer status;

	// Constructors

	/** default constructor */
	public TaskStatus() {
	}

	/** full constructor */
	public TaskStatus(TaskInfo taskInfo, Integer pid, Integer status) {
		this.taskInfo = taskInfo;
		this.pid = pid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TaskInfo getTaskInfo() {
		return this.taskInfo;
	}

	public void setTaskInfo(TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}