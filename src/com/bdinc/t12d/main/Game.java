package com.bdinc.t12d.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.bdinc.t12d.graphics.DisplayManager;
import com.bdinc.t12d.input.InputManager;
import com.bdinc.t12d.input.MouseInputManager;
import com.bdinc.t12d.input.MouseMotionManager;
import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.scenes.ProfilesListDialog;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.Timer;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private static boolean isRunning;
	public static long delta;
	public static Graphics g;
	
	public static Game canvas;
	
	public Level currentLevel;
	
	public static final int WIDTH = 1120; //32 x35
	public static final int HEIGHT = 704; //32 x22
	
	public static final String VERSION = "v1.0-build4";
	
	public static final boolean isDevelopmentBuild = true;
	public static boolean paused;
	
	public static boolean stopped;
	
	private DisplayManager display = new DisplayManager();
	private static ResourcesManager resources;
	private static MouseMotionManager mouseManager;
	private static MouseInputManager mouseInputManager;
	private static InputManager keyManager;
	
	public static LevelManager manager;
	public Level lvl1;
	public Map map;
	
	public static Color m_profileBtnColor;
	
	private static byte bufferCount;
	
	public static int m_playBtnX, m_playBtnY;
	public static int m_shopBtnX, m_shopBtnY;
	public static int m_optBtnX, m_optBtnY;
	public static int m_exitBtnX, m_exitBtnY;
	public static int m_profileBtnX, m_profileBtnY;
	public static int m_profileBtnWidth, m_profileBtnHeight;
	
	public static JFrame gameWindow;
	
	public static Entity player;
	
	public static Image m_playBtn, m_shopBtn, m_optBtn, m_exitBtn;
	
	private static Thread gameStream;
	public static Dimension screenSize;
	
	public void init()
	{
		map = new Map();
		mouseManager = new MouseMotionManager();
		mouseInputManager = new MouseInputManager();
		keyManager = new InputManager();
		map.init();
		LevelManager.setLevelByID(0);
		display.init();
		m_playBtn = resources.playBtn;
		m_shopBtn = resources.shopBtn;
		m_optBtn = resources.optBtn;
		m_exitBtn = resources.exitBtn;
		player = new Entity(resources.player);
		player.setMaxHealth(100);
		player.setHealth(100);
		player.setMagicCount(0);
		player.setMaxMagic(30);
		player.setName("Adam Robbins");
		player.setPosition(5, 1);
		m_profileBtnColor = Color.CYAN;
		ProfilesListDialog.init();
		if(screenSize.height <= 768 || screenSize.width <= 1366) {
			Options.bufferCount = 4;
			player.setSpeed(0.5f);
			player.setRunSpeed(1f);
		}
		else if (screenSize.height >= 768 || screenSize.width >= 1366){
			Options.bufferCount = 2;
			player.setSpeed(1f);
			player.setRunSpeed(1.5f);
		}
		System.out.println("BufferStrategy created with buffer count: " + Options.bufferCount);
		System.out.println("Music volume is: " + Options.musicVolume);
		System.out.println("Ambient volume is: " + Options.ambientVolume);
		System.out.println("Current profile is: <" + Options.profileName + ">");
		System.out.println("===========================");
		System.out.println("Initialization complete successfully!");
		System.out.println("===========================");
	}
	
	public static void pause(long millis) {
		try {
			gameStream.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void start()
	{
		isRunning = true;
		gameStream = new Thread(this);
		gameStream.start();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("===========================");
		System.out.println("The 12th Dimension");
		System.out.println("Version: 1.0");
		System.out.print("Build: v4 ");
		System.out.println("(Development Build)");
		System.out.println("Developed by <The Black Dragon Inc.> (C) 2017");
		System.out.println("Author: Alexandr Tulyakov");
		System.out.println("===========================");
		System.out.println("Screen size is: " + screenSize.width + "x" + screenSize.height);
		//Timer.waitSeconds(5);
	}
	
	public static void main(String[] args) {
		
		canvas = new Game();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				keyManager.keyTyped(e);
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keyManager.keyReleased(e);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				keyManager.keyPressed(e);
				
			}
		});
		canvas.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseInputManager.mouseReleased(e);
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				mouseInputManager.mousePressed(e);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				mouseInputManager.mouseExited(e);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(LevelManager.levelNumber == 0) {
					mouseInputManager.mouseEntered(e);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseInputManager.mouseClicked(e);
				
			}
		});
		canvas.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
					mouseManager.mouseMoved(e);
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
					mouseManager.mouseDragged(e);
				
			}
		});
		gameWindow = new JFrame("The 12th Dimension");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setIconImage(resources.gameIcon);
		gameWindow.setLayout(new BorderLayout());
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);
		gameWindow.add(canvas, BorderLayout.CENTER);
		
		gameWindow.pack();
		
		canvas.start();
		
	}
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	public static void stop() {
		gameStream.stop();
		gameWindow.dispose();
	}
	
	public long deltaTime()
	{
		return delta;
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(Options.bufferCount);
			requestFocus();
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(g == null) {
			System.err.println("Graphics lost!");
		}
		try
		{
			if(LevelManager.levelNumber > 0) {
				if(LevelManager.levelNumber == 1) {
					//g.clearRect(0, 0, WIDTH, HEIGHT);
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, WIDTH, HEIGHT);
				}
				try {
					LevelManager.currentLevel.load(g);
					//System.err.println(""+LevelManager.currentLevel.blocks.size());
				}
				catch(Exception e) {
					System.err.println("Can't load the level (Level.load(Graphics g))");
					System.err.println("Source: Game.render()");
					bs.dispose();
					e.printStackTrace();
				}
				
			}
			else if(LevelManager.levelNumber == -1) {
				//g.clearRect(0, 0, WIDTH, HEIGHT);
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				if(ProfilesListDialog.repaintWaiting()) {
					ProfilesListDialog.repaintProfiles(g);
					ProfilesListDialog.responseRepaint();
				}
				ProfilesListDialog.load(g);
			}
			else {
				/*
				 * #MainMenu level:
				 */
				g.drawImage(resources.logo, this.getWidth()/2-resources.logo.getWidth(null)/2, 50, null);
				
				m_playBtnX = this.getWidth()/2-m_playBtn.getWidth(null)/2;
				m_playBtnY = this.getHeight()/2 - 100;
				m_shopBtnX = this.getWidth()/2-m_shopBtn.getWidth(null)/2;
				m_shopBtnY = m_playBtnY+m_playBtn.getHeight(null)+5;
				m_optBtnX = this.getWidth()/2-m_optBtn.getWidth(null)/2;
				m_optBtnY = m_shopBtnY+m_shopBtn.getHeight(null)+5;
				m_exitBtnX = this.getWidth()/2-m_exitBtn.getWidth(null)/2;
				m_exitBtnY = m_optBtnY+m_optBtn.getHeight(null)+5;
				m_profileBtnX = Game.WIDTH-297;
				m_profileBtnY = Game.HEIGHT-94;
				m_profileBtnWidth = 290;
				m_profileBtnHeight = 39;
				g.drawImage(m_playBtn, m_playBtnX, m_playBtnY, null);
				g.drawImage(m_shopBtn, m_shopBtnX, m_shopBtnY, null);
				g.drawImage(m_optBtn, m_optBtnX, m_optBtnY, null);
				g.drawImage(m_exitBtn, m_exitBtnX, m_exitBtnY, null);
				
				//Профиль:
				g.setColor(Color.WHITE);
				g.drawRect(Game.WIDTH-300, Game.HEIGHT-150, 295, 100); //внешняя граница (фон)
				g.drawRect(Game.WIDTH-298, Game.HEIGHT-148, 51, 51); //граница аватара профиля
				g.drawImage(ResourcesManager.profile, Game.WIDTH-297, Game.HEIGHT-147, null);
				g.drawRect(Game.WIDTH-246, Game.HEIGHT-148, 240, 26);
				g.setFont(ResourcesManager.defaultFont);
				g.drawString(Options.profileName, Game.WIDTH-244, Game.HEIGHT-126);
				g.setFont(ResourcesManager.defaultFont14);
				g.drawString("Money: "+player.getMoney(), Game.WIDTH-244, Game.HEIGHT-104);
				g.drawRect(Game.WIDTH-298, Game.HEIGHT-95, 291, 40);
				g.setColor(m_profileBtnColor);
				g.fillRect(m_profileBtnX, m_profileBtnY, m_profileBtnWidth, m_profileBtnHeight);
				g.setColor(Color.black);
				String strSelectProfile = ">>   Select Profile";
				g.drawString(strSelectProfile, m_profileBtnX+50, m_profileBtnY+25);
				//Информация
				g.setColor(Color.white);
				g.setFont(ResourcesManager.defaultFont);
				g.drawString("The 12th Dimension", 5, getHeight()-40);
				g.drawString(VERSION, 5, getHeight()-10);
				g.setColor(Color.YELLOW);
				g.drawString("The Fantasy is real!", getWidth()/2+60, 70+resources.logo.getHeight(null));
				if(isDevelopmentBuild) {
					g.drawString("[Development Build]", 110, getHeight()-10);
				}
				g.setColor(Color.white);
				g.drawString("Copyright (C) BDINC 2017", getWidth()-235, getHeight()-10);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		long last = System.currentTimeMillis();
		
		init();
		display.init();
		while(isRunning)
		{
			//System.out.println(""+paused);
			if(!paused) {
				delta = System.currentTimeMillis() - last;
				last = System.currentTimeMillis();
				display.update(delta);
			}
			render();
			//System.out.println(""+player.getSpeed());
		}
		
		
	}

}
