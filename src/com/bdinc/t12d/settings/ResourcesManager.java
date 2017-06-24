package com.bdinc.t12d.settings;

import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.bdinc.t12d.graphics.FontManager;
import com.bdinc.t12d.main.Game;

public class ResourcesManager {
	
	public static String assetsDir = "assets";
	public static String spritesDir = "assets/sprites";
	public static String entitiesDir = "assets/sprites/entities";
	public static String blocksDir = "assets/sprites/blocks";
	public static String envDir = "assets/sprites/environment";
	public static String itemsDir = "assets/sprites/items";
	public static String particlesDir = "assets/sprites/particles";
	public static String guiDir = "assets/gui";
	public static String fontsDir = guiDir+"/fonts";
	
	/*
	 * Fonts
	 */
	public static Font defaultFont = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 18);
	public static Font defaultFont16 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 16);
	public static Font defaultFont14 = FontManager.getFont(fontsDir+"/Bulgaria_Glorious_Cyr.ttf", 14);
	
	/*
	 * #GUI
	 */
	public static Image gameIcon = new ImageIcon("assets/gameIcon.png").getImage();
	public static Image logo = new ImageIcon(guiDir+"/logo.png").getImage();
	public static Image playBtn = new ImageIcon(guiDir+"/buttons/btnPlay.png").getImage();
	public static Image loadBtn = new ImageIcon(guiDir+"/buttons/btnLoad.png").getImage();
	public static Image shopBtn = new ImageIcon(guiDir+"/buttons/btnShop.png").getImage();
	public static Image optBtn = new ImageIcon(guiDir+"/buttons/btnOptions.png").getImage();
	public static Image exitBtn = new ImageIcon(guiDir+"/buttons/btnExit.png").getImage();
	public static Image playBtnHover = new ImageIcon(guiDir+"/buttons/btnPlay_hover_style2.png").getImage();
	public static Image loadBtnHover = new ImageIcon(guiDir+"/buttons/btnLoad_hover_style2.png").getImage();
	public static Image shopBtnHover = new ImageIcon(guiDir+"/buttons/btnShop_hover_style2.png").getImage();
	public static Image optBtnHover = new ImageIcon(guiDir+"/buttons/btnOptions_hover_style2.png").getImage();
	public static Image exitBtnHover = new ImageIcon(guiDir+"/buttons/btnExit_hover_style2.png").getImage();
	public static Image profile = new ImageIcon(guiDir+"/profile_image.png").getImage();
	
	/*
	 * #Blocks
	 */
	public static Image vplatformGray = new ImageIcon(blocksDir+"/vplatform_gray.gif").getImage();
	public static Image brick1 = new ImageIcon(blocksDir+"/brick1.png").getImage();
	public static Image brick2 = new ImageIcon(blocksDir+"/brick2.png").getImage();
	public static Image brick3 = new ImageIcon(blocksDir+"/brick3.png").getImage();
	public static Image brick4 = new ImageIcon(blocksDir+"/brick4.png").getImage();
	public static Image brick5 = new ImageIcon(blocksDir+"/brick5.png").getImage();
	public static Image brick6 = new ImageIcon(blocksDir+"/brick6.png").getImage();
	public static Image brick7 = new ImageIcon(blocksDir+"/brick7.png").getImage();
	public static Image wall1 = new ImageIcon(blocksDir+"/wall1.png").getImage();
	
	/*
	 * #Entities
	 */
	public static Image player = new ImageIcon(entitiesDir+"/AdamRobbins.png").getImage();
	public static Image darkOfficer = new ImageIcon(entitiesDir+"/dark_officer.png").getImage();
	public static Image lightOfficer = new ImageIcon(entitiesDir+"/light_officer.png").getImage();
	public static Image fireOfficer = new ImageIcon(entitiesDir+"/fire_officer.png").getImage();
	public static Image iceOfficer = new ImageIcon(entitiesDir+"/ice_officer.png").getImage();
	
	public static Image monstDarkness = new ImageIcon(entitiesDir+"/monst_darkness.png").getImage();
	public static Image monstLight = new ImageIcon(entitiesDir+"/monst_light.png").getImage();
	public static Image monstFire = new ImageIcon(entitiesDir+"/monst_fire.png").getImage();
	public static Image monstIce = new ImageIcon(entitiesDir+"/monst_ice.png").getImage();
	
	public static Image thief = new ImageIcon(entitiesDir+"/thief.png").getImage();
	
	/*
	 * #Environment
	 */
	public static Image finish = new ImageIcon(envDir+"/finish.png").getImage();
	public static Image flame = new ImageIcon(envDir+"/flame.png").getImage();
	public static Image flameOff = new ImageIcon(envDir+"/flame_off.png").getImage();
	
	/*
	 * #Items
	 */
	public static Image coin10_X16 = new ImageIcon(itemsDir+"/coin10_16x16.png").getImage();
	public static Image coin10 = new ImageIcon(itemsDir+"/coin10.png").getImage();
	public static Image life = new ImageIcon(itemsDir+"/life.png").getImage();
	public static Image ruby = new ImageIcon(itemsDir+"/ruby.png").getImage();
	
	/*
	 * #Particles
	 */
	public static Image bullet1_01 = new ImageIcon(particlesDir+"/bullet1_01.png").getImage();
	public static Image bullet1_02 = new ImageIcon(particlesDir+"/bullet1_02.png").getImage();
	public static Image bullet1_03 = new ImageIcon(particlesDir+"/bullet1_03.png").getImage();
	public static Image fire1 = new ImageIcon(particlesDir+"/fire1.png").getImage();
	public static Image fire2 = new ImageIcon(particlesDir+"/fire2.png").getImage();
	public static Image fire3 = new ImageIcon(particlesDir+"/fire3.png").getImage();
	public static Image fire = new ImageIcon(particlesDir+"/fire.gif").getImage();
	
	/*
	 * Initialization Processes
	 */
	public static void loadConfiguration() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("assets/saves/config/config.dat"));
			ArrayList<String> lines = new ArrayList<String>();
			String line = "";
			String profile = "";
			int bufferCount = 0;
			float musicVol, ambVol;
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			for(String ln : lines) {
				String[] filter = ln.split("=");
				switch(filter[0]) {
					case "MusicVolume":
						Options.musicVolume = Float.parseFloat(filter[1]);
						break;
					case "AmbientVolume":
						Options.ambientVolume = Float.parseFloat(filter[1]);
						break;
					case "CanvasBufferStrategy.BufferCount":
						if(filter[1].equals("@autoCountMode")) {
							if(Game.screenSize.height <= 768 || Game.screenSize.width <= 1360) {
								Options.bufferCount = 4;
							}
							else if (Game.screenSize.height >= 768 || Game.screenSize.width >= 1360){
								Options.bufferCount = 2;
							}
						} else {
							Options.bufferCount = Integer.parseInt(filter[1]);
						}
						break;
					case "CurrentProfile":
						Options.profileName = filter[1];
						break;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ArrayList<String> getProfilesList() {
		BufferedReader reader = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader("assets/saves/config/profileList.dat"));
			String line = "";
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		for(String line : lines) {
			if(line.equals("") || line.equals(null)) {
				lines.remove(line);
			}
		}
		return lines;
	}
	
}