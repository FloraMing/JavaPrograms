package com.sn.note.dao;

import com.sn.note.entity.User;

public interface UserDao {
	
	//ע���û�
	public void save(User user);
	//���ע��ʱ�û����Ƿ���ڣ������û����ظ�������¼���
	public User findByName(String name);
	
	public User findById(String userId);
	
	public void changePassword(User user);
}
