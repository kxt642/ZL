/**
 * 4-29
 * autor:tkx
 * 功能：现在是用来发送显示表情
 * 
 */

package com.qq.common;


import java.awt.Color;
import java.awt.Image;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;



import com.qq.client.view.QqChat;
import com.qq.common.MyTools;;

public class MyTextPane 
{
	JTextPane textPane=null;
	StyledDocument  document=null;
	
	public MyTextPane(JTextPane textPane)
	{
		this.textPane=textPane;
		document=this.textPane.getStyledDocument();
	}
	
	public void addIcon(String imagePath,String friend)
	{
		try
		{
			String time= DateFormat.getTimeInstance().format(new Date());
			document.insertString(document.getLength(), friend+" "+time+"\n", MyTextPane.getTimeAttribute());
			textPane.setCaretPosition(document.getLength());
			textPane.insertIcon(MyTools.getIcon(imagePath));
			document.insertString(document.getLength(), "\n", getFriendAttribute());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addIcon(Image image,String friendName)
	{
		try
		{
			String time= DateFormat.getTimeInstance().format(new Date());
			document.insertString(document.getLength(), friendName+" "+time+"\n", MyTextPane.getTimeAttribute());
			textPane.setCaretPosition(document.getLength());
			textPane.insertIcon(new ImageIcon(image));
			document.insertString(document.getLength(), "\n", getFriendAttribute());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static SimpleAttributeSet getTimeAttribute()
	{
		return getFontAttribute("黑体", 14, Color.DARK_GRAY, false, false);
	}

	public static SimpleAttributeSet getFriendAttribute()
	{
		return getFontAttribute("黑体", 16, Color.blue, false, false);
	}

	public static SimpleAttributeSet getFontAttribute(String name, int size, Color color,
			boolean bold, boolean underline)
	{
		SimpleAttributeSet attribute = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attribute, name);
		StyleConstants.setFontSize(attribute, size);
		StyleConstants.setForeground(attribute, color);
		StyleConstants.setBold(attribute, bold);
		StyleConstants.setUnderline(attribute, underline);
		return attribute;
	}
	
	public static SimpleAttributeSet getMyAttribute()
	{
		return getFontAttribute("楷体", 22, Color.red, false, true);
	}

}
