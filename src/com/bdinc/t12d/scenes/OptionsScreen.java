package com.bdinc.t12d.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;
import com.bdinc.t12d.ui.UIComponent;
import com.bdinc.t12d.ui.UIDropList;
import com.bdinc.t12d.utils.ColorManager;

public class OptionsScreen {
	
	private static Polygon exitArrow;
	
	public static ArrayList<UIComponent> ui = new ArrayList<UIComponent>();
	
	public static int btnBackX = 30;
	public static int btnBackY = 30;
	public static int btnBackWidth = 30;
	public static int btnBackHeight = 30;
	
	public static Color btnBackColor;
	
	public static void init() {
		int firstY = 121;
		exitArrow = new Polygon();
		exitArrow.addPoint(35, 45);
		exitArrow.addPoint(55, 35);
		exitArrow.addPoint(55, 55);
		
		UIDropList dl = new UIDropList(10, 300, 150, 20, Color.WHITE, Color.WHITE, new Color(0,0,0,0), Color.WHITE);
		dl.setArrowHoverColor(Color.RED);
		dl.setFont(ResourcesManager.defaultFont12);
		dl.setFontColor(Color.white);
		dl.setFontHoverColor(Color.yellow);
		dl.setHoverColor(ColorManager.getAlphaColor(ColorManager.GOLD, 90));
		dl.setDropButtonBackground(new Color(0,0,0,0), true);
		dl.setDropButtonHover(ColorManager.getAlphaColor(ColorManager.GOLD, 90));
		dl.setBorderHoverColor(Color.YELLOW);
		dl.items.add("2");
		dl.items.add("4");
		dl.init();
		ui.add(dl);
	}
	
	public static void load(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(btnBackX, btnBackY, btnBackWidth, btnBackHeight);
		
		g.setColor(btnBackColor);
		g.fillRect(btnBackX+1, btnBackY+1, btnBackWidth-1, btnBackHeight-1);
		
		g.setColor(Color.white);
		g.drawPolygon(exitArrow);
		g.fillPolygon(exitArrow);
		
		g.setFont(ResourcesManager.defaultFont);
		g.drawString(ResourcesManager.m_back, 65, 55);
		
		for(UIComponent c : ui) {
			c.draw(g);
		}
	}
	
}
