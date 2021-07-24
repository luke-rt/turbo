package com.github.ltgr.turbo;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;


public class Window {
    private int width;
    private int height;
    private boolean visible;

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
        this.visible = false;

        this.frame = new JFrame(title);
        this.frame.setSize(this.width, this.height);
        this.frame.setPreferredSize(new Dimension(this.width, this.height));

        this.frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void show() {
        /*
         * Shows window at mouse (top left corner)
         *
         */

        // TODO: add setting to specify window coords
        Point location = MouseInfo.getPointerInfo().getLocation();
        this.frame.setLocation((int)location.getX(), (int)location.getY());

        this.frame.pack();
        this.frame.setVisible(true);

        this.visible = true;
    }

    public void hide() {
        /*
         * Hide window
         */
        this.frame.setVisible(false);

        this.visible = false;
    }

    public void show(int x, int y) {
        /*
         * Shows window at specified coords
         *
         * @param x     x coordinate
         * @param y     y coordinate
         */
        this.frame.setLocation(x, y);

        this.frame.pack();
        this.frame.setVisible(true);

        this.visible = true;
    }

    public boolean isVisible() {
        return visible;

    }

}
