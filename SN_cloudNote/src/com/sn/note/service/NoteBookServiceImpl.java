package com.sn.note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sn.note.dao.NoteBookDao;
import com.sn.note.dao.NoteDao;
import com.sn.note.entity.Note;
import com.sn.note.entity.NoteBook;
import com.sn.note.entity.NoteResult;
import com.sn.note.util.NoteUtil;

@Service
public class NoteBookServiceImpl implements NoteBookService {
	
	@Resource
	private NoteBookDao bookDao;
	
	@Resource
	private NoteDao noteDao;

	//加载笔记本
	public NoteResult loadBooks(String userId) {
		List<NoteBook> books=bookDao.findByUserId(userId);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("加载成功");
		result.setData(books);
		return result;
	}

	//添加笔记本
	public NoteResult addNoteBooks(String bookName, String userId) {
		NoteResult result=new NoteResult();
		NoteBook checkbook=bookDao.findByBookName(bookName);
		if(checkbook!=null){
			result.setStatus(1);
			result.setMsg("笔记本名已存在");
			return result;
		}
		NoteBook book=new NoteBook();
		String bookid=NoteUtil.createId();
		book.setCn_notebook_id(bookid);
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		book.setCn_notebook_type_id("4");
		book.setCn_notebook_desc("围城");
		book.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		
		bookDao.save(book);
		result.setStatus(0);
		result.setMsg("添加笔记本成功");
		result.setData(bookid);
		return result;
		
	}

	//重命名笔记本
	public NoteResult renameBook(String bookName,String bookId) {
		NoteBook book=new NoteBook();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(bookName);
		bookDao.renameBook(book);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("更改笔记本名字成功");
		return result;
	}

	//删除笔记本
	public NoteResult deleteBook(String bookId) {
		NoteResult result=new NoteResult();
		//先判断该笔记本下是否还有笔记，若由则不能删除笔记本
		List<Note> list=noteDao.findByBookId(bookId);
		if(list.isEmpty()){
			bookDao.deleteBook(bookId);
			result.setStatus(0);
			result.setMsg("删除笔记本成功");
			return result;
			
		}
		result.setStatus(1);
		result.setMsg("该笔记本下存在笔记，请先删除笔记");
		return result;
	}

	
}
