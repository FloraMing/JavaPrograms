package com.sn.note.service;

import java.security.NoSuchAlgorithmException;

import com.sn.note.entity.NoteResult;

public interface UserService {

	//注册方法
	public NoteResult register(String name, String password, String nick) throws NoSuchAlgorithmException;

	//登录方法
	public NoteResult checkLogin(String name, String pwd) throws NoSuchAlgorithmException;

	//修改密码
	public NoteResult changePwd(String userId,String lastpwd, String newpwd) throws NoSuchAlgorithmException;

}
