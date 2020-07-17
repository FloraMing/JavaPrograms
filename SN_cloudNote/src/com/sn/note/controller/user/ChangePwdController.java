package com.sn.note.controller.user;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.UserService;

@Controller
@RequestMapping("/user")
public class ChangePwdController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/changePwd.do")
	@ResponseBody
	public NoteResult execute(String userId,String lastpwd,String newpwd) throws NoSuchAlgorithmException{
		NoteResult result=userService.changePwd(userId,lastpwd,newpwd);
		return result;
	}
}
