package com.sn.note.dao;

import java.util.List;
import java.util.Map;

import com.sn.note.entity.Share;


public interface ShareDao {
	//将笔记插入到share表
	public void shareSave(Share share);
	
	//查询share表中是否已经存在
	public Share findShareByNoteId(String id);

	//搜索分享笔记
	public List<Share> findShareTitle(String value);

	//查看分享笔记内容
	public Map showShareNote(String shareId);
	

}
 







