package com.sn.note.entity;

import java.io.Serializable;

//实现序列化接口可以将数据读写到硬盘或磁盘
public class NoteResult implements Serializable{

	//向前台返回的三个属性
	private Object data; //数据
	private int status; //状态
	private String msg; //消息
	
	//生成get、set方法
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
