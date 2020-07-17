package com.sn.note.dao;

import java.util.List;

import com.sn.note.entity.NoteBook;

public interface NoteBookDao {

	//根据用户id查询当前用户的笔记本
	public List<NoteBook> findByUserId(String userId);
	
	//根据笔记本名查找笔记本是否已存在
	public NoteBook findByBookName(String bookName);
	
	//保存数据
	public void save(NoteBook book);

	//重命名笔记本
	public void renameBook(NoteBook book);

	//删除笔记本
	public void deleteBook(String bookId);

	
	
}
