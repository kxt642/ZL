package com.qq.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Message implements java.io.Serializable{

	private String mesType;
	
	private String sender;
	private String getter;
	private String con;
	private String sendTime;

	private List<File> imgs = new ArrayList<File>();// ͼƬ��Ϣ 4-28
	
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

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

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
}
