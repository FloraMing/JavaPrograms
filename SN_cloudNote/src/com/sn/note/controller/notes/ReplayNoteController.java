package com.sn.note.controller.notes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class ReplayNoteController {

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/replayNote.do")
	@ResponseBody 
	public NoteResult execute(String bookId, String noteId){
		NoteResult result=noteService.replayNote(bookId,noteId);
		return result;
	}
}
