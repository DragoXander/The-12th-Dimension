package com.bdinc.t12d.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.IReferences;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Level;

public class DisplayManager implements IReferences {
	
	private Canvas game = Game.canvas;
	//private Game main;
	Level lvl1 = new Level();
	public void init()
	{
		//System.out.println("S:");
		//game = Game.canvas;
		//main = new Game();
		//lvl1.create("level1.map");
		//System.out.println(lvl1);
		//LevelManager.setLevel(lvl1);
	}
	
	public void update(long delta)
	{
		
	}
	
	
	public void render(Graphics g)
	{
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
	
}
