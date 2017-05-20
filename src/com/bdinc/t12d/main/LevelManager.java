package com.bdinc.t12d.main;

import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Level;

public class LevelManager {
	
	public static final String BRICK_1 = "Brick1";
	public static final String BRICK_2 = "Brick2";
	public static final String BRICK_3 = "Brick3";
	public static final String BRICK_4 = "Brick4";
	public static final String BRICK_5 = "Brick5";
	public static final String BRICK_6 = "Brick6";
	public static final String BRICK_7 = "Brick7";
	public static final String FLOOR_1 = "Floor1";
	public static final String FLOOR_7 = "Floor7";
	public static final String BULLET_PACK_DEF = "BulletPack_Default";
	public static final String FINISH = "FINISH";
	public static final String COIN10 = "Coin10";
	public static final String THIEF = "ENT_THIEF";
	public static final String FIRE_MONSTER = "ENT_MONST_FIRE";
	public static final String LIGHT_OFFICER = "ENT_OFFICER_LIGHT";
	
	public static Level currentLevel;
	
	private static ResourcesManager resources = new ResourcesManager();
	
	public static void setLevel(Level lvl)
	{
		currentLevel = lvl;
	}
	
	public static Level getCurrentLevel()
	{
		return currentLevel;
	}
	
	public static Entity getEntityByName(String name)
	{
		if(name.equals(LIGHT_OFFICER))
		{
			Entity ent = new Entity(resources.brick6);
			//ent.damage = 50;
			//ent.maxHealth = 100;
			//ent.health = 100;
			return ent;
		}
		return null;
	}
	
	public static Block getObjectByName(String name)
	{
		if(name.equals(BRICK_6))
		{
			Block b = new Block(ResourcesManager.brick6);
			return b;
		}
		return null;
	}
	
}
