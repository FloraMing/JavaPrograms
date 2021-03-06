package com.sn.note.controller.notes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class saveNoteController {

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/savenote.do")
	@ResponseBody 
	public NoteResult execute(String noteId,String noteTitle,String noteBody){ //是否需要参数看前台页面是否需要后台的数据
		
		NoteResult result=noteService.saveNote(noteId,noteTitle,noteBody);
		return result;
	}
}
