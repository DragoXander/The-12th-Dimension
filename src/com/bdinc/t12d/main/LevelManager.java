package com.bdinc.t12d.main;

import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Level;

public class LevelManager {
	
	public static final String BRICK_1 = "BRICK_GRAY";
	public static final String BRICK_2 = "BRICK_ICE";
	public static final String BRICK_3 = "BRICK_GREEN";
	public static final String BRICK_4 = "BRICK_GOLD";
	public static final String BRICK_5 = "Brick5";
	public static final String BRICK_6 = "Brick6";
	public static final String BRICK_7 = "Brick7";
	public static final String FLOOR_1 = "Floor1";
	public static final String FLOOR_7 = "Floor7";
	public static final String WALL_1 = "WALL_GRAY";
	public static final String BULLET_PACK_DEF = "BulletPack_Default";
	public static final String FINISH = "FINISH";
	public static final String COIN10 = "Coin10";
	public static final String THIEF = "ENT_THIEF";
	public static final String FIRE_MAN = "ENT_MONST_FIRE";
	public static final String LIGHT_OFFICER = "ENT_OFFICER_LIGHT";
	public static final String FLAME_OFF = "FLAME";
	public static final String FLAME = "FLAME_ACTIVE";
	
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
		switch(name) {
			case FIRE_MAN:
				Entity ent = new Entity(resources.monstFire);
				ent.setHealth(100);
				ent.setMagicCount("unlimited");
				return ent;
			default:
				return null;
		}
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
			case WALL_1:
				b = new Block(ResourcesManager.wall1);
				b.isSolid = false;
				return b;
			default:
				return null;
		}
	}
	
	public static Flame getFlame(String name) {
		Flame f = null;
		switch(name) {
			case FLAME_OFF:
				f = new Flame();
				return f;
			case FLAME:
				f = new Flame();
				f.setActive(true);
				return f;
			default:
				return null;
		}
	}
	
}
