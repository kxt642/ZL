/**
 * ��ʽ�༭ҳ��
 * �޸��ˣ�tkx
 * ��һ���޸�ʱ�䣺3-17
 */

package com.qq.client.view;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class GSBJ extends Applet{
	Button north, south, east, west, center;
	public void init(){
		north = new Button("North");
		south = new Button("South");
		east = new Button("East");
		west = new Button("West");
		center = new Button("Center");
		setLayout(new BorderLayout());
		add(east,BorderLayout.EAST);
		add(center,BorderLayout.CENTER);
		add(north,BorderLayout.NORTH);
		add(south,BorderLayout.SOUTH);
		add(west,BorderLayout.WEST);
	
}
	
	
	//��ʽ�༭
//	public void GSBJ(){
//		
//	}
}
