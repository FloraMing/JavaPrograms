package com.sn.note.dao;

import java.util.List;
import java.util.Map;

import com.sn.note.entity.Activity;
import com.sn.note.entity.ActivityNote;
import com.sn.note.entity.Note;

public interface ActivityDao {

	
	
	 //��ȡָ���û��Ļ�ʼǱ��ıʼ��б�   
    public List<Note> getActionNoteListById(String notebookId);   
    
    //��ȡָ���û��Ļ�ʼǱ��ıʼ��б�   
    public List<Note> getActionNoteListByCode(String cnNotebookTypeCode);  
    
    //1.��ȡ���л�б�   
    public List<Activity> getAllActivityList();

    //2.��ȡָ����µ����бʼ�
	public List<ActivityNote> getAllActivityNoteList(String activityid);

	//3.���ݲμӻ�ʼǵ�id���Ҳμӻ�ʼ�
	public ActivityNote findActivityNoteById(String activityNoteId);   
    
       
    //��ָ���û���ָ���ʼǽ��л   
    
 //   public void activityNote(NoteActivity noteActivity);  
    
    //   
//    public NoteActivity findNoteActivityById(String noteActivityId);  
}
