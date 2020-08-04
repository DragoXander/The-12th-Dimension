package com.bdinc.t12d.scenes;

import com.bdinc.t12d.main.Game;
import com.bdinc.t12d.settings.Options;
import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.ui.menu.MenuButton;
import com.bdinc.t12d.ui.menu.Tooltip;
import com.bdinc.t12d.utils.ColorManager;
import com.bdinc.t12d.utils.Debug;
import com.bdinc.t12d.utils.LangManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainMenu {

    public static int m_playBtnX, m_playBtnY;
    public static int m_shopBtnX, m_shopBtnY;
    public static int m_optBtnX, m_optBtnY;
    public static int m_exitBtnX, m_exitBtnY;
    public static int m_profileBtnX, m_profileBtnY;
    public static int m_profileBtnWidth, m_profileBtnHeight;
    public static int m_labelX, m_labelY;
    public static int m_contRectX, m_contRectY, m_contBtnX, m_extraBtnX, m_extraBtnY, m_storyBtnY;
    public static int m_langBtnWidth, m_langBtnHeight, m_langBtnX, m_langBtnY;
    public static Image m_playBtn, m_shopBtn, m_optBtn, m_exitBtn, m_extraBtn, m_storyBtn, m_langBtn, crosshair,
            m_continueBtn;
    public static Color m_pauseBtnTmp, m_pauseBtnHoverColor;
    public static Color m_continueBtnColor, m_optionsBtnColor;
    public static Color m_exmBtnColor;
    public static Color m_profileBtnColor;

    private static LinkedList<MenuButton> buttons = new LinkedList<MenuButton>();

    public static boolean tooltip;
    public static int tooltipX, tooltipY;

    //public HashMap<String, MenuButton> buttons = new HashMap<String, MenuButton>();
    public static MenuButton playBtn, shopBtn, extraBtn, optBtn, exitBtn, storyBtn, langBtn, contBtn, profileBtn;

    public static void init() {
        playBtn = new MenuButton(ResourcesManager.playBtn, ResourcesManager.playBtnHover, ResourcesManager.playBtnHover);
        shopBtn = new MenuButton(ResourcesManager.shopBtn, ResourcesManager.shopBtnHover, ResourcesManager.shopBtnHover);
        optBtn = new MenuButton(ResourcesManager.optionsBtn, ResourcesManager.optionsBtnHover, ResourcesManager.optionsBtnHover);
        exitBtn = new MenuButton(ResourcesManager.exitBtn, ResourcesManager.exitBtnHover, ResourcesManager.exitBtnHover);
        extraBtn = new MenuButton(ResourcesManager.extraBtn, ResourcesManager.extraBtnHover, ResourcesManager.extraBtnHover);
        storyBtn = new MenuButton(ResourcesManager.storyBtn, ResourcesManager.storyBtnHover, ResourcesManager.storyBtnHover, ResourcesManager.storyBtnDisabled);
        langBtn = new MenuButton(ResourcesManager.langBtn, ResourcesManager.langBtnHover, ResourcesManager.langBtnHover);
        contBtn = new MenuButton(ResourcesManager.playBtn, ResourcesManager.playBtnHover, ResourcesManager.playBtnHover);
        profileBtn = new MenuButton();
        storyBtn.setDisabled(true);
        Tooltip.TooltipProperties storyTip = new Tooltip.TooltipProperties();
        storyTip.headerFont = ResourcesManager.defaultFont16;
        storyTip.font = ResourcesManager.defaultFont14;
        storyTip.bgColor = ColorManager.getAlphaColor(Color.CYAN, 90);
        storyTip.headerBg = ColorManager.getAlphaColor(Color.magenta, 90);
        storyTip.headerTextColor = Color.YELLOW;
        storyTip.borderColor = Color.WHITE;
        storyTip.borderWidth = 2;
        storyTip.textColor = Color.WHITE;
        storyTip.maxHeight = 290;
        storyTip.maxWidth = 350;
        storyTip.headerPadding = 2;
        storyTip.padding = 2;
        storyTip.headerText = ResourcesManager.m_storyMode;
        storyTip.text = ResourcesManager.m_storyModeDis;
        storyBtn.setTooltip(storyTip);
        buttons.add(playBtn);
        buttons.add(shopBtn);
        buttons.add(extraBtn);
        buttons.add(exitBtn);
        buttons.add(storyBtn);
        buttons.add(optBtn);
        buttons.add(langBtn);
        //buttons.add(contBtn);
        buttons.add(profileBtn);

        m_continueBtnColor = ColorManager.FOREST_GREEN;
        m_optionsBtnColor = ColorManager.VIOLET;
        m_exmBtnColor = ColorManager.FOREST_GREEN;
        m_pauseBtnTmp = ColorManager.FOREST_GREEN;
        m_pauseBtnHoverColor = ColorManager.VIOLET;
    }
    private static void markup() {
        m_contRectX = 10;
        playBtn.x = m_contRectX;
        playBtn.y = (Game.HEIGHT>>1) - 10;
        m_contRectY = playBtn.y+5;
        contBtn.x = m_contRectX;
        storyBtn.x = m_contRectX;
        storyBtn.y = (Game.HEIGHT>>1) - 100;
        shopBtn.x = m_contRectX;
        shopBtn.y = playBtn.y+playBtn.height+5;
        extraBtn.x = m_contRectX;
        extraBtn.y = shopBtn.y+shopBtn.height+5;
        exitBtn.x = m_contRectX;
        exitBtn.y = extraBtn.y+extraBtn.height+5;
        profileBtn.x = Game.WIDTH-297;
        profileBtn.y = Game.HEIGHT-94;
        profileBtn.width = 290;
        profileBtn.height = 39;
        langBtn.x = exitBtn.x+exitBtn.width+10;
        langBtn.y = exitBtn.y;
        langBtn.width = 60;
        langBtn.height = 60;
        optBtn.x = langBtn.x;
        optBtn.y = extraBtn.y;
        optBtn.width = 60;
        optBtn.height = 60;
        m_labelX = 10;
        m_labelY = playBtn.y-30;
    }
    public static void update(float delta) {
        markup();
        for(MenuButton b : buttons) {
            b.update(delta);
            if(b.hasTooltip) {
                b.tooltip().update(delta);
            }
        }

//        playBtn.update(delta);
//        shopBtn.update(delta);
//        storyBtn.update(delta);
//        extraBtn.update(delta);
//        langBtn.update(delta);
//        optBtn.update(delta);
//        exitBtn.update(delta);
//        contBtn.update(delta);
    }

    public static void render(Graphics g) {
        g.drawImage(ResourcesManager.logo, 10, 50, null);
        for(MenuButton b : buttons) {
            b.render(g);
            if(b.hasTooltip && b.showTooltip) {
                b.tooltip().render(g);
            }
            //b.render(g);
        }
//        playBtn.render(g);
//        shopBtn.render(g);
//        optBtn.render(g);
//        exitBtn.render(g);
//        extraBtn.render(g);
//        storyBtn.render(g);
//        langBtn.render(g);

        //�������:
        g.setColor(Color.WHITE);
        g.drawRect(Game.WIDTH-300, Game.HEIGHT-150, 295, 100); //������� ������� (���)
        g.drawRect(Game.WIDTH-298, Game.HEIGHT-148, 51, 51); //������� ������� �������
        g.drawImage(ResourcesManager.profile, Game.WIDTH-297, Game.HEIGHT-147, null);
        g.drawRect(Game.WIDTH-246, Game.HEIGHT-148, 240, 26);

        g.setFont(ResourcesManager.defaultFont);
        //g.drawString(ResourcesManager.m_moreContent, m_labelX, 500);
        g.drawString(Options.profileName, Game.WIDTH-244, Game.HEIGHT-126);
        g.setFont(ResourcesManager.defaultFont14);
        //g.drawString(ResourcesManager.m_moreContentSub, m_labelX, 500);
        g.drawString(ResourcesManager.m_profileMoney+Game.player.getMoney(), Game.WIDTH-244, Game.HEIGHT-104);
        g.drawRect(Game.WIDTH-298, Game.HEIGHT-95, 291, 40);
        g.setColor(m_profileBtnColor);
        g.fillRect(profileBtn.x, profileBtn.y, profileBtn.width, profileBtn.height);
        g.setColor(Color.black);
        String strSelectProfile = ResourcesManager.m_selectProfile;
        g.drawString(strSelectProfile, profileBtn.x+50, profileBtn.y+25);
        //����������
        g.setColor(Color.white);
        g.setFont(ResourcesManager.defaultFont);
        g.drawString("The 12th Dimension", 5, Game.HEIGHT-40);
        g.drawString(Game.VERSION, 5, Game.HEIGHT-10);
        g.setColor(Color.YELLOW);
        g.drawString("The Fantasy is real!", 450, 70+ResourcesManager.logo.getHeight(null));
        if(Game.isDevelopmentBuild) {
            g.setColor(Color.CYAN);
            g.drawString("[Development Build]", Game.WIDTH-190, 20);
        }
        g.setColor(Color.white);
        g.drawString("Copyright (C) MysteryFoxStudio 2020", Game.WIDTH-350, Game.HEIGHT-10);

//        if(tooltip) {
//            g.setColor(Color.WHITE);
//            if(LangManager.currentLang.equals("ru_RU.lang")) {
//                g.drawRect(tooltipX, tooltipY, 440, 100);
//                g.setColor(ColorManager.getAlphaColor(Color.BLACK, 90));
//                g.fillRect(tooltipX+1, tooltipY+1, 439, 99);
//            } else {
//                g.drawRect(tooltipX, tooltipY, 340, 100);
//                g.setColor(ColorManager.getAlphaColor(Color.BLACK, 90));
//                g.fillRect(tooltipX+1, tooltipY+1, 339, 99);
//            }
//            g.setColor(Color.YELLOW);
//            g.setFont(ResourcesManager.defaultFont16);
//            g.drawString(ResourcesManager.m_storyMode, tooltipX+3, tooltipY+20);
//            g.setColor(Color.WHITE);
//            g.setFont(ResourcesManager.defaultFont14);
//            g.drawString(ResourcesManager.m_storyModeDis1, tooltipX+3, tooltipY+35);
//            g.drawString(ResourcesManager.m_storyModeDis2, tooltipX+3, tooltipY+52);
//            g.setColor(ColorManager.VIOLET);
//            g.drawString(ResourcesManager.m_storyModeDis3, tooltipX+3, tooltipY+85);
//        }
    }
}
