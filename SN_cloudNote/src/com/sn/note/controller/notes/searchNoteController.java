package com.sn.note.controller.notes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class searchNoteController {

	@Resource
	private NoteService noteService;

	@RequestMapping("searchNote.do")
	@ResponseBody
	public NoteResult execute(String value) {
		NoteResult result = noteService.findShareTitle(value);
		return result;
	}
}
