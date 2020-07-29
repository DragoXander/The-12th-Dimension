package com.bdinc.t12d.ui.menu;

public interface MenuInteractable {

    void setHover(boolean state);
    void setActive(boolean state);
    void setDisabled(boolean state);
    void reset();
    boolean isHover();
    boolean isActive();
    boolean isNormal();
    boolean isDisabled();

}
