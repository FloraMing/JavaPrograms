package com.sn.note.controller.notes;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class LoadNotesController {

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/loadnotes.do")
	@ResponseBody //��������˸�ע�⣬���ȡ��/note/loadnotes.html
	public NoteResult execute(String bookId){
		NoteResult result=noteService.loadNotes(bookId);
		return result;
	}
}
