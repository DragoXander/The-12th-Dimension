package com.bdinc.t12d.ui.menu;

import java.awt.*;

public abstract class MenuComponent {
    public int x = 0, y = 0;
    public int width = 0, height = 0;
    public static boolean tooltip;
    public static int tooltipX, tooltipY;

    public abstract void render(Graphics g);
    public abstract void update(float delta);
}
