/**
 * 这是服务器端的控制界面，可以完成启动服务器，关闭服务器
 * 可以管理和监控用户.
 */
package com.qq.server.view;


import javax.swing.*;

import com.qq.server.model.MyQqServer;
import com.qq.server.model.MyQqServerClose;
import com.qq.server.model.ZLServer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class MyServerFrame extends JFrame implements ActionListener {

	
	JPanel jp1;
	JButton jb1,jb2;  //jb1:启动服务    jb2：关闭服务
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame mysf=new MyServerFrame();
	}
	
	public MyServerFrame()
	{
		jp1=new JPanel();
		jb1=new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
		jb2.addActionListener(this); //3-18修改
		jp1.add(jb1);
		jp1.add(jb2);
		
		
		this.add(jp1);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1)
		{
			new MyQqServer(); 
			//new ZLServer(); 4-8还不能用啊！！！
			
		}
		
		//3-18 修改
		else if(arg0.getSource()==jb2)
		{
			this.dispose();
			//new MyQqServerClose();
		}
	}
	

}
