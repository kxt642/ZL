/**
 * 功能:qq客户端登录界面
 */
package com.qq.client.view;
import com.qq.common.*;
import com.qq.client.tools.*;
import java.io.*;
import javax.swing.*;

import com.qq.client.model.QqClientUser;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;
public class QqClientLogin extends JFrame implements ActionListener{

	//定义北部需要的组件
	
	JLabel jbl1;
	
	//定义中部需要的组件
	//.中部有三个JPanel,有一个叫选项卡窗口管理
	JTabbedPane jtp;
	JPanel jp2,jp3;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//定义手机登陆3-24
	JLabel jp3_jbl1,jp3_jbl2,jp3_jbl3,jp3_jbl4;
	JButton jp3_jb1;
	JTextField jp3_jtf;
	JPasswordField jp3_jpf;
	JCheckBox jp3_jcb1,jp3_jcb2;
	
	
	//定义南部需要的组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
		
		}
		QqClientLogin qqClientLogin=new QqClientLogin();
	}
	
	public QqClientLogin()
	{
		//处理北部
		jbl1=new JLabel(new ImageIcon("image/toubu.png"));
		
		//处理中部
		jp2=new JPanel(new GridLayout(3,3));
		
		jp2_jbl1=new JLabel("用户号码",JLabel.CENTER);
		jp2_jbl2=new JLabel("用户密码",JLabel.CENTER);
		jp2_jbl3=new JLabel("忘记密码",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("申请密码保护",JLabel.CENTER);
		jp2_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("隐身登录");
		jp2_jcb2=new JCheckBox("记住密码");
		
		//把控件按照顺序加入到jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		//创建选项卡窗口
		jtp=new JTabbedPane();
		jtp.add("用户账号",jp2);
		jp3= new JPanel(new GridLayout(3,3));
		jtp.add("手机账号",jp3);
		
		
		//手机登陆3-24
        
		
		jp3_jbl1=new JLabel("手机号码",JLabel.CENTER);
		jp3_jbl2=new JLabel("账号密码",JLabel.CENTER);
		jp3_jbl3=new JLabel("忘记密码",JLabel.CENTER);
		jp3_jbl3.setForeground(Color.blue);
		jp3_jbl4=new JLabel("申请密码保护",JLabel.CENTER);
		jp3_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jp3_jtf=new JTextField();
		jp3_jpf=new JPasswordField();
		jp3_jcb1=new JCheckBox("隐身登录");
		jp3_jcb2=new JCheckBox("记住密码");
		
		//把控件按照顺序加入到jp2
		jp3.add(jp3_jbl1);
		jp3.add(jp3_jtf);
		jp3.add(jp3_jb1);
		jp3.add(jp3_jbl2);
		jp3.add(jp3_jpf);
		jp3.add(jp3_jbl3);
		jp3.add(jp3_jcb1);
		jp3.add(jp3_jcb2);
		jp3.add(jp3_jbl4);
		
		//3-24删除
//		jp4=new JPanel();
//		jtp.add("电子邮件",jp4);
		
		//处理南部
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		//响应用户点击登录
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/quxiao.gif"));
		
		jp1_jb2.addActionListener(this); //3-24响应用户点击取消
		jp1_jb3=new JButton(new ImageIcon("image/xiangdao.gif"));
		jp1_jb3.addActionListener(this); //3-24响应用户点击注册
		
		//把三个按钮放入到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		//..把jp1放在南部
		this.add(jp1,"South");
		this.setTitle("欢迎使用砖聊！");
		this.setIconImage((new ImageIcon("image/ZL.jpg").getImage()));
		this.setSize(350, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //3-18修改 窗口放置于中央
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//如果用户点击登录
		if(arg0.getSource()==jp1_jb1)
		{
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(qqClientUser.checkUser(u))
			{
				try {
					//把创建好友列表的语句提前.
					QqFriendList qqList=new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqList);
					
					//发送一个要求返回在线好友的请求包.
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					
					//做一个Message
					Message m=new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					//指明我要的是这个qq号的好友情况.
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
				
			
				//关闭掉登录界面
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"用户名密码错误");
			}
		}
		
		//用户点击取消的响应
		else if (arg0.getSource()==jp1_jb2) 
		{
			this.dispose();
		}
		
		//用户点击注册的响应
		else if (arg0.getSource()==jp1_jb3) 
		{
			new RegisterDialog();	
		}
	}

}
