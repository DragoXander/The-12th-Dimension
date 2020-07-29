package com.bdinc.t12d.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.maths.Physics;
import com.bdinc.t12d.objects.Button;
import com.bdinc.t12d.objects.Door;
import com.bdinc.t12d.objects.Entity;
import com.bdinc.t12d.objects.Gun;
import com.bdinc.t12d.objects.Item;
import com.bdinc.t12d.objects.Key;
import com.bdinc.t12d.objects.MakarovAmmo;
import com.bdinc.t12d.objects.MakarovGun;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.objects.Vase;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.utils.Debug;

public class InputManager extends KeyAdapter implements KeyListener {
	
	private Entity player;
	private boolean colBot, colTop;
	public static boolean interactKeyPressed, interactContKeyPressed;
	
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
		if(e.getKeyCode() == KeyEvent.VK_I) {
			player.inventoryShow = !player.inventoryShow;
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			Gun gun = (Gun)player.currentWeapon;
			int delta = (gun.maxAmmoCount - gun.currentAmmo);
			if(delta > gun.ammoCount) {
				delta = (gun.ammoCount - gun.currentAmmo);
			}
			gun.currentAmmo += delta;
			gun.ammoCount -= delta;
		}
		if(e.getKeyCode() == KeyEvent.VK_1) {
			for(UISlot c : Game.QAPanel.cells) {
				c.isQASSelected = false;
			}
			Game.QAPanel.cells.get(0).isQASSelected = true;
			if(Game.QAPanel.cells.get(0).hasItem) {
				Game.player.currentWeapon = Game.QAPanel.cells.get(0).getItem();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			for(UISlot c : Game.QAPanel.cells) {
				c.isQASSelected = false;
			}
			Game.QAPanel.cells.get(1).isQASSelected = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			for(UISlot c : Game.QAPanel.cells) {
				c.isQASSelected = false;
			}
			Game.QAPanel.cells.get(2).isQASSelected = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !(LevelManager.levelNumber <= 0 && LevelManager.levelNumber > -10)) {
			Game.paused = !Game.paused;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Game.player.attack();
		}
		if(e.getKeyCode() == KeyEvent.VK_E && !(LevelManager.levelNumber <= 0 && LevelManager.levelNumber > -10)) {
			//Debug.log(Game.player.interactiveTarget);
			Object tg = Game.player.interactiveTarget;
			
			if(tg instanceof SlotContainer && !(tg instanceof Vase)) {
				((SlotContainer)tg).show = !((SlotContainer)tg).show;
			} 
			else if(tg instanceof Button) {
				if(!((Button)tg).onceClickMode) {
					((Button)tg).press();
				} else {
					((Button)tg).press();
					((Button)tg).active = true;
				}
			} else if(tg instanceof Vase) {
				if(!((Vase)tg).broken) {
					((Vase)tg).broken = true;
				} else {
					((Vase)tg).show = !((Vase)tg).show;
				}
			} else if(tg instanceof Key) {
				Game.player.addItem((Key)tg);
				LevelManager.currentLevel.items.remove((Key)tg);
			} else if(tg instanceof MakarovAmmo) {
				boolean recieved = false;
				boolean found = false;
				for(Item i : Game.player.invList.values()) {
					int ammoCount = ((MakarovGun)i).ammoCount;
					int maxAmmoCount = ((MakarovGun)i).maxAmmoCount;
					int currentAmmo = ((MakarovGun)i).currentAmmo;
					int ammoItemCount = ((MakarovAmmo)tg).getCount();
					if(i instanceof MakarovGun && !recieved && (ammoCount != maxAmmoCount || currentAmmo != maxAmmoCount)) {
						if(ammoCount != maxAmmoCount && currentAmmo == maxAmmoCount) {
							if(ammoItemCount > maxAmmoCount - ammoCount) {
								((MakarovGun)i).ammoCount += maxAmmoCount - ammoCount;
							}
							else {
								((MakarovGun)i).ammoCount += ammoItemCount;
							}
						}
						else if(ammoCount == maxAmmoCount && currentAmmo != maxAmmoCount) {
							//ОТРЕДАКТИРОВАТЬ!!!
							if(ammoItemCount > maxAmmoCount - ammoCount) {
								((MakarovGun)i).ammoCount += maxAmmoCount - ammoCount;
							}
							else {
								((MakarovGun)i).ammoCount += ammoItemCount;
							}
						}
						else if(ammoCount != maxAmmoCount && currentAmmo != maxAmmoCount) {
							//ОТРЕДАКТИРОВАТЬ!!!
							if(ammoItemCount > maxAmmoCount - ammoCount) {
								((MakarovGun)i).ammoCount += maxAmmoCount - ammoCount;
							}
							else {
								((MakarovGun)i).ammoCount += ammoItemCount;
							}
						}
						((MakarovGun)i).ammoCount += ((MakarovAmmo)tg).getCount();
						Debug.log(((MakarovGun)i).ammoCount);
						recieved = true;
						found = true;
					}
				}
				if(!found) {
					for(UISlot s : Game.QAPS) {
						Item i = null;
						if(s.hasItem) {
							i = s.getItem();
							if(i instanceof MakarovGun && !recieved && ((MakarovGun)i).ammoCount != ((MakarovGun)i).maxAmmoCount) {
								((MakarovGun)i).ammoCount += ((MakarovAmmo)tg).getCount();
								Debug.log(((MakarovGun)i).ammoCount);
								recieved = true;
							}
						}
					}
				}
				LevelManager.currentLevel.items.remove((MakarovAmmo)tg);
			} else if(tg instanceof Gun) {
				Game.player.addItem((Gun)tg);
				LevelManager.currentLevel.items.remove((Gun)tg);
			} else if(tg instanceof Door) {
				if(((Door)tg).requiresKey && !((Door)tg).isOpened) {
					if(Game.player.invList.values().contains(((Door)tg).getKey())) {
						((Door)tg).open();
					}
				}
				if (!((Door)tg).requiresKey) {
					((Door)tg).isOpened = !((Door)tg).isOpened;
				}
				
			}
//			if(Game.player.isInteracting && !interactKeyPressed && !interactContKeyPressed) {
//				interactKeyPressed = true;
//			} else if(Game.player.isInteracting && interactKeyPressed) {
//				interactKeyPressed = false;
//			} else {
//				interactKeyPressed = false;
//			}
			interactContKeyPressed = !interactContKeyPressed;
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
