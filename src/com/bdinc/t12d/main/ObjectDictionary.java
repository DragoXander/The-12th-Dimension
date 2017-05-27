package com.bdinc.t12d.main;

import com.bdinc.t12d.objects.Block;

public class ObjectDictionary {
	
	public void init() {
		for(Object obj : LevelManager.currentLevel.blocks)
		{
			Block b = (Block)obj;
			if(b.getSprite().equals(ResourcesManager.brick1)) {
				b.id = "brick_default";
			}
			else if(b.getSprite().equals(ResourcesManager.brick2)) {
				b.id = "brick_ice";
			}
			else if(b.getSprite().equals(ResourcesManager.brick3)) {
				b.id = "brick_dange";
			}
			else if(b.getSprite().equals(ResourcesManager.brick4)) {
				b.id = "brick_light";
			}
			else if(b.getSprite().equals(ResourcesManager.brick5)) {
				b.id = "brick_lightVines";
			}
			else if(b.getSprite().equals(ResourcesManager.brick6)) {
				b.id = "brick_fire";
			}
			else if(b.getSprite().equals(ResourcesManager.brick7)) {
				b.id = "brick_dark";
			}
		}
	}
	
}
