/**
 * 功能：一些经常调用的方法和枚举，都集中放在这里
 * 4-16后面修改
 * autor：tkx
 * 没有实现，也没有造成BUG
 * 自己为添加表情而写
 */
package com.qq.common;

import java.awt.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

import com.qq.client.view.QqChat;


public class MyTools 
{
	//下面三句话定义一些标志，示例如下：
	//login#马化腾;1234567
	//friends_info#我的好友,刘显安,王林,吴志强;陌生人,胡锦涛,陈水扁
	public static final String FLAGEND="#";//定义最当头标志结束的字符
	public static final String SPLIT1=";";//定义一级分割字符
	public static final String SPLIT2=",";//定义二级分割字符
	public static final String SPLIT3="&";//定义三级分割字符
		
	//public static final int QQServerPort=6666;//QQ服务器端口号
	//public static final String QQServerIP="192.168.3.169";//服务器IP地址
	
	/**
	 * @author LXA
	 *定义一个标志的枚举
	 */
	public enum Flag
	{
		LOGIN,	//“登录”的标志
		SUCCESS,	//“成功”的标志
		FAILED,		//“失败”的标志
		USERINFO,	//“用户信息、好友列表”的标志
		FRIENDS_LIST,	//好友列表
		PORT,	//端口号的标志
		START_CHAT,	//聊天的标志
		MESSAGE,	//聊天内容的标志
		GET_FRIEND_INFO, //获取好友资料的标志
		REGISTER,  //注册
		SENDFILE, //发送文件的标志
		GETFILE_OK,
		QUN_CHAT,   //群聊
		PUBLIC_MESSAGE,  //公告
		SHOW_WINDOW,  //弹窗
		UNDERLINE_MESSAGE,//离线消息
		SENDIMG,//发送图片
		FACE//表情
	};
	
	/**
	 * 将窗体居中显示
	 * @param frame 需要居中显示的窗体
	 */
	public static void setWindowsMiddleShow(JFrame frame)
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2, frame.getWidth(), frame.getHeight());
	}
	
	/**
	 * 将窗体居中显示
	 * @param frame 需要居中显示的窗体
	 * @param width 窗体的宽度
	 * @param height 窗体的高度
	 */
	public static void setWindowsMiddleShow(JFrame frame,int width,int height)
	{
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
	}
	
	/**
	 * 根据文件路径获取图片
	 * @param path 路径
	 * @return 返回获取的图片
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
			System.out.println("图片："+path+"不存在！");
			return null;
		}
	}
	
	public static String getFaceByIdx(int idx)
	{
		String fileName=" ";
		if(idx<10)
    	{
    		fileName= "image/img/face/f00"+idx+".png";//修改图片路径 
    	}
    	else
    	{
    		fileName= "image/img/face/f0"+idx+".png";
		}
		return fileName;
	}

}
