package com.sn.note.controller.activity;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.note.entity.NoteResult;
import com.sn.note.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class GetActivityController {
	@Resource
	private ActivityService activityservice;

	@RequestMapping("/getActionList.do")
	@ResponseBody
	public NoteResult getActionList() {
		NoteResult result = new NoteResult();
		result = activityservice.getAllActivity();
		System.out.println("获得活动");
		return result;
	}

}
