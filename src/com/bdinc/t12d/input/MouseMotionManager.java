package com.bdinc.t12d.input;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.SlotContainer;
import com.bdinc.t12d.scenes.*;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;
import com.bdinc.t12d.ui.UIComponent;
import com.bdinc.t12d.ui.UIDropList;
import com.bdinc.t12d.ui.UISlot;
import com.bdinc.t12d.utils.ColorManager;
import com.bdinc.t12d.utils.Debug;

public class MouseMotionManager extends MouseAdapter implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Game.player.shootX = e.getX();
		Game.player.shootY = e.getY();
		if(Game.paused) {
			if(e.getX() >= Game.m_continueBtnX && e.getX() <= Game.m_continueBtnX+300) {
				if(e.getY() >= Game.m_continueBtnY && e.getY() <= Game.m_continueBtnY+45) {
					Game.m_continueBtnColor = Game.m_pauseBtnHoverColor;
				}
				else {
					Game.m_continueBtnColor = Game.m_pauseBtnTmp;
				}
			}
			else {
				Game.m_continueBtnColor = Game.m_pauseBtnTmp;
			}
			if(e.getX() >= Game.m_optionsBtnX && e.getX() <= Game.m_optionsBtnX+300) {
				if(e.getY() >= Game.m_optionsBtnY && e.getY() <= Game.m_optionsBtnY+45) {
					Game.m_optionsBtnColor = Game.m_pauseBtnHoverColor;
				}
				else {
					Game.m_optionsBtnColor = Game.m_pauseBtnTmp;
				}
			}
			else {
				Game.m_optionsBtnColor = Game.m_pauseBtnTmp;
			}
			if(e.getX() >= Game.m_exmBtnX && e.getX() <= Game.m_exmBtnX+300) {
				if(e.getY() >= Game.m_exmBtnY && e.getY() <= Game.m_exmBtnY+45) {
					Game.m_exmBtnColor = Game.m_pauseBtnHoverColor;
				}
				else {
					Game.m_exmBtnColor = Game.m_pauseBtnTmp;
				}
			}
			else {
				Game.m_exmBtnColor = Game.m_pauseBtnTmp;
			}
		}
		
		if(LevelManager.levelNumber == 0) {
			if(e.getX() >= MainMenu.playBtn.x && e.getX() <= MainMenu.playBtn.x+MainMenu.playBtn.width) {
				if(e.getY() >= MainMenu.playBtn.y && e.getY() <= MainMenu.playBtn.y+MainMenu.playBtn.height) {
					MainMenu.playBtn.setHover(true);
				}
				else {
					MainMenu.playBtn.reset();
				}
			}
			else {
				MainMenu.playBtn.reset();
			}
			//ShopButton
			if(e.getX() >= MainMenu.shopBtn.x && e.getX() <= MainMenu.shopBtn.x+MainMenu.shopBtn.width) {
				if(e.getY() >= MainMenu.shopBtn.y && e.getY() <= MainMenu.shopBtn.y+MainMenu.shopBtn.height) {
					MainMenu.shopBtn.setHover(true);
				}
				else {
					MainMenu.shopBtn.reset();
				}
			}
			else {
				MainMenu.shopBtn.reset();
			}
			//OptionsButton
			if(e.getX() >= MainMenu.optBtn.x && e.getX() <= MainMenu.optBtn.x+MainMenu.optBtn.width) {
				if(e.getY() >= MainMenu.optBtn.y && e.getY() <= MainMenu.optBtn.y+MainMenu.optBtn.height) {
					MainMenu.optBtn.setHover(true);
				}
				else {
					MainMenu.optBtn.reset();
				}
			}
			else {
				MainMenu.optBtn.reset();
			}
			//ExitButton
			if(e.getX() >= MainMenu.exitBtn.x && e.getX() <= MainMenu.exitBtn.x+MainMenu.exitBtn.width) {
				if(e.getY() >= MainMenu.exitBtn.y && e.getY() <= MainMenu.exitBtn.y+MainMenu.exitBtn.height) {
					MainMenu.exitBtn.setHover(true);
				}
				else {
					MainMenu.exitBtn.reset();
				}
			}
			else {
				MainMenu.exitBtn.reset();
			}
			//Profile select button
			if(e.getX() >= MainMenu.profileBtn.x && e.getX() <= MainMenu.profileBtn.x+MainMenu.profileBtn.width) {
				if(e.getY() >= MainMenu.profileBtn.y && e.getY() <= MainMenu.profileBtn.y+MainMenu.profileBtn.height) {
					MainMenu.m_profileBtnColor = Color.ORANGE;
				}
				else {
					MainMenu.m_profileBtnColor = Color.CYAN;
				}
			}
			else {
				MainMenu.m_profileBtnColor = Color.CYAN;
			}
			//ExtraBtn
			if(e.getX() >= MainMenu.extraBtn.x && e.getX() <= MainMenu.extraBtn.x+MainMenu.extraBtn.width) {
				if(e.getY() >= MainMenu.extraBtn.y && e.getY() <= MainMenu.extraBtn.y+MainMenu.extraBtn.height) {
					MainMenu.extraBtn.setHover(true);
				}
				else {
					MainMenu.extraBtn.reset();
				}
			}
			else {
				MainMenu.extraBtn.reset();
			}
			//StoryBtn
			if(e.getX() >= MainMenu.storyBtn.x && e.getX() <= MainMenu.storyBtn.x+MainMenu.storyBtn.width) {
				if(e.getY() >= MainMenu.storyBtn.y && e.getY() <= MainMenu.storyBtn.y+MainMenu.storyBtn.height) {
					//MainMenu.m_storyBtn = ResourcesManager.storyBtnDisabled;
//					MainMenu.tooltip = true;
//					MainMenu.tooltipX = e.getX()+10;
//					MainMenu.tooltipY = e.getY();
					MainMenu.storyBtn.setHover(true);
					MainMenu.storyBtn.showTooltip = true;
					MainMenu.storyBtn.tooltip().x = e.getX()+10;
					MainMenu.storyBtn.tooltip().y = e.getY();
				}
				else {
					//MainMenu.m_storyBtn = ResourcesManager.storyBtnDisabled;
//					MainMenu.tooltip = false;
//					MainMenu.tooltipX = 0;
//					MainMenu.tooltipY = 0;
					MainMenu.storyBtn.reset();
					MainMenu.storyBtn.showTooltip = false;
					MainMenu.storyBtn.tooltip().x = 0;
					MainMenu.storyBtn.tooltip().y = 0;
				}
			}
			else {
				//MainMenu.m_storyBtn = ResourcesManager.storyBtnDisabled;
//				MainMenu.tooltip = false;
//				MainMenu.tooltipX = 0;
//				MainMenu.tooltipY = 0;
				MainMenu.storyBtn.reset();
				MainMenu.storyBtn.showTooltip = false;
				MainMenu.storyBtn.tooltip().x = 0;
				MainMenu.storyBtn.tooltip().y = 0;
			}
			//LangBtn
			if(e.getX() >= MainMenu.langBtn.x && e.getX() <= MainMenu.langBtn.x+MainMenu.langBtn.width) {
				if(e.getY() >= MainMenu.langBtn.y && e.getY() <= MainMenu.langBtn.y+MainMenu.langBtn.height) {
					MainMenu.langBtn.setHover(true);
				}
				else {
					MainMenu.langBtn.reset();
				}
			}
			else {
				MainMenu.langBtn.reset();
			}
		}
		else if(LevelManager.levelNumber == -3)
		{
			if(e.getX() >= LangListDialog.btnBackX && e.getX() <= LangListDialog.btnBackX+LangListDialog.btnBackWidth) {
				if(e.getY() >= LangListDialog.btnBackY && e.getY() <= LangListDialog.btnBackY+LangListDialog.btnBackHeight) {
					LangListDialog.btnBackColor = Color.BLUE;
				} else {
					LangListDialog.btnBackColor = Color.RED;
				}
			} else {
				LangListDialog.btnBackColor = Color.RED;
			}
			
		}
		else if(LevelManager.levelNumber == -2)
		{
			if(e.getX() >= DLCListDialog.btnBackX && e.getX() <= DLCListDialog.btnBackX+DLCListDialog.btnBackWidth) {
				if(e.getY() >= DLCListDialog.btnBackY && e.getY() <= DLCListDialog.btnBackY+DLCListDialog.btnBackHeight) {
					DLCListDialog.btnBackColor = Color.BLUE;
				} else {
					DLCListDialog.btnBackColor = Color.RED;
				}
			} else {
				DLCListDialog.btnBackColor = Color.RED;
			}
			if(e.getX() >= DLCListDialog.btnPlayX && e.getX() <= DLCListDialog.btnPlayX+DLCListDialog.btnPlayWidth) {
				if(e.getY() >= DLCListDialog.btnPlayY && e.getY() <= DLCListDialog.btnPlayY+DLCListDialog.btnPlayHeight) {
					DLCListDialog.btnPlayColor = Color.BLUE;
				} else {
					DLCListDialog.btnPlayColor = ColorManager.VIOLET;
				}
			} else {
				DLCListDialog.btnPlayColor = ColorManager.VIOLET;
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
		else if(LevelManager.levelNumber == -4) {
			if(e.getX() >= OptionsScreen.btnBackX && e.getX() <= OptionsScreen.btnBackX+OptionsScreen.btnBackWidth) {
				if(e.getY() >= OptionsScreen.btnBackY && e.getY() <= OptionsScreen.btnBackY+OptionsScreen.btnBackHeight) {
					OptionsScreen.btnBackColor = Color.BLUE;
				}
				else {
					OptionsScreen.btnBackColor = Color.RED;
				}
			} else {
				OptionsScreen.btnBackColor = Color.RED;
			}
			for(UIComponent c : OptionsScreen.ui) {
				if(c instanceof UIDropList){
					UIDropList dl = (UIDropList)c;
					if(e.getX() >= dl.getX() && e.getX() <= dl.getX()+dl.getWidth()) {
						if(e.getY() >= dl.getY() && e.getY() <= dl.getY()+dl.getHeight()) {
							dl.isHover = true;
						}
						else {
							dl.isHover = false;
						}
					} else {
						dl.isHover = false;
					}
					if(dl.drop) {
						for(UICell uc : dl.cells) {
							if(!uc.isSelected) {
								if(e.getX() >= uc.getX() && e.getX() <= uc.getX()+uc.getWidth()) {
									if(e.getY() >= uc.getY() && e.getY() <= uc.getY()+uc.getHeight()) {
										uc.setBackground(uc.hoverColor);
									} else {
										uc.resetBackground();
									}
								} else {
									uc.resetBackground();
								}
							}
							
						}
					}
				}
			}
		}
		else if(LevelManager.levelNumber > 0 || LevelManager.levelNumber <= -10) {
			if (Game.player.inventoryShow) {
				for(UISlot c : Game.player.inventory.cells) {
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.isHover = true;
						} else {
							c.isHover = false;
						}
					} else {
						c.isHover = false;
					}
				}
			}
			if (Game.player.inventoryShow && Game.player.inventory.dragging) {
				/*
				 * Armor cells must be described too.
				 * Don't forget!
				 */
				Game.player.inventory.dragCell.imgX = e.getX();
				Game.player.inventory.dragCell.imgY = e.getY();
			}
			for(SlotContainer c : LevelManager.currentLevel.conts) {
				for(UISlot cell : c.cells) {
					if(e.getX() >= cell.getX() && e.getX() <= cell.getX()+cell.getWidth()) {
						if(e.getY() >= cell.getY() && e.getY() <= cell.getY()+cell.getHeight()) {
							cell.isHover = true;
							cell.toolTipX = e.getX();
							cell.toolTipY = e.getY();
						} else {
							cell.isHover = false;
						}
					} else {
						cell.isHover = false;
					}
				}
			}
		}

	}

}
