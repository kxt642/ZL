/**
 * �������������Ľ���
 * ��Ϊ�ͻ��ˣ�Ҫ���ڶ�ȡ��״̬��������ǰ�������һ���߳�
 */
package com.qq.client.view;




import com.qq.client.view.FontAttr;

import com.qq.client.view.*;

import com.qq.client.tools.*;

import com.qq.client.model.*;
import com.qq.common.*;

import javax.imageio.ImageIO; //4-10
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;  //4-10
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
//import com.qq.client.view.FaceFrame;//4-16night
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class QqChat extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JTextArea jta; //�����ı���4-16ע��  4-21
	
	private JTextPane jta; //4-16�޸�  4-21
	
	private JScrollPane jScrollPane;//4-14���  ������֮��
	//public static JTextField jtf; ��ţд�� ����̭
	//public static JTextArea jtf;  //3-20�޸� �����ı���4-16ע��
	
	public static JTextPane jtf; //4-16�޸�
	
	JButton jb;//sendButton
	JButton jb1;//�Լ���ӵ�һ�� ��ʽ��ť
	JButton jbPIC; //4-10 ���ͼƬ��ť
	JPanel jp; //�������
	JPanel GJ; //4-14 �������
	JPanel jp_accept;//4-14�������
	String ownerId;
	String friendId;
	JLabel JL_Font;//4-14����
	JLabel JL_biaoqing;//4-14 ����
	JLabel JL_PIC;//4-14ͼƬ
	JLabel JL_Music;//4-14����
	JLabel JL_jietu;//4-14��ͼ
	JLabel JL_jilu;//4-14�����¼
	
	private JTextPane showJTextPane;//4-21
	private StyledDocument doc; //4-21Ϊ����ʾ�ı�����
	private StyledDocument dic; //4-21Ϊ����ʾͼƬ����
	
	public String ImgPath="";//4-16ͼƬ·���жϣ�
	public static int faceIdx=-1; //4-16 ���������
	
	//private Socket s = null;//4-28
	//private ObjectOutputStream oos = null;//4-28
	private SimpleAttributeSet msgAttrSet = new SimpleAttributeSet();//4-28���ͺͽ�����Ϣ������ʽ
	private SimpleAttributeSet tipAttrSet = null;//4-28  ��ʾ�����˺�ʱ�����ʽ
	private Font f = null; // ����Ի��򷵻ص����塣
	
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
	
	/*
	//3-20�޸�
	public JTextArea getJTextArea()
	{
		return this.jtf;
	}
	*/
	
	
	//4-28 ����֮��
	public Font getF() {
		return f;
	}

	public void setF(Font f) {
		this.f = f;
	}
	
	//4-16����д
	public JTextPane getJTextPane() 
	{
		return this.jtf;
	}
	
	public QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		//jta=new JTextArea();//4-16ע��   4-21
		jta=new JTextPane();//4-16�޸�   4-21
		
		jta.setEditable(false);  //3-20 ���������ı����ֹ�༭
		//jtf=new JTextField(30);  3-20 ɾ��
		
		jScrollPane=new JScrollPane(jta);//4-14
		//jScrollPane.setBackground(new java.awt.Color(51, 204, 255));4-14 ���ñ�����ɫ
		
		//4-14 �������
		jp_accept=new JPanel();
		jp_accept.setLayout(new BorderLayout());
		jp_accept.add(jScrollPane,BorderLayout.CENTER);
		//4-14
		
		//4-28��ʼ������
		FontAttr fa = new FontAttr(new Font("����", Font.PLAIN, 12), Color.BLUE,
				Color.WHITE);
		tipAttrSet = fa.getAttributeSet();
		setF(new Font("����", Font.PLAIN, 12));
		//4-28afternoon
		
		JL_Font=new JLabel(new ImageIcon(
				"image/img/chat/fun_font_32.png"));//�����������--����4-14  4-15
		//4-28 ��������Ӽ���
		JL_Font.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				FontDialog fd=new FontDialog(QqChat.this);
				fd.setVisible(true);
				if (QqChat.this.getF()!=null) 
				{
					FontAttr fa = new FontAttr(msgAttrSet, QqChat.this.getF());
					// System.out.println(fa);
					msgAttrSet = fa.getAttributeSet();
					jtf.setParagraphAttributes(msgAttrSet, true);
					jta.setParagraphAttributes(msgAttrSet, true);
				}
			}
		});
		//4-28
		
		//�����������--����4-14 4-15
		JL_biaoqing=new JLabel((new ImageIcon(
				"image/img/chat/fun_face_32.png")));
		JL_biaoqing.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectFace();
			}

			
		});
		//4-14
		
		
		
		//�����������--ͼƬ4-14 4-15
		JL_PIC=new JLabel(new ImageIcon(
				"image/img/chat/fun_picture_32.png"));
		JL_PIC.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) 
			{
				sendImg();
			}

			private void sendImg() 
			{
				JFileChooser fc = new JFileChooser();//4-16ͼƬѡ��·��
				//fc.setCurrentDirectory(new File("C:\\Users\\melo_t\\Desktop"));//4-16�����ļ�·��
				//fc.showOpenDialog(null);//4-16��ͼƬ�ļ�
				
				//�����ļ�4-16����
				fc.addChoosableFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return ".jpg/.png/.bmp/.wmf/.gif";
					}
					
					@Override
					public boolean accept(File f) {
						// ��ȡ�ļ���
						String fileName=f.getName();
						if (f.isDirectory()) {
							return true;
						}
						
						//�����ļ���
						if (fileName.endsWith(".jpg")||fileName.endsWith(".png")
								||fileName.endsWith(".bmp")||fileName.endsWith(".wmf")
								||fileName.endsWith(".gif")) {
							return true;
						}
						return false;
					}
				});
				fc.setCurrentDirectory(new File("C:\\Users\\melo_t\\Desktop"));
				int result = fc.showOpenDialog(null);//4-28�޸�
				
				//ѡ���ʱ
				if (result == JFileChooser.APPROVE_OPTION) 
				{
					//4-28
					//File f = fc.getSelectedFile();
					//List<File> imgs = new ArrayList<File>();
					//imgs.add(f);
					//Message m=new Message();
					//m.setImgs(imgs); 4-28
					//4-28
					
					String filePath = fc.getSelectedFile().getAbsolutePath();
					// ��ͼƬ���·��
					ImgPath = filePath;
					try 
					{
						jtf.insertIcon(new ImageIcon(ImageIO
								.read(new FileInputStream(filePath))));
						
					} catch (FileNotFoundException e) 
					{
						System.out.println("�ļ�δ�ҵ�");
						e.printStackTrace();
					}catch (IOException e) {
						System.out.println("io�쳣");
						e.printStackTrace();
					}
				}
				
				//ѡ��ر�ʱ
				else 
				{
					dispose();
				}
			}
		});
		//4-14
		
		
		JL_Music=new JLabel(new ImageIcon(
				"image/img/chat/fun_music_32.png"));//�����������--����4-14 4-15
		
		//�����������--��ͼ4-14 4-15
		JL_jietu=new JLabel(new ImageIcon(
				"image/img/chat/fun_snap_32.png"));
		JL_jietu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				screenFram();
			}

			private void screenFram() {
				// TODO Auto-generated method stub
				
			}
		});
		//4-14
		
		JL_jilu=new JLabel(new ImageIcon(
				"image/img/chat/fun_message_history_32.png"));//�����������--�����¼4-14
		
		//4-14 ������������
		GJ=new JPanel();
		GJ.setBackground(new Color(204,255,255));
		JL_Font.setToolTipText("\u5b57\u4f53");
		/*JL_Font.setBorder(BorderFactory.createEtchedBorder(
				new Color(204, 255, 255), new Color(204, 255,
						255)));*/
		
		JL_biaoqing.setToolTipText("\u8868\u60c5");
		JL_PIC.setToolTipText("\u53d1\u9001\u56fe\u7247");
		
		JL_Music.setToolTipText("\u53d1\u9001\u97f3\u4e50");

		JL_jietu.setToolTipText("\u622a\u56fe");
		
		JL_jilu.setText("\u804a\u5929\u8bb0\u5f55");
		JL_jilu.setToolTipText("\u6253\u5f00\u6211\u7684\u804a\u5929\u8bb0\u5f55");
		GroupLayout GJLayout = new GroupLayout(GJ);
		
		GJ.setLayout(GJLayout);
		
		GJLayout.setHorizontalGroup(GJLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(GJLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(JL_Font,
								GroupLayout.PREFERRED_SIZE,
								36,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(JL_biaoqing)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)						
						.addComponent(JL_PIC)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(JL_Music)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(JL_jietu)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
										193,Short.MAX_VALUE)
						.addComponent(JL_jilu)));
		
		GJLayout.setVerticalGroup(GJLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(GJLayout
						.createSequentialGroup()
						.addGroup(GJLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(JL_Font)
								.addComponent(JL_biaoqing)
								.addComponent(JL_PIC)
								.addComponent(JL_Music)
								.addComponent(JL_jietu)
								.addComponent(JL_jilu))
								.addContainerGap()));
		//4-14
		
		
		
		
		//jtf=new JTextArea(3,30);  //3-20  4-16ע��
		
		jtf=new JTextPane();//4-16
		jb=new JButton("����");
		jb.setToolTipText("Alt+Enter����");
		jb.addActionListener(this);

		jb1=new JButton("��ʽ");  //�Լ���ӵ�һ��
		jb1.addActionListener(this);
		
		//jbPIC=new JButton(new ImageIcon("image/PIC.png")); //4-10 ���
		//jbPIC.addActionListener(this);
		
		jp=new JPanel();
		jp.setBackground(new Color(204, 255, 255));//4-14 ����jp������ɫ
		
		/*
		jp.add(jtf);
		jp.add(jb);
		jp.add(jb1);  //�Լ���ӵ�һ��
		jp.add(jbPIC); //4-10 ���
		*/  //4-14ע��
		
		//4-14������岼��
		GroupLayout jpLayout=new GroupLayout(jp);
		jp.setLayout(jpLayout);
		jpLayout.setHorizontalGroup(jpLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(GJ,GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
				.addGroup(GroupLayout.Alignment.TRAILING,
						jpLayout.createSequentialGroup()
						        .addContainerGap(321, Short.MAX_VALUE)
						        .addComponent(jb,GroupLayout.PREFERRED_SIZE,
						        		56,GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(jb1,GroupLayout.PREFERRED_SIZE,
						        		56,GroupLayout.PREFERRED_SIZE)
						        .addGap(5, 5, 5))
				.addComponent(jtf,GroupLayout.DEFAULT_SIZE,486,
						Short.MAX_VALUE));
		
		jpLayout.setVerticalGroup(jpLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING,
						jpLayout.createSequentialGroup()
						.addComponent(GJ,GroupLayout.PREFERRED_SIZE,
								36,GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jtf,GroupLayout.PREFERRED_SIZE,
										96,GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10)
								.addGroup(jpLayout
										.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(jb,GroupLayout.DEFAULT_SIZE,
												35,Short.MAX_VALUE)
										.addComponent(jb1,GroupLayout.DEFAULT_SIZE,
												35,Short.MAX_VALUE))
								        .addContainerGap()));
		//4-14
		
		
		doc=jta.getStyledDocument();//4-21���磬����ͼƬʱ
		
		//this.add(jta,"North");
		this.add(jp_accept,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+" ���ں� "+friend+" ����");
		this.setIconImage((new ImageIcon("image/ydy.png").getImage()));
		this.setSize(500, 500);
		//this.setLocation(300, 200); //3-18�޸�  ����λ�� 
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		jb.setMnemonic(KeyEvent.VK_ENTER); //4-14 ���÷��Ͱ�ť�Ŀ�ݼ�ΪAlt+Enter
	}
	
	
	 //4-23����֮��
	protected void selectFace() {
		// TODO Auto-generated method stub
		new FaceFrame(this);
	}

	//дһ��������������ʾ��Ϣ
	public void showMessage(Message m) throws BadLocationException
	{
		//String info=m.getSender()+" �� "+m.getGetter()+" ˵         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
		//this.jta.append(info);// 4-16ע�� �����⣡!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
			
			//4-21 ���е����⣬���շ�ͼƬ��ʾ����
			if(m.equals(ImgPath)) {
				try {
					jta.setCaretPosition(doc.getLength());//4-24�����ݽӺ�
					jta.insertIcon(new ImageIcon(ImageIO
							.read(new FileInputStream(ImgPath))));	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					doc.insertString(doc.getLength(),
							m.getSender()+" �� "+m.getGetter()+" ˵         "+m.getSendTime()+"\n"+m.getCon()+"\r\n\n",
							null);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		
			
		/*
		//4-21night ͼƬ֮��
		 Vector picVector = new Vector();
		 for(int i = 0; i < jtf.getStyledDocument().getRootElements()[0].getElement(0).getElementCount(); i++)
		 {
			 Icon icon = StyleConstants.getIcon(jtf.getStyledDocument().
					 getRootElements()[0].getElement(0).getElement(i).getAttributes());
			 if (icon!=null) 
			 {
				 picVector.add(icon.toString());
			 }
		 }
		 for (int i = 0; i < jtf.getText().length(); i++) 
		 {
			 if (jtf.getStyledDocument().getCharacterElement(i).getName().equals("icon")) 
			 {
				 jta.insertIcon(new ImageIcon(picVector.get(0).toString()));
			 }
			 else 
			 {
				try 
				{
					jta.getStyledDocument().insertString(jta.getText().length(), jtf.getStyledDocument().getText(i,1), null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
			
		 }
		 picVector.clear();
		 //4-21night
		*/
			
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
			
			//4-16������͵����ı���Ϣ
			if("".equals(ImgPath))
			{
				
			//���Ͳ���Ϊ�� 4-10 
			if (jtf.getText().trim().equals("")) 
			{
				//System.out.println("���Ͳ���Ϊ��,���������룡");
				JOptionPane.showMessageDialog(null, "���Ͳ���Ϊ��,���������룡","��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			m.setCon(jtf.getText());
			jtf.setText(null); //3-20 ��� ����˷���֮��
			//m.setSendTime(new java.util.Date().toString());4-21
			m.setSendTime(DateFormat.getTimeInstance().format(new Date()));//4-21 ��һ��ʱ��
			
			//���Լ������������ʾ���͵���Ϣ 4-21ע��
			//String infomy=m.getSender()+" �� "+m.getGetter()+" ˵         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
			//this.jta.append(infomy); //4-16ע�� �����⣡!!!!!!!!!!!!!!!!!!!!!!!!
			
			try {
				doc.insertString(doc.getLength(),
						m.getSender()+" �� "+m.getGetter()+" ˵         "+m.getSendTime()+"\n"+m.getCon()+"\r\n\n",
						null);
				
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//���͸�������.
			try {
				
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				oos.flush();//4-21night��ջ���������
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
				
		}
			}
			
			
			//4-21����ͼƬ    BUG1:ѡ��ͼƬ֮����ɾ�����ڷ����ı���Ϣ�Լ����������ʾ֮ǰͼƬ�����������ʾ�ı���Ϣ
			//BUG2:���Լ�����ʱ�򣬷�����ʾ��ͼƬ���ö�����
			else if (!"".equals(ImgPath)) 
			{
				
				//m.setCon(jtf.getText());//Ӧ�ô�������4-21  4-23 4-28
				
				jtf.setText(null); 
				m.setSendTime(DateFormat.getTimeInstance().format(new Date()));
				
				//���Լ������������ʾ���͵���Ϣ
				//String infomy=m.getSender()+" �� "+m.getGetter()+" ˵         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
				//this.jta.append(infomy); //4-16ע�� �����⣡!!!!!!!!!!!!!!!!!!!!!!!!
				
				try {
					doc.insertString(doc.getLength(),
							m.getSender()+" �� "+m.getGetter()+" ˵         "+m.getSendTime()+"\n"+"\r\n",
							null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try 
				{
					jta.setCaretPosition(doc.getLength());//4-24����������ݽӵ����������ݵ�β����
					
					jta.insertIcon(new ImageIcon(ImageIO
							.read(new FileInputStream(ImgPath))));//4-21��ʾͼƬ
					
					doc.insertString(doc.getLength(), "\n\n", null);//4-21night ����֮��
					
					/*
					 * û�гɹ�
					//4-23
					 Vector picVector = new Vector();
					 for(int i = 0; i < jtf.getStyledDocument().getRootElements()[0].getElement(0).getElementCount(); i++)
					 {
						 Icon icon = StyleConstants.getIcon(jtf.getStyledDocument().
								 getRootElements()[0].getElement(0).getElement(i).getAttributes());
						 if (icon!=null) 
						 {
							 picVector.add(icon.toString());
						 }
					 }
					 for (int i = 0; i < jtf.getText().length(); i++) 
					 {
						 if (jtf.getStyledDocument().getCharacterElement(i).getName().equals("icon")) 
						 {
							 jta.insertIcon(new ImageIcon(picVector.get(0).toString()));
						 }
						 else 
						 {
							try 
							{
								jta.getStyledDocument().insertString(jta.getText().length(), jtf.getStyledDocument().getText(i,1), null);
							} catch (Exception e) {
								e.printStackTrace();
							}
						 }
						
					 }
					 picVector.clear();
					//4-23
					*/
					
					//4-28
					ObjectOutputStream fos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
					fos.writeObject(m);
					
					
					//sendMsg(m);//4-28
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//������ͼƬ���ÿ�ͼƬ·��
				ImgPath ="";
				
			}
			
			
			
			/*
			//4-16 ����ͼƬ
			else if (!"".equals(ImgPath)) 
			{
				try 
				{
					//�ÿշ��Ϳ�
					jtf.setText("");
					
					//���տ���ʾͼƬ
					jta.insertIcon(new ImageIcon(ImageIO
							.read(new FileInputStream(ImgPath))));
					//����ͼƬ�����д�����
					
				} catch (FileNotFoundException e) {
					// TODO: handle exception
					e.printStackTrace();
				}catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			*/
		}
		
		//�Լ���ӵڶ���  3-17
		else if(arg0.getSource()==jb1)
		{
			//����û�����˹�ʽ��ť
              //new TermEditDialog();    4-10�޸�
			
			//���ù�ʽ�༭���  4-10 ���  author��tkx
			try 
			{
				Runtime.getRuntime().exec("D:\\����\\����\\��ʽ�༭���\\MathType.exe");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
		}	
		
		/*
		//4-10 ���ͼƬѡ�񷽷�    û��д�꣬�д����ƣ�����
		else if (arg0.getSource()==jbPIC) 
		{
			//����û����ͼƬ��ť
			try 
			{
				BufferedImage image=ImageIO.read(new File("mm.jpg"));
				int m=image.getHeight();
				int n=image.getWidth();
				
				for(int i=0;i<n;i++)
				{
					for (int j = 0; j < m; j++) 
					{
						int rgb=image.getRGB(i, j);
						System.out.println(i);
						System.out.println(j);
						Color color=new Color(rgb);
						//g.setColor(color);
						//g.drawLine(i,j,i,j);
						
					}
					
				}
					
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			
		}
		*/

	} 
	
	/*
	 4-21
	public void selectFace(){
		new FaceFrame(this);
	}
	*/
	
	/*
	//4-28 connect to server
	public void connect() {
		try {
			s = new Socket(InetAddress.getLocalHost(), 9999);
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//4-28 send msg to server
	public void sendMsg(Message m)
	{
		try {
			oos.writeObject(m);
			oos.flush();
		} catch (IOException e) {
			System.out.println("send msg failed.");
			e.printStackTrace();
		}
	}
	// 4-28disconnect to server when exit.
		public void disconnect() {
			try {
				if (oos != null)
					oos.close();
				if (s != null)
					s.close();
			} catch (IOException e) {
				System.out.println("exit chat.");
				e.printStackTrace();
			}
		}
	*/
		
	public SimpleAttributeSet getMsgAttrSet() {
		return msgAttrSet;
	}

	public void setMsgAttrSet(SimpleAttributeSet msgAttrSet) {
		this.msgAttrSet = msgAttrSet;
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
