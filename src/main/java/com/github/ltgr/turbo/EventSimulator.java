package com.github.ltgr.turbo;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;


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

    public void run() {

        // this.clipboardCopy();
        this.typeAltTab();

        if(this.autopaste) this.clipboardPaste();
        if(this.autoenter) this.typeEnter();

    }

    private void clipboardCopy(TurboImage image) {


    }

    private void clipboardPaste() {
        // idk how to detect cross platform stuff like ctrl vs cmd so ill figure it out

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

    public void typeEnter() {
        /*
         * Simulate enter action
         *
         */
        simulator.keyPress(KeyEvent.VK_ENTER);
        simulator.keyRelease(KeyEvent.VK_ENTER);

    }
}
