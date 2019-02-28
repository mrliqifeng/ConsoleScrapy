package com.njci.student.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.njci.student.bean.TaskInfo;
import com.njci.student.bean.TaskStatus;
import com.njci.student.bean.Users;
import com.njci.student.dao.TaskInfoDao;
import com.njci.student.dao.TaskStatusDao;
import com.njci.student.util.ScrapyRabbitCon;
import com.njci.student.util.ScrapyRabbitPro;

public class TaskInfoAction {
	private TaskInfoDao taskInfoDaoImpl;
	private TaskStatusDao taskStatusDaoImpl;
	private Integer prevPage;
	private Integer nextPage;
	private Integer page;
	private List<TaskInfo> list; //传递任务列表
	private String statusString;
	private TaskInfo taskInfo; //传递具体某个任务的任务详情
	private Map<Integer,String> statusMap; //传递状态字典
	private Integer id;
	private String uuid;
	private String rulecontent;
	private String url;
	private String taskname;
	private ScrapyRabbitPro scPro = ScrapyRabbitPro.getSendRabbit();
//	private ScrapyRabbitCon scCon = ScrapyRabbitCon.getRabbit();
	private List<List<String>> previewDatas;
	
	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRulecontent() {
		return rulecontent;
	}

	public void setRulecontent(String rulecontent) {
		this.rulecontent = rulecontent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatusString() {
		return statusString;
	}

	public List<List<String>> getPreviewDatas() {
		return previewDatas;
	}

	public void setPreviewDatas(List<List<String>> previewDatas) {
		this.previewDatas = previewDatas;
	}
	
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public TaskInfo getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}

