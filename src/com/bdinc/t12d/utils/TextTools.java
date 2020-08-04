package com.bdinc.t12d.utils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class TextTools {

    public static Rectangle2D stringBounds(String text, Font font) {
        TextLayout tl = new TextLayout(text, font, new FontRenderContext(null, true, true));
        return tl.getBounds();
    }

    public static int symbolCount(String text, char symbol) {
        if(!text.contains(""+symbol))
            return 0;
        else {
            int res = 0;
            for(int i = 0; i < text.length(); i++) {
                if(text.charAt(i) == symbol) res++;
            }
            return res;
        }
    }

    public static String wrappedText(String text, Font f, int contMaxWidth, int contMaxHeight) {
        String[] words = text.split(" ");

        String line = "";
        String res = "";
        for(String s : words) {
            String tmp = !line.isEmpty() ? line + " " + s : line + s;
            if(stringBounds(tmp, f).getWidth() < contMaxWidth) {
                line = !line.isEmpty() ? line + " " + s : line + s;
            }
            else {
                String tmpRes = res + line;
                if(stringBounds(tmpRes, f).getHeight()*symbolCount(tmpRes+'\n', '\n') < contMaxHeight) {
                    res += line + '\n';
                    line = "";
                }
                else {
                    if(res.endsWith("\n")) {
                        res = res.substring(0, res.length()-2);
                    }
                }
            }
//            if(stringBounds(tmp, f).getWidth() < contMaxWidth && stringBounds(res, f).getHeight()*symbolCount(res, '\n') < contMaxHeight) {
//                line = !line.isEmpty() ? line + " " + s : line + s;
//            }
//            else if(stringBounds(tmp, f).getWidth() >= contMaxWidth && stringBounds(res, f).getHeight()*symbolCount(res, '\n') < contMaxHeight) {
//                res += (line+'\n');
//                line = "";
//            }
//            else if(stringBounds(res, f).getHeight()*symbolCount(res, '\n') >= contMaxHeight) {
//
//            }
        }
        return res;
    }

    public static String[] wrappedTextLines(String text, Font f, int contMaxWidth, int contMaxHeight) {
        return wrappedText(text, f, contMaxWidth, contMaxHeight).split("\n");
    }

}
