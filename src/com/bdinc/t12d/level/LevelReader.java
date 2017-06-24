package com.bdinc.t12d.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Platform;
import com.bdinc.t12d.utils.Container;

public class LevelReader {
	
	private static int tmpIndex = 0;
	private static int blockCount;
	private static boolean entity = false;
	private static boolean flame = false;
	private static boolean platform = false;
	
	private static Container cont = new Container(null, null);
	
	public Level readLevel(String file)
	{
		BufferedReader reader = null;
		Level lvl = new Level();
		ArrayList<Block> blocks = new ArrayList<Block>();
		ArrayList<Entity> entities = new ArrayList<Entity>();
		ArrayList<Flame> flames = new ArrayList<Flame>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String line;
			String text = "";
			//System.out.println("A:"+file);
			while((line = reader.readLine()) != null)
			{
				if(line.startsWith("\t")) {
					line = line.replace("\t", "").trim();
				}
				text += line;
			}
			//System.out.println("A:"+text);
			String[] tmp = text.split("\\.");
			blockCount = tmp.length;

			while(tmpIndex < blockCount)
			{
				HashMap<String, String[]> map = readBlock(tmp[tmpIndex]+".", "#", ";", ":", "\\.");
				
				try {
					analyzeCode(lvl, map, blocks, entities, flames);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				
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
		lvl.flames = flames;
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
	
	private static void analyzeCode(Level lvl1, HashMap<String, String[]> map, ArrayList<Block> blocks, ArrayList<Entity> entities,
			ArrayList<Flame> flames)
	{
		for(String n : map.keySet())
		{
			if(n.startsWith("ENT"))
			{
				entity = true;
			}
			else {
				entity = false;
			}
			if(n.startsWith("PLT")) {
				platform = true;
			} else {
				platform = false;
			}
			if(n.startsWith("FLAME")) {
				flame = true;
			}
			else {
				flame = false;
			}
			String[] vec = map.get(n);
			Platform p = LevelManager.getPlatformByName(n);
			if(platform) {
				for(int i = 0; i < 4; i++) {
					//System.out.println("property: "+vec[i]);
					String[] propertyParts = vec[i].split("=");
					//System.out.println(propertyParts[1]);
					if(propertyParts[0].endsWith(" ") || propertyParts[0].startsWith(" ")) {
						propertyParts[0] = propertyParts[0].replace(" ", "").trim();
					}
					if (propertyParts[1].endsWith(" ") || propertyParts[1].startsWith(" ")) {
						propertyParts[1] = propertyParts[1].replace(" ", "").trim();
						
					}
					switch(propertyParts[0]) {
						case "speed":
							//char[] ppc = propertyParts[1].toCharArray();
							String v = propertyParts[1].replace(',', '.');
							p.setSpeed(Float.parseFloat(v));
							break;
						case "count":
							p.setPathCount(Integer.parseInt(propertyParts[1]));
							break;
						case "direction":
							p.setDirection(Integer.parseInt(propertyParts[1]));
							break;
						case "position":
							String[] cords = propertyParts[1].split(",");
							int x = Integer.parseInt(cords[0]);
							int y = Integer.parseInt(cords[1]);
							p.setLocation(x, y);
							break;
					}
				}
				//System.out.println(""+p.getCell().x);
				blocks.add(p);
			} 
			else 
			{
				for(int i = 0; i < vec.length; i++)
				{
					String[] v = vec[i].split(",");
					if(isCharExistIn(v[0], '-'))
					{
						String[] v_pt2 = v[0].split("-");
						for(int j = Integer.parseInt(v_pt2[0]); j < Integer.parseInt(v_pt2[1])+1; j+=1)
						{
							if(entity)
							{
								Entity ent = LevelManager.getEntityByName(n);
								ent.setPosition(j, Integer.parseInt(v[1]));
								entities.add(ent);
							}
							else if(flame) {
								Flame f = LevelManager.getFlame(n);
								f.setLocation(j, Integer.parseInt(v[1]));
								flames.add(f);
							}
							else if(!entity && !flame && !platform)
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
						for(int j = Integer.parseInt(v_pt2[0]); j < Integer.parseInt(v_pt2[1])+1; j+=1)
						{
							if(entity)
							{
								Entity ent = LevelManager.getEntityByName(n);
								ent.setPosition(Integer.parseInt(v[0]), j);
								entities.add(ent);
							}
							else if(flame) {
								Flame f = LevelManager.getFlame(n);
								f.setLocation(Integer.parseInt(v[0]), j);
								flames.add(f);
							}
							else if(!entity && !flame && !platform)
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
						}
						else if(flame) {
							Flame f = LevelManager.getFlame(n);
							f.setLocation(Integer.parseInt(v[0]), Integer.parseInt(v[1]));
							flames.add(f);
						}
						else if(!entity && !flame && !platform)
						{
							Block b = LevelManager.getObjectByName(n);
							b.setLocation(Integer.parseInt(v[0]), Integer.parseInt(v[1]));
							blocks.add(b);
								
						}
							
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

