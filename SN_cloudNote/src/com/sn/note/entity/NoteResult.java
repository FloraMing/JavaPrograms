package com.sn.note.entity;

import java.io.Serializable;

//ʵ�����л��ӿڿ��Խ����ݶ�д��Ӳ�̻����
public class NoteResult implements Serializable{

	//��ǰ̨���ص���������
	private Object data; //����
	private int status; //״̬
	private String msg; //��Ϣ
	
	//����get��set����
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
