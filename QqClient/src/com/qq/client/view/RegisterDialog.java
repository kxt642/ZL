/**
 * �û�ע�����
 * ���ߣ�tkx
 * ʱ�䣺3-24
 * ��ɶȣ�ע�������ɣ�cancelJbutton������ɣ�ע�Ṧ��δ��ʵ�֣��ֻ�ע��ģ��û��д
 * �´ι�����ʵ�ֵ��okJButton���ע�ᣬ���û����ݵ������ݿ�
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
	//�����в���Ҫ�����
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
	
	//�����ϲ���Ҫ�����
	JPanel southJPanel;
	JButton okJButton,cancelJButton;
	
	public RegisterDialog () 
	{
		//�����в�
		user_JPanel=new JPanel(new GridLayout(7,3));
		NickName_JLabel=new JLabel("�ǳ�",JLabel.CENTER);
		Password_JLabel=new JLabel("����",JLabel.CENTER);
		Psd_JLabel=new JLabel("ȷ������",JLabel.CENTER);
		Sex_JLabel=new JLabel("�Ա�",JLabel.CENTER);
		Age_JLabel=new JLabel("����",JLabel.CENTER);
		Name_JLabel=new JLabel("����",JLabel.CENTER);
		NickName_jtf=new JTextField();
		Age_jtf=new JTextField();
		Name_jtf=new JTextField();
		Password_jpf=new JPasswordField();
		Psd_jpf=new JPasswordField();
		manBox=new JRadioButton("��");
		womenBox= new JRadioButton("Ů");
		nickJLabel=new JLabel("�ǳƲ���Ϊ�գ�",JLabel.CENTER);
		paJLabel=new JLabel("���������룡",JLabel.CENTER);
		pasJLabel=new  JLabel("���ٴ�����һ�����룡",JLabel.CENTER);
		ageJLabel=new JLabel("��������ʵ���䣡",JLabel.CENTER);
		nameJLabel=new JLabel("��������ʵ������",JLabel.CENTER);
		
		//3-24night
		ButtonGroup SEX=new ButtonGroup();  
		ZLNUM=new JLabel("�û�����",JLabel.CENTER);
		ZLNUM_F=new JTextField();
		ZLINFO=new JLabel("�������Ϊ���֣�",JLabel.CENTER);
		
		
		//�ѿؼ�����˳����뵽user_JPanel
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
		//user_JPanel.add(womenBox); ������
		SEX.add(manBox);
		SEX.add(womenBox);
		user_JPanel.add(manBox);
		user_JPanel.add(womenBox);//3-24night�޸ģ�ok
		
		user_JPanel.add(Age_JLabel);
		user_JPanel.add(Age_jtf);
		user_JPanel.add(ageJLabel);
		
		user_JPanel.add(Name_JLabel);
		user_JPanel.add(Name_jtf);
		user_JPanel.add(nameJLabel);
		
		
		
		//����ѡ�����
		jtp=new JTabbedPane();
		jtp.add("�û��˺�",user_JPanel);
		telephone_JPanel= new JPanel(new GridLayout(7,3));
		jtp.add("�ֻ��˺�",telephone_JPanel);
		//�ֻ�ע����ʱ��д
				
		//�����ϲ�
		southJPanel=new JPanel();
		okJButton= new JButton(new ImageIcon("image/RD.gif"));
		okJButton.addActionListener(this);
		cancelJButton=new JButton(new ImageIcon("image/quxiao.gif"));
		cancelJButton.addActionListener(this);
		
		//��������ť����southJPanel
		southJPanel.add(okJButton);
		southJPanel.add(cancelJButton);
		
		this.add(jtp,"Center");
		this.add(southJPanel, "South");
		this.setTitle("�����˺�");
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
			//����д
			String nickname = NickName_jtf.getText().trim();
			String passwordInfo = new String(Password_jpf.getPassword()).trim();
			String configPasswordInfo = new String(Psd_jpf.getPassword()). trim();
			String ageInfo=new String(Age_jtf.getText()).trim();
			String truename=new String(Name_jtf.getText()).trim();
			
			//�д��޸�
			String sexInfo =new String(((JRadioButton)e.getSource()).getText());
			
			if (nickname == null || nickname.equals(""))
			{
	            JOptionPane.showMessageDialog(this, "�û�������Ϊ�գ�");
	            NickName_jtf.requestFocus();
			}
			else if (!passwordInfo.equals(configPasswordInfo))
			{
	            JOptionPane.showMessageDialog(this, "������������벻һ�£�");
	        } 
			else 
			{
	            long ZLnum = registerNewUser(nickname, passwordInfo,sexInfo, ageInfo,truename);
	            if (ZLnum == 0) {
	                JOptionPane.showMessageDialog(this, "ע��ʧ��!");
	            } else {
	                JOptionPane.showMessageDialog(this, "ע��ɹ������QQ����Ϊ��" + ZLnum);
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


	//3-24night ע�Ṧ��ʵ��,�д������⣡����
	private long registerNewUser(String nickname, String passwordInfo,
			String sexInfo, String ageInfo, String truename) 
	{
		long ZLnum = 0;
		String serverInfo = "";//ʲô�ã�
		try {
            //�����׽ӿ�
			ServerSocket ss=new ServerSocket(9999);
			Socket s=ss.accept();
            //����������
            DataInputStream in = new DataInputStream(s.getInputStream());
            //���������
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            //�����������ע�����û�������
            out.writeUTF("registerNewUser");
            //�����������ע���û�����Ϣ
            out.writeUTF(nickname);
            out.writeUTF(passwordInfo);
            out.writeUTF(sexInfo);
            out.writeUTF(ageInfo);
            out.writeUTF(truename);
            //��ȡ�û�ע���QQ����
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
