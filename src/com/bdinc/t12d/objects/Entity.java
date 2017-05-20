package com.bdinc.t12d.objects;

import java.awt.Image;

import com.bdinc.t12d.main.IReferences;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Vector2;

public class Entity implements IReferences {
	
	private float x, y;
	private Map map = new Map();
	//private LevelManager manager = new LevelManager();
	
	private Image texture;
	
	public Entity(Image texture)
	{
		this.texture = texture;
		map.init();
	}
	
	public void setPosition(int x, int y)
	{
		Vector2 pos = map.getCell(x, y);
		this.x = pos.x;
		this.y = pos.y;
	}
	
}
