/**
 * 4-28
 * autor:tkx
 * ���ܣ�����֮��--�������Լ���
 * 
 */

package com.qq.client.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

public class FontAttr 
{
	public String ToString()
	{
		return "FontAttr [fontName=" + fontName + ", fontStyle="
				+ fontStyle + ", fontSize=" + fontSize + ", foreColor="
				+ foreColor + ", backColor=" + backColor + "]";
	}

	private String fontName;
	private int fontStyle;
	private int fontSize;
	private Color foreColor = null, backColor = null;
	private SimpleAttributeSet attrSet = new SimpleAttributeSet();
	
	public FontAttr(SimpleAttributeSet sas, Font f) {
		this(f);
		this.attrSet = sas;
	}
	
	public FontAttr(Font f, Color foreColor, Color backColor) {
		this(f);
		this.foreColor = foreColor;
		this.backColor = backColor;
	}

	public FontAttr(Font f) {
		this.fontName = f.getName();
		this.fontSize = f.getSize();
		this.fontStyle = f.getStyle();
	}
	
	public SimpleAttributeSet getAttributeSet() {
		if (fontName != null && !"".equals(fontName)) {
			StyleConstants.setFontFamily(attrSet, fontName);
		}
		if (fontStyle == 0) { // ����
			StyleConstants.setBold(attrSet, false);
			StyleConstants.setItalic(attrSet, false);
		} else if (fontStyle == 1) { // ����
			StyleConstants.setBold(attrSet, true);
			StyleConstants.setItalic(attrSet, false);
		} else if (fontStyle == 2) { // б��
			StyleConstants.setBold(attrSet, false);
			StyleConstants.setItalic(attrSet, true);
		} else if (fontStyle == 3) { // ���� б��
			StyleConstants.setBold(attrSet, true);
			StyleConstants.setItalic(attrSet, true);
		}
		if (foreColor != null) { // ����ɫ
			StyleConstants.setForeground(attrSet, foreColor);
		}
		if (backColor != null) { // ǰ��ɫ
			StyleConstants.setBackground(attrSet, backColor);
		}
		if (fontSize != -1) {
			StyleConstants.setFontSize(attrSet, fontSize);
		}

		return attrSet;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
