package com.github.ltgr.turbo;

import java.lang.System;


public class Main {

    public static void main(String[] args) throws java.lang.InterruptedException {
        Window window = new Window("Turbo", 600, 600);

        if(!Resizer.resizeAll("/home/luke/dev/turbo/assets", 64, 64)) System.exit(1);

        EventListener.listenerInit("1 + 2");
        while(true) {
            if(EventListener.hotkeyPressed()) {
                if(window.isVisible()) window.hide();
                else window.show();

                EventListener.HK_1_PRESSED = false;
                EventListener.HK_2_PRESSED = false;
            }
            else System.out.print("");

        }

    }
}
