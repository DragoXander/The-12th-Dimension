package com.bdinc.t12d.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Level;

public class LevelReader implements IReferences {
	
	private static int tmpIndex = 0;
	private static int blockCount;
	private static boolean entity = false;
	private static boolean thief = false;
	
	private static Container cont = new Container(null, null);
	
	
	
	public Level readLevel(String file)
	{
		//System.out.println("A:");
		BufferedReader reader = null;
		Level lvl = new Level();
		ArrayList<Block> blocks = new ArrayList<Block>();
		ArrayList<Entity> entities = new ArrayList<Entity>();
		//HashMap<ArrayList<Block>, ArrayList<Entity>> res = new HashMap<ArrayList<Block>, ArrayList<Entity>>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String line;
			String text = "";
			//System.out.println("A:"+file);
			while((line = reader.readLine()) != null)
			{
				//System.out.println("A:");
				text += line;
			}
			//System.out.println("A:"+text);
			String[] tmp = text.split("\\.");
			blockCount = tmp.length;
			while(tmpIndex < blockCount)
			{
				HashMap<String, String[]> map = readBlock(tmp[tmpIndex]+".", "#", ";", ":", "\\.");
				analyzeCode(lvl, map, blocks, entities);
				tmpIndex++;
			}
			
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Can't read the level!\n"+file, "Error! #101", 0);
			e.printStackTrace();
		}
		finally
		{
			if(reader != null)
			{
				try
				{
					reader.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		//res.put(blocks, entities);
		//cont = new Container(blocks, entities);
		lvl.entities = entities;
		lvl.blocks = blocks;
		//System.out.println("A:"+cont.getKey().get(1));
		
		return lvl;
	}
	
	private static HashMap<String, String[]> readBlock(String txt, String bNameStart, String separator, String bBodyOpen, 
			String bBodyClose) {
		String[] bPreName1 = txt.split(bNameStart);
		String[] bPreName2 = bPreName1[1].split(bBodyOpen);
		String bName = bPreName2[0];
		String[] bPreValue = bPreName2[1].split(bBodyClose);
		String[] bValueArr = bPreValue[0].split(separator);
		HashMap<String, String[]> value = new HashMap<String, String[]>();
		value.put(bName, bValueArr);
		return value;
	}
	
	private static void analyzeCode(Level lvl1, HashMap<String, String[]> map, ArrayList<Block> blocks, ArrayList<Entity> entities)
	{
		for(String n : map.keySet())
		{
			//System.out.println("A:");
			if(n.startsWith("ENT"))
			{
				entity = true;
			}
			String[] vec = map.get(n);
			for(int i = 0; i < vec.length; i++)
			{
				String[] v = vec[i].split(",");
				if(isCharExistIn(v[0], '-'))
				{
					String[] v_pt2 = v[0].split("-");
					for(int j = Integer.parseInt(v_pt2[0]); j < Integer.parseInt(v_pt2[1]); j+=1)
					{
						if(entity)
						{
							Entity ent = LevelManager.getEntityByName(n);
							ent.setPosition(j, Integer.parseInt(v[1]));
							entities.add(ent);
							entity = false;
						}
						else
						{
							Block b = LevelManager.getObjectByName(n);
							b.setLocation(j, Integer.parseInt(v[1]));
							blocks.add(b);
						}
						
					}
				}
				else if(isCharExistIn(v[1], '-'))
				{
					String[] v_pt2 = v[1].split("-");
					for(int j = Integer.parseInt(v_pt2[0]); j < Integer.parseInt(v_pt2[1]); j+=1)
					{
						if(entity)
						{
							Entity ent = LevelManager.getEntityByName(n);
							ent.setPosition(Integer.parseInt(v[0]), j);
							entities.add(ent);
							entity = false;
						}
						else
						{
							Block b = LevelManager.getObjectByName(n);
							b.setLocation(Integer.parseInt(v[0]), j);
							blocks.add(b);
						}
						
					}
				}
				else
				{
					if(entity)
					{
						Entity ent = LevelManager.getEntityByName(n);
						ent.setPosition(Integer.parseInt(v[0]), Integer.parseInt(v[1]));
						entities.add(ent);
						entity = false;
					}
					else
					{
						//System.out.println("A:");
						Block b = LevelManager.getObjectByName(n);
						//System.out.println(Integer.parseInt(v[0]));
						b.setLocation(Integer.parseInt(v[0]), Integer.parseInt(v[1]));
						blocks.add(b);
						
					}
					
				}
				
			}
		}
	}
	
	private static boolean isCharExistIn(String s, char c)
	{
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i] == c)
			{
				return true;
			}
		}
		return false;
	}
	
}

