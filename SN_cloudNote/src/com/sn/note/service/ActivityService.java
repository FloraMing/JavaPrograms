package com.sn.note.service;


import com.sn.note.entity.NoteResult;


public interface ActivityService {
	
	
	//1.显示活动页面信息
	public NoteResult getAllActivity();

	//2.显示指定活动的活动笔记列表
	public NoteResult getActivityNotes(String activityid);

	//3.显示活动笔记的详细内容
	public NoteResult getActivityNotesDetail(String activityNoteId);
}
