package com.bdinc.t12d.level;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.bdinc.t12d.objects.Ammo;
import com.bdinc.t12d.objects.Block;
import com.bdinc.t12d.objects.Button;
import com.bdinc.t12d.objects.Chest;
import com.bdinc.t12d.objects.Door;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.objects.Gun;
import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.objects.Key;
import com.bdinc.t12d.objects.MakarovAmmo;
import com.bdinc.t12d.objects.MakarovGun;
import com.bdinc.t12d.objects.Platform;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.utils.ColorManager;
import com.bdinc.t12d.utils.Container;
import com.bdinc.t12d.utils.Debug;

public class LevelReader {
	
	private static int tmpIndex = 0;
	private static int blockCount;
	private static boolean entity = false;
	private static boolean flame = false;
	private static boolean platform = false;
	private static boolean info = false;
	private static boolean button = false;
	private static boolean container = false;
	private static boolean door = false;
	private static boolean item = false, key = false, gun = false, ammo = false;
	
	private static String lvlName, lvlAuthor, lvlVer;
	private static int lvlID;
	
	private static Container cont = new Container(null, null);
	
	public Level readLevel(String file)
	{
		BufferedReader reader = null;
		Level lvl = new Level();
		ArrayList<Block> blocks = new ArrayList<Block>();
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Entity> entities = new ArrayList<Entity>();
		ArrayList<Flame> flames = new ArrayList<Flame>();
		ArrayList<SlotContainer> conts = new ArrayList<SlotContainer>();
		int p = 0;
		try
		{
			if(file.startsWith("assets/levels/extra/")) {
				lvl.isExtra = true;
			}
			reader = new BufferedReader(new FileReader(file));
			String line;
			String text = "";

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
					analyzeCode(lvl, map, blocks, items, entities, flames, conts);
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
//		for(Block b : blocks) {
//			Button btn = null;
//			if(btn instanceof Button) {
//				btn = (Button)b;
//				for(Block b1 : blocks) {
//					if(btn.getActionObject() instanceof Block && ((Block)btn.getActionObject()).getCell().x == b1.getCell().x && ((Block)btn.getActionObject()).getCell().y == b1.getCell().y) {
//						btn.setActionObject(b1);
//					}
//				}
//				for(Block b1 : blocks) {
//					Platform p1 = (Platform)b1;
//					if(btn.getActionObject() instanceof Platform && ((Platform)btn.getActionObject()).getCell().x == p1.getCell().x && ((Platform)btn.getActionObject()).getCell().y == p1.getCell().y) {
//						btn.setActionObject(b1);
//					}
//				}
//				for(Block b1 : blocks) {
//					Door p1 = (Door)b1;
//					if(btn.getActionObject() instanceof Door && ((Door)btn.getActionObject()).getCell().x == p1.getCell().x && ((Door)btn.getActionObject()).getCell().y == p1.getCell().y) {
//						btn.setActionObject(b1);
//					}
//				}
//				for(Block b1 : blocks) {
//					Chest p1 = (Chest)b1;
//					if(btn.getActionObject() instanceof Chest && ((Chest)btn.getActionObject()).getCell().x == p1.getCell().x && ((Chest)btn.getActionObject()).getCell().y == p1.getCell().y) {
//						btn.setActionObject(b1);
//					}
//				}
//			}
//			
//		}
		lvl.entities = entities;
		lvl.blocks = blocks;
		lvl.flames = flames;
		lvl.conts = conts;
		lvl.items = items;
		//Debug.log(lvlName);
		lvl.setName(lvlName);
		lvl.setID(lvlID);
		lvl.setAuthor(lvlAuthor);
		lvl.setVersion(lvlVer);
		tmpIndex = 0;
		//Debug.log(lvlName);
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
	
	private static void analyzeCode(Level lvl1, HashMap<String, String[]> map, ArrayList<Block> blocks, ArrayList<Item> items, ArrayList<Entity> entities,
			ArrayList<Flame> flames, ArrayList<SlotContainer> conts)
	{
		for(String n : map.keySet())
		{
			if(n.equals("INFO")) {
				info = true;
			} else {
				info = false;
			}
			if(n.startsWith("ENT"))
			{
				entity = true;
			}
			else {
				entity = false;
			}
			if(n.startsWith("ITM")) {
				item = true;
			}  else {
				item = false;
			}
			if(n.startsWith("GUN")) {
				gun = true;
			}  else {
				gun = false;
			}
			if(n.equals("KEY")) {
				key = true;
			}  else {
				key = false;
			}
			if(n.startsWith("AMMO")) {
				ammo = true;
			} else {
				ammo = false;
			}
			if(n.startsWith("PLT")) {
				platform = true;
			} else {
				platform = false;
			}
			if(n.startsWith("BUTTON")) {
				button = true;
			} else {
				button = false;
			}
			if(n.startsWith("FLAME")) {
				flame = true;
			}
			else {
				flame = false;
			}
			if(n.startsWith("CONT")) {
				container = true;
			} else {
				container = false;
			}
			if(n.startsWith("DOOR")) {
				door = true;
			} else {
				door = false;
			}
			String[] vec = map.get(n);
			Platform p = LevelManager.getPlatformByName(n);
			Button btn = LevelManager.getButton(n);
			SlotContainer cont = LevelManager.getContainer(n);
			int slotW = 0, slotH = 0, slotNum = 0, ovW = 0, ovH = 0;
			Color ovBg = null, ovBorder = null, slotBg = null, 
					slotBorder = null, slotInfo = null, ttipBg = null, 
					ttipBorder = null, ttipTxt = null, slotHover = null;
			if(info) {
				//Debug.log("!");
				for(int i = 0; i < 4; i++) {
					String[] propertyParts = vec[i].split("=");
					if(propertyParts[0].endsWith(" ") || propertyParts[0].startsWith(" ")) {
						propertyParts[0] = propertyParts[0].replace(" ", "").trim();
					}
					if (propertyParts[1].endsWith(" ") || propertyParts[1].startsWith(" ")) {
						if(isCharExistIn(propertyParts[1], '"')) {
							String[] part = propertyParts[1].split("\"");
							propertyParts[1] = part[1];
						} else {
							propertyParts[1] = propertyParts[1].replace(" ", "").trim();
						}
						
						
					}
					switch(propertyParts[0]) {
						case "levelName":
							lvlName = propertyParts[1];
							break;
						case "levelID":
							lvlID = Integer.parseInt(propertyParts[1]);
							break;
						case "author":
							lvlAuthor = propertyParts[1];
							break;
						case "version":
							lvlVer = propertyParts[1];
							break;
					}
				}
			} 
			else if(ammo) {
				Ammo itm = null;
				String[] namePt = n.split("_");
				switch(namePt[1]) {
					case "MAKAROVGUN":
						itm = new MakarovAmmo();
						break;
				}
				for(int i = 0; i < vec.length; i++) {
					String[] propertyParts = vec[i].split("=");
					if(propertyParts[0].endsWith(" ") || propertyParts[0].startsWith(" ")) {
						propertyParts[0] = propertyParts[0].replace(" ", "").trim();
					}
					if (propertyParts[1].endsWith(" ") || propertyParts[1].startsWith(" ")) {
						propertyParts[1] = propertyParts[1].replace(" ", "").trim();
						
					}
					switch(propertyParts[0]) {
						case "count": 
							itm.setCount(Integer.parseInt(propertyParts[1]));
							break;
						case "position": 
							String[] cds = propertyParts[1].split("\\,");
							int x = Integer.parseInt(cds[0]);
							int y = Integer.parseInt(cds[1]);
							itm.setLocation(x, y);
							break;
					}
				}
				items.add(itm);
			}
			else if (gun) {
				Gun itm = null;
				String[] namePt = n.split("_");
				switch(namePt[1]) {
					case "MAKAROVGUN":
						itm = new MakarovGun();
						break;
				}
				for(int i = 0; i < vec.length; i++) {
					String[] propertyParts = vec[i].split("=");
					if(propertyParts[0].endsWith(" ") || propertyParts[0].startsWith(" ")) {
						propertyParts[0] = propertyParts[0].replace(" ", "").trim();
					}
					if (propertyParts[1].endsWith(" ") || propertyParts[1].startsWith(" ")) {
						propertyParts[1] = propertyParts[1].replace(" ", "").trim();
						
					}
					switch(propertyParts[0]) {
						case "ammoCount":
							itm.ammoCount = Integer.parseInt(propertyParts[1]);
							break;
						case "maxAmmoCount":
							itm.maxAmmoCount = Integer.parseInt(propertyParts[1]);
							break;
						case "hitDamage":
							itm.hitDamage = Integer.parseInt(propertyParts[1]);
							break;
						case "count":
							itm.setCount(Integer.parseInt(propertyParts[1]));
							break;
						case "position":
							String[] cords = propertyParts[1].split("\\,");
							int x = Integer.parseInt(cords[0]);
							int y = Integer.parseInt(cords[1]);
							itm.setLocation(x, y);
							break;
					}
				}
				items.add(itm);
			} 
			else if (key) {
				Key k = null;
				for(int i = 0; i < vec.length; i++) {
					String[] propertyParts = vec[i].split("=");
					if(propertyParts[0].endsWith(" ") || propertyParts[0].startsWith(" ")) {
						propertyParts[0] = propertyParts[0].replace(" ", "").trim();
					}
					if (propertyParts[1].endsWith(" ") || propertyParts[1].startsWith(" ")) {
						propertyParts[1] = propertyParts[1].replace(" ", "").trim();
						
					}
					switch(propertyParts[0]) {
						case "type":
							switch(propertyParts[1]) {
								case "0":
									k = new Key(ResourcesManager.key);
									break;
								default:
									k = new Key(ResourcesManager.key);
									break;
							}
							break;
						case "position":
							String[] cords = propertyParts[1].split("\\,");
							int x = Integer.parseInt(cords[0]);
							int y = Integer.parseInt(cords[1]);
							k.setLocation(x, y);
							break;
						case "count":
							k.setCount(Integer.parseInt(propertyParts[1]));
							break;
					}
				}
				items.add(k);
			}
			else if(platform) {
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
			else if (door) {
				Door d = new Door(ResourcesManager.doorClosed);
				for(int i = 0; i < vec.length; i++) {
					String[] propertyParts = vec[i].split("=");
					if(propertyParts[0].endsWith(" ") || propertyParts[0].startsWith(" ")) {
						propertyParts[0] = propertyParts[0].replace(" ", "").trim();
					}
					if (propertyParts[1].endsWith(" ") || propertyParts[1].startsWith(" ")) {
						propertyParts[1] = propertyParts[1].replace(" ", "").trim();
					}
					switch(propertyParts[0]) {
						case "isOpened":
							if(propertyParts[1].equals("true")) {
								d.open();
							} else {
								d.close();
							}
							break;
						case "key":
							String[] cord = propertyParts[1].split("\\,");
							int x = Integer.parseInt(cord[0]);
							int y = Integer.parseInt(cord[1]);
							for(Item it : items) {
								if(it instanceof Key) {
									Key k = (Key)it;
									if(k.getCell().x == x && k.getCell().y == y) {
										d.setKey(k);
									}
								}
							}
							break;
						case "requiresKey":
							boolean reqK = Boolean.parseBoolean(propertyParts[1]);
							d.requiresKey = reqK;
							break;
						case "position":
							String[] cord2 = propertyParts[1].split("\\,");
							int x2 = Integer.parseInt(cord2[0]);
							int y2 = Integer.parseInt(cord2[1]);
							d.setLocation(x2, y2);
							break;
					}
				}
				//Debug.log(d.getCell().x+","+d.getCell().y);
				blocks.add(d);
			}
			else if(button) {
				for(int i = 0; i < vec.length; i++) {
					String[] propertyParts = vec[i].split("=");
					if(propertyParts[0].endsWith(" ") || propertyParts[0].startsWith(" ")) {
						propertyParts[0] = propertyParts[0].replace(" ", "").trim();
					}
					if (propertyParts[1].endsWith(" ") || propertyParts[1].startsWith(" ")) {
						if(isCharExistIn(propertyParts[1], '"')) {
							String[] part = propertyParts[1].split("\"");
							propertyParts[1] = part[1];
						} else {
							propertyParts[1] = propertyParts[1].replace(" ", "").trim();
						}
					}
					switch(propertyParts[0]) {
						case "target":
							if (propertyParts[1].startsWith("platform")) {
								String[] plP1 = propertyParts[1].split("\\(");
								String[] plP2 = plP1[1].split("\\)");
								String[] cords = plP2[0].split("\\,");
								int x = Integer.parseInt(cords[0]);
								int y = Integer.parseInt(cords[1]);
								for(Block bck : blocks) {
									if(bck instanceof Platform) {
										Platform pl = (Platform)bck;
										if(pl.getCell().x == x && pl.getCell().y == y) {
											btn.setActionObject(pl);
										}
									}
									
								}
								
							} else if (propertyParts[1].startsWith("door")) {
								Door pl = new Door(null);
								String[] plP1 = propertyParts[1].split("\\(");
								String[] plP2 = plP1[1].split("\\)");
								String[] cords = plP2[0].split("\\,");
								int x = Integer.parseInt(cords[0]);
								int y = Integer.parseInt(cords[1]);
								for(Block bck : blocks) {
									if(bck instanceof Door) {
										Door dr = (Door)bck;
										if(dr.getCell().x == x && dr.getCell().y == y) {
											btn.setActionObject(dr);
										}
									}
									
								}
							} else if (propertyParts[1].startsWith("block")) {
								Block pl = new Block(null);
								String[] plP1 = propertyParts[1].split("\\(");
								String[] plP2 = plP1[1].split("\\)");
								String[] cords = plP2[0].split("\\,");
								int x = Integer.parseInt(cords[0]);
								int y = Integer.parseInt(cords[1]);
								for(Block bck : blocks) {
									if(bck instanceof Block) {
										Block ct = (Block)bck;
										if(ct.getCell().x == x && ct.getCell().y == y) {
											btn.setActionObject(ct);
										}
									}
									
								}
							} else if (propertyParts[1].startsWith("chest")) {
								Chest pl = new Chest(null);
								String[] plP1 = propertyParts[1].split("\\(");
								String[] plP2 = plP1[1].split("\\)");
								String[] cords = plP2[0].split("\\,");
								int x = Integer.parseInt(cords[0]);
								int y = Integer.parseInt(cords[1]);
								for(Block bck : blocks) {
									if(bck instanceof Chest) {
										Chest ct = (Chest)bck;
										if(ct.getCell().x == x && ct.getCell().y == y) {
											btn.setActionObject(ct);
										}
									}
									
								}
							} else {
								JOptionPane.showMessageDialog(null, "������������ �������� �������� <TARGET> ������ <"+btn.toString()+">!\nIncorrect value of button's <"+btn.toString()+"> property <TARGET>!", "Error!", 0);
							}
							break;
						case "onceClick":
							btn.onceClickMode = Boolean.parseBoolean(propertyParts[1]);
							break;
						case "commandArgs":
							btn.setActionArguments(propertyParts[1]);
							break;
						case "position":
							String[] cords = propertyParts[1].split(",");
							btn.setLocation(Integer.parseInt(cords[0]), Integer.parseInt(cords[1]));
							break;
						case "active":
							btn.active = Boolean.parseBoolean(propertyParts[1]);
							break;
					}
				}
				blocks.add(btn);
			}
			else if (container)
			{
				String[] p1 = null;
				String[] p2 = null;
				String[] properties = null;
				int r = 0;
				int g = 0;
				int b = 0;
				int a = 0;
				int cellCount = 0;
				for(int i = 0; i < vec.length; i++) {
					String[] propertyParts = vec[i].split("=");
					if(propertyParts[0].startsWith("cell")) {
						cellCount += 1;
					}
				}
				Item[] slots = new Item[cellCount];
				//Debug.log("Count: "+);
				for(int i = 0; i < vec.length; i++) {
					//System.out.println("property: "+vec[i]);
					String[] propertyParts = vec[i].split("=");
					if(propertyParts[0].endsWith(" ") || propertyParts[0].startsWith(" ")) {
						propertyParts[0] = propertyParts[0].replace(" ", "").trim();
					}
					if (propertyParts[1].endsWith(" ") || propertyParts[1].startsWith(" ")) {
						propertyParts[1] = propertyParts[1].replace(" ", "").trim();
					}
					if(propertyParts[0].startsWith("cell"))	{
						String[] t1 = propertyParts[0].split("\\[");
						String[] t2 = t1[1].split("\\]");
						int index = Integer.parseInt(t2[0]);
						String[] t3 = propertyParts[1].split("\\(");
						switch(t3[0]) {
							case "item":
								String[] t4 = t3[1].split("\\)");
								String[] args = t4[0].split(",");
								args[0] = args[0].replace("\"", "").trim();
								switch(args[0]) {
									case "MakarovGun":
										slots[index] = new MakarovGun(ResourcesManager.makarovGun);
										slots[index].setCount(Integer.parseInt(args[1].trim()));
										break;
								}
								break;
						}
					} else {
						switch(propertyParts[0]) {
							case "slotWidth":
								slotW = Integer.parseInt(propertyParts[1]);
								break;
							case "slotHeight":
								slotH = Integer.parseInt(propertyParts[1]);
								break;
							case "slotNumber":
								slotNum = Integer.parseInt(propertyParts[1]);
								break;
							case "overlayWidth":
								ovW = Integer.parseInt(propertyParts[1]);
								break;
							case "overlayHeight":
								ovH = Integer.parseInt(propertyParts[1]);
								break;
							case "overlayBackground":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								ovBg = new Color(r, g, b, a);
								break;
							case "overlayBorderColor":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								ovBorder = new Color(r, g, b, a);
								break;
							case "slotBackground":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								slotBg = new Color(r, g, b, a);
								break;
							case "slotBorderColor":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								slotBorder = new Color(r, g, b, a);
								break;
							case "slotItemCountColor":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								slotInfo = new Color(r, g, b, a);
								//Debug.log("R:"+r+" G:"+g+" B:"+b+" A:"+a);
								break;
							case "slotHoverColor":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								slotHover = new Color(r, g, b, a);
								break;
							case "tooltipBackground":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								ttipBg = new Color(r, g, b, a);
								break;
							case "tooltipBorder":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								ttipBorder = new Color(r, g, b, a);
								break;
							case "tooltipTextColor":
								p1 = propertyParts[1].split("\\(");
								p2 = p1[1].split("\\)");
								properties = p2[0].split("\\,");
								r = Integer.parseInt(properties[0]);
								g = Integer.parseInt(properties[1]);
								b = Integer.parseInt(properties[2]);
								a = Integer.parseInt(properties[3]);
								ttipTxt = new Color(r, g, b, a);
								break;
							case "position":
								String[] cords = propertyParts[1].split("\\,");
								int x = Integer.parseInt(cords[0]);
								int y = Integer.parseInt(cords[1]);
								cont.setLocation(x, y);
								break;
						}	
					}
				}
				UISlot slot = new UISlot(0, 0, slotW, slotH, slotBg, slotHover, true);
				slot.setBorderColor(slotBorder);
				slot.setCountInfoColor(slotInfo);
				slot.setTooltipBackground(ttipBg);
				slot.setTooltipBorderColor(ttipBorder);
				slot.setTooltipTextColor(ttipTxt);
				cont.init(slotNum, ovBg, ovBorder,slot, ovW, ovH);
				//Debug.log(slots.length);
				for(int i = 0; i < slots.length; i++) {
					cont.cells.get(i).putItem(slots[i]);
					cont.cells.get(i).setItemCount(slots[i].getCount());
				}
				conts.add(cont);
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
							} else if (item) {
								Item it = LevelManager.getItemByName(n);
								it.setLocation(j, Integer.parseInt(v[1]));
								items.add(it);
							}
							else if(!entity && !flame && !platform && !door && !container && !button && !item)
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
							} else if (item) {
								Item it = LevelManager.getItemByName(n);
								it.setLocation(Integer.parseInt(v[0]), j);
								items.add(it);
							}
							else if(!entity && !flame && !platform && !door && !container && !button && !item)
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
						} else if (item) {
							Item it = LevelManager.getItemByName(n);
							it.setLocation(Integer.parseInt(v[0]), Integer.parseInt(v[1]));
							items.add(it);
						}
						else if(!entity && !flame && !platform && !door && !container && !button && !item)
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

