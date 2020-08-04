package com.bdinc.t12d.ui.menu;

import com.bdinc.t12d.settings.ResourcesManager;
import com.bdinc.t12d.utils.ColorManager;
import com.bdinc.t12d.utils.TextTools;

import java.awt.*;

public class Tooltip extends MenuComponent {

    private static final int LINE_OFFSET_Y = 2;

    private TooltipProperties properties;
    private String wrapped = "";
    private String[] lines;

    public Tooltip(TooltipProperties properties) {
        this.properties = properties;
        wrapped = TextTools.wrappedText(properties.text, properties.font, properties.maxWidth-getBorderWidth()*2-properties.padding*2, properties.maxHeight-getHeaderHeight()-getBorderWidth()*2);
        lines = wrapped.contains("\n") ? wrapped.split("\n") : new String[] {wrapped};
        calcWidth();
        calcHeight();
    }

    private void calcWidth() {
        int lineMaxWidth = 0;
        for(String line : lines) {
            if(TextTools.stringBounds(line, properties.font).getWidth() > lineMaxWidth)
                lineMaxWidth = (int)TextTools.stringBounds(line, properties.font).getWidth()+1;
        }
        if(TextTools.stringBounds(properties.headerText, properties.headerFont).getWidth() > lineMaxWidth)
            lineMaxWidth = (int)TextTools.stringBounds(properties.headerText, properties.headerFont).getWidth();
        if(getMaxWidth() < lineMaxWidth)
            properties.maxWidth = lineMaxWidth;
        width = lineMaxWidth+getBorderWidth()*2+properties.padding*2;
    }

    private void calcHeight() {
        height = (TextTools.symbolCount(wrapped, '\n')+1)*(int)(TextTools.stringBounds(wrapped, properties.font).getHeight())+LINE_OFFSET_Y*2;
        if(getMaxHeight() < height)
            properties.maxHeight = height;
    }

    public void setText(String text) {
        properties.text = text;
    }

    public void setBackgroundColor(Color c) {
        properties.bgColor = c;
    }

    public void setBorderColor(Color c) {
        properties.borderColor = c;
    }

    public void setBorderWidth(int v) {
        properties.borderWidth = v;
    }

    public void setMaxWidth(int v) {
        properties.maxWidth = v;
    }

    public void setMaxHeight(int v) {
        properties.maxHeight = v;
    }
    /**
    Recommended for use instead of direct
     call width!
    @param w width of the tooltip
     **/
    public void setWidth(int w) {
        if (width <= getMaxWidth())
            width = w;
    }
    /**
     Recommended for use instead of direct
     call height!
     @param h height of the tooltip
     **/
    public void setHeight(int h) {
        if (height <= getMaxHeight())
            height = h;
    }

    public void setFont(Font f) {
        properties.font = f;
    }

    public int getHeaderHeight() {
        return (int)TextTools.stringBounds(properties.headerText, properties.headerFont).getHeight();
    }

    public Color getBackgroundColor() {
        return properties.bgColor;
    }

    public Color getBorderColor() {
        return properties.borderColor;
    }

    public String getText() {
        return properties.text;
    }

    public int getBorderWidth() {
        return properties.borderWidth;
    }

    public int getMaxWidth() {
        return properties.maxWidth;
    }

    public int getMaxHeight() {
        return properties.maxHeight;
    }

    public TooltipProperties getProperties() {
        return properties;
    }

    public Font getFont() {
        return properties.font;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(properties.borderColor);
        g.drawRect(x, y, width+getBorderWidth()*2, height+getHeaderHeight()+getBorderWidth()*3);

        //Fill Header and draw separator
        g.setColor(properties.headerBg);
        g.fillRect(x+getBorderWidth(), y+getBorderWidth(), width, getHeaderHeight()+properties.headerPadding*2);
        g.setColor(properties.borderColor);
        int headerBottom = y+getBorderWidth()+getHeaderHeight()+properties.headerPadding*2;
        g.drawLine(x, headerBottom, x+width+getBorderWidth(), headerBottom);

        //Fill body
        g.setColor(properties.bgColor);
        g.fillRect(x+getBorderWidth(), headerBottom, width, height-(getHeaderHeight()+getBorderWidth())-getBorderWidth());

        //Draw Header Text
        g.setColor(properties.headerTextColor);
        g.setFont(properties.headerFont);
        g.drawString(properties.headerText, x+getBorderWidth()+properties.headerPadding, y+getBorderWidth()+getHeaderHeight()+properties.headerPadding);

        //Draw Body Text
        g.setColor(properties.textColor);
        g.setFont(properties.font);
        int offset = 0;
        for(String s : lines) {
            g.drawString(s, x+getBorderWidth()+properties.padding, y+getBorderWidth()+getHeaderHeight()*2+properties.padding+offset);
            offset += (int)TextTools.stringBounds(s, properties.font).getHeight()+LINE_OFFSET_Y;
        }

    }

    @Override
    public void update(float delta) {

    }

    public static class TooltipProperties {
        public String text = "";
        public String headerText = "";
        public Color bgColor = new Color(255, 255, 255, 255), borderColor = new Color(0, 0, 0, 255);
        public Color headerTextColor = new Color(0,0,0,255), textColor = new Color(110, 110, 110, 255);
        public Color headerBg = new Color(255, 255, 255, 255);
        public Font font, headerFont;
        public int borderWidth = 1;
        public int maxWidth = 100, maxHeight = 100;
        public int padding = 0, headerPadding = 0;

        public TooltipProperties() {}
    }
}

