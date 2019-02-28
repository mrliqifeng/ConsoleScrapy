package com.njci.student.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.njci.student.bean.TaskInfo;
import com.njci.student.bean.TaskStatus;
import com.njci.student.dao.TaskStatusDao;

public class TaskStatusDaoImpl extends HibernateDaoSupport implements TaskStatusDao {

	@Override
	public void inesrt(TaskStatus entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(TaskStatus entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(TaskStatus entity) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(entity);
	}

	@Override
	public TaskStatus getById(Integer id) {
		// TODO Auto-generated method stub
		return (TaskStatus)getHibernateTemplate().get(TaskStatus.class, id);
	}

	@Override
	public TaskStatus getByTaskId(Integer id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<TaskStatus> taskstatus = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "from TaskStatus as t where t.taskInfo.id =:id";
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				return query.list();
			}
		});
		if(taskstatus!=null) {
			return taskstatus.get(0);
		}
		else {
			return null;
		}
	}
	
}
	

