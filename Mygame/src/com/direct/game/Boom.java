package com.direct.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Boom 
{
	private Image img;//爆炸图片
	private int x=8;//飞机的x坐标
	private int y=200;//飞机的y坐标
	
	public Boom (String imgpath, int x,int y)//子弹的构造函数。imgPath是子弹图片路径；
	{
		this.x = x;
		this.y = y;
		this.img=Toolkit.getDefaultToolkit().getImage(Boom.class.getResource(imgpath));	
	}
	
	public void drawImage(Graphics g)//画爆炸图的方法；
	{
		g.drawImage(img,x,y,120,100,null);
	}
}
