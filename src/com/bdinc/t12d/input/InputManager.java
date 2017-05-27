package com.bdinc.t12d.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.objects.Entity;

public class InputManager extends KeyAdapter implements KeyListener {
	
	private Entity player;
	private boolean colBot, colTop;
	
	@Override
	public void keyPressed(KeyEvent e) {
		player = Game.player;
		if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.isRunning = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			colBot = Physics.collidesBottom(player.posX(), player.posY());
			colTop = Physics.collidesTop(player.posX(), player.posY());
			if(colBot && !colTop)
			{
				player.jump = true;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player = Game.player;
		if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.isRunning = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

}
