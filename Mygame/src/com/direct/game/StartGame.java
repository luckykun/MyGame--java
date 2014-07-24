package com.direct.game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
/*jian_zhi_kun
2014-7-17*/
/*游戏窗口类*/
public class StartGame extends Frame
{
	 private background bgground = new background("/images/bg_01.gif");//创建背景图片类
	 private Plane myPlane =new Plane("/images/my_plane.gif");//创建飞机类
	 //创建敌机类
	 static List<ExPlane> explanes = new ArrayList<ExPlane>();
	 private BossPlane bossPlane =new BossPlane(380,150);//创建Boss类
	 static List<Bullet> bullets = new ArrayList<Bullet>();//创建子弹类（数组）
	 private GameOver overimg = new GameOver("/images/Gameover.jpg");
	 private int width;
	 private int height;
	 private boolean flag = true;
	 private int expsum;//定义杀掉敌机的数量
	 private int bloodwidth=120;//定义血条的长度；
	//定义头像图片；
	 private Image userimg =Toolkit.getDefaultToolkit().getImage(StartGame.class.getResource("/images/user.gif"));
	 
     public StartGame (int width,int height,String title)
     {
    	 this.width = width;
    	 this.height = height;
    	 //设置窗口大小；
    	 this.setSize(width,height);
    	//设置窗口标题；
    	 this.setTitle(title);
    	 //设置窗口居中显示
    	 this.setLocationRelativeTo(null);
    	 //添加窗口监听事件
    	 this.addWindowListener(new WindowListener());
    	 //添加键盘监听事件
    	 this.addKeyListener(new KeyListener());
    	//设置窗口可见；
    	 this.setVisible(true);
    	//启动线程类;
    	 new MainThread().start();
    	 //开启背景音乐
    	 new sound("/music/game_music.mp3").playLoop();//调用第二个构造函数，直接得到路径；
     }
     @Override
     public void update(Graphics g)
     {
    	 Image bufimg = this.createImage(width,height);//创建一张图片
    	 Graphics bufGs = bufimg.getGraphics();//获取该图片的画笔
    	 bufGs.setColor(getBackground());//给画笔设置颜色
    	 bufGs.fillRect(0, 0, width, height);//在图片中画一个矩形，宽高度由窗口大小决定；
    	 //下面的图像全部设置在创建的图层中；
    	 paint(bufGs);
    	 g.drawImage(bufimg, 0, 0, this);//真正能看到的执行代码，使用窗口的画笔画出图片。
     }
	@Override
	public void paint(Graphics g) /*绘制容器-----当前窗口*/
	{
		if(bloodwidth>0)
		{
			bgground.drawImage(g);//画出背景图
			g.setColor(Color.RED);
			g.drawString("当前敌军数量："+ explanes.size(),50,50);   //画出统计信息
			g.drawString("当前消灭敌军的数量："+ expsum,200,50);
			g.drawImage(userimg,20,590,70,100,this);
			g.setColor(Color.red);
			g.fillRect(100,660, bloodwidth, 20);
			myPlane.drawImage(g);//画出我的飞机
			//画出敌机,也要画出敌机的子弹
			for(int i=0;i<explanes.size();i++)
			{
				ExPlane explane = explanes.get(i);
				
				if(explane.status)//判断敌机状态为true
				{
					explane.drawImage(g);
					List<ExBullet> exBullets = explane.getBullets();//创建敌机子弹集合
					for(int j=0;j<exBullets.size();j++)
					{
						ExBullet exBullet = exBullets.get(j);//得到每一个敌机子弹
						if(exBullet.getRect().intersects(myPlane.getRect()))//判断敌机子弹是否与我的飞机相交
						{
							bloodwidth =bloodwidth-20;
							exBullet.status = false;//相交之后，子弹状态改为false
							exBullets.remove(exBullet);//在集合中删除子弹
							new sound("/music/plane_down.mp3").play();
						}
						if(exBullet.status)
						{
							exBullet.drawImage(g);
						}
					}
					//判断当前敌机是否与我的子弹相交
					for(int k =0;k<bullets.size();k++)
					{
						Bullet bullet =bullets.get(k);
						if(bullet.getRect().intersects(explane.getRect()))//判断我的子弹是否打中敌机；
						{
							expsum++;
							bullet.status=false;
							bullets.remove(bullet);
							explane.status=false;
							explanes.remove(explane);
							new Boom ("/images/bg_01.gif",explane.x,explane.y).drawImage(g);
							new sound("/music/enemy_down.mp3").play();
						}
					}
				}
			}
			
			if(expsum>=20)//画出boss
			{
				bossPlane.drawImage(g);
				List<BossBullet> bossbullets = bossPlane.bossbullets;
						for(int i =0;i<bossbullets.size();i++)
						{
							BossBullet bossbullet = bossbullets.get(i);
							if(bossbullet.status)
							{
								bossbullet.drawImage(g);//画出敌机子弹
							}
						}
				flag = false;
			}
			//画出子弹
			for(int i =0;i<bullets.size();i++)
			{
				Bullet bullet = bullets.get(i);
				bullet.drawImage(g);
			}
		}
		else
		{
			new Boom("/images/bg_01.gif",myPlane.x,myPlane.y).drawImage(g);//创建爆炸图片
			/**/;
			overimg.drawImage(g);
		}
		
	}
	
	/*线程类，用于重复调用paint，使图像移动*/
    class MainThread extends Thread
	{
		public void run ()
		{
			Random random = new Random();
			int fg =0;
			while (true)
			{
				fg++;
				try
				{
					Thread.sleep(200);//让当前线程休眠100毫秒再执行
					repaint();//repaint函数会重复调用paint();
					//隔一段时间创建敌机，放进集合；
					if(fg%5==0 && flag== true)
					{
						explanes.add(new ExPlane("/images/exp_01.gif",1000,random.nextInt(450)));
					}
					
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
     
	class WindowListener extends WindowAdapter//自定义窗口事件
	{
		public void windowClosing (WindowEvent e)//点击窗口关闭按钮时触发此事件
		{
			System.exit(0);
		}
	}
  
	class KeyListener extends KeyAdapter//自定义键盘事件
	{

		@Override  /*按下键盘时触发的事件*/
		public void keyPressed(KeyEvent e) 
		{
			if(bloodwidth>0)
			myPlane.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) 
		{
			if(bloodwidth>0)
			myPlane.keyReleased(e);
		}
		
	}
}
