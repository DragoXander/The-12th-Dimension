package com.bdinc.t12d.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.ListIterator;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.objects.MakarovAmmo;
import com.bdinc.t12d.objects.Particle;
import com.bdinc.t12d.objects.Platform;
import com.bdinc.t12d.scenes.SceneManager;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.utils.Debug;
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
		//new Thread(this).start();
	}
	
	boolean collisionBottom;
	Entity player;
	IntVector2 plCell, flameCell;
	Graphics g = null;

	public void render(Canvas c) {
		BufferStrategy bs = c.getBufferStrategy();
		if(bs == null)
		{
			c.createBufferStrategy(Options.bufferCount);
			c.requestFocus();
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
	}
	
	public void update(float delta)
	{
		player = Game.player;
		SceneManager.updateScenes(delta);
		if(LevelManager.levelNumber > 0 || LevelManager.levelNumber <= -10) {
			Vector2 checkedCell = map.checkCell(player.posX(), player.posY());
			
			entities = LevelManager.currentLevel.entities;
			blocks = LevelManager.currentLevel.blocks;
			flames = LevelManager.currentLevel.flames;
			particles = LevelManager.currentLevel.particles;
			collisionBottom = Physics.collidesBottom(player.posX(), player.posY());
			plCell = Game.player.getCell();
			
			if(player.invSize != player.invList.size() || player.inventory.change) {
//				for(UISlot c : player.inventory.cells) {
//					c.putItem(null);
//					c.hasItem = false;
//				}
				for(int i = 0; i < player.invList.size(); i++) {
					if(!(i >= player.inventory.cells.size())) {
						//player.inventory.cells.get(i).putItem(player.invList.get(i));
						if(!player.inventory.cells.get(i).equals(player.invList.get(i))) {
							if(!player.invList.get(i).equals(null)) {
								player.inventory.cells.get(i).putItem(player.invList.get(i));
							} else {
								player.inventory.cells.get(i).removeItem();
							}
							
						}
					}
					
				}
				//player.invSize = player.invList.size();
				player.inventory.change = false;
			}
			
//			if(!Game.paused) {
//				//blocks.forEach();
//				for(Block b : blocks) {
//					if(!b.isSolid) {
//						b.incY(Physics.gravity);
//					}
//				}
//			}
			
			if(!Game.paused) {
				for(Block b : blocks) {
					if(b instanceof Platform) {
						((Platform)b).move();
					}
					
				}
			}
			
			for (Object a : LevelManager.currentLevel.levelObjects) {
				if(a instanceof Block) {
					Block b = (Block)a;
					if (b.isInteractive) {
						if (player.getCell().x == b.getCell().x-1 || player.getCell().x == b.getCell().x+1 || player.getCell().x == b.getCell().x) {
							if (player.getCell().y == b.getCell().y || player.getCell().y == b.getCell().y+1) {
								player.isInteracting = true;
								player.interactiveTarget = b;
								break;
							} else {
								player.isInteracting = false;
								player.interactiveTarget = null;
								continue;
							}
						} else {
							player.isInteracting = false;
							player.interactiveTarget = null;
							continue;
						}
					} else {
						player.isInteracting = false;
						player.interactiveTarget = null;
						continue;
					}
				} 
				else if (a instanceof Item) {
					Item b = (Item)a;
					if (b.isInteractive) {
						if (player.getCell().x == b.getCell().x-1 || player.getCell().x == b.getCell().x+1 || player.getCell().x == b.getCell().x) {
							if (player.getCell().y == b.getCell().y || player.getCell().y == b.getCell().y+1) {
								player.isInteracting = true;
								player.interactiveTarget = b;
								//Debug.log(player.interactiveTarget);
								break;
							} else {
								player.isInteracting = false;
								player.interactiveTarget = null;
								continue;
							}
						} else {
							player.isInteracting = false;
							player.interactiveTarget = null;
							continue;
						}
					} else {
						player.isInteracting = false;
						player.interactiveTarget = null;
						continue;
					}
				}
				
			}
			
//			for (Item i : LevelManager.currentLevel.items) {
//				if (i.isInteractive) {
//					if (player.getCell().x == i.getCell().x-1 || player.getCell().x == i.getCell().x+1 || player.getCell().x == i.getCell().x) {
//						if (player.getCell().y == i.getCell().y || player.getCell().y == i.getCell().y+1) {
//							player.isInteracting = true;
//							player.interactiveTarget = i;
//							break;
//						} else {
//							player.isInteracting = false;
//							player.interactiveTarget = null;
//							continue;
//						}
//					} else {
//						player.isInteracting = false;
//						player.interactiveTarget = null;
//						continue;
//					}
//				} else {
//					player.isInteracting = false;
//					player.interactiveTarget = null;
//					continue;
//				}
//			}
			
			//Debug.log("Interact:"+Game.player.isInteracting+", Target:"+Game.player.interactiveTarget);
			
			player.setCell(checkedCell);
			if(!Game.paused) {
				if(!collisionBottom && !player.jump && !player.isLifting) {
					player.incY(Physics.gravity);
					//player.setCell(checkedCell);
					player.isFalling = true;
				}
				else {
					player.isFalling = false;
				}
				
				if(player.jump) {
					player.jump();
				}
				
				if(player.left) {
					player.moveLeft();
				}
				if(player.right) {
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
			
			}
			
			if(particles.size() > 0 && !Game.paused) {
				ListIterator<Particle> it = particles.listIterator();
				if(it.hasNext()) {
					Particle p = it.next();
					p.move();
					if(p.position().x >= Game.WIDTH || p.position().x <= 0) {
						p.hit = true;
					}
					if(p.position().y >= Game.HEIGHT || p.position().y <= 0) {
						p.hit = true;
					}
					if(p.hit) {
						particles.remove(p);
					}
					
				}
			}
			if(!Game.paused) {
				for(Entity e : entities) {
					if(!e.equals(player)) {
						e.enemyMove();
						if(player.getCell().x == e.getCell().x-3) {
							e.attack(player);
						}
					}
				}
			}
			
		}
	}

	
	public void riseUp(float value) {
		Game.player.incY(value);
		Game.player.setCell(map.checkCell(Game.player.posX(), Game.player.posY()));
	}
	
}
