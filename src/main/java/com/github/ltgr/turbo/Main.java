package com.github.ltgr.turbo;

import java.io.File;
import java.lang.System;


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
        path = "/home/luke/dev/turbo/assets";
        hotkey = "alt + w";
        // config

        Window window = new Window("Turbo", cols * 80, 640);
        Resizer.resizeAll(path, 64, 64);
        EventListener.listenerInit(hotkey);
        EventSimulator event_simulator = new EventSimulator(autopaste, autoenter);
        Image2Clipboard img_copier = new Image2Clipboard();

        File[] assets = new File(path + "/resized").listFiles();
        TurboImage[] emojis = new TurboImage[assets.length];
        for(int i = 0; i < emojis.length; i++) emojis[i] = new TurboImage(assets[i]);

        img_copier.copyImage(emojis[0].handle);
        Thread.sleep(2000);
        event_simulator.run();

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
