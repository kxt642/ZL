/**
 * 这是与好友聊天的界面
 * 因为客户端，要处于读取的状态，因此我们把它做成一个线程
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
	//private JTextArea jta; //接收文本域4-16注释  4-21
	
	private JTextPane jta; //4-16修改  4-21
	
	private JScrollPane jScrollPane;//4-14添加  滚动框之用
	//public static JTextField jtf; 大牛写的 已淘汰
	//public static JTextArea jtf;  //3-20修改 发送文本域4-16注释
	
	public static JTextPane jtf; //4-16修改
	
	JButton jb;//sendButton
	JButton jb1;//自己添加第一次 公式按钮
	JButton jbPIC; //4-10 添加图片按钮
	JPanel jp; //发送面板
	JPanel GJ; //4-14 工具面板
	JPanel jp_accept;//4-14接收面板
	String ownerId;
	String friendId;
	JLabel JL_Font;//4-14字体
	JLabel JL_biaoqing;//4-14 表情
	JLabel JL_PIC;//4-14图片
	JLabel JL_Music;//4-14音乐
	JLabel JL_jietu;//4-14截图
	JLabel JL_jilu;//4-14聊天记录
	
	private JTextPane showJTextPane;//4-21
	private StyledDocument doc; //4-21为了显示文本内容
	private StyledDocument dic; //4-21为了显示图片内容
	
	public String ImgPath="";//4-16图片路径判断？
	public static int faceIdx=-1; //4-16 表情的索引
	
	//private Socket s = null;//4-28
	//private ObjectOutputStream oos = null;//4-28
	private SimpleAttributeSet msgAttrSet = new SimpleAttributeSet();//4-28发送和接受消息面板的样式
	private SimpleAttributeSet tipAttrSet = null;//4-28  显示发送人和时间的样式
	private Font f = null; // 字体对话框返回的字体。
	
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
	//3-20修改
	public JTextArea getJTextArea()
	{
		return this.jtf;
	}
	*/
	
	
	//4-28 字体之用
	public Font getF() {
		return f;
	}

	public void setF(Font f) {
		this.f = f;
	}
	
	//4-16重新写
	public JTextPane getJTextPane() 
	{
		return this.jtf;
	}
	
	public QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		//jta=new JTextArea();//4-16注释   4-21
		jta=new JTextPane();//4-16修改   4-21
		
		jta.setEditable(false);  //3-20 聊天内容文本域禁止编辑
		//jtf=new JTextField(30);  3-20 删除
		
		jScrollPane=new JScrollPane(jta);//4-14
		//jScrollPane.setBackground(new java.awt.Color(51, 204, 255));4-14 设置背景颜色
		
		//4-14 接收面板
		jp_accept=new JPanel();
		jp_accept.setLayout(new BorderLayout());
		jp_accept.add(jScrollPane,BorderLayout.CENTER);
		//4-14
		
		//4-28初始化字体
		FontAttr fa = new FontAttr(new Font("楷体", Font.PLAIN, 12), Color.BLUE,
				Color.WHITE);
		tipAttrSet = fa.getAttributeSet();
		setF(new Font("楷体", Font.PLAIN, 12));
		//4-28afternoon
		
		JL_Font=new JLabel(new ImageIcon(
				"image/img/chat/fun_font_32.png"));//工具面板内容--字体4-14  4-15
		//4-28 给字体添加监听
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
		
		//工具面板内容--表情4-14 4-15
		JL_biaoqing=new JLabel((new ImageIcon(
				"image/img/chat/fun_face_32.png")));
		JL_biaoqing.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectFace();
			}

			
		});
		//4-14
		
		
		
		//工具面板内容--图片4-14 4-15
		JL_PIC=new JLabel(new ImageIcon(
				"image/img/chat/fun_picture_32.png"));
		JL_PIC.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) 
			{
				sendImg();
			}

			private void sendImg() 
			{
				JFileChooser fc = new JFileChooser();//4-16图片选择路径
				//fc.setCurrentDirectory(new File("C:\\Users\\melo_t\\Desktop"));//4-16更改文件路径
				//fc.showOpenDialog(null);//4-16打开图片文件
				
				//过滤文件4-16后来
				fc.addChoosableFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return ".jpg/.png/.bmp/.wmf/.gif";
					}
					
					@Override
					public boolean accept(File f) {
						// 获取文件名
						String fileName=f.getName();
						if (f.isDirectory()) {
							return true;
						}
						
						//过滤文件名
						if (fileName.endsWith(".jpg")||fileName.endsWith(".png")
								||fileName.endsWith(".bmp")||fileName.endsWith(".wmf")
								||fileName.endsWith(".gif")) {
							return true;
						}
						return false;
					}
				});
				fc.setCurrentDirectory(new File("C:\\Users\\melo_t\\Desktop"));
				int result = fc.showOpenDialog(null);//4-28修改
				
				//选择打开时
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
					// 给图片添加路径
					ImgPath = filePath;
					try 
					{
						jtf.insertIcon(new ImageIcon(ImageIO
								.read(new FileInputStream(filePath))));
						
					} catch (FileNotFoundException e) 
					{
						System.out.println("文件未找到");
						e.printStackTrace();
					}catch (IOException e) {
						System.out.println("io异常");
						e.printStackTrace();
					}
				}
				
				//选择关闭时
				else 
				{
					dispose();
				}
			}
		});
		//4-14
		
		
		JL_Music=new JLabel(new ImageIcon(
				"image/img/chat/fun_music_32.png"));//工具面板内容--音乐4-14 4-15
		
		//工具面板内容--截图4-14 4-15
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
				"image/img/chat/fun_message_history_32.png"));//工具面板内容--聊天记录4-14
		
		//4-14 工具面板的设置
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
		
		
		
		
		//jtf=new JTextArea(3,30);  //3-20  4-16注释
		
		jtf=new JTextPane();//4-16
		jb=new JButton("发送");
		jb.setToolTipText("Alt+Enter发送");
		jb.addActionListener(this);

		jb1=new JButton("公式");  //自己添加第一次
		jb1.addActionListener(this);
		
		//jbPIC=new JButton(new ImageIcon("image/PIC.png")); //4-10 添加
		//jbPIC.addActionListener(this);
		
		jp=new JPanel();
		jp.setBackground(new Color(204, 255, 255));//4-14 设置jp背景颜色
		
		/*
		jp.add(jtf);
		jp.add(jb);
		jp.add(jb1);  //自己添加第一次
		jp.add(jbPIC); //4-10 添加
		*/  //4-14注释
		
		//4-14发送面板布局
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
		
		
		doc=jta.getStyledDocument();//4-21下午，发送图片时
		
		//this.add(jta,"North");
		this.add(jp_accept,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+" 正在和 "+friend+" 聊天");
		this.setIconImage((new ImageIcon("image/ydy.png").getImage()));
		this.setSize(500, 500);
		//this.setLocation(300, 200); //3-18修改  放置位置 
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		jb.setMnemonic(KeyEvent.VK_ENTER); //4-14 设置发送按钮的快捷键为Alt+Enter
	}
	
	
	 //4-23表情之用
	protected void selectFace() {
		// TODO Auto-generated method stub
		new FaceFrame(this);
	}

	//写一个方法，让它显示消息
	public void showMessage(Message m) throws BadLocationException
	{
		//String info=m.getSender()+" 对 "+m.getGetter()+" 说         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
		//this.jta.append(info);// 4-16注释 有问题！!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
			
			//4-21 还有点问题，接收方图片显示不了
			if(m.equals(ImgPath)) {
				try {
					jta.setCaretPosition(doc.getLength());//4-24新内容接后
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
							m.getSender()+" 对 "+m.getGetter()+" 说         "+m.getSendTime()+"\n"+m.getCon()+"\r\n\n",
							null);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		
			
		/*
		//4-21night 图片之用
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
			//如果用户点击了，发送按钮
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			
			//4-16如果发送的是文本信息
			if("".equals(ImgPath))
			{
				
			//发送不能为空 4-10 
			if (jtf.getText().trim().equals("")) 
			{
				//System.out.println("发送不能为空,请重新输入！");
				JOptionPane.showMessageDialog(null, "发送不能为空,请重新输入！","温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			m.setCon(jtf.getText());
			jtf.setText(null); //3-20 添加 解决了发送之后
			//m.setSendTime(new java.util.Date().toString());4-21
			m.setSendTime(DateFormat.getTimeInstance().format(new Date()));//4-21 另一种时间
			
			//在自己的聊天界面显示发送的信息 4-21注释
			//String infomy=m.getSender()+" 对 "+m.getGetter()+" 说         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
			//this.jta.append(infomy); //4-16注释 有问题！!!!!!!!!!!!!!!!!!!!!!!!!
			
			try {
				doc.insertString(doc.getLength(),
						m.getSender()+" 对 "+m.getGetter()+" 说         "+m.getSendTime()+"\n"+m.getCon()+"\r\n\n",
						null);
				
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//发送给服务器.
			try {
				
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				oos.flush();//4-21night清空缓冲区数据
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
				
		}
			}
			
			
			//4-21发送图片    BUG1:选择图片之后再删掉，在发送文本信息自己聊天面板显示之前图片，聊天对象显示文本信息
			//BUG2:和自己聊天时候，发送显示的图片会置顶变乱
			else if (!"".equals(ImgPath)) 
			{
				
				//m.setCon(jtf.getText());//应该存在问题4-21  4-23 4-28
				
				jtf.setText(null); 
				m.setSendTime(DateFormat.getTimeInstance().format(new Date()));
				
				//在自己的聊天界面显示发送的信息
				//String infomy=m.getSender()+" 对 "+m.getGetter()+" 说         "+m.getSendTime()+"\n"+m.getCon()+"\r\n";
				//this.jta.append(infomy); //4-16注释 有问题！!!!!!!!!!!!!!!!!!!!!!!!!
				
				try {
					doc.insertString(doc.getLength(),
							m.getSender()+" 对 "+m.getGetter()+" 说         "+m.getSendTime()+"\n"+"\r\n",
							null);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try 
				{
					jta.setCaretPosition(doc.getLength());//4-24新输入的内容接到已输入内容的尾部了
					
					jta.insertIcon(new ImageIcon(ImageIO
							.read(new FileInputStream(ImgPath))));//4-21显示图片
					
					doc.insertString(doc.getLength(), "\n\n", null);//4-21night 换行之用
					
					/*
					 * 没有成功
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
				
				//发送完图片后置空图片路径
				ImgPath ="";
				
			}
			
			
			
			/*
			//4-16 发送图片
			else if (!"".equals(ImgPath)) 
			{
				try 
				{
					//置空发送框
					jtf.setText("");
					
					//接收框显示图片
					jta.insertIcon(new ImageIcon(ImageIO
							.read(new FileInputStream(ImgPath))));
					//发送图片方法有待继续
					
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
		
		//自己添加第二次  3-17
		else if(arg0.getSource()==jb1)
		{
			//如果用户点击了公式按钮
              //new TermEditDialog();    4-10修改
			
			//调用公式编辑插件  4-10 添加  author：tkx
			try 
			{
				Runtime.getRuntime().exec("D:\\毕设\\工具\\公式编辑插件\\MathType.exe");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
		}	
		
		/*
		//4-10 添加图片选择方法    没有写完，有待完善！！！
		else if (arg0.getSource()==jbPIC) 
		{
			//如果用户点击图片按钮
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
//				//读取[如果读不到就等待.]
//				ObjectInputStream ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				
//				Message m=(Message)ois.readObject();
//				
//				//显示
//				String info=m.getSender()+" 对 "+m.getGetter()+" 说:"+m.getCon()+"\r\n";
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
