package com.sn.note.service;


import com.sn.note.entity.NoteResult;


public interface ActivityService {
	
	
	//1.��ʾ�ҳ����Ϣ
	public NoteResult getAllActivity();

	//2.��ʾָ����Ļ�ʼ��б�
	public NoteResult getActivityNotes(String activityid);

	//3.��ʾ��ʼǵ���ϸ����
	public NoteResult getActivityNotesDetail(String activityNoteId);
}
