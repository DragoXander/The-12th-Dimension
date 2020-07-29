package com.bdinc.t12d.objects;

import java.awt.Image;

import com.bdinc.t12d.maths.Vector2;
import com.bdinc.t12d.settings.ResourcesManager;

public class Ammo extends Item {
	public Ammo(Image sprite) {
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
	
	public Ammo() {
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
	public void setLocation(int x, int y) {
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
}
