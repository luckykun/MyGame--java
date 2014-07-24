package com.direct.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ExBullet extends View
{
	
	int count=0;
	
	public ExBullet (String imgPath,int x,int y)//子弹的构造函数。imgPath是子弹图片路径；
	{
		this.x = x;
		this.y = y;
		this.img = Toolkit.getDefaultToolkit().getImage(Bullet.class.getResource(imgPath));	
	}
	
	public void drawImage(Graphics g)
	{
		g.drawImage(img, x,y,null);
		move();
	}
	public void move()
	{
		x=x-20;
	}
}
