/**
 * ���ܣ���ʽ�༭����
 * �޸�ʱ�䣺3-17
 * �޸��ˣ���ţ
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
    
   
    
    //3-20 ɾ���ģ��о�û���õ�
//    public String getTextContent()
//    {
//    	return jTextArea.getText();
//    }
    
    public TermEditDialog()
    {
    	jTextArea=new JTextArea();
    	jScrollPane=new JScrollPane(jTextArea);
    	jPanel=new JPanel();
    	okButton=new JButton("ȷ��");
    	cancelButton=new JButton("ȡ��");
    	
    	okButton.addActionListener(this); //3-20����
    	cancelButton.addActionListener(this); //3-20����
    	
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
		       3-20 ȡ��*/
    	
    	setLayout(new BorderLayout());
    	jPanel.add(okButton);
    	jPanel.add(cancelButton);
    	add(jScrollPane,BorderLayout.CENTER);
    	add(jPanel,BorderLayout.SOUTH);
    	setTitle("��ʽ�༭");
    	
    	setIconImage((new ImageIcon("image/bianxie.png").getImage()));  //�޸��ˣ�tkx 3-17
    	
    	setSize(300, 400);
    	setLocation(800, 200); //3-18
    	setVisible(true);
    }

	//3-20�޸�
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
