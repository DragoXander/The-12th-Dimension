package com.bdinc.t12d.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Physics;

public class InputManager extends KeyAdapter implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Game.player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			Game.player.right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			Game.player.isRunning = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			if(Physics.collidesBottom(Game.player.posX(), Game.player.posY()))
			{
				Game.player.jump = true;
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Game.player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			Game.player.right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			Game.player.isRunning = false;
		}
//		if(e.getKeyCode() == KeyEvent.VK_W) {
//			if(Physics.collidesBottom(Game.player.posX(), Game.player.posY()))
//			{
//				Game.player.jump = false;
//			}
//			
//		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
