package com.sn.note.controller.activity;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.ActivityService;

@Controller 
@RequestMapping("/activity")
public class getActivtiyNoteDetailController {
	@Resource
	private ActivityService activityservice;
	
	@RequestMapping("/getActivityNoteDetail.do")
	@ResponseBody
	public NoteResult getActivityNotesDetail(String activityNoteId) {
		NoteResult result=new NoteResult();
		result=activityservice.getActivityNotesDetail(activityNoteId);
		System.out.println("��û�ʼ�������Ϣ");
		return result;
	}
}
