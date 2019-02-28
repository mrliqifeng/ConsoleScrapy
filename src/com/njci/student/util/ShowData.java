package com.njci.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowData {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
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
			statement.execute("drop table a_dasd");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("此任务尚未产生表");
		}
		statement.close();
		conn.close();
		
	}

}
