package com.sn.note.dao;

import com.sn.note.entity.User;

public interface UserDao {
	
	//注册用户
	public void save(User user);
	//检查注册时用户名是否存在（避免用户名重复）及登录检查
	public User findByName(String name);
	
	public User findById(String userId);
	
	public void changePassword(User user);
}
