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
	public static int levelNumber;
	
	private static ResourcesManager resources = new ResourcesManager();
	private static Level lvl;
	
	public static void setLevelByID(int ID) {
		switch(ID) {
			case 0:
				levelNumber = 0;
				break;
			case 1:
				lvl = new Level();
				lvl.create("level1.map");
				levelNumber = 1;
				setLevel(lvl);
				break;
			case 2:
				lvl = new Level();
				lvl.create("level2.map");
				levelNumber = 2;
				setLevel(lvl);
				break;
			case 3:
				lvl = new Level();
				lvl.create("level3.map");
				levelNumber = 3;
				setLevel(lvl);
				break;
		}
	}
	
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
		Block b = null;
		switch(name) {
			case BRICK_1:
				b = new Block(ResourcesManager.brick1);
				return b;
			case BRICK_2:
				b = new Block(ResourcesManager.brick2);
				return b;
			case BRICK_3:
				b = new Block(ResourcesManager.brick3);
				return b;
			case BRICK_4:
				b = new Block(ResourcesManager.brick4);
				return b;
			case BRICK_5:
				b = new Block(ResourcesManager.brick5);
				return b;
			case BRICK_6:
				b = new Block(ResourcesManager.brick6);
				return b;
			case BRICK_7:
				b = new Block(ResourcesManager.brick7);
				return b;
			default:
				return null;
		}
		
		
	}
	
}
