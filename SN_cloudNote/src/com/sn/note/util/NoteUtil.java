package com.sn.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

//�������id������������
public class NoteUtil { 
	
	//md5+base64���������㷨�����룩
	public static String md5(String msg) throws NoSuchAlgorithmException{
		//��Ϣ����msg
		MessageDigest md=MessageDigest.getInstance("MD5");
		//msgת��λ�ֽ�����
		byte[] in=msg.getBytes();
		byte[] out=md.digest(in);
		//����md5��out����λ�ַ��������ҷ�ֹ����
		String message=Base64.encodeBase64String(out);
		return message;
	}
   
	//�������id;
	public static String createId(){  
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
		System.out.println(md5("1"));
	}
	
}
