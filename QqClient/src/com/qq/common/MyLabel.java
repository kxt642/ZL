/**
 * ���ܣ�
 * 4-16���
 * autor��tkx
 * û��ʵ�֣�Ҳû�����BUG
 * �Լ�Ϊ��������
 */
package com.qq.common;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import javax.swing.BorderFactory;
//import javax.swing.JLabel;
import javax.swing.*;

import com.qq.common.MyTools;

public class MyLabel 
{
	//�涨������״̬ͼƬ����Ϊ1��������Ϊ2����갴��Ϊ3������ͷŻ���2
	JLabel jLabel=null;
	String fileName="";
	String extension="";
	int mode=1;//ģʽΪ1��ʾ��ͼƬ��ģʽΪ2��ʾ��Boder
	Color backColor=null;//Label�ĸ������ı���ɫ
	/**
	 * ����ָ����������ͼƬ��ʵ����һ��Label
	 * @param fileName ͼƬ��·��,������ͼƬ��������չ��������
	 * ���ͼƬ��Ϊimg/QQ_1.png����д��"../img/QQ"
	 * @param extension ��չ����������ǰ��ĵ�"."
	 */

	public MyLabel(JLabel jLabel,String fileName,String extension)
	{
		this.jLabel=jLabel;
		this.fileName=fileName;
		this.extension=extension;
	}
	
	public MyLabel(JLabel jLabel)
	{
		this.jLabel=jLabel;
		this.mode=0;
		backColor=this.jLabel.getParent().getBackground();
		setEtchedBorder(backColor);//��label��Ĭ�ϱ߿���ɫ���õĺͱ���ɫһ�����Դﵽ����ʵ�߿������
	}
	
	public MyLabel(JLabel jLabel,Color color)
	{
		this.jLabel=jLabel;
		this.mode=1;//4-21 ������0�����ڸ�Ϊ1����
		backColor=color;
		setEtchedBorder(backColor);
	}
	
	/**
	 * ���ó�һ�ֱ߿����ķ�����ʲô��Ҳ��֪��
	 * @param color
	 */
	public void setEtchedBorder(Color color)
	{
		jLabel.setBorder(BorderFactory.createEtchedBorder(color, color));
	}
	
	/**
	 * ��jLabel����¼�
	 */
	public void addEvent()
	{
		jLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_2."+extension));
				else 
					setEtchedBorder(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_1."+extension));
				else 
					setEtchedBorder(backColor);
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_3."+extension));
				else 
					jLabel.setBorder(BorderFactory.createBevelBorder(
							javax.swing.border.BevelBorder.LOWERED));
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				if(mode==1)
					jLabel.setIcon(MyTools.getIcon(fileName+"_2."+extension));
				else 
					setEtchedBorder(Color.red);
			}
		});
	}
}
