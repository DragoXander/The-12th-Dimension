package com.bdinc.t12d.objects;

import java.awt.Image;

import com.bdinc.t12d.maths.Vector2;

public class Platform extends Block {
	
	protected float speed = 0.5f;
	
	protected int count, direction = 1;
	
	protected boolean moving = false;
	
	public Platform(Image sprite) {
		super(sprite);
	}
	public Platform(Image sprite, float speed, int count, int direction) {
		super(sprite);
		this.speed = speed;
		this.count = count;
		this.direction = direction;
	}
	
	public void setPathCount(int count) {
		this.count = count;
	}
	
	public void setSpeed(float value) {
		this.speed = value;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void move() {}
	
	public void setCell(Vector2 cell) {
		cellX = (int)cell.x;
		cellY = (int)cell.y;
	}

}
