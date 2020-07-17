package com.sn.note.controller.user;

import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sn.note.entity.NoteResult;
import com.sn.note.service.UserService;
//注册controller

@Controller
@RequestMapping("/user") //添加的二级路径，用于区分
public class RegisterController {

	@Resource
	private UserService service; 
	
	@RequestMapping("/register.do") //http://localhost:8081/SN_cloudNote/user/register.do
	@ResponseBody  //将返回的内容转换为JSON
	public NoteResult execute(String name,String password,String nick) throws NoSuchAlgorithmException{ //页面传来的参数 
		NoteResult result=service.register(name,password,nick);
		System.out.println("controllerGet:"+name);
		return result;
	}
}
