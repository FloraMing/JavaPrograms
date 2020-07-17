package com.sn.note.controller.notebook;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteBookService;

@Controller
@RequestMapping("/notebook")
public class AddBooksController {

	@Resource
	private NoteBookService noteBookService;
	
	@RequestMapping("/addnotebook.do")
	@ResponseBody 
	public NoteResult execute(String bookName,String userId){ //是否需要参数看前台页面是否需要后台的数据
		
		NoteResult result=noteBookService.addNoteBooks(bookName,userId);
		return result;
	}
}
