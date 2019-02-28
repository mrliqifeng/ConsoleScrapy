package com.njci.student.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.logicalcobwebs.concurrent.Takable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.njci.student.bean.TaskInfo;
import com.njci.student.bean.TaskStatus;
import com.njci.student.bean.Users;
import com.njci.student.dao.TaskInfoDao;
import com.njci.student.dao.TaskStatusDao;
import com.njci.student.dao.UsersDao;
import com.njci.student.dao.impl.UsersDaoImpl;

@ContextConfiguration(locations = "classpath:Context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDaoImplTest {
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private TaskInfoDao taskInfoDao;
	
	@Autowired
	private TaskStatusDao taskStatusDao;
	
	@Test
	@Rollback(false)
	public void testInsrt() {
//		Users users = new Users();
//		users.setId(20);
//		List<TaskInfo> infos = taskInfoDao.getByUserId(0, 3, users);
//		for(TaskInfo info:infos) {
//			System.out.println(info.getHostname());
//		}
		TaskInfo info = new TaskInfo();
		info.setId(1);
		TaskStatus status = taskStatusDao.getByTaskId(info.getId());
		System.out.println(status.getPid());
		
	} 
}
