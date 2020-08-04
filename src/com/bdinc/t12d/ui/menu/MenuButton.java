package com.bdinc.t12d.ui.menu;

import java.awt.*;

public class MenuButton extends MenuComponent implements MenuInteractable {

    private boolean hover = false, active = false, disabled = false;
    private Image img_hover, img_active, img_dis, img_default;
    private Image toDraw;
    private Tooltip tooltip;
    public boolean showTooltip = false;
    public boolean hasTooltip = false;

    public MenuButton() {}
    public MenuButton(Image def, Image hov, Image act) {
        init(def, hov, act);
        toDraw = def;
        width = toDraw.getWidth(null);
        height = toDraw.getHeight(null);
    }
    public MenuButton(Image def, Image hov, Image act, Image dis) {
        init(def, hov, act, dis);
        toDraw = def;
        width = toDraw.getWidth(null);
        height = toDraw.getHeight(null);
    }

    public void init(Image def, Image hov, Image act) {
        this.img_hover = hov;
        this.img_active = act;
        this.img_default = def;
    }
    public void init(Image def, Image hov, Image act, Image dis) {
        this.img_hover = hov;
        this.img_active = act;
        this.img_dis = dis;
        this.img_default = def;
    }

    public void setTooltip(Tooltip.TooltipProperties properties) {
        tooltip = new Tooltip(properties);
        hasTooltip = true;
    }

    public void resetTooltip() {
        hasTooltip = false;
        tooltip = null;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(toDraw, x, y, width, height, null);
    }

    @Override
    public void update(float delta) {
        if(!disabled) {
            if(hover && !active)
                toDraw = img_hover;
            else if(active && !hover)
                toDraw = img_active;
            else if(active && hover)
                toDraw = img_active;
            else
                toDraw = img_default;
        }
        else {
            toDraw = img_dis;
        }
    }

    public Tooltip tooltip() {
        if(tooltip != null)
            return tooltip;
        else
            return new Tooltip(new Tooltip.TooltipProperties());
    }

    @Override
    public void setHover(boolean state) {
        hover = state;
    }

    @Override
    public void setActive(boolean state) {
        active = state;
    }

    @Override
    public void setDisabled(boolean state) {
        if(state) {
            disabled = true;
            hover = false;
            active = false;
        }
        else {
            disabled = false;
        }
    }

    @Override
    public void reset() {
        hover = false;
        active = false;
    }

    @Override
    public boolean isHover() {
        return hover;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public boolean isNormal() {
        return !hover && !active && !disabled;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }
}
