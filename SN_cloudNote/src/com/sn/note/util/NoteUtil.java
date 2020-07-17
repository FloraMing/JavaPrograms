package com.sn.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

//随机创建id，并加密密码
public class NoteUtil { 
	
	//md5+base64——加密算法（密码）
	public static String md5(String msg) throws NoSuchAlgorithmException{
		//信息处理msg
		MessageDigest md=MessageDigest.getInstance("MD5");
		//msg转换位字节数组
		byte[] in=msg.getBytes();
		byte[] out=md.digest(in);
		//利用md5将out加密位字符串，并且防止乱码
		String message=Base64.encodeBase64String(out);
		return message;
	}
   
	//随机生成id;
	public static String createId(){  
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
		System.out.println(md5("1"));
	}
	
}
