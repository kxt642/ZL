/**
 * �������������Ľ���
 * ��Ϊ�ͻ��ˣ�Ҫ���ڶ�ȡ��״̬��������ǰ�������һ���߳�
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
	
	//public static JTextField jtf; ��ţд�� ����̭
	public static JTextArea jtf;  //3-20�޸�
	JButton jb;//sendButton
	JButton jb1;//�Լ���ӵ�һ�� ��ʽ��ť
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
	
	//3-20�޸�
	public JTextArea getJTextArea()
	{
		return this.jtf;
	}
	
	public QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		jta=new JTextArea();
		jta.setEditable(false);  //3-20 ���������ı����ֹ�༭
		//jtf=new JTextField(30);  3-20 ɾ��
		jtf=new JTextArea(3,35);  //3-20
		jb=new JButton("����");
		jb.addActionListener(this);
		
		jb1=new JButton("��ʽ");  //�Լ���ӵ�һ��
		jb1.addActionListener(this);
		
		jp=new JPanel();
		
		jp.add(jtf);
		
		jp.add(jb);
		
		jp.add(jb1);  //�Լ���ӵ�һ��
		
		//this.add(jta,"North");
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+" ���ں� "+friend+" ����");
		this.setIconImage((new ImageIcon("image/ydy.png").getImage()));
		this.setSize(500, 300);
		this.setLocation(300, 200); //3-18�޸�  ����λ�� 
		this.setVisible(true);
		
		
	}
	
	//дһ��������������ʾ��Ϣ
	public void showMessage(Message m)
	{
		String info=m.getSender()+" �� "+m.getGetter()+" ˵         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
		this.jta.append(info);
		
	}
	
	

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			//����û�����ˣ����Ͱ�ť
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			jtf.setText(null); //3-20 ��� ����˷���֮��
			m.setSendTime(new java.util.Date().toString());
			
			//���Լ������������ʾ���͵���Ϣ
			String infomy=m.getSender()+" �� "+m.getGetter()+" ˵         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
			this.jta.append(infomy);
			
			//���͸�������.
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
				
		}
		
		
		//�Լ���ӵڶ���  3-17
		else if(arg0.getSource()==jb1){
			//����û�����˹�ʽ��ť
              new TermEditDialog();         		
			
		}	
		
		
		

	} 

//	public void run() {
//		// TODO Auto-generated method stub
//		while(true)
//		{
//			try {
//				//��ȡ[����������͵ȴ�.]
//				ObjectInputStream ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				
//				Message m=(Message)ois.readObject();
//				
//				//��ʾ
//				String info=m.getSender()+" �� "+m.getGetter()+" ˵:"+m.getCon()+"\r\n";
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
