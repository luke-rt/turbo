package com.github.ltgr.turbo;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Window {
    private int width;
    private int height;
    private boolean visible;

    final private EventSimulator simulator;

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

        this.simulator = new EventSimulator(Main.autopaste, Main.autoenter);

        this.frame = new JFrame(title);
        this.frame.setSize(this.width, this.height);
        this.frame.getContentPane().setBackground(new Color(46, 46, 52));
        this.frame.setPreferredSize(new Dimension(this.width, this.height));

        this.frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.refresh(Main.path);
    }

    public void refresh(String path) {
        // clear screen
        this.frame.setLayout(new GridBagLayout());

        File[] assets = new File(path + "/resized").listFiles();
        TurboImage[] images = new TurboImage[assets.length];
        for(int i = 0; i < images.length; i++) images[i] = new TurboImage(assets[i]);

        Resizer.resizeAll(path, 64, 64);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JButton[] buttons = new JButton[images.length];

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

        if(Main.open_at_mouse) {
            Point location = MouseInfo.getPointerInfo().getLocation();
            this.frame.setLocation((int)location.getX(), (int)location.getY());

        } else {
            this.frame.setLocation(Main.open_location_x, Main.open_location_y);

        }

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
