package com.bdinc.t12d.maths;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;

public class Physics {
	
	private static Map map = new Map();
	private static float chekedCellX, chekedCellY;
	
	public static boolean collidesRight(float px, float py)
	{
		chekedCellX = map.checkCell(px, py).x;
		chekedCellY = map.checkCell(px, py).y;
		Block b = null;
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			b = (Block)obj;
			if(chekedCellX+1 == b.getCell().x && b.isSolid) {
				if(chekedCellY == b.getCell().y) {
					if(collidesBottom(px, py)) {
						return true;
					}
				}
				
			}
		}
		return false;
	}
	
	public static boolean collidesRightForGravity(Block b, float px, float py)
	{
		if(px+32 >= b.posX() && px+32 <= b.posX()+32 && b.isSolid) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean collidesLeft(float px, float py)
	{
		Block b = null;
		chekedCellX = map.checkCell(px, py).x;
		chekedCellY = map.checkCell(px, py).y;
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			b = (Block)obj;
			if(chekedCellX == b.getCell().x && b.isSolid) {
				if(chekedCellY == b.getCell().y) {
					if(collidesBottom(px, py)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean collidesLeftForGravity(Block b, float px, float py)
	{
		if(px <= b.posX()+32 && px >= b.posX() && b.isSolid) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean collidesBottom(float px, float py)
	{
		Block b = null;
		chekedCellY = map.checkCell(px, py).y;
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			b = (Block)obj;
			if(chekedCellY+1 == b.getCell().y) {
				if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
					return true;
				}
				
			}
		}
		return false;
	}
	public static boolean collidesEntity(float px, float py) {
		//Entity e = null;
		chekedCellX = Game.player.getCell().x;
		chekedCellY = Game.player.getCell().y;
		for(Entity e : LevelManager.currentLevel.entities) {
			if(e.getCell().x <= chekedCellX+1 && e.getCell().x >= chekedCellX) {
				if(e.getCell().y == chekedCellY) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean collidesTop(float px, float py)
	{
		Block b = null;
		chekedCellY = map.checkCell(px, py).y;
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			b = (Block)obj;
			if(chekedCellY == b.getCell().y) {
				if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
					return true;
				}
			}
		}
		return false;
	}

}
