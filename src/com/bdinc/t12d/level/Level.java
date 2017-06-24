package com.bdinc.t12d.level;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.objects.Particle;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.Container;
import com.bdinc.t12d.utils.ColorManager;

public class Level {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public ArrayList<Flame> flames = new ArrayList<Flame>();
	public ArrayList<Item> items = new ArrayList<Item>();
	public ArrayList<Particle> playerMagic = new ArrayList<Particle>();
	public ArrayList<Particle> enemyMagic = new ArrayList<Particle>();
	
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
			//System.out.println("Level has been read");
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
//			if(entities.size() == 0) {
//				System.err.println("No entities on the current level!");
//			}
//			if(blocks.size() == 0) {
//				System.err.println("No blocks on the current level!");
//			}
			for(Entity e : LevelManager.currentLevel.entities) {
				if(e.getSprite() == null)
				{
					System.err.println("No sprite in entity<"+e.toString()+">!");
				}
				e.draw(g);
			}
			for(Particle p : LevelManager.currentLevel.particles) {
				if(p.getSprite() == null) {
					System.err.println("No sprite in particle<"+p.toString()+">!");
				}
				p.draw(g);
			}
			int lifeStrX = 10+ResourcesManager.life.getWidth(null)+5;
			int moneyX = lifeStrX+70;
			int moneyStrX = moneyX+21;
			int rubyX = moneyStrX+70;
			int rubyStrX = rubyX + 21;
			g.setColor(ColorManager.getAlphaColor(ColorManager.VIOLET, 60));
			g.fillRect(0, 0, Game.WIDTH, 30);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH-240, 2, 235, 24);
			g.drawImage(ResourcesManager.life, 10, 10, null);
			g.drawImage(ResourcesManager.coin10_X16, moneyX, 10, null);
			g.drawImage(ResourcesManager.ruby, rubyX, 10, null);
			g.setColor(Color.WHITE);
			g.setFont(ResourcesManager.defaultFont);
			g.drawString(""+Game.player.getHealth(), lifeStrX, 25);
			g.drawString(""+Game.player.getMoney(), moneyStrX, 25);
			g.drawString(""+Game.player.getRubyCount(), rubyStrX, 25);
			String profile = Options.profileName;
//			int size = g.getFont().getSize();
//			int length = profile.length();
//			int align = size*length - size;
			if(profile.length() >= 21) {
				profile = profile.substring(0, 21);
			}
			g.drawString(profile, Game.WIDTH-235, 23);
			g.drawLine(0, 30, Game.WIDTH, 30);
			
		}
		catch(Exception e)
		{
			System.err.println("Can't load the level (Level.load(Graphics g))");
			System.err.println("Source: Level.load(Graphics g)");
			e.printStackTrace();
		}
		
	}
	
	public Map getMap()
	{
		return map;
	}
	
}
