package com.njci.student.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.njci.student.bean.TaskInfo;
import com.njci.student.bean.Users;
import com.njci.student.dao.TaskInfoDao;
import com.njci.student.util.HibernateSessionFactory;

public class TaskInfoDaoImpl extends HibernateDaoSupport implements TaskInfoDao {

	@Override
	public void inesrt(TaskInfo entity) {
		getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(TaskInfo entity) {
		getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void update(TaskInfo entity) {
		getHibernateTemplate().update(entity);
		
	}

	@Override
	public TaskInfo getById(Integer id) {
		// TODO Auto-generated method stub
		return (TaskInfo)getHibernateTemplate().get(TaskInfo.class, id);
	}

	@Override
	public List<TaskInfo> getByUserId(Integer pageIndex, Integer pageSize, Users users) {
		// TODO Auto-generated method stub`
		String hql = "from TaskInfo as o where o.users.id =:id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", users.getId());
		return findByParamsHqlPage(hql, pageIndex, pageSize, param);
	}
	
	public List findByParamsHqlPage(final String hql, final Integer pageIndex, final Integer pageSize, final Map<String, Object> param ){
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				
				Query query = session.createQuery(hql);
				query.setFirstResult((pageIndex-1)*pageSize);
				query.setMaxResults(pageSize);
				if(param!=null) {
					for(String key:param.keySet()) {
						query.setParameter(key, param.get(key));
					}
				}
				return query.list();
			}
		});
		return list;
	}

	@Override
	public TaskInfo getByUuid(String uuid) {
		Session session = null;
		TaskInfo info = null;
		try {
			session = HibernateSessionFactory.getSession();
			String hqlString = "from TaskInfo where uuid=:uuid";
			Query query = session.createQuery(hqlString);
			query.setString("uuid", uuid);
			info = (TaskInfo)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return info;
	}
	
	

}
