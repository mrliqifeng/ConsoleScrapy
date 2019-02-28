package com.njci.student.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.njci.student.dao.UsersDao;
import com.njci.student.bean.Users;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -544807092935108171L;
	private UsersDao userDao;
	
	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}
	private String repwd;
	private String username;
	private String pwd;
	private String tel;
	private String isadmin;
	private String nextDispose;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRepwd() {
		return repwd;
	}
	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	public String getNextDispose() {
		return nextDispose;
	}
	public void setNextDispose(String nextDispose) {
		this.nextDispose = nextDispose;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	
	public void validateLogin() {
		Users users = userDao.getByNameAndPwd(username, pwd);
		if(users==null) {
			super.addActionError("用户名或密码错误，登录失败");
		}
		else {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("loginUser", users);
		}
	}
	
	@SuppressWarnings("unused")
	public String login() {
		HttpSession session = ServletActionContext.getRequest().getSession();
//		Users users = userDao.getByNameAndPwd(username, pwd);
		Users users = (Users)session.getAttribute("loginUser");
//		if(users==null) {
//			this.message = "登录失败，用户名或密码错误";
//			return "error";
//		}
//		else {
			session.setAttribute("loginUser", users);
			nextDispose = "pagging";
			return "success";
//		}
	}
	
	public String register() {
		Users users = new Users();
		users.setPwd(pwd);
		users.setUsername(username);
		users.setTel(tel);
		if(repwd.equals(pwd)) {
			Users testUsers = userDao.getByName(username);
			if(testUsers!=null) {
				this.setMessage("用户已存在");
				return "error";
			}
			else {
				userDao.inesrt(users);
				HttpSession session =  ServletActionContext.getRequest().getSession();
				session.setAttribute("loginUser", users);
				return "success";
			}
		}
		else {
			this.setMessage("两次密码不一致");
			return "error";
		}
	}


}
