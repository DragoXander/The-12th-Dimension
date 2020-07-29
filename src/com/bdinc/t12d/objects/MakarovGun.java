package com.bdinc.t12d.objects;

import java.awt.Image;

import com.bdinc.t12d.settings.ResourcesManager;

public class MakarovGun extends Gun {
	
	public MakarovGun(Image sprite) {
		super(sprite);
		this.name = "MakarovGun";
		this.type = "gun";
		this.isInteractive = true;
		this.hitAccuracy = 0.6f;
		this.hitDamage = 4;
		super.maxAmmoCount = 35;
		super.ammoCount = 35;
		super.currentAmmo = 35;
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
	
	public MakarovGun() {
		this.name = "MakarovGun";
		this.sprite = ResourcesManager.makarovGun;
		this.type = "gun";
		this.isInteractive = true;
		this.hitAccuracy = 0.6f;
		this.hitDamage = 4;
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
	
	
}
