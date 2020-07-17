package com.sn.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sn.note.dao.ActivityDao;
import com.sn.note.entity.Activity;
import com.sn.note.entity.ActivityNote;
import com.sn.note.entity.NoteResult;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	    @Resource
	    private ActivityDao activityDao;  
	  
	     // 1.��ȡ��б�  
	    public NoteResult getAllActivity(){  
	    	NoteResult result=new NoteResult();
	        List<Activity> list = activityDao.getAllActivityList();//��ȡ���л�б�  
	        result.setStatus(0);
			result.setMsg("�ɹ���û");
			result.setData(list);
			return result;
	    }

		//2.��ȡָ����²μӻ�ıʼ��б�
		public NoteResult getActivityNotes(String activityid) {
			NoteResult result=new NoteResult();
	        List<ActivityNote> list = this.activityDao.getAllActivityNoteList(activityid);//��ȡָ����µ����бʼ�  
	        result.setStatus(0);
			result.setMsg("�ɹ���û�ʼ��б�");
			result.setData(list);
			return result; 
		}

		//3.��ʾ��ʼǵľ�������
		public NoteResult getActivityNotesDetail(String activityNoteId) {
			NoteResult result=new NoteResult();
			ActivityNote activityNote =activityDao.findActivityNoteById(activityNoteId);
			result.setStatus(0);
			result.setMsg("�ɹ���û�ʼ�����");
			result.setData(activityNote);
			return result; 
		}

		
	      
	  
	   
}
