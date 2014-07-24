package com.direct.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class background 
{
	private Image img1,img2;
	private int x1=0,y1=0;
	private int x2=1000,y2=0;
	
	public background(String imgPath)//飞机的构造函数。imgPath是飞机图片路径；
	{
		this.img1 = this.img2 = Toolkit.getDefaultToolkit().getImage(background.class.getResource(imgPath));
	}
		
	
	public void drawImage(Graphics g)
	{
		g.drawImage(img1,x1,y1,1000,700,null);
		g.drawImage(img2,x2,y2,1000,700,null);
		move();
	}
	public void move()
	{
		x1 = x1-8;
		if(x1<=-990)
		{x1=1000;}
		x2 = x2-8;
		if(x2<=-990)
		{x2=1000;}
	}
}
