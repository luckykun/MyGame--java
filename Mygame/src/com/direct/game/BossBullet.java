package com.direct.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BossBullet extends View
{
	private int y1=200;
	private int y2=200;
	private int y3=200;
	private int y4=200;
	private int count=0;
	private int sum=0;
	Image[] imgs =new Image[2];
	
	public BossBullet (int x,int y)//子弹的构造函数。imgPath是子弹图片路径；
	{
		this.x = x;
		this.y =this.y1=this.y2=this.y3=this.y4=y;
		for(int i=0;i<imgs.length;i++)
		{
			imgs[i] =Toolkit.getDefaultToolkit().getImage(Bullet.class.getResource("/images/0"+(i+1)+".gif"));
		}
		this.img = imgs[0];		
	}
	
	public void drawImage(Graphics g)
	{
		g.drawImage(imgs[count], x+200,y+150,null);
		g.drawImage(imgs[count], x+200,y1+150,null);
		g.drawImage(imgs[count], x+200,y2+150,null);
		g.drawImage(imgs[count], x+200,y3+150,null);
		g.drawImage(imgs[count], x+200,y4+150,null);
		move();
	}
	public void move()
	{
		x=x-20;
		y1=y1-5;
		y2=y2+5;
		y3=y3+10;
		y4=y4+10;
		if(x<-700)
		{
			this.status =false;
		}
	}
}
