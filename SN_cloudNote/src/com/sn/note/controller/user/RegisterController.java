package com.sn.note.controller.user;

import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sn.note.entity.NoteResult;
import com.sn.note.service.UserService;
//ע��controller

@Controller
@RequestMapping("/user") //��ӵĶ���·������������
public class RegisterController {

	@Resource
	private UserService service; 
	
	@RequestMapping("/register.do") //http://localhost:8081/SN_cloudNote/user/register.do
	@ResponseBody  //�����ص�����ת��ΪJSON
	public NoteResult execute(String name,String password,String nick) throws NoSuchAlgorithmException{ //ҳ�洫���Ĳ��� 
		NoteResult result=service.register(name,password,nick);
		System.out.println("controllerGet:"+name);
		return result;
	}
}
