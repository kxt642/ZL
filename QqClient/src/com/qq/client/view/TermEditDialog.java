/**
 * 功能：公式编辑界面
 * 修改时间：3-17
 * 修改人：大牛
 * 
 */


package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;




public class TermEditDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTextArea jTextArea;
    private JScrollPane jScrollPane;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel jPanel;
    
   
    
    //3-20 删除的，感觉没有用到
//    public String getTextContent()
//    {
//    	return jTextArea.getText();
//    }
    
    public TermEditDialog()
    {
    	jTextArea=new JTextArea();
    	jScrollPane=new JScrollPane(jTextArea);
    	jPanel=new JPanel();
    	okButton=new JButton("确定");
    	cancelButton=new JButton("取消");
    	
    	okButton.addActionListener(this); //3-20增加
    	cancelButton.addActionListener(this); //3-20增加
    	
    	/*
    	okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		      QqChat.jtf.setText(jTextArea.getText());
		           
		         
			}
		});
    	cancelButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();		
			}
		});
		       3-20 取消*/
    	
    	setLayout(new BorderLayout());
    	jPanel.add(okButton);
    	jPanel.add(cancelButton);
    	add(jScrollPane,BorderLayout.CENTER);
    	add(jPanel,BorderLayout.SOUTH);
    	setTitle("公式编辑");
    	
    	setIconImage((new ImageIcon("image/bianxie.png").getImage()));  //修改人：tkx 3-17
    	
    	setSize(300, 400);
    	setLocation(800, 200); //3-18
    	setVisible(true);
    }

	//3-20修改
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==okButton) 
		{
			 QqChat.jtf.setText(jTextArea.getText());
			 jTextArea.setText(null);
		}
		else if (e.getSource()==cancelButton) 
		{
			dispose();
		}
		
	}
}
