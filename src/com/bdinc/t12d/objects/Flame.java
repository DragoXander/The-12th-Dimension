package com.bdinc.t12d.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.main.ResourcesManager;
import com.bdinc.t12d.maths.IntVector2;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Vector2;

public class Flame {
	
	private float x, y;
	private int cellX, cellY;
	
	private Image sprite;
	
	private Map map = new Map();
	
	public boolean activated;
	
	public Flame() {
		if(activated) {
			sprite = ResourcesManager.flame;
		}
		else {
			sprite = ResourcesManager.flameOff;
		}
		
		try
		{
			map.init();
		}
		catch(Exception e)
		{
			System.err.println("Can't initialize the map...");
			System.err.println("Caused by entity<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public void setActive(boolean state) {
		if(state) {
			sprite = ResourcesManager.flame;
			activated = true;
		}
		else {
			sprite = ResourcesManager.flameOff;
			activated = false;
		}
	}
	
	public void activate() {
		if(!activated) {
			sprite = ResourcesManager.flame;
			BufferedWriter writer1 = null;
			BufferedWriter writer2 = null;
			try {
				writer1 = new BufferedWriter(new FileWriter("assets/saves/"+Game.profileName+"_info.dat"));
				writer1.write("LevelID:"+LevelManager.levelNumber+"\n");
				writer1.write("Player.position.x:"+Game.player.posX()+"\n");
				writer1.write("Player.position.y:"+Game.player.posY()+"\n");
				writer1.write("Player.position.cellX:"+Game.player.getCell().x+"\n");
				writer1.write("Player.position.cellY:"+Game.player.getCell().y+"\n");
				writer1.write("Player.health:"+Game.player.getHealth()+"\n");
				writer1.write("Player.maxHealth:"+Game.player.getMaxHealth()+"\n");
				writer1.write("Player.magic:"+Game.player.getMagicCount()+"\n");
				writer1.write("Player.maxMagic:"+Game.player.getMaxMagicCount()+"\n");
				writer1.write("Flame.cellX:"+this.cellX+"\n");
				writer1.write("Flame.cellY:"+this.cellY+"\n");
				writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Game.profileName+"_blocks.dat"));
				for(Block b : LevelManager.currentLevel.blocks) {
					writer2.write(""+b);
					writer2.newLine();
				}
				if(writer2 != null) {
					writer2.close();
				}
				writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Game.profileName+"_entities.dat"));
				for(Entity e: LevelManager.currentLevel.entities) {
					writer2.write(""+e);
					writer2.newLine();
				}
				if(writer2 != null) {
					writer2.close();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(writer1 != null) {
					try {
						writer1.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
				if(writer2 != null) {
					try {
						writer2.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			activated = true;
		}
	}
	
	public void setPosition(int x, int y) {
		Vector2 pos = null;
		try
		{
			pos = map.getCell(x, y);
		}
		catch(Exception e)
		{
			System.err.println("Can't set the location to entity<"+this.toString()+">!");
		}
		
		this.cellX = x;
		this.cellY = y;
		this.x = pos.x;
		this.y = pos.y;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(Object e : LevelManager.currentLevel.flames.toArray()) {
			Entity ent = (Entity)e;
			if(this.equals(ent))
			{
				return "t12d:flame#"+id;
			}
			id++;
		}
		return "t12d:flame#???(null)";
	}
	
	public void draw(Graphics g)
	{
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by flame<"+this.toString()+">!");
		}
		try
		{
			g.drawImage(sprite, (int)x, (int)y, null);
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the flame<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public IntVector2 getCell() {
		return new IntVector2(cellX, cellY);
	}
	
	public float posX() {
		return x;
	}
	
	public float posY() {
		return y;
	}
	
}
