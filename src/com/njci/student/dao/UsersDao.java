package com.njci.student.dao;

import com.njci.student.bean.Users;

public interface UsersDao {
	public void inesrt(Users entity);

	public void delete(Users entity);
	
	public void update(Users entity);
	public Users getById(Integer id);
	public Users getByNameAndPwd(String username,String pwd);

	public Users getByName(String name);

}
