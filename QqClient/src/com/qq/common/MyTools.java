/**
 * ���ܣ�һЩ�������õķ�����ö�٣������з�������
 * 4-16�����޸�
 * autor��tkx
 * û��ʵ�֣�Ҳû�����BUG
 * �Լ�Ϊ��ӱ����д
 */
package com.qq.common;

import java.awt.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

import com.qq.client.view.QqChat;


public class MyTools 
{
	//�������仰����һЩ��־��ʾ�����£�
	//login#����;1234567
	//friends_info#�ҵĺ���,���԰�,����,��־ǿ;İ����,������,��ˮ��
	public static final String FLAGEND="#";//�����ͷ��־�������ַ�
	public static final String SPLIT1=";";//����һ���ָ��ַ�
	public static final String SPLIT2=",";//��������ָ��ַ�
	public static final String SPLIT3="&";//���������ָ��ַ�
		
	//public static final int QQServerPort=6666;//QQ�������˿ں�
	//public static final String QQServerIP="192.168.3.169";//������IP��ַ
	
	/**
	 * @author LXA
	 *����һ����־��ö��
	 */
	public enum Flag
	{
		LOGIN,	//����¼���ı�־
		SUCCESS,	//���ɹ����ı�־
		FAILED,		//��ʧ�ܡ��ı�־
		USERINFO,	//���û���Ϣ�������б��ı�־
		FRIENDS_LIST,	//�����б�
		PORT,	//�˿ںŵı�־
		START_CHAT,	//����ı�־
		MESSAGE,	//�������ݵı�־
		GET_FRIEND_INFO, //��ȡ�������ϵı�־
		REGISTER,  //ע��
		SENDFILE, //�����ļ��ı�־
		GETFILE_OK,
		QUN_CHAT,   //Ⱥ��
		PUBLIC_MESSAGE,  //����
		SHOW_WINDOW,  //����
		UNDERLINE_MESSAGE,//������Ϣ
		SENDIMG,//����ͼƬ
		FACE//����
	};
	
	/**
	 * �����������ʾ
	 * @param frame ��Ҫ������ʾ�Ĵ���
	 */
	public static void setWindowsMiddleShow(JFrame frame)
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2, frame.getWidth(), frame.getHeight());
	}
	
	/**
	 * �����������ʾ
	 * @param frame ��Ҫ������ʾ�Ĵ���
	 * @param width ����Ŀ��
	 * @param height ����ĸ߶�
	 */
	public static void setWindowsMiddleShow(JFrame frame,int width,int height)
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
	}
	
	/**
	 * �����ļ�·����ȡͼƬ
	 * @param path ·��
	 * @return ���ػ�ȡ��ͼƬ
	 */
	public static ImageIcon getIcon(String path)
	{
		try
		{
			//System.out.println(Main.class.getResource(path));
			ImageIcon icon=new ImageIcon(ImageIO.read(QqChat.class.getResource(path)));
			
			return icon;
		}
		catch (IOException e)
		{
			System.out.println("ͼƬ��"+path+"�����ڣ�");
			return null;
		}
	}
	
	public static String getFaceByIdx(int idx)
	{
		String fileName=" ";
		if(idx<10)
    	{
    		fileName= "image/img/face/f00"+idx+".png";//�޸�ͼƬ·�� 
    	}
    	else
    	{
    		fileName= "image/img/face/f0"+idx+".png";
		}
		return fileName;
	}

}
