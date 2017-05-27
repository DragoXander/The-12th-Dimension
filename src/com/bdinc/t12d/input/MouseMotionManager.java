package com.bdinc.t12d.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.main.ResourcesManager;

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
			//LoadButton
			if(e.getX() >= Game.m_loadBtnX && e.getX() <= Game.m_loadBtnX+Game.m_loadBtn.getWidth(null)) {
				if(e.getY() >= Game.m_loadBtnY && e.getY() <= Game.m_loadBtnY+Game.m_loadBtn.getHeight(null)) {
					Game.m_loadBtn = ResourcesManager.loadBtnHover;
				}
				else {
					Game.m_loadBtn = ResourcesManager.loadBtn;
				}
			}
			else {
				Game.m_loadBtn = ResourcesManager.loadBtn;
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
		}

	}

}
