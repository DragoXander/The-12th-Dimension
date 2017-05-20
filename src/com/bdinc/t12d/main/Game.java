package com.bdinc.t12d.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.bdinc.t12d.graphics.DisplayManager;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.objects.Block;
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
	public static LevelManager manager;
	public Level lvl1;
	public Map map;
	
	public void init()
	{
		map = new Map();
		map.init();
		lvl1 = new Level();
		lvl1.create("level1.map");
		LevelManager.setLevel(lvl1);
		display.init();
		//initRender();
		System.out.println("");
	}
	
	public void start()
	{
		isRunning = true;
		new Thread(this).start();
	}
	
	public static void main(String[] args) {
		
		canvas = new Game();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
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
	
	public void initRender()
	{
		BufferStrategy bs = canvas.getBufferStrategy();
		if(bs == null)
		{
			canvas.createBufferStrategy(2);
			canvas.requestFocus();
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.dispose();
		
		bs.show();
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
		BufferStrategy bs = canvas.getBufferStrategy();
		if(bs == null)
		{
			canvas.createBufferStrategy(2);
			canvas.requestFocus();
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.dispose();
		
		bs.show();
		try
		{
			//LevelManager.currentLevel.load(g);
			for(Block b : LevelManager.currentLevel.blocks) {
				b.draw(g);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		long last = System.currentTimeMillis();
		
		init();
		
		int i = 0;
		while(isRunning)
		{
			delta = System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
			
			display.update(delta);
			
			render();
		}
		
		
	}

}
