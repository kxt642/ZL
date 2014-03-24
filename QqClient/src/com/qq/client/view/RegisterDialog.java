/**
 * 用户注册界面
 * 作者：tkx
 * 时间：3-24
 * 完成度：注册界面完成，cancelJbutton功能完成；注册功能未能实现，手机注册模块没有写
 * 下次工作：实现点击okJButton完成注册，把用户数据导入数据库
 * 
 */

package com.qq.client.view;

import com.qq.common.*;
import com.qq.client.tools.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

import com.qq.client.model.QqClientUser;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;

//import java.lang.*; //3-24

public class RegisterDialog extends JFrame implements ActionListener
{
	//定义中部需要的组件
	JTabbedPane jtp;
	JPanel user_JPanel;
	JPanel telephone_JPanel;
	JLabel NickName_JLabel,Password_JLabel,Psd_JLabel,Sex_JLabel,Age_JLabel,Name_JLabel;
	JLabel nickJLabel,paJLabel,pasJLabel,ageJLabel,nameJLabel;
	JTextField NickName_jtf,Age_jtf,Name_jtf;
	JPasswordField Password_jpf,Psd_jpf;
	JRadioButton manBox,womenBox; //3-24 night
	ButtonGroup SEX; //3-24 night
	JLabel ZLNUM,ZLINFO; //3-24night
	JTextField ZLNUM_F; //3-24night
	
	//定义南部需要的组件
	JPanel southJPanel;
	JButton okJButton,cancelJButton;
	
