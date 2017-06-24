package com.bdinc.t12d.maths;

import java.util.ArrayList;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Particle;

public class Physics {
	
	private static Map map = new Map();
	private static float chekedCellX, chekedCellY;
	
	private static ArrayList<Entity> entities;
	private static ArrayList<Block> blocks;
	private static ArrayList<Flame> flames;
	private static ArrayList<Particle> particles;
	
	public static final float gravity = 1f;
	
	public static boolean collidesRight(float px, float py)
	{
		chekedCellX = map.checkCell(px, py).x;
		chekedCellY = map.checkCell(px, py).y;
		blocks = LevelManager.currentLevel.blocks;
		for (Block b : blocks)
		{
			if(chekedCellX+1 == b.getCell().x && !b.isTrigger) {
				if(((py+32 >= b.posY() && py+32 <= b.posY()+32) || (py <= b.posY()+32 && py >= b.posY())) && !b.isTrigger) { //chekedCellY == b.getCell().y
					if(!collidesBottom(px, py)) {
						return true;
					} else if(collidesBottom(px, py) && chekedCellY == b.getCell().y && !b.isTrigger){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean collidesRightForGravity(Block b, float px, float py)
	{
		if(px+32 >= b.posX()+3 && px+32 <= b.posX()+32 && !b.isTrigger) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean collidesLeft(float px, float py)
	{
		chekedCellX = map.checkCell(px, py).x;
		chekedCellY = map.checkCell(px, py).y;
		blocks = LevelManager.currentLevel.blocks;
		for (Block b : blocks)
		{
			if(chekedCellX == b.getCell().x && !b.isTrigger) {
				if(chekedCellY == b.getCell().y) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean collidesLeftForGravity(Block b, float px, float py)
	{
		if(px <= b.posX()+31 && px >= b.posX() && !b.isTrigger) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean collidesBottom(float px, float py)
	{
		chekedCellY = map.checkCell(px, py).y;
		chekedCellX = map.checkCell(px, py).x;
		blocks = LevelManager.currentLevel.blocks;
		for (Block b : blocks)
		{
			if(chekedCellY+1 == b.getCell().y && !b.isTrigger) {
				if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean collidesEntity(float px, float py) {
		chekedCellX = Game.player.getCell().x;
		chekedCellY = Game.player.getCell().y;
		entities = LevelManager.currentLevel.entities;
		for(Entity e : entities) {
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
		chekedCellY = map.checkCell(px, py).y;
		blocks = LevelManager.currentLevel.blocks;
		for (Block b : blocks)
		{
			if(chekedCellY == b.getCell().y) {
				if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
					return true;
				}
			}
		}
		return false;
	}

}
