package com.sn.note.service;

import java.security.NoSuchAlgorithmException;

import com.sn.note.entity.NoteResult;

public interface UserService {

	//ע�᷽��
	public NoteResult register(String name, String password, String nick) throws NoSuchAlgorithmException;

	//��¼����
	public NoteResult checkLogin(String name, String pwd) throws NoSuchAlgorithmException;

	//�޸�����
	public NoteResult changePwd(String userId,String lastpwd, String newpwd) throws NoSuchAlgorithmException;

}
