package com.bdinc.t12d.objects;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.settings.ResourcesManager;

public class MakarovAmmo extends Ammo {
	
	public MakarovAmmo(Image sprite) {
		super(sprite);
		this.description = ResourcesManager.makarovGunDescStr;
		this.name = ResourcesManager.makarovGunName;
		this.isInteractive = true;
	}
	
	public MakarovAmmo() {
		this.sprite = ResourcesManager.ammo;
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
					g.setFont(ResourcesManager.defaultFont12);
					FontMetrics fm = g.getFontMetrics();
					g.drawString(this.getCount()+" "+ResourcesManager.ammoInteractMsg, 5, Game.HEIGHT-50);
					g.drawImage(ResourcesManager.makarovGun, 5+fm.stringWidth(this.getCount()+" "+ResourcesManager.ammoInteractMsg)+5, Game.HEIGHT-65, 16, 16, null);
					g.setColor(Color.RED);
					g.drawString(ResourcesManager.makarovGunName, 5+fm.stringWidth(this.getCount()+" "+ResourcesManager.ammoInteractMsg)+23, Game.HEIGHT-50);
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
	
	
}
