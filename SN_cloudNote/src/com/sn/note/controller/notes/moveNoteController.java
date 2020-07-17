package com.sn.note.controller.notes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class moveNoteController {

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/movenote.do")
	@ResponseBody 
	public NoteResult execute(String noteId,String bookId){
		NoteResult result=noteService.moveNote(noteId,bookId);
		return result;
	}
}
