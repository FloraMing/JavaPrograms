package com.sn.note.controller.notes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class shareNoteController {

	@Resource
	private NoteService noteService;

	@RequestMapping("sharenote.do")
	@ResponseBody
	public NoteResult execute(String noteId) {
		NoteResult result = noteService.shareNote(noteId);
		return result;
	}
	
}
