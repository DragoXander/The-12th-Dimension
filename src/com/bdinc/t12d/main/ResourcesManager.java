package com.bdinc.t12d.main;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResourcesManager {
	
	public static String assetsDir = "assets";
	public static String spritesDir = "assets/sprites";
	public static String entitiesDir = "assets/sprites/entities";
	public static String blocksDir = "assets/sprites/blocks";
	public static String envDir = "assets/sprites/environment";
	public static String itemsDir = "assets/sprites/items";
	public static String particlesDir = "assets/sprites/particles";
	public static String guiDir = "assets/gui";
	
	/*
	 * #GUI
	 */
	public static Image gameIcon = new ImageIcon("assets/gameIcon.png").getImage();
	public static Image logo = new ImageIcon(guiDir+"/logo.gif").getImage();
	public static Image playBtn = new ImageIcon(guiDir+"/buttons/btnPlay.png").getImage();
	public static Image loadBtn = new ImageIcon(guiDir+"/buttons/btnLoad.png").getImage();
	public static Image optBtn = new ImageIcon(guiDir+"/buttons/btnOptions.png").getImage();
	public static Image exitBtn = new ImageIcon(guiDir+"/buttons/btnExit.png").getImage();
	public static Image playBtnHover = new ImageIcon(guiDir+"/buttons/btnPlay_hover_style2.png").getImage();
	public static Image loadBtnHover = new ImageIcon(guiDir+"/buttons/btnLoad_hover_style2.png").getImage();
	public static Image optBtnHover = new ImageIcon(guiDir+"/buttons/btnOptions_hover_style2.png").getImage();
	public static Image exitBtnHover = new ImageIcon(guiDir+"/buttons/btnExit_hover_style2.png").getImage();
	
	/*
	 * #Blocks
	 */
	public static Image brick1 = new ImageIcon(blocksDir+"/brick1.png").getImage();
	public static Image brick2 = new ImageIcon(blocksDir+"/brick2.png").getImage();
	public static Image brick3 = new ImageIcon(blocksDir+"/brick3.png").getImage();
	public static Image brick4 = new ImageIcon(blocksDir+"/brick4.png").getImage();
	public static Image brick5 = new ImageIcon(blocksDir+"/brick5.png").getImage();
	public static Image brick6 = new ImageIcon(blocksDir+"/brick6.png").getImage();
	public static Image brick7 = new ImageIcon(blocksDir+"/brick7.png").getImage();
	
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
}
