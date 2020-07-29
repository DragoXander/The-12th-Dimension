package com.bdinc.t12d.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import com.bdinc.t12d.settings.ResourcesManager;

public class UIDropList extends UIComponent {
	
	private Color borderColor, borderHover, background, hover, tmpBg, btnDropBackground, btnDropHover, btnDropTmpBg;
	private Color arrowBackground, arrowBorder, arrowHover, arrowTmpBg, fontColor, fontHover;
	
	public boolean isHover, isArrowHover;
	
	public Polygon arrow;
	
	public int btnDropX = x+(width-(width/5));
	public int btnDropY = y+1;
	public int btnDropWidth = (width/5);
	public int btnDropHeight = height-2;
	public int arrowWidth = btnDropWidth/2;
	public int arrowHeight = btnDropHeight/2;
	public int arrowX = btnDropX+arrowWidth/2;
	public int arrowY = btnDropY+arrowHeight/2;
	
	private Font font;
	
	public String caption = "";
	
	public boolean drop;
	
	public ArrayList<String> items = new ArrayList<String>();
	public ArrayList<UICell> cells = new ArrayList<UICell>();
	
	public UICell selected;
	
	private int firstY, index = 0, count = 0;
	
	public UIDropList() {}
	
	public UIDropList(int x, int y, int w, int h, Color border, Color arrowBorder, Color background, Color arrowBackground) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.borderColor = border;
		this.arrowBorder = arrowBorder;
		this.background = background;
		this.tmpBg = background;
		this.arrowTmpBg = arrowBackground;
		this.arrowBackground = arrowBackground;
		btnDropX = x+(width-(width/5));
		btnDropY = y+1;
		btnDropWidth = (width/5);
		btnDropHeight = height-2;
		arrowWidth = btnDropWidth/2;
		arrowHeight = btnDropHeight/2;
		arrowX = btnDropX+arrowWidth/2;
		arrowY = btnDropY+arrowHeight/2;
		arrow = new Polygon();
		arrow.addPoint(arrowX, arrowY);
		arrow.addPoint(arrowX+arrowWidth, arrowY);
		arrow.addPoint(arrowX+(arrowWidth/2), arrowY+arrowHeight);
		firstY = y+height+1;
	}
	
	public void init() {
		for(String c : items) {
			UICell item = new UICell(c, ResourcesManager.ruby, Color.GRAY, Color.RED, Color.CYAN, false);
			item.setBorderColor(Color.WHITE);
			item.setSize(width, 25);
			item.setPosition(x, firstY+index);
			item.setTitleColor(Color.WHITE);
			item.setTitleFont(ResourcesManager.defaultFont14);
			cells.add(item);
			firstY = firstY+index;
			index = 27;
			count++;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(isHover) {
			g.setColor(borderHover);
			g.drawRect(x, y, width, height);
			g.setColor(hover);
			g.fillRect(x+1, y+1, width-(width/5), height-1);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics();
			g.setColor(fontHover);
			g.drawString(caption, x+2, (int)(y+fm.getLineMetrics(caption, g).getHeight()));
			
			g.setColor(borderHover);
			g.drawRect(x+(width-(width/5)), y+1, width/5, height-1);
			g.setColor(btnDropHover);
			g.fillRect(x+(width-(width/5))+1, y+2, width/5-1, height-2);
			
			g.setColor(arrowBorder);
			g.drawPolygon(arrow);
			g.setColor(arrowHover);
			g.fillPolygon(arrow);
			
			if(drop) {
				for(UICell d : cells) {
					if(d.isSelected && !d.equals(selected)) {
						for(UICell d2 : cells) {
							if(d2.equals(selected)) {
								d2.isSelected = false;
								d2.resetBackground();
								selected = null;
							}
						}
						selected = d;
						selected.setBackground(selected.activeColor);
					}
					d.draw(g);
				}
			}
		} else {
			g.setColor(borderColor);
			g.drawRect(x, y, width, height);
			g.setColor(background);
			g.fillRect(x+1, y+1, width-1, height-1);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics();
			g.setColor(fontColor);
			g.drawString(caption, x+2, y+fm.getHeight());
			
			g.setColor(borderColor);
			g.drawRect(x+(width-(width/5)), y+1, width/5, height-1);
			g.setColor(btnDropBackground);
			g.fillRect(x+(width-(width/5))+1, y+2, width/5-1, height-2);
			
			g.setColor(arrowBorder);
			g.drawPolygon(arrow);
			g.setColor(arrowBackground);
			g.fillPolygon(arrow);
			
			if(drop) {
				for(UICell d : cells) {
					if(d.isSelected && !d.equals(selected)) {
						for(UICell d2 : cells) {
							if(d2.equals(selected)) {
								d2.isSelected = false;
								d2.resetBackground();
								selected = null;
							}
						}
						selected = d;
						selected.setBackground(selected.activeColor);
					}
					d.draw(g);
				}
			}
		}
		
	}
	
	public void setBackground(Color bg) {
		this.background = bg;
	}
	
	public void resetBackground() {
		this.background = this.tmpBg;
	}
	
	public void setHoverColor(Color h) {
		this.hover = h;
	}
	
	public void setBorderColor(Color b) {
		this.borderColor = b;
	}
	
	public void setBorderHoverColor(Color bh) {
		this.borderHover = bh;
	}
	
	public void setDropButtonBackground(Color bdBg, boolean init) {
		this.btnDropBackground = bdBg;
		if(init) {
			this.btnDropTmpBg = bdBg;
		}
	}
	
	public void setDropButtonHover(Color dbH) {
		this.btnDropHover = dbH;
	}
	
	public void resetDropButtonBackground() {
		this.btnDropBackground = this.btnDropTmpBg;
	}
	
	public void setArrowBackground(Color bg, boolean init) {
		this.arrowBackground = bg;
		if(init) {
			this.arrowTmpBg = bg;
		}
	}
	
	public void resetArrowBackground() {
		this.arrowBackground = this.arrowTmpBg;
	}
	
	public void setArrowHoverColor(Color h) {
		this.arrowHover = h;
	}
	
	public void setArrowBorderColor(Color b) {
		this.arrowBorder = b;
	}
	
	public void setFont(Font f) {
		this.font = f;
	}
	
	public void setFontColor(Color f) {
		this.fontColor = f;
	}
	
	public void setFontHoverColor(Color fh) {
		this.fontHover = fh;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Font getFont() {
		return this.font;
	}
}
