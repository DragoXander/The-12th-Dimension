package com.bdinc.t12d.scenes;

import java.awt.*;

public class SceneManager {

    public static void initScenes() {
        ProfilesListDialog.init();
        DLCListDialog.init();
        LangListDialog.init();
        OptionsScreen.init();
        MainMenu.init();
    }

    public static void updateScenes(float delta) {
        MainMenu.update(delta);
    }

    public static void renderScene(Graphics g, int id) {
        switch (id) {
            case 0:
                MainMenu.render(g);
                break;
            default:
                break;
        }
    }

}
