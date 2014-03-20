/**
 * 这是与好友聊天的界面
 * 因为客户端，要处于读取的状态，因此我们把它做成一个线程
 */
package com.qq.client.view;

import com.qq.client.tools.*;

import com.qq.client.model.*;
import com.qq.common.*;
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class QqChat extends JFrame implements ActionListener{

	private JTextArea jta;
	
	//public static JTextField jtf; 大牛写的 已淘汰
	public static JTextArea jtf;  //3-20修改
	JButton jb;//sendButton
	JButton jb1;//自己添加第一次 公式按钮
	JPanel jp;
	String ownerId;
	String friendId;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqChat qqChat=new QqChat("1");
//		try {
//			String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//			UIManager.setLookAndFeel(lookAndFeel);
//		} catch (Exception e) {
//		
//		}
	}
	
	/*
	public  JTextField getTextFiled()
	{
		return this.jtf;
	}
	*/
	
	//3-20修改
	public JTextArea getJTextArea()
	{
		return this.jtf;
	}
	
	public QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		jta=new JTextArea();
		jta.setEditable(false);  //3-20 聊天内容文本域禁止编辑
		//jtf=new JTextField(30);  3-20 删除
		jtf=new JTextArea(3,35);  //3-20
		jb=new JButton("发送");
		jb.addActionListener(this);
		
		jb1=new JButton("公式");  //自己添加第一次
		jb1.addActionListener(this);
		
		jp=new JPanel();
		
		jp.add(jtf);
		
		jp.add(jb);
		
		jp.add(jb1);  //自己添加第一次
		
		//this.add(jta,"North");
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+" 正在和 "+friend+" 聊天");
		this.setIconImage((new ImageIcon("image/ydy.png").getImage()));
		this.setSize(500, 300);
		this.setLocation(300, 200); //3-18修改  放置位置 
		this.setVisible(true);
		
		
	}
	
	//写一个方法，让它显示消息
	public void showMessage(Message m)
	{
		String info=m.getSender()+" 对 "+m.getGetter()+" 说         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
		this.jta.append(info);
		
	}
	
	

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			//如果用户点击了，发送按钮
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			jtf.setText(null); //3-20 添加 解决了发送之后
			m.setSendTime(new java.util.Date().toString());
			
			//在自己的聊天界面显示发送的信息
			String infomy=m.getSender()+" 对 "+m.getGetter()+" 说         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
			this.jta.append(infomy);
			
			//发送给服务器.
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
				
		}
		
		
		//自己添加第二次  3-17
		else if(arg0.getSource()==jb1){
			//如果用户点击了公式按钮
              new TermEditDialog();         		
			
		}	
		
		
		

	} 

//	public void run() {
//		// TODO Auto-generated method stub
//		while(true)
//		{
//			try {
//				//读取[如果读不到就等待.]
//				ObjectInputStream ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				
//				Message m=(Message)ois.readObject();
//				
//				//显示
//				String info=m.getSender()+" 对 "+m.getGetter()+" 说:"+m.getCon()+"\r\n";
//				this.jta.append(info);
//				
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				// TODO: handle exception
//			}
//		
//			
//			
//		}
//		
//	}

}
