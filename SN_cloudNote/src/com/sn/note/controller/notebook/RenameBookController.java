package com.sn.note.controller.notebook;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.NoteBookService;

@Controller
@RequestMapping("/notebook")
public class RenameBookController {

	@Resource
	private NoteBookService bookService;

	@RequestMapping("/updateNoteBookName.do")
	@ResponseBody
	public NoteResult execute(String bookName,String bookId){
		NoteResult result=bookService.renameBook(bookName,bookId);		
		return result;
	}
}
