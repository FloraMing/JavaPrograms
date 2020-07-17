package com.sn.note.service;

import com.sn.note.entity.NoteResult;

public interface NoteService {

	//加载笔记
	public NoteResult loadNotes(String bookId);

	//添加笔记
	public NoteResult addNotes(String noteName, String bookId, String userId);

	//根据笔记id查找笔记（编辑器出对应文本内容）
	public NoteResult findNoteByNoteId(String noteId);

	//编辑笔记内容保存
	public NoteResult saveNote(String noteId, String noteTitle, String noteBody);

	//删除笔记
	public NoteResult deleteNote(String noteId);

	//移动笔记
	public NoteResult moveNote(String noteId,String bookId);

	//分享笔记
	public NoteResult shareNote(String noteId);

	//搜索分享笔记
	public NoteResult findShareTitle(String value);

	//显示搜索分享笔记的内容
	public NoteResult showShareNote(String shareId);

	//处理回收站笔记
	public NoteResult rollBackNote(String userId);

	//从回收站恢复笔记
	public NoteResult replayNote(String bookId, String noteId);

	//从回收站彻底删除笔记
	public NoteResult sureDelete(String noteId);

}
