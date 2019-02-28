package com.njci.student.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.njci.student.bean.Users;
import com.njci.student.dao.UsersDao;
import com.njci.student.util.HibernateSessionFactory;

public class UsersDaoImpl extends HibernateDaoSupport implements UsersDao {
	
	@Override
	public void inesrt(Users entity) {
		getHibernateTemplate().save(entity);
		getHibernateTemplate().flush();
	}

	@Override
	public void delete(Users entity) {
		getHibernateTemplate().delete(entity);		
	}

	@Override
	public void update(Users entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public Users getById(Integer id) {
		return (Users)getHibernateTemplate().get(Users.class, id);
	}

	@Override
	public Users getByNameAndPwd(String username, String pwd) {
		Session session = null;
		Users users = null;
		try {
			session = HibernateSessionFactory.getSession();
			String hqlString = "from Users where username=:username and pwd=:pwd";
			Query query = session.createQuery(hqlString);
			query.setString("username", username);
			query.setString("pwd", pwd);
			users = (Users)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	@Override
	public Users getByName(String username) {
		Session session = null;
		Users users = null;
		try {
			session = HibernateSessionFactory.getSession();
			String hqlString = "from Users where username=:username";
			Query query = session.createQuery(hqlString);
			query.setString("username", username);
			users = (Users)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}
	
}
