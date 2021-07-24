package com.github.ltgr.turbo;

import java.lang.System;


public class Main {

    static boolean autopaste;
    static boolean autoenter;
    static int cols;
    static String path;
    static String hotkey;

    public static void main(String[] args) {
        // config
        autopaste = true;
        autoenter = true;
        cols = 8;
        path = "home/luke/dev/turbo/assets";
        hotkey = "alt + w";
        // config

        Window window = new Window("Turbo", cols * 80, 640);
        Resizer.resizeAll(path, 64, 64);
        EventListener.listenerInit(hotkey);
        EventSimulator event_simulator = new EventSimulator(true, true);

        // window event loop
        boolean shouldClose = false;
        while(!shouldClose) {
            /*
             * NOTE I actually hate this method of implementing detection of two key presses its naive and consumes CPU usage
             * can someone hmu with better method, check EventListener.java and the hotkeyRegistered method to see the code
             */
            if(EventListener.hotkeyRegistered()) window.toggleVisibility();
            else System.out.print("");

        }
    }
}
