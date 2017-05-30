package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import com.bdinc.t12d.main.Container;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.IReferences;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.main.LevelReader;
import com.bdinc.t12d.main.ResourcesManager;
import com.bdinc.t12d.maths.Map;

public class Level implements IReferences {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public ArrayList<Flame> flames = new ArrayList<Flame>();
	public ArrayList<Item> items = new ArrayList<Item>();
	
	private LevelReader reader = new LevelReader();
	
	private Map map = new Map();
	
	public Level() {}
	
	public Level(String file) {
		create(file);
	}
	
	public void init()
	{
		map.init();
		try
		{
			entities.add(Game.player);
		}
		catch(Exception e) {
			System.err.println("Can't add player to the entities list!");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void create(String file)
	{
		Level source = null;
		try
		{
			source = reader.readLevel("assets/levels/"+file);
		}
		catch(Exception e)
		{
			System.err.println("Can't read the level file: <"+file+">!");
			e.printStackTrace();
		}
		entities = source.entities;
		blocks = source.blocks;
		flames = source.flames;
		this.init();
	}
	
	public void load(Graphics g)
	{
		try
		{
			for(Block b : LevelManager.currentLevel.blocks) {
				if(b.getSprite() == null)
				{
					System.err.println("No sprite in block<"+b.toString()+">!");
				}
				b.draw(g);
			}
			for(Flame f : LevelManager.currentLevel.flames) {
				if(f.getSprite() == null)
				{
					System.err.println("No sprite in flame<"+f.toString()+">!");
				}
				f.draw(g);
			}
			if(entities.size() == 0) {
				System.err.println("No entities on the current level!");
			}
			if(blocks.size() == 0) {
				System.err.println("No blocks on the current level!");
			}
			for(Entity e : LevelManager.currentLevel.entities) {
				if(e.getSprite() == null)
				{
					System.err.println("No sprite in entity<"+e.toString()+">!");
				}
				e.draw(g);
			}
			g.drawImage(ResourcesManager.life, 10, 10, null);
			g.setColor(Color.WHITE);
			g.setFont(ResourcesManager.defaultFont);
			g.drawString(""+Game.player.getHealth(), 40, 25);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public Map getMap()
	{
		return map;
	}
	
}
