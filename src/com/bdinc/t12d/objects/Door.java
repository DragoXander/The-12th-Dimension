package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.settings.ResourcesManager;

public class Door extends Block {
	
	public boolean isOpened;
	public boolean requiresKey;
	public boolean noKey = true;
	private Key key;
	
	public Door(Image sprite) {
		super(sprite);
		this.isInteractive = true;
		try
		{
			map.init();
		}
		catch(Exception e)
		{
			System.err.println("Can't initialize the map...");
			System.err.println("Caused by item<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(isOpened) {
			sprite = ResourcesManager.doorOpened;
			this.isTrigger = true;
		} else {
			sprite = ResourcesManager.doorClosed;
			this.isTrigger = false;
		}
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by "+id+"<"+this.toString()+">!");
		}
		try
		{
			g.drawImage(sprite, (int)x, (int)y, null);
			if(Game.player.getCell().x == this.cellX-1 || Game.player.getCell().x == this.cellX+1 || Game.player.getCell().x == this.cellX) {
				if(Game.player.getCell().y == this.cellY || Game.player.getCell().y == this.cellY+1) {
					//Game.player.isInteracting = true;
					if(requiresKey) {
						if(!Game.player.invList.values().contains(key)) {
							g.setColor(Color.RED);
							g.setFont(ResourcesManager.defaultFont14);
							g.drawString("Заперто. Нужен ключ!", 5, Game.HEIGHT-30);
						}
						else {
							g.setColor(Color.YELLOW);
							g.setFont(ResourcesManager.defaultFont14);
							g.drawString(ResourcesManager.interactTooltip, 5, Game.HEIGHT-30);
						}
					} else {
						g.setColor(Color.YELLOW);
						g.setFont(ResourcesManager.defaultFont14);
						g.drawString(ResourcesManager.interactTooltip, 5, Game.HEIGHT-30);
					}
					
				} 
				
			}
			
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the "+id+"<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
	public void setKey(Key k) {
		this.key = k;
	}
	
	public Key getKey() {
		return this.key;
	}
	
	public void open() {
		isOpened = true;
	}
	
	public void close() {
		isOpened = false;
	}
	
}
