package com.github.ltgr.turbo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window {
    private int width;
    private int height;
    private boolean visible;

    private EventSimulator simulator;

    private JButton[] buttons;

    private final JFrame frame;

    public Window(String title, int w, int h, TurboImage[] images, boolean autopaste, boolean autoenter) {
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

        this.simulator = new EventSimulator(autopaste, autoenter);

        this.frame = new JFrame(title);
        this.frame.setSize(this.width, this.height);
        this.frame.getContentPane().setBackground(new Color(46, 46, 52));
        this.frame.setPreferredSize(new Dimension(this.width, this.height));

        this.frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.refresh(images);
    }

    public void refresh(TurboImage[] images) {
        // clear screen
        this.frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 1;

        buttons = new JButton[images.length];

        for(int i = 0; i < images.length; i++) {
            buttons[i] = new JButton(new ImageIcon(images[i].handle));
            buttons[i].setBorder(BorderFactory.createEmptyBorder());
            buttons[i].setContentAreaFilled(false);

            final int index = i;
            buttons[i].addActionListener(e -> sendEmoji(images[index]));

            panel.add(buttons[i]);
        }

        this.frame.add(panel, gbc);
    }

    public void sendEmoji(TurboImage image) {
        this.hide();
        try {
            this.simulator.run(image);
        } catch(InterruptedException e) {
            System.exit(1);
        }
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

    public void hide() {
        /*
         * Hide window
         */
        this.frame.setVisible(false);

        this.visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void toggleVisibility() {
        if(this.isVisible()) this.hide();
        else this.show();
    }

}
