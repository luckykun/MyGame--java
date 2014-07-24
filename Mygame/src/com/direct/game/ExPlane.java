package com.direct.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExPlane extends View
{
	Random random = new Random();
	private int count =0;
	
	List<ExBullet> exBullets =new ArrayList<ExBullet>();
	
	public ExPlane (String imgPath,int x,int y)//敌机的构造函数。
	{
		this.x = x;
		this.y = y;
		this.img = Toolkit.getDefaultToolkit().getImage(ExPlane.class.getResource("/images/exp_0"+(random.nextInt(4)+1)+".gif"));
		
	}
	
	public void drawImage(Graphics g)//画敌机的方法；
	{
		count++;
		if(count%20==0)
		{
			exBullets.add(new ExBullet("/images/00.gif",x,y+50));
		}
		g.drawImage(img,x,y,100,70,null);
		move();
	}
	public void move()
	{
		x=x-random.nextInt(15);
		if(x<-100)
		{
			this.status = false;
			StartGame.explanes.remove(this);
		}
	}
	//@override
	public List<ExBullet> getBullets()
	{
		return exBullets;
	}
}
