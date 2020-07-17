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

	//注册方法
	public NoteResult register(String name, String password, String nick) throws NoSuchAlgorithmException {
		
		NoteResult result=new NoteResult();
		System.out.println("registerGet:"+name);
		
		User checkuser=userDao.findByName(name);
		System.out.println("object:"+checkuser);
		
	    if(checkuser!=null){
	    	System.out.println("用户名已存在！！！");
	    	result.setStatus(1);
			result.setMsg("用户名已存在！");
			return result;
	    }
	    
			User user=new User();
			//user.setCn_user_id("11");
			user.setCn_user_id(NoteUtil.createId()); //调用静态方法，用类名.方法名
			user.setCn_user_name(name);
			user.setCn_user_password(NoteUtil.md5(password));
			user.setCn_user_nick(nick);
			userDao.save(user);
			
			result.setStatus(0); //状态：0成功 1失败
			result.setMsg("注册成功");
			
	     	return result;
	}

	//登录方法
	public NoteResult checkLogin(String name, String pwd) throws NoSuchAlgorithmException {
		
		NoteResult result=new NoteResult();
		
		User user=userDao.findByName(name);
		System.out.println("LoginGet:"+user);
		if(user==null){//用户名不存在
			result.setStatus(1);
			result.setMsg("用户不存在");
		}else{ //用户名存在，还需检验密码
			String pwd_md5=NoteUtil.md5(pwd);
			if(!user.getCn_user_password().equals(pwd_md5)){ //用户名存在，密码不对
				result.setStatus(2);
				result.setMsg("密码不正确");
			}else{ //用户名存在，密码正确
				result.setStatus(0);
				result.setMsg("账号和密码都正确");
				//登录成功后，把当前登录者的id记录下来
				result.setData(user.getCn_user_id());
			}
		}
		return result;
		
		
	}

	//修改密码
	public NoteResult changePwd(String userId,String lastpwd, String newpwd) throws NoSuchAlgorithmException {
		
		NoteResult result=new NoteResult();
		User user=userDao.findById(userId);
		String lastPwd=user.getCn_user_password(); //获取原数据库密码（加密后）
		System.out.println("原密码："+lastPwd);
		String iolastpwd=NoteUtil.md5(lastpwd); //对输入的原密码加密
		System.out.println("输入原密码："+iolastpwd);
		String md5newpwd=NoteUtil.md5(newpwd); //对新密码加密
		if(iolastpwd.equals(lastPwd)){
			if(md5newpwd.equals(iolastpwd)){
				result.setStatus(1);
				result.setMsg("新密码重复");
			}else{
			user.setCn_user_password(md5newpwd);
			userDao.changePassword(user);
			result.setStatus(0);
			result.setMsg("修改密码成功");
			//result.setData(lastPwd);
			}
		}else{
			result.setStatus(2);
			result.setMsg("原密码输入错误"); 
		}
		return result;
	}
}
