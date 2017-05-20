package com.bdinc.t12d.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.main.ResourcesManager;

public class MouseInputManager implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		if(LevelManager.levelNumber == 0) {
			if(e.getX() >= Game.m_playBtnX && e.getX() <= Game.m_playBtnX+Game.m_playBtn.getWidth(null)) {
				if(e.getY() >= Game.m_playBtnY && e.getY() <= Game.m_playBtnY+Game.m_playBtn.getHeight(null)) {
					LevelManager.setLevelByID(1);
				}
			}
			//LoadButton
			if(e.getX() >= Game.m_loadBtnX && e.getX() <= Game.m_loadBtnX+Game.m_loadBtn.getWidth(null)) {
				if(e.getY() >= Game.m_loadBtnY && e.getY() <= Game.m_loadBtnY+Game.m_loadBtn.getHeight(null)) {
					//soon
				}
			}
			//OptionsButton
			if(e.getX() >= Game.m_optBtnX && e.getX() <= Game.m_optBtnX+Game.m_optBtn.getWidth(null)) {
				if(e.getY() >= Game.m_optBtnY && e.getY() <= Game.m_optBtnY+Game.m_optBtn.getHeight(null)) {
					//soon
				}
			}
			//ExitButton
			if(e.getX() >= Game.m_exitBtnX && e.getX() <= Game.m_exitBtnX+Game.m_exitBtn.getWidth(null)) {
				if(e.getY() >= Game.m_exitBtnY && e.getY() <= Game.m_exitBtnY+Game.m_exitBtn.getHeight(null)) {
					//soon
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
