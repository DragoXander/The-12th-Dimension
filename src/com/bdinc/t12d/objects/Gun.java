package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.Debug;

public class Gun extends Item {
	
	public int ammoCount = 10;
	public int currentAmmo = 10;
	public int hitDamage = 2;
	public int maxAmmoCount = 10;
	public int ammoSpeed = 2;
	public float hitAccuracy = 0.5f;
	
	public Gun(Image img) {
		super(img);
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
	
	public Gun() {
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
	public void setLocation(int x, int y)
	{
		Vector2 pos = null;
		try
		{
			pos = map.getCell(x, y);
		}
		catch(Exception e)
		{
			System.err.println("Can't set the location to <"+this.toString()+">!");
		}
		
		this.cellX = x;
		this.cellY = y;
		this.x = pos.x;
		this.y = pos.y;
	}
	
	@Override
	public void draw(Graphics g) {
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by "+"<"+this.toString()+">!");
		}
		try
		{
			g.drawImage(sprite, (int)x, (int)y, null);
			if(Game.player.getCell().x == this.cellX-1 || Game.player.getCell().x == this.cellX+1 || Game.player.getCell().x == this.cellX) {
				if(Game.player.getCell().y == this.cellY || Game.player.getCell().y == this.cellY+1) {
					g.setColor(Color.YELLOW);
					g.setFont(ResourcesManager.defaultFont14);
					g.drawString(ResourcesManager.interactTooltip, 5, Game.HEIGHT-30);
					//Game.player.isInteracting = true;
					
					
				} 
				
			}
			
		}
		catch(Exception e)
		{
			System.err.println("Can't draw the "+"<"+this.toString()+">!");
			e.printStackTrace();
		}
	}
	
}
