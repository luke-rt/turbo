package com.github.ltgr.turbo;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Window {
    private boolean visible;
    private enum State {
        EMOJI,

    }

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
        this.visible = false;

        this.simulator = new EventSimulator(Main.autopaste, Main.autoenter);

        this.frame = new JFrame(title);
        this.frame.setSize(w, h);

        this.frame.getContentPane().setBackground(new Color(46, 46, 52));
        this.frame.setPreferredSize(new Dimension(w, h));

        this.frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.refresh(Main.path);
    }

    public void refresh(String path) {

        // converting assets
        File[] assets = new File(path + "/resized").listFiles();
        TurboImage[] images = new TurboImage[assets.length];
        for(int i = 0; i < images.length; i++) images[i] = new TurboImage(assets[i]);

        // resize all images
        Resizer.resizeAll(path, 64, 64);

        // GUI
        JPanel master = new JPanel(new BorderLayout());
        JPanel emojis = new JPanel(new GridBagLayout());
        JPanel options = new JPanel();


        // emojis
        emojis.setOpaque(false);

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

            emojis.add(buttons[i], gbc);
        }

        // options bar
        options.setOpaque(false);

        JButton hideButton = new JButton("HIDE");
        hideButton.setBorder(BorderFactory.createEmptyBorder());
        hideButton.setContentAreaFilled(false);
        hideButton.setForeground(new Color(220, 220, 220));
        hideButton.setFont(new Font("Cousine", Font.BOLD, 16));

        hideButton.addActionListener(e -> hide());

        hideButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent m) {
                hideButton.setForeground(new Color(245,245,245));
            }
            public void mouseExited(java.awt.event.MouseEvent m) {
                hideButton.setForeground(new Color(220, 220, 220));
            }
        });

        options.add(hideButton, BorderLayout.LINE_END);

        // grouping together
        master.setOpaque(false);

        master.add(options, BorderLayout.NORTH);
        master.add(emojis, BorderLayout.CENTER);

        this.frame.add(master);
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