	public Map<Integer,String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<Integer,String> statusMap) {
		this.statusMap = statusMap;
	}

	public Integer getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(Integer prevPage) {
		this.prevPage = prevPage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTaskInfoDaoImpl(TaskInfoDao taskInfoDaoImpl) {
		this.taskInfoDaoImpl = taskInfoDaoImpl;
	}

	public void setTaskStatusDaoImpl(TaskStatusDao taskStatusDaoImpl) {
		this.taskStatusDaoImpl = taskStatusDaoImpl;
	}

	public List<TaskInfo> getList() {
		return list;
	}

	public void setList(List<TaskInfo> list) {
		this.list = list;
	}
	
	/**
	 * 获取当前用户的用户名
	 * @return
	 */
	public String getUserName() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users)session.getAttribute("loginUser");
		return users.getUsername();
	}
	
	/**
	 * 获取当前爬虫的表名 
	 * @return
	 */
	public String getTableName() {
		if(this.taskname!=null) {
			String tableName = getUserName() +"_"+this.taskname;
			return tableName;
		} else if(this.id!=null) {
			TaskInfo info= taskInfoDaoImpl.getById(id);
			this.setTaskname(info.getTaskname());
			String tableName = getUserName() +"_"+this.taskname;
			return tableName;
		}
		return null;
		
		
	}
	
	/**
	 * 文件下载方法
	 * @return
	 */
	public String deleteTask() {
		return "success";
	}
	
	/**
	 * 获取下载的文件流
	 * @return
	 */
	public InputStream getCsvInputStream() {
		FileInputStream fis = null;
		String filename = "D:\\QMDownload\\"+ getTableName()+".csv";
		File file = new File(filename);
		if(file.exists()) {
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fis;
	}
	
	/**
	 * 向用户传输下载的文件名
	 * @return
	 */
	public String getDownFileName() throws UnsupportedEncodingException {
		String downFileName = this.taskname+".csv";
		downFileName = URLEncoder.encode(downFileName, "UTF-8");
		System.out.println("getDownFileName");
		return downFileName;
	}
	
	/**
	 * 下载文件方法
	 * @return
	 */
	public String  downloadCsv() {
		String filename = "D:\\QMDownload\\"+getTableName()+".csv";
		File file = new File(filename);
		if(file.exists()) {
			return "success";
		}
		else {
			this.setStatusString("此任务目前尚无文件产生");
			this.setId(id);
			return "fail";
		}
		
	}
	
	/**
	 * 根据前端传回来的ID，获取指定爬虫任务详情
	 * @return 
	 */
	public String detail() {
		TaskInfo info = taskInfoDaoImpl.getById(this.id);
		TaskStatus status = taskStatusDaoImpl.getByTaskId(this.id);
		this.setTaskInfo(info);
		this.setStatusString("初始化");
		if(status!=null) {
			switch(status.getStatus()) {
				case 1:
					this.setStatusString("正在运行");
					break;
				case 0:
					this.setStatusString("未运行");
					break;
			}
		}
		return "success";
	}
	
	public String deleteScrapy() {
		TaskInfo info = taskInfoDaoImpl.getById(this.id);
		TaskStatus status = taskStatusDaoImpl.getByTaskId(this.id);
		String tablename = getTableName();
		if(status.getStatus()==1) {
			this.setStatusString("此爬虫正在运行，请停止运行后删除");
			return "fail";
		}
		else {	
			taskStatusDaoImpl.delete(status);
			taskInfoDaoImpl.delete(info);
			try {
				deleteTable(tablename);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "success";
		}
	}
	
	/**
	 * 分页获取爬虫任务
	 * @return
	 */
	public String pagging() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users)session.getAttribute("loginUser");
		if(this.page==null) {
			this.prevPage=1;
			this.nextPage=2;
			List<TaskInfo> taskinfos = taskInfoDaoImpl.getByUserId(1, 5, users);
			//根据任务详情获取爬虫状态
			Map<Integer, String> statusMaps = new HashMap<Integer, String>();
			for (TaskInfo taskInfo : taskinfos) {
				TaskStatus status = taskStatusDaoImpl.getByTaskId(taskInfo.getId());
				switch(status.getStatus()) {
				case 1:
					statusMaps.put(taskInfo.getId(),"正在运行");
					break;
				case 0:
					statusMaps.put(taskInfo.getId(),"未运行");
					break;
				}
				
			}
			this.setStatusMap(statusMaps);
			this.setList(taskinfos);
			return "success";
		}
		else {
			if(this.page==1) {
				this.prevPage=1;
			} else {
				this.prevPage=this.page-1;
			}
			this.nextPage = this.page+1;
			List<TaskInfo> taskinfos = taskInfoDaoImpl.getByUserId(this.page,5, users);
			Map<Integer, String> statusMaps = new HashMap<Integer, String>();
			for (TaskInfo taskInfo : taskinfos) {
				TaskStatus status = taskStatusDaoImpl.getByTaskId(taskInfo.getId());
				switch(status.getStatus()) {
				case 1:
					statusMaps.put(taskInfo.getId(),"正在运行");
					break;
				case 0:
					statusMaps.put(taskInfo.getId(),"未运行");
					break;
				}
			}
			this.setStatusMap(statusMaps);
			this.setList(taskinfos);
			return "success";
		}
	}
	
	/**
	 * 控制爬虫的启动与停止
	 * @return
	 */
	public String control() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users users = (Users)session.getAttribute("loginUser");
		if(this.id!=0) {
			if(this.statusString.equals("未运行")) {
				TaskInfo info = taskInfoDaoImpl.getById(id);
				String tablename = users.getUsername()+"_"+info.getTaskname();
				try {
					deleteTable(tablename);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				info.setUrl(url);
				info.setTaskname(taskname);
				info.setRulecontent(rulecontent);
				taskInfoDaoImpl.update(info);
				JSONObject message = new JSONObject();
				message.put("method", "start");
				message.put("taskid", id);
				scPro.send(message);
				this.setStatusString("启动命令已发送");
				System.out.println("启动命令已发送");
				
			}
			else if(this.statusString.equals("正在运行")) {
				JSONObject message = new JSONObject();
				message.put("method", "stop");
				message.put("taskid", id);
				scPro.send(message);
				this.setStatusString("停止命令已发送");
				System.out.println("停止命令已发送");
			}
		}
		
		else {
			TaskInfo info = new TaskInfo();
			info.setHostname("localhost");
			info.setRulecontent(this.rulecontent);
			info.setRulename(this.taskname+".xml");
			info.setUrl(this.url);
			info.setTaskname(this.taskname);
			info.setUsers(users);
			String uuid =  UUID.randomUUID().toString().replaceAll("-", "");;
			info.setUuid(uuid);
			this.taskInfoDaoImpl.inesrt(info);
			TaskInfo taskinfo = taskInfoDaoImpl.getByUuid(uuid);
			this.setId(taskinfo.getId());
			System.out.println(taskinfo.getUuid());
			TaskStatus taskStatus = new TaskStatus();
			taskStatus.setStatus(0);
			taskStatus.setPid(0);
			taskStatus.setTaskInfo(taskinfo);
			this.taskStatusDaoImpl.inesrt(taskStatus);
			JSONObject message = new JSONObject();
			message.put("method", "start");
			message.put("taskid", taskinfo.getId());
			scPro.send(message);
			this.setStatusString("启动命令已发送");
			System.out.println("启动命令已发送");
		}
		return "success";
	}
	
	/**
	 * 预览爬虫任务产生的数据
	 * @return
	 */
	public String preview() {
		String tableName = getTableName();
		try {
			List<List<String>> datasList = getData(tableName);
			if(datasList!=null) {
				this.setPreviewDatas(datasList);
				return "success";
			} else {
				this.setStatusString("此爬虫目前尚无数据产生");
				this.setId(id);
				return "fail";
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 *  再次启动爬虫时，用来删除之前爬虫任务产生的表，以免字段变更后产生错误
	 * @param tableName 需要删除的表名
	 */
	public void deleteTable(String tableName) throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/scrapy?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false";
		String user = "root";
		String password = "000000";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		if(!conn.isClosed()) {
			System.out.println("数据库连接成功"); 
		} else {
			System.out.println("数据库连接失败");
		}
		Statement statement = conn.createStatement();
		try {
			statement.execute("drop table "+tableName);
		} catch (java.sql.SQLSyntaxErrorException e) {
			// TODO: handle exception
			System.out.println("此任务尚未产生表");
		}
		statement.close();
		conn.close();
	}
	
	/**
	 * 根据表名称获取表的前100条记录，此方法主要提供用户数据预览功能
	 * @param tableName 需要获取数据的表名称
	 * @return
	 */
	public List<List<String>> getData(String tableName) throws ClassNotFoundException, SQLException{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/scrapy?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false";
		String user = "root";
		String password = "000000";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		if(!conn.isClosed()) {
			System.out.println("数据库连接成功"); 
		} else {
			System.out.println("数据库连接失败");
		}
		Statement statement = conn.createStatement();
		try {
			ResultSet results = statement.executeQuery("SELECT * FROM "+tableName+" limit 100");
			ResultSetMetaData metadata = results.getMetaData();
			List<String> columnNames = new ArrayList<String>();
			List<List<String>> datas = new ArrayList<List<String>>(); 
			for (int i = 1; i <= metadata.getColumnCount(); i++) {
				columnNames.add(metadata.getColumnName(i));
			}
			datas.add(columnNames);
			while(results.next()) {
				List<String> lines = new ArrayList<String>();
				for(String colunmString:columnNames) {
					lines.add(results.getString(colunmString));
				}
				datas.add(lines);
			}
			statement.close();
			conn.close();
			return datas;
		} catch (java.sql.SQLSyntaxErrorException e) {
			// TODO: handle exception
			System.out.println("此表不存在");
			return null;
		}
	}

}
