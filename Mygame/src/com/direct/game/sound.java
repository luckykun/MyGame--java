package com.direct.game;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;


public class sound
{
	private InputStream music;
	private AdvancedPlayer player;
	private boolean flag;
	private String musicpath;
	
	public sound(InputStream stram)//构造函数，传入的是根据文件路径得到的文件流；
	{
		this.music = stram;
	}
	public sound(String musicpath)//构造函数，传入参数是文件的路径；
	{
		this.musicpath = musicpath;
	}
	
	public void play()//歌曲播放一次
	{
		flag = false;
		new PlayThread().start();
	}
	public void playLoop()//歌曲循环播放
	{
		flag = true;
		new PlayThread().start();
	}
	
	class PlayThread extends Thread 
	{
		public void run ()
		{
			do
			{
				try
				{
					music = sound.class.getResourceAsStream(musicpath);
					player = new AdvancedPlayer(music);
					player.play();
				}
				catch(JavaLayerException e )
				{
					e.printStackTrace();
				}
			}
			while(flag);
		}
	}
	
}