	public RegisterDialog () 
	{
		//处理中部
		user_JPanel=new JPanel(new GridLayout(7,3));
		NickName_JLabel=new JLabel("昵称",JLabel.CENTER);
		Password_JLabel=new JLabel("密码",JLabel.CENTER);
		Psd_JLabel=new JLabel("确认密码",JLabel.CENTER);
		Sex_JLabel=new JLabel("性别",JLabel.CENTER);
		Age_JLabel=new JLabel("年龄",JLabel.CENTER);
		Name_JLabel=new JLabel("姓名",JLabel.CENTER);
		NickName_jtf=new JTextField();
		Age_jtf=new JTextField();
		Name_jtf=new JTextField();
		Password_jpf=new JPasswordField();
		Psd_jpf=new JPasswordField();
		manBox=new JRadioButton("男");
		womenBox= new JRadioButton("女");
		nickJLabel=new JLabel("昵称不能为空！",JLabel.CENTER);
		paJLabel=new JLabel("请输入密码！",JLabel.CENTER);
		pasJLabel=new  JLabel("请再次输入一致密码！",JLabel.CENTER);
		ageJLabel=new JLabel("请输入真实年龄！",JLabel.CENTER);
		nameJLabel=new JLabel("请输入真实姓名！",JLabel.CENTER);
		
		//3-24night
		ButtonGroup SEX=new ButtonGroup();  
		ZLNUM=new JLabel("用户号码",JLabel.CENTER);
		ZLNUM_F=new JTextField();
		ZLINFO=new JLabel("号码必须为数字！",JLabel.CENTER);
		
		
		//把控件按照顺序加入到user_JPanel
		user_JPanel.add(ZLNUM);
		user_JPanel.add(ZLNUM_F);
		user_JPanel.add(ZLINFO);
		
		user_JPanel.add(NickName_JLabel);
		user_JPanel.add(NickName_jtf);
		user_JPanel.add(nickJLabel);
		
		user_JPanel.add(Password_JLabel);
		user_JPanel.add(Password_jpf);
		user_JPanel.add(paJLabel);
		
		user_JPanel.add(Psd_JLabel);
		user_JPanel.add(Psd_jpf);
		user_JPanel.add(pasJLabel);
		
		user_JPanel.add(Sex_JLabel);
		//user_JPanel.add(manBox);
		//user_JPanel.add(womenBox); 无用了
		SEX.add(manBox);
		SEX.add(womenBox);
		user_JPanel.add(manBox);
		user_JPanel.add(womenBox);//3-24night修改，ok
		
		user_JPanel.add(Age_JLabel);
		user_JPanel.add(Age_jtf);
		user_JPanel.add(ageJLabel);
		
		user_JPanel.add(Name_JLabel);
		user_JPanel.add(Name_jtf);
		user_JPanel.add(nameJLabel);
		
		
		
		//创建选项卡窗口
		jtp=new JTabbedPane();
		jtp.add("用户账号",user_JPanel);
		telephone_JPanel= new JPanel(new GridLayout(7,3));
		jtp.add("手机账号",telephone_JPanel);
		//手机注册暂时不写
				
		//处理南部
		southJPanel=new JPanel();
		okJButton= new JButton(new ImageIcon("image/RD.gif"));
		okJButton.addActionListener(this);
		cancelJButton=new JButton(new ImageIcon("image/quxiao.gif"));
		cancelJButton.addActionListener(this);
		
		//把两个按钮放入southJPanel
		southJPanel.add(okJButton);
		southJPanel.add(cancelJButton);
		
		this.add(jtp,"Center");
		this.add(southJPanel, "South");
		this.setTitle("申请账号");
		this.setIconImage(new ImageIcon("image/zce.png").getImage());
		this.setSize(450, 300);
		this.setLocation(600, 20);
		this.setVisible(true);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==okJButton) 
		{
			//待填写
			String nickname = NickName_jtf.getText().trim();
			String passwordInfo = new String(Password_jpf.getPassword()).trim();
			String configPasswordInfo = new String(Psd_jpf.getPassword()). trim();
			String ageInfo=new String(Age_jtf.getText()).trim();
			String truename=new String(Name_jtf.getText()).trim();
			
			//有待修改
			String sexInfo =new String(((JRadioButton)e.getSource()).getText());
			
			if (nickname == null || nickname.equals(""))
			{
	            JOptionPane.showMessageDialog(this, "用户名不能为空！");
	            NickName_jtf.requestFocus();
			}
			else if (!passwordInfo.equals(configPasswordInfo))
			{
	            JOptionPane.showMessageDialog(this, "两次输入的密码不一致！");
	        } 
			else 
			{
	            long ZLnum = registerNewUser(nickname, passwordInfo,sexInfo, ageInfo,truename);
	            if (ZLnum == 0) {
	                JOptionPane.showMessageDialog(this, "注册失败!");
	            } else {
	                JOptionPane.showMessageDialog(this, "注册成功！你的QQ号码为：" + ZLnum);
	            }
	        }
		}
		else if (e.getSource()==cancelJButton)
		{
			ZLNUM_F.setText("");
			NickName_jtf.setText("");
			Password_jpf.setText("");
			Psd_jpf.setText("");
	        manBox.setSelected(true);
	        Age_jtf.setText("");
	        Name_jtf.setText("");
		}
	}


	//3-24night 注册功能实现,尚存在问题！！！
	private long registerNewUser(String nickname, String passwordInfo,
			String sexInfo, String ageInfo, String truename) 
	{
		long ZLnum = 0;
		String serverInfo = "";//什么用？
		try {
            //定义套接口
			ServerSocket ss=new ServerSocket(9999);
			Socket s=ss.accept();
            //定义输入流
            DataInputStream in = new DataInputStream(s.getInputStream());
            //定义输出流
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            //向服务器发送注册新用户的申请
            out.writeUTF("registerNewUser");
            //向服务器发送注册用户的信息
            out.writeUTF(nickname);
            out.writeUTF(passwordInfo);
            out.writeUTF(sexInfo);
            out.writeUTF(ageInfo);
            out.writeUTF(truename);
            //读取用户注册的QQ号码
            serverInfo = in.readUTF();
            if (serverInfo.equals("registerFail"))
            {
                return 0;
            } 
            else
            {
                ZLnum = in.readInt();
            }
        } catch (IOException ex) 
        {
            ex.printStackTrace();
        }
		
        return ZLnum;
	}

}
