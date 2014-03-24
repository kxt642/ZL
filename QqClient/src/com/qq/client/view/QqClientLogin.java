/**
 * ����:qq�ͻ��˵�¼����
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

	//���山����Ҫ�����
	
	JLabel jbl1;
	
	//�����в���Ҫ�����
	//.�в�������JPanel,��һ����ѡ����ڹ���
	JTabbedPane jtp;
	JPanel jp2,jp3;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//�����ֻ���½3-24
	JLabel jp3_jbl1,jp3_jbl2,jp3_jbl3,jp3_jbl4;
	JButton jp3_jb1;
	JTextField jp3_jtf;
	JPasswordField jp3_jpf;
	JCheckBox jp3_jcb1,jp3_jcb2;
	
	
	//�����ϲ���Ҫ�����
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
		//������
		jbl1=new JLabel(new ImageIcon("image/toubu.png"));
		
		//�����в�
		jp2=new JPanel(new GridLayout(3,3));
		
		jp2_jbl1=new JLabel("�û�����",JLabel.CENTER);
		jp2_jbl2=new JLabel("�û�����",JLabel.CENTER);
		jp2_jbl3=new JLabel("��������",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("�������뱣��",JLabel.CENTER);
		jp2_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("�����¼");
		jp2_jcb2=new JCheckBox("��ס����");
		
		//�ѿؼ�����˳����뵽jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		//����ѡ�����
		jtp=new JTabbedPane();
		jtp.add("�û��˺�",jp2);
		jp3= new JPanel(new GridLayout(3,3));
		jtp.add("�ֻ��˺�",jp3);
		
		
		//�ֻ���½3-24
        
		
		jp3_jbl1=new JLabel("�ֻ�����",JLabel.CENTER);
		jp3_jbl2=new JLabel("�˺�����",JLabel.CENTER);
		jp3_jbl3=new JLabel("��������",JLabel.CENTER);
		jp3_jbl3.setForeground(Color.blue);
		jp3_jbl4=new JLabel("�������뱣��",JLabel.CENTER);
		jp3_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jp3_jtf=new JTextField();
		jp3_jpf=new JPasswordField();
		jp3_jcb1=new JCheckBox("�����¼");
		jp3_jcb2=new JCheckBox("��ס����");
		
		//�ѿؼ�����˳����뵽jp2
		jp3.add(jp3_jbl1);
		jp3.add(jp3_jtf);
		jp3.add(jp3_jb1);
		jp3.add(jp3_jbl2);
		jp3.add(jp3_jpf);
		jp3.add(jp3_jbl3);
		jp3.add(jp3_jcb1);
		jp3.add(jp3_jcb2);
		jp3.add(jp3_jbl4);
		
		//3-24ɾ��
//		jp4=new JPanel();
//		jtp.add("�����ʼ�",jp4);
		
		//�����ϲ�
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		//��Ӧ�û������¼
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/quxiao.gif"));
		
		jp1_jb2.addActionListener(this); //3-24��Ӧ�û����ȡ��
		jp1_jb3=new JButton(new ImageIcon("image/xiangdao.gif"));
		jp1_jb3.addActionListener(this); //3-24��Ӧ�û����ע��
		
		//��������ť���뵽jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		//..��jp1�����ϲ�
		this.add(jp1,"South");
		this.setTitle("��ӭʹ��ש�ģ�");
		this.setIconImage((new ImageIcon("image/ZL.jpg").getImage()));
		this.setSize(350, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //3-18�޸� ���ڷ���������
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//����û������¼
		if(arg0.getSource()==jp1_jb1)
		{
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(qqClientUser.checkUser(u))
			{
				try {
					//�Ѵ��������б�������ǰ.
					QqFriendList qqList=new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqList);
					
					//����һ��Ҫ�󷵻����ߺ��ѵ������.
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					
					//��һ��Message
					Message m=new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					//ָ����Ҫ�������qq�ŵĺ������.
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
				
			
				//�رյ���¼����
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"�û����������");
			}
		}
		
		//�û����ȡ������Ӧ
		else if (arg0.getSource()==jp1_jb2) 
		{
			this.dispose();
		}
		
		//�û����ע�����Ӧ
		else if (arg0.getSource()==jp1_jb3) 
		{
			new RegisterDialog();	
		}
	}

}
