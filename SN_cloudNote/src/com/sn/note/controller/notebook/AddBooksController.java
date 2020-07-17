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
	public NoteResult execute(String bookName,String userId){ //�Ƿ���Ҫ������ǰ̨ҳ���Ƿ���Ҫ��̨������
		
		NoteResult result=noteBookService.addNoteBooks(bookName,userId);
		return result;
	}
}
