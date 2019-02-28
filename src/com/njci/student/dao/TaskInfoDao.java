package com.njci.student.dao;

import java.util.List;

import com.njci.student.bean.TaskInfo;
import com.njci.student.bean.Users;

public interface TaskInfoDao {
	public void inesrt(TaskInfo entity);

	public void delete(TaskInfo entity);
	
	public void update(TaskInfo entity);
	public TaskInfo getById(Integer id);
	
	public TaskInfo getByUuid(String uuid);
	
	public List<TaskInfo> getByUserId(Integer pageIndex, Integer pageSize, Users users);

}
