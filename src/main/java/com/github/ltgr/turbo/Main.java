package com.github.ltgr.turbo;

import java.lang.System;

public class Main {

    public static void main(String[] args) {

        Window window = new Window("Turbo", 600, 400);
        window.show();

        if(!Resizer.resizeAll("/home/luke/dev/turbo/assets", 64, 64)) System.exit(1);
    }
}
