package com.njci.student.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String pwd;
	private String tel;
	private Set taskInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(String username, String pwd, String tel, Set taskInfos) {
		this.username = username;
		this.pwd = pwd;
		this.tel = tel;
		this.taskInfos = taskInfos;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Set getTaskInfos() {
		return this.taskInfos;
	}

	public void setTaskInfos(Set taskInfos) {
		this.taskInfos = taskInfos;
	}

}