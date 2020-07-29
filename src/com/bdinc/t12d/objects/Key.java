package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.settings.ResourcesManager;

public class Key extends Item {
	
	private Door target;
	
	public Key() {
		this.sprite = ResourcesManager.key;
		this.isInteractive = true;
	}
	
	public Key(Image img) {
		super(img);
		this.isInteractive = true;
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(sprite == null)
		{
			System.err.println("No sprite(null)! Caused by <"+this.toString()+">!");
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
			System.err.println("Can't draw the <"+this.toString()+">!");
			e.printStackTrace();
		}
		
	}
	
	public void setTarget(Door tg) {
		this.target = tg;
	}
	
	public Door getTarget() {
		return this.target;
	}
}
