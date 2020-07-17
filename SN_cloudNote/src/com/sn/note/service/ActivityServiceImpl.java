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
	  
	     // 1.获取活动列表  
	    public NoteResult getAllActivity(){  
	    	NoteResult result=new NoteResult();
	        List<Activity> list = activityDao.getAllActivityList();//获取所有活动列表  
	        result.setStatus(0);
			result.setMsg("成功获得活动");
			result.setData(list);
			return result;
	    }

		//2.获取指定活动下参加活动的笔记列表
		public NoteResult getActivityNotes(String activityid) {
			NoteResult result=new NoteResult();
	        List<ActivityNote> list = this.activityDao.getAllActivityNoteList(activityid);//获取指定活动下的所有笔记  
	        result.setStatus(0);
			result.setMsg("成功获得活动笔记列表");
			result.setData(list);
			return result; 
		}

		//3.显示活动笔记的具体内容
		public NoteResult getActivityNotesDetail(String activityNoteId) {
			NoteResult result=new NoteResult();
			ActivityNote activityNote =activityDao.findActivityNoteById(activityNoteId);
			result.setStatus(0);
			result.setMsg("成功获得活动笔记详情");
			result.setData(activityNote);
			return result; 
		}

		
	      
	  
	   
}
