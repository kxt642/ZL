/**
 * 功能：表情的选择
 * 4-16添加
 * autor：tkx
 * 没有ok
 */

package com.qq.client.view;



import com.qq.client.tools.*;

import com.qq.client.model.*;
import com.qq.common.*;

import javax.imageio.ImageIO; 
import javax.swing.*;
import javax.swing.filechooser.FileFilter;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;  
import java.io.*;

import com.qq.common.MyLabel;//4-16
import com.qq.client.view.QqChat;

public class FaceFrame extends JWindow
{
	//private static final long serialVersionUID=1L;//4-16night   4-29注释
	
	private JPanel contentPane;
	
	GridLayout gridLayout1 = new GridLayout();//4-16night
	public static JLabel[] ico = new JLabel[108];//4-16night
	QqChat chat; // 4-16night
	int width=720;
	int height=288;
	//MouseAdapter mouseAdapter;
	
	
	public FaceFrame(QqChat chat)
	{
		super(chat);
		this.chat = chat;
		try
		{
			jbInit();
			
			//4-23
			int left=chat.getLocation().x+20;
			int top=chat.getLocation().y+chat.getHeight()-200-height;
			this.setBounds(left, top, width, height);
			
			this.setAlwaysOnTop(true);
			this.setVisible(true);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	

	/*
	//4-16night 没有ok
	public FaceFrame(MouseAdapter mouseAdapter) 
	{
		super();
		this.mouseAdapter=mouseAdapter;
		try
		{
			jbInit();
			int left=chat.getLocation().x+20;
			int top=chat.getLocation().y+chat.getHeight()-200-height;
			this.setBounds(left, top, width, height);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	//4-16night
	*/
	
	private void jbInit() throws Exception
	{
//		this.setPreferredSize(new Dimension(28*15,28*7));//4-16night
//		JPanel contentPane=new JPanel();//4-16night
//		contentPane.setOpaque(true);//4-16night
//		this.setContentPane(contentPane);//4-16night
//		contentPane.setLayout(gridLayout1);//4-16night
		
		getContentPane().setLayout(gridLayout1);//4-16night注释
		gridLayout1.setColumns(15);
		gridLayout1.setHgap(1);
		gridLayout1.setRows(6);
		gridLayout1.setVgap(1);
		//this.add(contentPane);//4-21
		String fileName = " ";
		for (int i = 0; i < ico.length; i++)
		{
			if(i<10)
        	{
        		fileName= "image/img/face/f00"+i+".png";//修改图片路径 
				//fileName="image/img/face/f000.png";//4-29修改
        		//fileName="D:\\毕设\\code\\QqClient\\image\\img\\face\\f00"+i+".png";
        	}
        	else if(i>=10&&i<100)
        	{
        		fileName= "image/img/face/f0"+i+".png";
			}
			else 
			{
				fileName="image/img/face/f"+i+".png";
			}
			//ico[i]=new JLabel(new ImageIcon(FaceFrame.class.getResource(fileName)));//4-23
			
			ico[i]=new JLabel(new ImageIcon(fileName));//4-23
			
			ico[i].setToolTipText("点此添加QQ表情哟^_^");
			new MyLabel(ico[i],Color.black).addEvent(); //有问题待解决！！！！！！！！！！！！
			final Icon img = ico[i].getIcon();
			final int count=i;
			ico[i].addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					chat.jtf.insertIcon(img);
					chat.faceIdx=count;
					getObj().dispose();// 这里最好用单例模式
				}
			});
			this.getContentPane().add(ico[i]);
		}
		this.getContentPane().setBackground(SystemColor.text);
	}

	private JWindow getObj()
	{
		return this;
	}

}
