package com.bdinc.t12d.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.bdinc.t12d.level.Level;
import com.bdinc.t12d.level.LevelManager;
import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.objects.Flame;
import com.bdinc.t12d.scenes.ProfilesListDialog;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.UICell;

public class MouseInputManager implements MouseListener {
	
	File sav1 = null;
	File sav2 = null;
	File sav3 = null;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(LevelManager.levelNumber == 0) {
			if(e.getX() >= Game.m_playBtnX && e.getX() <= Game.m_playBtnX+Game.m_playBtn.getWidth(null)) {
				if(e.getY() >= Game.m_playBtnY && e.getY() <= Game.m_playBtnY+Game.m_playBtn.getHeight(null)) {
					if(ResourcesManager.getProfilesList().size() > 0 && !Options.profileName.equals("Unknown Player (???)")) {
						sav1 = new File("assets/saves/"+Options.profileName+"_info.dat");
						sav2 = new File("assets/saves/"+Options.profileName+"_blocks.dat");
						sav3 = new File("assets/saves/"+Options.profileName+"_entities.dat");
						if(!sav1.exists() || !sav2.exists() || !sav3.exists()) {
							JOptionPane.showMessageDialog(null, "Data files are damaged!\nCan't load the game!", ""+sav1.getAbsolutePath(), 0);
						} else {
							try {
								if(loadGame() != -1) {
									
								} else {
									JOptionPane.showMessageDialog(null, "Error with loading the game!", "Error", 0);
								}
							} catch(Exception ex) {
								ex.printStackTrace();
							}
							
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Create a profile before you will start playing!", "Error", 0);
					}                                                                       
				}
			}
			//ShopButton
			if(e.getX() >= Game.m_shopBtnX && e.getX() <= Game.m_shopBtnX+Game.m_shopBtn.getWidth(null)) {
				if(e.getY() >= Game.m_shopBtnY && e.getY() <= Game.m_shopBtnY+Game.m_shopBtn.getHeight(null)) {
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
					Game.stop();
				}
			}
			//Profile select button
			if(e.getX() >= Game.m_profileBtnX && e.getX() <= Game.m_profileBtnX+Game.m_profileBtnWidth) {
				if(e.getY() >= Game.m_profileBtnY && e.getY() <= Game.m_profileBtnY+Game.m_profileBtnHeight) {
					LevelManager.setLevelByID(-1);
				}
			}
		} 
		else if (LevelManager.levelNumber == -1) 
		{
			if(e.getX() >= ProfilesListDialog.btnBackX && e.getX() <= ProfilesListDialog.btnBackX+ProfilesListDialog.btnBackWidth) {
				if(e.getY() >= ProfilesListDialog.btnBackY && e.getY() <= ProfilesListDialog.btnBackY+ProfilesListDialog.btnBackHeight) {
					LevelManager.setLevelByID(0);
				}
			}
			if(e.getX() >= ProfilesListDialog.btnDelX && e.getX() <= ProfilesListDialog.btnDelX+ProfilesListDialog.btnDelWidth) {
				if(e.getY() >= ProfilesListDialog.btnDelY && e.getY() <= ProfilesListDialog.btnDelY+ProfilesListDialog.btnDelHeight) {
//					for(UICell c : ProfilesListDialog.profiles) {
//						if(c.isSelected) {
//							File s1 = new File("assets/saves/"+c.getTitle()+"_info.dat");
//							File s2 = new File("assets/saves/"+c.getTitle()+"_blocks.dat");
//							File s3 = new File("assets/saves/"+c.getTitle()+"_entities.dat");
//							s1.delete();
//							s2.delete();
//							s3.delete();
//							BufferedReader reader = null;
//							try {
//								reader = new BufferedReader(new FileReader("assets/saves/config/profileList.dat"));
//								String line = "";
//								ArrayList<String> lines = new ArrayList<String>();
//								while((line = reader.readLine()) != null) {
//									lines.add(line);
//								}
//								int index = 0;
//								for(String ln : lines) {
//									if(ln.equals(c.getTitle())) {
//										
//									}
//									index += 1;
//								}
//							} catch (IOException ex) {
//								ex.printStackTrace();
//							} finally {
//								if(reader != null) {
//									try {
//										reader.close();
//									} catch(IOException ex) {
//										ex.printStackTrace();
//									}
//								}
//							}
//							ProfilesListDialog.profiles.remove(c);
//						}
//					}
//					ProfilesListDialog.init();
//					ProfilesListDialog.resetInfo();
				}
			}
			if(e.getX() >= ProfilesListDialog.btnNewX && e.getX() <= ProfilesListDialog.btnNewX+ProfilesListDialog.btnNewWidth) {
				if(e.getY() >= ProfilesListDialog.btnNewY && e.getY() <= ProfilesListDialog.btnNewY+ProfilesListDialog.btnNewHeight) {
					if(ResourcesManager.getProfilesList().size() < 4) {
						String profile = JOptionPane.showInputDialog("Enter profile's name:");
						BufferedWriter writer = null;
						try {
							writer = new BufferedWriter(new FileWriter("assets/saves/config/profileList.dat", true));
							writer.append(profile+"\n");
						} catch (IOException ex) {
							ex.printStackTrace();
						} finally {
							if(writer != null) {
								try {
									writer.close();
								} catch (IOException ex) {
									ex.printStackTrace();
								}
							}
						}
						File sav1 = new File("assets/saves/"+profile+"_info.dat");
						File sav2 = new File("assets/saves/"+profile+"_blocks.dat");
						File sav3 = new File("assets/saves/"+profile+"_entities.dat");
						try {
							sav1.createNewFile();
							sav2.createNewFile();
							sav3.createNewFile();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						saveGame(profile);
						ProfilesListDialog.resetAll();
						ProfilesListDialog.init();
						for(UICell c : ProfilesListDialog.profiles) {
							c.setY(c.getY()-101);
						}
					}
					
				}
			}
			for(UICell c : ProfilesListDialog.profiles) {
				if(!c.isSelected && !existActive(c)) {
					if(e.getX() >= c.getX() && e.getX() <= c.getX()+c.getWidth()) {
						if(e.getY() >= c.getY() && e.getY() <= c.getY()+c.getHeight()) {
							c.isSelected = true;
							ProfilesListDialog.viewinfo();
							Options.profileName = c.getTitle();
							for(UICell cell : ProfilesListDialog.activeCells) {
								cell.isSelected = false;
							}
							ProfilesListDialog.activeCells.removeAll(ProfilesListDialog.activeCells);
							ProfilesListDialog.activeCells.add(c);
							ProfilesListDialog.requestRepaint();
						}
					}
				}
			}
		}

	}
	
	public boolean existActive(UICell c) {
		boolean res = false;
		for(UICell cell : ProfilesListDialog.activeCells) {
			if(c.equals(cell)) {
				res = true;
			}
		}
		return res;
	}
	
	public void saveGame(String profile) {
		BufferedWriter writer1 = null;
		BufferedWriter writer2 = null;
		try {
			writer1 = new BufferedWriter(new FileWriter("assets/saves/"+profile+"_info.dat"));
			writer1.write("LevelID:1"+"\n");
			writer1.write("Player.position.x:32"+"\n");
			writer1.write("Player.position.y:32"+"\n");
			writer1.write("Player.position.cellX:20"+"\n");
			writer1.write("Player.position.cellY:4"+"\n");
			writer1.write("Player.health:"+Game.player.getHealth()+"\n");
			writer1.write("Player.maxHealth:"+Game.player.getMaxHealth()+"\n");
			writer1.write("Player.magic:"+Game.player.getMagicCount()+"\n");
			writer1.write("Player.maxMagic:"+Game.player.getMaxMagicCount()+"\n");
			writer1.write("Player.money:"+Game.player.getMoney()+"\n");
			writer1.write("Player.rubies:"+Game.player.getRubyCount()+"\n");
			writer1.write("Flame.cellX:0"+"\n");
			writer1.write("Flame.cellY:0"+"\n");
			writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_blocks.dat"));
			writer2.write(""+LevelManager.currentLevel.blocks);
			if(writer2 != null) {
				writer2.close();
			}
			writer2 = new BufferedWriter(new FileWriter("assets/saves/"+Options.profileName+"_entities.dat"));
			writer2.write(""+LevelManager.currentLevel.entities);
			if(writer2 != null) {
				writer2.close();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(writer1 != null) {
				try {
					writer1.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(writer2 != null) {
				try {
					writer2.close();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int loadGame() {
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		int levelID, pcX, pcY, health, magic, maxH, maxM, flameX, flameY, money, ruby;
		float px, py;
		String lvlID, psx, psy, pscx, pscy, psH, psM, psMH, psMM, fX, fY, psmoney, psruby;
		String pName, line;
		ArrayList<String> lines = new ArrayList<String>();
		int count = 0, i = 0;
		try {
			reader1 = new BufferedReader(new FileReader("assets/saves/"+Options.profileName+"_info.dat"));
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
			psmoney = lines.get(9);
			psruby = lines.get(10);
			fX = lines.get(11);
			fY = lines.get(12);
			levelID = Integer.parseInt(lvlID.split(":")[1]);
			px = Float.parseFloat(psx.split(":")[1]);
			py = Float.parseFloat(psy.split(":")[1]);
			pcX = Integer.parseInt(pscx.split(":")[1]);
			pcY = Integer.parseInt(pscy.split(":")[1]);
			health = Integer.parseInt(psH.split(":")[1]);
			maxH = Integer.parseInt(psMH.split(":")[1]);
			magic = Integer.parseInt(psM.split(":")[1]);
			maxM = Integer.parseInt(psMM.split(":")[1]);
			money = Integer.parseInt(psmoney.split(":")[1]);
			ruby = Integer.parseInt(psruby.split(":")[1]);
			flameX = Integer.parseInt(fX.split(":")[1]);
			flameY = Integer.parseInt(fY.split(":")[1]);
			
			Game.player.setHealth(health);
			Game.player.setMaxHealth(maxH);
			Game.player.setMagicCount(magic);
			Game.player.setMaxMagic(maxM);
			Game.player.setMoney(money);
			Game.player.setRubyCount(ruby);

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
			return 0;
		}
		catch(IOException e) {
			e.printStackTrace();
			return -1;
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
