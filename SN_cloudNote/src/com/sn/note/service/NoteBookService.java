package com.sn.note.service;

import com.sn.note.entity.NoteResult;

public interface NoteBookService {

	//加载笔记本列表
	public NoteResult loadBooks(String userId);
	
	//添加笔记本
	public NoteResult addNoteBooks(String bookName,String userId);

	//重命名笔记本
	public NoteResult renameBook(String bookName,String bookId);

	//删除笔记本
	public NoteResult deleteBook(String bookId);
}
