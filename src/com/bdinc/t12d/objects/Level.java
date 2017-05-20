package com.bdinc.t12d.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import com.bdinc.t12d.main.Container;
import com.bdinc.t12d.main.IReferences;
import com.bdinc.t12d.main.LevelReader;
import com.bdinc.t12d.maths.Map;

public class Level implements IReferences {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public ArrayList<Particle> particles = new ArrayList<Particle>();
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
		
	}
	
	@SuppressWarnings("unchecked")
	public void create(String file)
	{
		//HashMap<ArrayList<Block>, ArrayList<Entity>> source = null;
		Level source = null;
		//System.out.println("A:"+file);
		String s = "";
		try
		{
			//System.out.println("A:"+file);
			source = reader.readLevel("assets/levels/"+file);
			//reader.read("assets/levels/"+file);
			//System.out.println("FF");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println("S:"+source);
		}
		
		//System.out.println("S:"+source);
		//entities = (ArrayList<Entity>)source.values().toArray()[0];
		//blocks = (ArrayList<Block>)source.keySet().toArray()[0];
		entities = source.entities;
		blocks = source.blocks;
		//System.out.println("S:"+source.getValue().toArray()[0]);
		//System.out.println("S:");
	}
	
	public void load(Graphics g)
	{
		try
		{
			//System.err.println(g);
			for(Block b : blocks) {
				b.draw(g);
			}
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
