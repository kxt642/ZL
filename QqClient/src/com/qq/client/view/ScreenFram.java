/**
 * 4-29
 * autor:tkx
 * 功能：截图
 * 
 */
package com.qq.client.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import com.sun.org.apache.bcel.internal.generic.NEW;



/**
 * java截屏 运行后将当前屏幕截取，并最大化显示。 拖拽鼠标，选择自己需要的部分,松开鼠标后，截图插入发送框jtf。
 * 
 * 
 * 
 */

public class ScreenFram 
{
	public static void main()
	{
		// 全屏运行  
		RectD rd = new RectD();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		gd.setFullScreenWindow(rd);
		System.out.println("main方法中的截图名："+ rd.sc_name);
		//return rd.sc_name;//4-30
	}
}

class RectD extends JFrame
{
	private static final long serialVersionUID = 1L;
	int orgx, orgy, endx, endy;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	BufferedImage image;
	BufferedImage tempImage;
	BufferedImage saveImage;
	Graphics g;
	public String sc_name;
	
	String sc_path;//4-30回宿舍之后
	
	public void paint(Graphics g)
	{
		RescaleOp ro = new RescaleOp(0.8f, 0, null);
		tempImage = ro.filter(image, null);
		g.drawImage(tempImage, 0, 0, this);
	}
	
	public RectD()
	{
		snapshot();
		setVisible(true);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				endx = e.getX();
				endy = e.getY();
				g = getGraphics();
				g.drawImage(tempImage, 0, 0, RectD.this);
				int x = Math.min(orgx, endx);
				int y = Math.min(orgy, endy);
				int width = Math.abs(endx - orgx) + 1;
				int height = Math.abs(endy - orgy) + 1;
				// 加上1，防止width或height为0
				g.setColor(Color.BLUE);
				g.drawRect(x - 1, y - 1, width + 1, height + 1);
				// 减1，加1都是为了防止图片将矩形框覆盖掉
				saveImage = image.getSubimage(x, y, width, height);
				g.drawImage(saveImage, x, y, RectD.this);
			}
		});
		
		this.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				orgx = e.getX();
				orgy = e.getY();
			}

			public void mouseReleased(MouseEvent e)
			{
				saveToFile();
				
				//4-30回宿舍之后
				try {
					QqChat.jtf.insertIcon(new ImageIcon(ImageIO
							.read(new FileInputStream(sc_path))));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//4-30
				
				dispose();
			}
			
		});
	
	}
	
	public void saveToFile()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
		 //sc_name ="snap"; //4-30注释
		
		 //sdf.format(new Date());
//		setName(sdf.format(new Date()));
		
		sc_name=sdf.format(new Date());//4-30afternoon
		QqChat.screenCutImgName=sc_name;//4-30回宿舍之后写的
		
		String path = "./image/screenCut/";
		String format = "jpg";
		//File f = new File(path, name + "." + format);//4-30afternoon注释
		File fsc = new File(path+File.separator+sc_name+"."+format);//4-30afternoon
		
		sc_path=path+File.separator+sc_name+"."+format;//4-30回宿舍之后写的
		QqChat.screenCutpath=path+File.separator+sc_name+"."+format;//4-30回宿舍之后写的
		
		try
		{
			ImageIO.write(saveImage, format, fsc);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void snapshot()
	{
		try
		{
			Robot robot = new Robot();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			image = robot.createScreenCapture(new Rectangle(0, 0, d.width,
					d.height));
		}
		catch (AWTException e)
		{

		}
	}
}
