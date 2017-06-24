package com.bdinc.t12d.graphics;

import java.awt.Canvas;
import java.util.ArrayList;

import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Particle;
import com.bdinc.t12d.objects.Platform;
import com.bdinc.t12d.utils.IntVector2;

public class DisplayManager {
	
	private Map map = new Map();
	
	private ArrayList<Entity> entities;
	private ArrayList<Block> blocks;
	private ArrayList<Flame> flames;
	private ArrayList<Particle> particles;
	
	public void init()
	{
		map.init();
	}
	
	boolean collisionBottom;
	Entity player;
	IntVector2 plCell, flameCell;
	
	public void update(long delta)
	{
		player = Game.player;
		if(LevelManager.levelNumber > 0) {
			
			Vector2 checkedCell = null;
			try {
				checkedCell = map.checkCell(player.posX(), player.posY());
			}
			catch(Exception e) {
				System.err.println("No reference to 'Player'! Can't do this!");
				e.printStackTrace();
			}
			
			entities = LevelManager.currentLevel.entities;
			blocks = LevelManager.currentLevel.blocks;
			flames = LevelManager.currentLevel.flames;
			particles = LevelManager.currentLevel.particles;
			collisionBottom = Physics.collidesBottom(player.posX(), player.posY());
			plCell = Game.player.getCell();
			
			for(Block b : blocks) {
				if(!b.isSolid) {
					b.incY(Physics.gravity);
				}
			}
			
			for(Block b : blocks) {
				if(b instanceof Platform) {
					((Platform)b).move();
					//System.out.println(""+b.getCell().x);
				}
				
			}
			
			if(!collisionBottom && !player.jump) {
				player.incY(Physics.gravity);
				player.setCell(checkedCell);
				player.isFalling = true;
			}
			else {
				player.isFalling = false;
			}
			
			if(Game.player.jump) {
				player.jump();
			}
			if(Game.player.left) {
				player.moveLeft();
			}
			if(Game.player.right) {
				player.moveRight();
			}
			
			for(Flame f : flames) {
				flameCell = f.getCell();
				if(plCell.x == flameCell.x) {
					if(plCell.y == flameCell.y) {
						f.activate();
					}
				}
			}
			
			for(Entity e : entities) {
				if(!e.equals(player)) {
					e.enemyMove();
				}
			}
//			if(particles.size() > 0) {
//				for(Particle p : particles) {
//					if(p.active) {
//						if(p.getTarget().getCell().x > p.getSource().getCell().x) {
//							p.moveTo(Particle.DIRECTION_RIGHT);
//						}
//						if(p.getTarget().getCell().x < p.getSource().getCell().x) {
//							p.moveTo(Particle.DIRECTION_LEFT);
//						}
//					}
//					else {
//						particles.remove(p);
//					}
//				}
//			}
		}
	}
	
}
