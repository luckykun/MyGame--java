package com.direct.game;

import java.awt.Image;
import java.awt.Rectangle;

public class View 
{
	protected  boolean status=true;
	protected Image img;//飞机图片
	protected int x=8;//飞机的x坐标
	protected int y=200;//飞机的y坐标
	
	public Rectangle getRect()
	{
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
}
