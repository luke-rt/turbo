package com.github.ltgr.turbo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class TurboImage {

    public BufferedImage handle;
    public boolean isFavorite;

    public TurboImage(File file) {
        this.isFavorite = false;

        try {
            this.handle = ImageIO.read(file);
        } catch(IOException e) {
            System.out.println("Problem reading images");
            System.exit(1);
        }

    }
}
