package com.direct.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class Bullet extends View
{
	
	private Image[] imgs =new Image[5];
	
	private int count = 0;
	private int sum = 0;
	
/*	public Bullet (String imgPath,int x,int y)//子弹的构造函数。imgPath是子弹图片路径；
	{
		this.x = x;
		this.y = y;
		this.img = Toolkit.getDefaultToolkit().getImage(Bullet.class.getResource(imgPath));	
	}*/
	
	public Bullet (int x,int y)//子弹的构造函数。imgPath是子弹图片路径；
	{
		this.x = x;
		this.y = y;
		for(int i=0;i<imgs.length;i++)
		{
			imgs[i] =Toolkit.getDefaultToolkit().getImage(Bullet.class.getResource("/images/Bullets/0"+(i + 1)+".gif"));
		}
		this.img = imgs[0];
	}
	
	public void drawImage(Graphics g)
	{
		this.img = imgs[count];
		g.drawImage(imgs[count], x,y,40,40,null);
		move();
		
		if(count<4)
		{
			if(sum%2 == 0)
			{
				 count ++;
			}
			sum++;
		}
	}
	public void move()
	{
		x=x+26;
		if(x>1100)
		{
			this.status = false;
			StartGame.bullets.remove(this);
		}
	}
}
