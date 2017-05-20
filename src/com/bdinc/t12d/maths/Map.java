package com.bdinc.t12d.maths;

public class Map {
	
	private int horizontalCount = 35;
	private int verticalCount = 25;
	private int size = horizontalCount * verticalCount;
	public static final int cellSize = 32;
	
	public Vector2[][] cells = new Vector2[horizontalCount][verticalCount];
	
	public void init()
	{
		int x = 0, y = 0;
		
		for(int i = 0; i < horizontalCount; i++)
		{
			for(int j = 0; j < verticalCount; j++)
			{
				cells[i][j] = new Vector2(x, y);
				y += cellSize;
				
			}
			y = 0;
			x += cellSize;
		}
	}
	
	public Vector2 getCell(int x, int y)
	{
		//System.out.println("X: "+x+";Y: "+y);
		return cells[x][y];
	}
	
	public void setHorizontalCount(int count)
	{
		horizontalCount = count;
	}
	
	public void setVerticalCount(int count)
	{
		verticalCount = count;
	}
	
	public int getHorizontalCount()
	{
		return horizontalCount;
	}
	
	public int getVerticalCount()
	{
		return verticalCount;
	}
	
}
