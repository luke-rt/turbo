package com.github.ltgr.turbo;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class EventSimulator {

    private final boolean autopaste;
    private final boolean autoenter;

    private Robot simulator;

    public EventSimulator(boolean autopaste, boolean autoenter) {
        this.autopaste = autopaste;
        this.autoenter = autoenter;
        try {
            this.simulator = new Robot();
        } catch (AWTException e) {
            System.out.println("AWT Robot failed to initialize");
            System.exit(1);
        }

    }

    public void run() throws InterruptedException {
        if(this.autopaste) this.clipboardPaste();
        Thread.sleep(10);
        if(this.autoenter) this.typeEnter();

    }

    private void clipboardPaste() {
        simulator.keyPress(KeyEvent.VK_CONTROL);
        simulator.keyPress(KeyEvent.VK_V);

        simulator.keyRelease(KeyEvent.VK_CONTROL);
        simulator.keyRelease(KeyEvent.VK_V);

    }

    private void typeAltTab() {
        /*
         * Simulate alt tab action
         */
        simulator.keyPress(KeyEvent.VK_ALT);
        simulator.keyPress(KeyEvent.VK_TAB);

        simulator.keyRelease(KeyEvent.VK_ALT);
        simulator.keyRelease(KeyEvent.VK_TAB);

    }

    private void typeEnter() {
        /*
         * Simulate enter action
         *
         */
        simulator.keyPress(KeyEvent.VK_ENTER);
        simulator.keyRelease(KeyEvent.VK_ENTER);

    }
}

class Image2Clipboard implements ClipboardOwner {
    // TODO: Make an Image2Clipboard instance a field of eventsimulator
    public void copyImage(BufferedImage bufimg) {
        TransferableImage t = new TransferableImage(bufimg);
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(t, this);
    }

    public void lostOwnership( Clipboard clip, Transferable trans ) {
        System.out.println( "Lost Clipboard Ownership" );
    }

    private class TransferableImage implements Transferable {

        Image i;

        public TransferableImage(Image i) {
            this.i = i;
        }

        public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
            if(flavor.equals( DataFlavor.imageFlavor ) && i != null){
                return i;
            } else throw new UnsupportedFlavorException( flavor );
        }

        public DataFlavor[] getTransferDataFlavors() {
            DataFlavor[] flavors = new DataFlavor[ 1 ];
            flavors[0] = DataFlavor.imageFlavor;
            return flavors;
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            DataFlavor[] flavors = getTransferDataFlavors();
            for (DataFlavor dataFlavor : flavors) {
                if (flavor.equals(dataFlavor)) return true;
            }

            return false;
        }
    }
}
