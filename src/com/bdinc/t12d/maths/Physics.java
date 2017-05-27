package com.bdinc.t12d.maths;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.objects.Block;

public class Physics {
	
	private static Map map = new Map();
	
	public static boolean collidesRight(float px, float py)
	{
		int f = 0;
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			Block b = (Block)obj;
			if(map.checkCell(px, py).x+1 == b.getCell().x && b.isSolid) {
				if(map.checkCell(px, py).y == b.getCell().y) {
					f = 1;
				}
				
			}
		}
		if(f == 1) {
			
			return true;
		}
		else {
			//System.err.println(map.checkCell(px, py).x);
			return false;
		}
	}
	public static boolean collidesRightForGravity(Block b, float px, float py)
	{
		int f = 0;
//		for (Object obj : LevelManager.currentLevel.blocks)
//		{
//			Block b = (Block)obj;
//			if(px+32 >= b.posX() && px+32 <= b.posX()+32) {
//				f = 1;
//			}
//		}
		if(px+32 >= b.posX() && px+32 <= b.posX()+32 && b.isSolid) {
			f = 1;
		}
		if(f == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean collidesLeft(float px, float py)
	{
		int f = 0;
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			Block b = (Block)obj;
			if(map.checkCell(px, py).x == b.getCell().x && b.isSolid) {
				if(map.checkCell(px, py).y == b.getCell().y) {
					f = 1;
				}
			}
		}
		if(f == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean collidesLeftForGravity(Block b, float px, float py)
	{
		int f = 0;
//		for (Object obj : LevelManager.currentLevel.blocks)
//		{
//			Block b = (Block)obj;
//			if(map.checkCell(px, py).y+1 == b.getCell().y) {
//				if(px <= b.posX()+32 && px >= b.posX()) {
//					f = 1;
//				}
//			}
//			
//		}
		if(px <= b.posX()+32 && px >= b.posX() && b.isSolid) {
			f = 1;
		}
		if(f == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean collidesBottom(float px, float py)
	{
		int f = 0;
		
		return bottom(px, py);
	}
	public static boolean collidesTop(float px, float py)
	{
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			Block b = (Block)obj;
			if(map.checkCell(px, py).y == b.getCell().y) {
				if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
					return true;
				}
			}
		}
		return false;
	}
	public static Block getBlockUnder(float px, float py) {
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			Block b = (Block)obj;
			if(map.checkCell(px, py).y+1 == b.getCell().y) {
				return b;
			}
		}
		return null;
	}
	public static Block getBlockRight(float px, float py) {
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			Block b = (Block)obj;
			if(map.checkCell(px, py).x+1 == b.getCell().x) {
				return b;
			}
		}
		return null;
	}
	public static Block getBlockLeft(float px, float py) {
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			Block b = (Block)obj;
			if(map.checkCell(px, py).x-1 == b.getCell().x) {
				return b;
			}
		}
		return null;
	}
	public static boolean bottom(float px, float py)
	{
		for (Object obj : LevelManager.currentLevel.blocks)
		{
			Block b = (Block)obj;
			if(map.checkCell(px, py).y+1 == b.getCell().y) {
				if(collidesRightForGravity(b, px, py) || collidesLeftForGravity(b, px, py)) {
					return true;
				}
			}
		}
		return false;
	}
}
