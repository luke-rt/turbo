package com.github.ltgr.turbo;

import org.imgscalr.Scalr;

import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class Resizer {

    public static void resizeAll(String dir, int width, int height) {
        /*
         * Scans specified asset directory, checks which images have not yet been resized
         * Resizes non-resized images
         *
         * @param dir       asset directory string
         * @param width     new image widths
         * @param height    new image heights
         *
         */
        File original = new File(dir + "/original");
        File resized = new File(dir + "/resized");
        resized.mkdirs();
        original.mkdirs();

        File[] original_files = original.listFiles();
        File[] resized_files = resized.listFiles();

        boolean exists = false;
        for(File original_img : original_files) {
            for(File resized_img : resized_files) {
                if(resized_img.getName().equals(original_img.getName())) {
                    exists = true;
                    break;
                }
            }

            if(!exists) {
                if(!resize(dir, original_img, width, height)) System.exit(1);
                exists = true;
            }
        }

    }

    private static boolean resize(String dir, File img, int width, int height) {
        /*
         * Resizes img using Graphics2D library
         *
         * @param dir       asset directory string
         * @param img       pointer of type File
         * @param width     new image width
         * @param height    new image height
         *
         */
        try {
            BufferedImage input = ImageIO.read(img);
            BufferedImage output = Scalr.resize(
                input,
                Scalr.Method.AUTOMATIC,
                Scalr.Mode.AUTOMATIC,
                width,
                height,
                Scalr.OP_ANTIALIAS
            );

            String filename = dir + "/resized/" + img.getName();
            String extension = img.getName().substring(img.getName().lastIndexOf(".") + 1);

            ImageIO.write(output, extension, new File(filename));
        } catch(IOException e){
            System.out.println("Problem reading files from assets directory");
            System.exit(1);

            return false;
        }

        return true;
    }
}
