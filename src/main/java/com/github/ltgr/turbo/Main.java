package com.github.ltgr.turbo;

import java.io.File;


public class Main {

    static boolean autopaste;
    static boolean autoenter;
    static int cols;
    static String path;
    static String hotkey;

    public static void main(String[] args) throws InterruptedException {
        // config
        autopaste = true;
        autoenter = true;
        cols = 8;
        path = args[0];
        hotkey = "alt + w";
        // config

        Window window = new Window("Turbo", cols * 60, 480, path, autopaste, autoenter);
        EventListener.listenerInit(hotkey);

        // window event loop
        boolean shouldClose = false;
        while(!shouldClose) {
            /*
             * NOTE I actually hate this method of implementing detection of two key presses its naive and consumes CPU usage
             * can someone hmu with better method, check EventListener.java and the hotkeyRegistered method to see the code
             */
            if(EventListener.hotkeyRegistered()) window.toggleVisibility();

            Thread.sleep(10);
        }
    }
}
