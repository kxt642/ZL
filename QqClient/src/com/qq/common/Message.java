package com.qq.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.StyledDocument;



public class Message implements java.io.Serializable{

	private String mesType;

	private String sender;
	private String getter;
	private String con;
	
	
	//private String msgContent;// 文本消息 4-28
	private List<File> imgs = new ArrayList<File>();// 图片消息 4-28
	
	private String sendTime;

	
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	//4-28可以注释
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	//4-28
	
	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
	
	

	/*
	//4-28
	public Message()
	{
		super();
	}
	
	//4-28
	public Message(String msgContent) {
		this.msgContent = msgContent;
	}
	
	//4-28
	public Message(String msgContent, List<File> imgs, 
			String sendTime, String sender,String getter) {
		super();
		this.msgContent = msgContent;
		this.imgs = imgs;
		this.sendTime = sendTime;
		this.sender = sender;
		this.getter=getter;
	}
	
	//4-28
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public List<File> getImgs() {
		return imgs;
	}
	public void setImgs(List<File> imgs) {
		this.imgs = imgs;
	}
	
	//4-28
	public boolean isEmpty() {
		boolean a = msgContent == null || msgContent.equals("");
		boolean b = imgs == null || imgs.size() == 0;
		if (a && b ) return true;
		return false;
	}
	public boolean isMsg() {
		if (isEmpty()) {
			if (sender != null && !"".equals(sender)) {
				return false;
			}
		}
		return true;
	}
	*/
}
