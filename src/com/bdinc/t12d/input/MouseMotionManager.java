package com.bdinc.t12d.input;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.scenes.ProfilesListDialog;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;

public class MouseMotionManager extends MouseAdapter implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(LevelManager.levelNumber == 0) {
			if(e.getX() >= Game.m_playBtnX && e.getX() <= Game.m_playBtnX+Game.m_playBtn.getWidth(null)) {
				if(e.getY() >= Game.m_playBtnY && e.getY() <= Game.m_playBtnY+Game.m_playBtn.getHeight(null)) {
					Game.m_playBtn = ResourcesManager.playBtnHover;
				}
				else {
					Game.m_playBtn = ResourcesManager.playBtn;
				}
			}
			else {
				Game.m_playBtn = ResourcesManager.playBtn;
			}
			//ShopButton
			if(e.getX() >= Game.m_shopBtnX && e.getX() <= Game.m_shopBtnX+Game.m_shopBtn.getWidth(null)) {
				if(e.getY() >= Game.m_shopBtnY && e.getY() <= Game.m_shopBtnY+Game.m_shopBtn.getHeight(null)) {
					Game.m_shopBtn = ResourcesManager.shopBtnHover;
				}
				else {
					Game.m_shopBtn = ResourcesManager.shopBtn;
				}
			}
			else {
				Game.m_shopBtn = ResourcesManager.shopBtn;
			}
			//OptionsButton
			if(e.getX() >= Game.m_optBtnX && e.getX() <= Game.m_optBtnX+Game.m_optBtn.getWidth(null)) {
				if(e.getY() >= Game.m_optBtnY && e.getY() <= Game.m_optBtnY+Game.m_optBtn.getHeight(null)) {
					Game.m_optBtn = ResourcesManager.optBtnHover;
				}
				else {
					Game.m_optBtn = ResourcesManager.optBtn;
				}
			}
			else {
				Game.m_optBtn = ResourcesManager.optBtn;
			}
			//ExitButton
			if(e.getX() >= Game.m_exitBtnX && e.getX() <= Game.m_exitBtnX+Game.m_exitBtn.getWidth(null)) {
				if(e.getY() >= Game.m_exitBtnY && e.getY() <= Game.m_exitBtnY+Game.m_exitBtn.getHeight(null)) {
					Game.m_exitBtn = ResourcesManager.exitBtnHover;
				}
				else {
					Game.m_exitBtn = ResourcesManager.exitBtn;
				}
			}
			else {
				Game.m_exitBtn = ResourcesManager.exitBtn;
			}
			//Profile select button
			if(e.getX() >= Game.m_profileBtnX && e.getX() <= Game.m_profileBtnX+Game.m_profileBtnWidth) {
				if(e.getY() >= Game.m_profileBtnY && e.getY() <= Game.m_profileBtnY+Game.m_profileBtnHeight) {
					Game.m_profileBtnColor = Color.ORANGE;
				}
				else {
					Game.m_profileBtnColor = Color.CYAN;
				}
			}
			else {
				Game.m_profileBtnColor = Color.CYAN;
			}
		}
		else if(LevelManager.levelNumber == -1)
		{
			if(e.getX() >= ProfilesListDialog.btnBackX && e.getX() <= ProfilesListDialog.btnBackX+ProfilesListDialog.btnBackWidth) {
				if(e.getY() >= ProfilesListDialog.btnBackY && e.getY() <= ProfilesListDialog.btnBackY+ProfilesListDialog.btnBackHeight) {
					ProfilesListDialog.btnBackColor = Color.BLUE;
				}
				else {
					ProfilesListDialog.btnBackColor = Color.RED;
				}
			} else {
				ProfilesListDialog.btnBackColor = Color.RED;
			}
			if(e.getX() >= ProfilesListDialog.btnNewX && e.getX() <= ProfilesListDialog.btnNewX+ProfilesListDialog.btnNewWidth) {
				if(e.getY() >= ProfilesListDialog.btnNewY && e.getY() <= ProfilesListDialog.btnNewY+ProfilesListDialog.btnNewHeight) {
					ProfilesListDialog.btnNewColor = Color.ORANGE;
				} else {
					ProfilesListDialog.btnNewColor = Color.YELLOW;
				}
			} else {
				ProfilesListDialog.btnNewColor = Color.YELLOW;
			}
			for(UICell c : ProfilesListDialog.profiles) {
				if(!c.isSelected) {
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.setBackground(c.getHoverColor());
						}
						else {
							try {
								c.resetBackground();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							
						}
					} else {
						try {
							c.resetBackground();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				} else {
					c.setBackground(c.getActiveColor());
				}
			}
		}

	}

}
