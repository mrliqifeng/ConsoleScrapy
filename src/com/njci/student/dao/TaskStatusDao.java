package com.njci.student.dao;

import com.njci.student.bean.TaskInfo;
import com.njci.student.bean.TaskStatus;

public interface TaskStatusDao {
	public void inesrt(TaskStatus entity);

	public void delete(TaskStatus entity);
	
	public void update(TaskStatus entity);
	public TaskStatus getById(Integer id);
	
	public TaskStatus getByTaskId(Integer id);

}
