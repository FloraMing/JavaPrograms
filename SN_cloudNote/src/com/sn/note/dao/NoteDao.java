package com.sn.note.dao;

import java.util.List;
import java.util.Map;

import com.sn.note.entity.Note;

public interface NoteDao {

	//加载笔记列表
	public List<Note> findByBookId(String bookId);

	//查找笔记名，避免添加笔记时笔记名重复
	public Note findNoteByNoteName(String noteName);
	
	//添加笔记
	public void addNotes(Note note);

	//查找笔记id，编辑笔记
	public Note findNoteByNoteId(String noteId);

	//修改笔记
	public void updateNote(Note note);

	//删除笔记（实际放入回收站）
	public void deleteNote(String noteId);

	//移动笔记（）
	public void dynamicUpdate(Note note);

	//回收站笔记
	public List<Note> rollBackNote(String userId);

	//从回收站恢复笔记
	public void replayNote(Note note);

	//从回收站彻底删除笔记
	public void deleteNotes(String[] strings);

	


	

}
