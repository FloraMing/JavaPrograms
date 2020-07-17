package com.sn.note.dao;

import java.util.List;
import java.util.Map;

import com.sn.note.entity.Share;


public interface ShareDao {
	//���ʼǲ��뵽share��
	public void shareSave(Share share);
	
	//��ѯshare�����Ƿ��Ѿ�����
	public Share findShareByNoteId(String id);

	//��������ʼ�
	public List<Share> findShareTitle(String value);

	//�鿴����ʼ�����
	public Map showShareNote(String shareId);
	

}
 







