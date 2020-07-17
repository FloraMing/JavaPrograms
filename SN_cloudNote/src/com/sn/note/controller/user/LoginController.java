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
public class LoginController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult execute(String name,String pwd) throws NoSuchAlgorithmException{
		NoteResult  result=userService.checkLogin(name,pwd);
		System.out.println("loginController:"+ name);
		return result;
	}
}
