package com.bdinc.t12d.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.bdinc.t12d.graphics.DisplayManager;
import com.bdinc.t12d.input.InputManager;
import com.bdinc.t12d.input.MouseInputManager;
import com.bdinc.t12d.input.MouseMotionManager;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Level;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning;
	private long delta;
	private Graphics g;
	
	public static Game canvas;
	
	public Level currentLevel;
	
	public static final int WIDTH = 1120; //32 x35
	public static final int HEIGHT = 704; //32 x22
	
	private DisplayManager display = new DisplayManager();
	private static ResourcesManager resources;
	private static MouseMotionManager mouseManager;
	private static MouseInputManager mouseInputManager;
	private static InputManager keyManager;
	
	public static LevelManager manager;
	public Level lvl1;
	public Map map;
	
	public static int m_playBtnX, m_playBtnY;
	public static int m_loadBtnX, m_loadBtnY;
	public static int m_optBtnX, m_optBtnY;
	public static int m_exitBtnX, m_exitBtnY;
	
	public static Entity player;
	
	public static Image m_playBtn, m_loadBtn, m_optBtn, m_exitBtn;
	
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
		m_loadBtn = resources.loadBtn;
		m_optBtn = resources.optBtn;
		m_exitBtn = resources.exitBtn;
		player = new Entity(resources.player);
		player.setMaxHealth(100);
		player.setHealth(100);
		player.setMagicCount(0);
		player.setMaxMagic(30);
		player.setName("Adam Robbins");
		player.setPosition(5, 1);
		
	}
	
	public void start()
	{
		isRunning = true;
		new Thread(this).start();
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
				mouseInputManager.mouseEntered(e);
				
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
		JFrame gameWindow = new JFrame("The 12th Dimension");
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
	
	public long deltaTime()
	{
		return delta;
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(4);
			requestFocus();
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(g == null)
		{
			System.err.println("Graphics lost!");
		}
		try
		{
			if(LevelManager.levelNumber > 0) {
				
				LevelManager.currentLevel.load(g);
			}
			else {
				//#MainMenu level:
				g.drawImage(resources.logo, this.getWidth()/2-resources.logo.getWidth(null)/2, 50, null);
				
				m_playBtnX = this.getWidth()/2-m_playBtn.getWidth(null)/2;
				m_playBtnY = this.getHeight()/2 - 100;
				m_loadBtnX = this.getWidth()/2-m_loadBtn.getWidth(null)/2;
				m_loadBtnY = m_playBtnY+m_playBtn.getHeight(null)+5;
				m_optBtnX = this.getWidth()/2-m_optBtn.getWidth(null)/2;
				m_optBtnY = m_loadBtnY+m_loadBtn.getHeight(null)+5;
				m_exitBtnX = this.getWidth()/2-m_exitBtn.getWidth(null)/2;
				m_exitBtnY = m_optBtnY+m_optBtn.getHeight(null)+5;
				
				g.drawImage(m_playBtn, m_playBtnX, m_playBtnY, null);
				g.drawImage(m_loadBtn, m_loadBtnX, m_loadBtnY, null);
				g.drawImage(m_optBtn, m_optBtnX, m_optBtnY, null);
				g.drawImage(m_exitBtn, m_exitBtnX, m_exitBtnY, null);
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
		
		//int i = 0;
		while(isRunning)
		{
			delta = System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
			
			display.update(delta);
			
			render();
		}
		
		
	}

}
