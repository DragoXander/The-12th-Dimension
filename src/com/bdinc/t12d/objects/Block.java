package com.bdinc.t12d.objects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.IReferences;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.main.ResourcesManager;
import com.bdinc.t12d.maths.IntVector2;
import com.bdinc.t12d.maths.Map;
import com.bdinc.t12d.maths.Vector2;

public class Block implements IReferences {
	
	private Image sprite;
	//private LevelManager manager = new LevelManager();
	private float x, y;
	private int cellX, cellY;
	private Map map = new Map();
	private Game game = new Game();
	
	public Block(Image sprite)
	{
		this.sprite = sprite;
		map.init();
	}
	
	public void setLocation(int x, int y)
	{
		Vector2 pos = null;
		try
		{
			pos = map.getCell(x, y);
		}
		catch(Exception e)
		{
			System.out.println(LevelManager.currentLevel);
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
	
	public float posX()
	{
		return x;
	}
	
	public float posY()
	{
		return y;
	}
	
	public void draw(Graphics g)
	{
		//Vector2 pos = map.getCell(x, y);
		try
		{
			//System.err.println(x);
			if(sprite == null)
			{
				System.err.println("FFF");
			}
			g.drawImage(sprite, (int)x, (int)y, null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
