package com.direct.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.font.GraphicAttribute;

public class Plane extends View
{
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	
	public Plane (String imgPath)//飞机的构造函数。imgPath是飞机图片路径；
	{
		this.img = Toolkit.getDefaultToolkit().getImage(Plane.class.getResource(imgPath));
		
	}
	public void drawImage(Graphics g)//画飞机的方法；
	{
		g.drawImage(img,x,y,120,100,null);
		move();
	}
	public Rectangle getRect()
	{
		return new Rectangle(x,y+50,img.getWidth(null)-320,img.getHeight(null)-340);
	}
	public void move()//飞机移动的方法
	{
		if(left && !right && !up && !down)
		{
			if(x>10)
			x=x-24;
		}
		if(right && !left && !up && !down)
		{
			if(x<850)
			x=x+24;
		}
		if(up && !right && !left && !down)
		{
			if(y>10)
			y=y-24;
		}
		if(down && !right && !up && !left)
		{
			if(y<626)
			y=y+24;
			else if(y>626)
				y=626;
		}
		
		if(left && !right && up && !down)
		{
			if(x>10 && y>10)
			{
				x=x-24;
				y=y-24;
			}
		}
		if(!left && right && up && !down)
		{
			if(x<850 && y>10)
			{
				x=x+24;
				y=y-24;
			}
		}
		if(left && !right && !up && down)
		{
			if(x>10 && y<626)
			{
				x=x-24;
				y=y+24;
			}
			if(y>626) y=626;
		}
		if(!left && right && !up && down)
		{
			if(x<850 && y<626)
			{
				x=x+24;
				y=y+24;
			}
			if(y>626) y=626;
			
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		char keychar = e.getKeyChar();
		switch (keychar) 
		{
		case 'a':  
		case 'A':
			left = true;
			break;
		case 'd':
		case 'D':
			right=true;
			break;
		case 'x':
		case 'X':
			down=true;
			break;
		case 'w':
		case 'W':
			up=true;
			break;
		case 'j':
		case 'J':
			//创建子弹；
			StartGame.bullets.add(new Bullet(x+100,y+40));
			//调出声音；
			new sound("/music/beam.mp3").play();
			break;
		default:
			break;
		}
	}
	public void keyReleased(KeyEvent e)
	{
		char keychar = e.getKeyChar();
		switch (keychar) 
		{
		case 'a':  
		case 'A':
			left = false;
			break;
		case 'd':
		case 'D':
			right= false;
			break;
		case 'x':
		case 'X':
			down= false;
			break;
		case 'w':
		case 'W':
			up= false;
			break;
		default:
			break;
		}
	}
}
