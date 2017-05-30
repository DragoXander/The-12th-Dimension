package com.bdinc.t12d.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.main.LevelManager;
import com.bdinc.t12d.objects.Flame;

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
					loadGame();
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
					Game.stop();
				}
			}
		}

	}
	
	public void loadGame() {
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		int levelID, pcX, pcY, health, magic, maxH, maxM, flameX, flameY;
		float px, py;
		String lvlID, psx, psy, pscx, pscy, psH, psM, psMH, psMM, fX, fY;
		String pName, line;
		ArrayList<String> lines = new ArrayList<String>();
		int count = 0, i = 0;
		try {
			reader1 = new BufferedReader(new FileReader("assets/saves/"+Game.profileName+"_info.dat"));
			while((line = reader1.readLine()) != null) {
				lines.add(line);
			}
			lvlID = lines.get(0);
			//System.err.println(""+lvlID);
			psx = lines.get(1);
			psy = lines.get(2);
			pscx = lines.get(3);
			pscy = lines.get(4);
			psH = lines.get(5);
			psMH = lines.get(6);
			psM = lines.get(7);
			psMM = lines.get(8);
			fX = lines.get(9);
			fY = lines.get(10);
			levelID = Integer.parseInt(lvlID.split(":")[1]);
			px = Float.parseFloat(psx.split(":")[1]);
			py = Float.parseFloat(psy.split(":")[1]);
			pcX = Integer.parseInt(pscx.split(":")[1]);
			pcY = Integer.parseInt(pscy.split(":")[1]);
			health = Integer.parseInt(psH.split(":")[1]);
			maxH = Integer.parseInt(psMH.split(":")[1]);
			magic = Integer.parseInt(psM.split(":")[1]);
			maxM = Integer.parseInt(psMM.split(":")[1]);
			flameX = Integer.parseInt(fX.split(":")[1]);
			flameY = Integer.parseInt(fY.split(":")[1]);
			
			Game.player.setHealth(health);
			Game.player.setMaxHealth(maxH);
			Game.player.setMagicCount(magic);
			Game.player.setMaxMagic(maxM);

			LevelManager.setLevelByID(levelID);
			for(Flame f: LevelManager.currentLevel.flames) {
				if(f.getCell().x == flameX && f.getCell().y == flameY) {
					f.setActive(true);
				}
			}
			Game.player.setPosition(pcX, pcY);
//			reader2 = new BufferedReader(new FileReader("assets/saves/"+Game.profileName+"_blocks.dat"));
//			for(Block b : LevelManager.currentLevel.blocks) {
//				b = reader2.readLine();
//			}
//			if(reader2 != null) {
//				reader2.close();
//			}
//			reader2 = new BufferedWriter(new FileWriter("assets/saves/"+Game.profileName+"_entities.dat"));
//			for(Entity e: LevelManager.currentLevel.entities) {
//				reader2.write(""+e);
//				reader2.newLine();
//			}
//			if(reader2 != null) {
//				reader2.close();
//			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(reader1 != null) {
				try {
					reader1.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
//			if(reader2 != null) {
//				try {
//					reader2.close();
//				}
//				catch(IOException e) {
//					e.printStackTrace();
//				}
//			}
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
