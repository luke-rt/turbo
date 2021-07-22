package com.github.ltgr.turbo;

import javax.swing.*;


public class Window {
    private int width;
    private int height;

    private final JFrame frame;

    public Window(String title, int w, int h) {
        /*
         * Window initialization
         *
         * @param   title   string giving the window name
         * @param   w       integer width of window
         * @param   h       integer height of window
         *
         */
        this.width = w;
        this.height = h;

        this.frame = new JFrame(title);
        this.frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void show() {
        /*
         * Show window
         *
         */
        this.frame.setLocationRelativeTo(null);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void hide() {
        /*
         * Hide window
         */
        this.frame.setVisible(false);
    }

}
