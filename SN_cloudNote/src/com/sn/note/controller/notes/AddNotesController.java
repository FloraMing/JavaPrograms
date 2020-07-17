package com.sn.note.controller.notes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteBookService;
import com.sn.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class AddNotesController {

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/addnotes.do")
	@ResponseBody 
	public NoteResult execute(String noteName,String bookId,String userId){ //是否需要参数看前台页面是否需要后台的数据
		
		NoteResult result=noteService.addNotes(noteName,bookId,userId);
		return result;
	}
}
