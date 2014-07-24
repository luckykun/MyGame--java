package com.direct.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameOver 
{
	private Image img;
	private int x=0;
	private int y=0;
	
	public GameOver(String imgpath)
	{
		this.img = Toolkit.getDefaultToolkit().getImage(GameOver.class.getResource(imgpath));
	}
	public void drawImage(Graphics g)
	{
		g.drawImage(img,x,y,1000,700,null);
	}
}
