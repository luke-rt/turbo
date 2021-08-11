package com.github.ltgr.turbo;


import org.ini4j.Ini;
import java.io.File;
import java.io.IOException;

public class Main {

    static boolean autopaste;
    static boolean autoenter;
    static int cols;
    static String path;
    static String hotkey;
    static int sleep;
    static int open_location_x;
    static int open_location_y;
    static boolean open_at_mouse;

    public static void main(String[] args) throws InterruptedException {
        // defaults
        path = args[0];
        hotkey = "alt + w";
        open_location_x = 100;
        open_location_y = 100;
        open_at_mouse = true;
        cols = 8;
        autopaste = true;
        autoenter = true;
        sleep = 10;
        // defaults

        try {
            Ini conf = new Ini(new File(path + "/turbo.conf"));

            hotkey = conf.get("HOTKEYS", "HOTKEY");
            open_location_x = Integer.parseInt(conf.get("WINDOW", "WINDOW_COORD_X"));
            open_location_y = Integer.parseInt(conf.get("WINDOW", "WINDOW_COORD_Y"));
            open_at_mouse = (conf.get("WINDOW", "OPEN_AT_MOUSE").equals("true") || Integer.parseInt(conf.get("WINDOW", "OPEN_AT_MOUSE")) == 1);
            cols = Integer.parseInt(conf.get("GUI", "COLS"));
            autopaste = (conf.get("FUNCTIONALITY", "AUTOPASTE").equals("true") || Integer.parseInt(conf.get("FUNCTIONALITY", "AUTOPASTE")) == 1);
            autoenter = (conf.get("FUNCTIONALITY", "AUTOENTER").equals("true") || Integer.parseInt(conf.get("FUNCTIONALITY", "AUTOENTER")) == 1);
            sleep = Integer.parseInt(conf.get("EXPERIMENTAL", "SLEEP_TIME"));

            if(autoenter) autopaste = true;
            // check if open_location_x, open_location_y, cols, and sleep are NaN

        } catch(IOException e) {
            System.out.println("configuration file in assets not found, defaulting to default settings");
        }

        Window window = new Window("Turbo", cols * 60, 480);
        EventListener.listenerInit();

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
