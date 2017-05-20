package com.bdinc.t12d.main;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResourcesManager {
	
	public static String assetsDir = "assets";
	public static String spritesDir = "assets/sprites";
	public static String entitiesDir = "assets/sprites/entities";
	public static String blocksDir = "assets/sprites/blocks";
	
	public static Image gameIcon = new ImageIcon("assets/gameIcon.png").getImage();
	public static Image brick6 = new ImageIcon(blocksDir+"/brick6.png").getImage();
}
