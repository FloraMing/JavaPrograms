package com.sn.note.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sn.note.dao.UserDao;
import com.sn.note.entity.NoteResult;
import com.sn.note.entity.User;
import com.sn.note.util.NoteUtil;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	//ע�᷽��
	public NoteResult register(String name, String password, String nick) throws NoSuchAlgorithmException {
		
		NoteResult result=new NoteResult();
		System.out.println("registerGet:"+name);
		
		User checkuser=userDao.findByName(name);
		System.out.println("object:"+checkuser);
		
	    if(checkuser!=null){
	    	System.out.println("�û����Ѵ��ڣ�����");
	    	result.setStatus(1);
			result.setMsg("�û����Ѵ��ڣ�");
			return result;
	    }
	    
			User user=new User();
			//user.setCn_user_id("11");
			user.setCn_user_id(NoteUtil.createId()); //���þ�̬������������.������
			user.setCn_user_name(name);
			user.setCn_user_password(NoteUtil.md5(password));
			user.setCn_user_nick(nick);
			userDao.save(user);
			
			result.setStatus(0); //״̬��0�ɹ� 1ʧ��
			result.setMsg("ע��ɹ�");
			
	     	return result;
	}

	//��¼����
	public NoteResult checkLogin(String name, String pwd) throws NoSuchAlgorithmException {
		
		NoteResult result=new NoteResult();
		
		User user=userDao.findByName(name);
		System.out.println("LoginGet:"+user);
		if(user==null){//�û���������
			result.setStatus(1);
			result.setMsg("�û�������");
		}else{ //�û������ڣ������������
			String pwd_md5=NoteUtil.md5(pwd);
			if(!user.getCn_user_password().equals(pwd_md5)){ //�û������ڣ����벻��
				result.setStatus(2);
				result.setMsg("���벻��ȷ");
			}else{ //�û������ڣ�������ȷ
				result.setStatus(0);
				result.setMsg("�˺ź����붼��ȷ");
				//��¼�ɹ��󣬰ѵ�ǰ��¼�ߵ�id��¼����
				result.setData(user.getCn_user_id());
			}
		}
		return result;
		
		
	}

	//�޸�����
	public NoteResult changePwd(String userId,String lastpwd, String newpwd) throws NoSuchAlgorithmException {
		
		NoteResult result=new NoteResult();
		User user=userDao.findById(userId);
		String lastPwd=user.getCn_user_password(); //��ȡԭ���ݿ����루���ܺ�
		System.out.println("ԭ���룺"+lastPwd);
		String iolastpwd=NoteUtil.md5(lastpwd); //�������ԭ�������
		System.out.println("����ԭ���룺"+iolastpwd);
		String md5newpwd=NoteUtil.md5(newpwd); //�����������
		if(iolastpwd.equals(lastPwd)){
			if(md5newpwd.equals(iolastpwd)){
				result.setStatus(1);
				result.setMsg("�������ظ�");
			}else{
			user.setCn_user_password(md5newpwd);
			userDao.changePassword(user);
			result.setStatus(0);
			result.setMsg("�޸�����ɹ�");
			//result.setData(lastPwd);
			}
		}else{
			result.setStatus(2);
			result.setMsg("ԭ�����������"); 
		}
		return result;
	}
}
