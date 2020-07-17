package com.sn.note.dao;

import java.util.List;
import java.util.Map;

import com.sn.note.entity.Activity;
import com.sn.note.entity.ActivityNote;
import com.sn.note.entity.Note;

public interface ActivityDao {

	
	
	 //获取指定用户的活动笔记本的笔记列表   
    public List<Note> getActionNoteListById(String notebookId);   
    
    //获取指定用户的活动笔记本的笔记列表   
    public List<Note> getActionNoteListByCode(String cnNotebookTypeCode);  
    
    //1.获取所有活动列表   
    public List<Activity> getAllActivityList();

    //2.获取指定活动下的所有笔记
	public List<ActivityNote> getAllActivityNoteList(String activityid);

	//3.根据参加活动笔记的id查找参加活动笔记
	public ActivityNote findActivityNoteById(String activityNoteId);   
    
       
    //将指定用户、指定笔记进行活动   
    
 //   public void activityNote(NoteActivity noteActivity);  
    
    //   
//    public NoteActivity findNoteActivityById(String noteActivityId);  
}
