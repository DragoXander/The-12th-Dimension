package com.bdinc.t12d.level;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Button;
import com.bdinc.t12d.objects.Chest;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.HorizontalPlatform;
import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.objects.MakarovGun;
import com.bdinc.t12d.objects.Platform;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.objects.Vase;
import com.bdinc.t12d.objects.VerticalPlatform;
import com.bdinc.t12d.scenes.ProfilesListDialog;
import com.bdinc.t12d.settings.ResourcesManager;

public class LevelManager {
	
	public static final String BRICK_1 = "BRICK_DANGEON";
	public static final String BRICK_2 = "BRICK_ICE";
	public static final String BRICK_3 = "BRICK_SEWER";
	public static final String BRICK_4 = "BRICK_GOLD";
	public static final String BRICK_5 = "BRICK_LIGHT";
	public static final String BRICK_6 = "BRICK_FIRE";
	public static final String BRICK_7 = "BRICK_DARK";
	public static final String VPLATFORM_DANGEON = "PLT_VERTICAL_DANGEON";
	public static final String HPLATFORM_DANGEON = "PLT_HORIZONTAL_DANGEON";
	public static final String FLOOR_1 = "Floor1";
	public static final String FLOOR_7 = "Floor7";
	public static final String WALL_1 = "WALL_DANGEON";
	public static final String CHEST = "CHEST";
	public static final String VASE = "VASE";
	public static final String KEY = "KEY";
	public static final String DOOR = "DOOR";
	public static final String BUTTON = "BUTTON";
	public static final String BULLET_PACK_DEF = "BulletPack_Default";
	public static final String FINISH = "FINISH";
	public static final String COIN10 = "Coin10";
	public static final String THIEF = "ENT_THIEF";
	public static final String FIRE_MAN = "ENT_MONST_FIRE";
	public static final String LIGHT_OFFICER = "ENT_OFFICER_LIGHT";
	public static final String FLAME_OFF = "FLAME";
	public static final String FLAME = "FLAME_ACTIVE";
	public static final String BUTTON_DEFAULT = "BUTTON";
	public static final String MAKAROV_GUN = "MAKAROV_GUN";
	public static final String CONT_CHEST = "CONT_CHEST";
	public static final String CONT_VASE = "CONT_VASE";
	
	public static Level currentLevel;
	public static int levelNumber;

	private static Level lvl;
	
	public static void setLevelByID(int ID) {
		switch(ID) {
			case -4:
				levelNumber = -4;
				break;
			case -3:
				levelNumber = -3;
				break;
			case -2:
				levelNumber = -2;
				break;
			case -1:
				levelNumber = -1;
				break;
			case 0:
				levelNumber = 0;
				break;
			case 1:
				lvl = new Level();
				lvl.isExtra = false;
				try {
					lvl.create("level1.map");
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				levelNumber = 1;
				setLevel(lvl);
				break;
			case 2:
				lvl = new Level();
				lvl.isExtra = false;
				lvl.create("level2.map");
				levelNumber = 2;
				setLevel(lvl);
				break;
			case 3:
				lvl = new Level();
				lvl.isExtra = false;
				lvl.create("level3.map");
				levelNumber = 3;
				setLevel(lvl);
				break;
		}
	}
	
	public static void setLevelByName(String name) {
		lvl = new Level();
		lvl.create("assets/levels/extra/"+name+".map");
		levelNumber = lvl.getID();
		setLevel(lvl);
	}
	
	public static void setLevel(Level lvl)
	{
		currentLevel = lvl;
		levelNumber = lvl.getID();
	}
	
	public static Level getCurrentLevel()
	{
		return currentLevel;
	}
	
}
