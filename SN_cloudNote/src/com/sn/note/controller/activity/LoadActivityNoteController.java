package com.sn.note.controller.activity;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.ActivityService;
//��ѯָ����²μӻ�ıʼ��б�

@Controller 
@RequestMapping("/activity")
public class LoadActivityNoteController {

	@Resource
	private ActivityService activityservice;
	
	@RequestMapping("/getActivityNote.do")
	@ResponseBody
	public NoteResult getActivityNotes(String activityid) {
		NoteResult result=new NoteResult();
		result=activityservice.getActivityNotes(activityid);
		System.out.println("��û�ʼ�");
		return result;
	}
}
