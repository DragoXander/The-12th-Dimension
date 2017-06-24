package com.bdinc.t12d.objects;

import java.awt.Image;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Vector2;

public class VerticalPlatform extends Platform {
	
	private int tmpY;
	
	public VerticalPlatform(Image sprite) {
		super(sprite);
	}
	public VerticalPlatform(Image sprite, float speed, int count, int direction) {
		super(sprite);
		this.speed = speed;
		this.count = count;
		this.direction = direction;
		this.x = map.getCell(cellX, cellY).x;
		this.y = map.getCell(cellX, cellY).x;
	}
	
	/*
	 * Direction:
	 * 1 - Down
	 * -1 - Up
	 */
	public void move(int count, int direction) {
		if(!this.moving) {
			this.tmpY = this.cellY;
			//System.out.println("TMP:"+tmpY+";CELLY:"+cellY);
			this.moving = true;
		} else {
			System.out.println("TMP:"+this.tmpY+";CELLY:"+this.cellY);
			if(this.cellY == this.tmpY+this.count) {
				this.direction = -1;
			} else if(this.cellY == this.tmpY) {
				this.direction = 1;
			}
			this.y += this.direction * this.speed; 
			this.setCell(map.checkCell(this.x, this.y));
		}
	}
	
	@Override
	public void setLocation(int x, int y)
	{
		Vector2 pos = null;
		try
		{
			pos = map.getCell(x, y);
		}
		catch(Exception e)
		{
			System.err.println("Can't set the location to "+id+"<"+this.toString()+">!");
		}
		
		this.cellX = x;
		this.cellY = y;
		this.x = pos.x;
		this.y = pos.y;
	}
	
	@Override
	public void move() {
		if(!this.moving) {
			this.tmpY = this.cellY;
			//System.out.println("TMP:"+tmpY+";CELLY:"+cellY);
			this.moving = true;
		} else {
			if(this.cellY == this.tmpY+this.count) {
				this.direction = -1;
			} else if(this.cellY == this.tmpY-1) {
				this.direction = 1;
			}
			this.y += this.direction * this.speed;
			if(Game.player.getCell().y == this.cellY-1 && Game.player.getCell().x == this.cellX) {
				Game.player.incY(this.direction * this.speed);
				Game.player.setCell(map.checkCell(Game.player.posX(), Game.player.posY()));
			}
			this.setCell(map.checkCell(this.x, this.y));
		}
	}
	
}
