package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.IReferences;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.maths.IntVector2;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.maths.Vector2;

public class Entity implements IReferences {
	
	private float x, y, tmp;
	private int cellX, cellY;
	private Map map = new Map();
	
	public String id;
	
	private int health = 100;
	private int maxHealth = 100;
	
	private int magicCount = 50;
	private int maxMagic = 50;
	
	private float speed = 0.5f, runSpeed = 1;
	
	public boolean isRunning, right, left, jump;
	private boolean magicUnlimited;
	
	private String name = "#Entity:???";
	
	Game game = new Game();
	
	private Image texture;
	
	public Entity(Image texture)
	{
		this.texture = texture;
		try
		{
			map.init();
		}
		catch(Exception e)
		{
			System.err.println("Can't initialize the map...");
			System.err.println("Caused by entity<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public void moveRight() {
		Entity player = Game.player;
		boolean colRight = Physics.collidesRight(player.posX(), player.posY());
		if(!colRight) {
			if(!isRunning) {
				if(this.x+map.cellSize < game.getWidth()) {
					this.x += 1 * speed;
					setCell(map.checkCell(x, y));
				}
			}
			else {
				if(this.x+map.cellSize < game.getWidth()) {
					this.x += 1 * runSpeed;
					setCell(map.checkCell(x, y));
				}
			}
		}
	}
	
	public void setCell(Vector2 cell) {
		cellX = (int)cell.x;
		cellY = (int)cell.y;
	}
	
	public void jump() {
		Entity player = Game.player;
		boolean colBot = Physics.collidesBottom(player.posX(), player.posY());
		boolean colTop = Physics.collidesTop(player.posX(), player.posY());
		
		if(colBot) {
			tmp = y;
		}
		y -= 1;
		setCell(map.checkCell(x, y));
		if(y <= tmp-50 || colTop) {
			jump = false;
		}
	}
	
	public void moveLeft() {
		Entity player = Game.player;
		boolean colLeft = Physics.collidesLeft(player.posX(), player.posY());
		if(!colLeft) {
			if(!isRunning) {
				if(this.x > 0) {
					this.x -= 1 * speed;
					setCell(map.checkCell(x, y));
				}
			}
			else {
				if(this.x > 0) {
					this.x -= 1 * runSpeed;
					setCell(map.checkCell(x, y));
				}
			}
		}
	}
	
	public void attack(Graphics g, Entity target) {
		g.setColor(Color.YELLOW);
		g.drawLine((int)this.x, (int)this.y, (int)target.posX(), (int)target.posY());
		target.decreaseHealth(10);
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMaxHealth(int health) {
		this.maxHealth = health;
	}
	
	public void increaseHealth() {
		this.health += 1;
	}
	
	public void decreaseHealth() {
		this.health -= 1;
	}
	
	public void increaseHealth(int value) {
		this.health += value;
	}
	
	public void decreaseHealth(int value) {
		this.health -= value;
	}
	
	public void setMagicCount(int magic) {
		this.magicCount = magic;
	}
	
	public void setMagicCount(String tag) {
		if(tag.equals("unlimited")) {
			magicUnlimited = true;
		}
	}
	
	public void setMaxMagic(int magic) {
		this.maxMagic = magic;
	}
	
	public void increaseMagic() {
		this.magicCount += 1;
	}
	
	public void decreaseMagic() {
		this.magicCount -= 1;
	}
	
	public void increaseMagic(int value) {
		this.magicCount += value;
	}
	
	public void decreaseMagic(int value) {
		this.magicCount -= value;
	}
	
	public void incY(float value) {
		this.y += value;
	}
	
	public int getMagicCount() {
		return this.magicCount;
	}
	
	public int getMaxMagicCount() {
		return this.maxMagic;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public Image getSprite()
	{
		return this.texture;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPosition(int x, int y)
	{
		Vector2 pos = null;
		try
		{
			pos = map.getCell(x, y);
		}
		catch(Exception e)
		{
			System.err.println("Can't set the location to entity<"+this.toString()+">!");
		}
		
		this.cellX = x;
		this.cellY = y;
		this.x = pos.x;
		this.y = pos.y;
	}
	
	public IntVector2 getCell()
	{
		return new IntVector2(cellX, cellY);
	}
	
	@Override
	public String toString()
	{
		int id = 0;
		for(Object e : LevelManager.currentLevel.entities.toArray()) {
			Entity ent = (Entity)e;
			if(this.equals(ent))
			{
				return "t12d:entity#"+id;
			}
			id++;
		}
		return "t12d:entity#???(null)";
	}
	
	public void draw(Graphics g)
	{
		if(texture == null)
		{
			System.err.println("No sprite(null)! Caused by entity<"+this.toString()+">!");
		}
		try
		{
			g.drawImage(texture, (int)x, (int)y, null);
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the entity<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public float posX()
	{
		return x;
	}
	
	public float posY()
	{
		return y;
	}
	
}
