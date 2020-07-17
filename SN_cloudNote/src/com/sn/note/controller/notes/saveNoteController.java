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
	public NoteResult execute(String noteId,String noteTitle,String noteBody){ //�Ƿ���Ҫ������ǰ̨ҳ���Ƿ���Ҫ��̨������
		
		NoteResult result=noteService.saveNote(noteId,noteTitle,noteBody);
		return result;
	}
}
