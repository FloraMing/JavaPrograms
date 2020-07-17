package com.sn.note.controller.notes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class showShareNoteController {

	@Resource
	private NoteService noteService;

	@RequestMapping("showShareNote.do")
	@ResponseBody
	public NoteResult execute(String shareId) {
		NoteResult result = noteService.showShareNote(shareId);
		return result;
	}
}
