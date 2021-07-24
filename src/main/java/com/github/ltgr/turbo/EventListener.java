package com.github.ltgr.turbo;

import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.GlobalScreen;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class EventListener {

    public static int HOTKEY_1;
    public static int HOTKEY_2;
    public static boolean HK_1_PRESSED;
    public static boolean HK_2_PRESSED;

    public static void listenerInit(String hotkey) {
        /*
         * Initializes the Native listener
         *
         */

        // disable logging of mouse drag events
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        // TODO: convert string to Native Key ID
        HOTKEY_1 = NativeKeyEvent.VC_ALT;
        HOTKEY_2 = NativeKeyEvent.VC_W;
        HK_1_PRESSED = false;
        HK_2_PRESSED = false;

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.out.println("Issue with registering jnativehook");
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new NativeListener());
    }

    public static boolean hotkeyRegistered() {
        if(EventListener.hotkeyPressed()) {
            // NOTE I actually hate this method of implementing detection of two key presses can someome hmu with better method
            while(EventListener.hotkeyPressed()) System.out.print("");

            return true;
        }
        return false;
    }

    public static boolean hotkeyPressed() {
        /*
         * returns whether the hotkey is pressed
         * NOTE: Turbo only reads when the key is released, not when it is pressed or typed
         *
         */
        return (EventListener.HK_1_PRESSED && EventListener.HK_2_PRESSED);
    }

}

class NativeListener implements NativeKeyListener {


    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        /*
         * Measures when the key is released
         *
         */
        if(e.getKeyCode() == EventListener.HOTKEY_1) {
            EventListener.HK_1_PRESSED = false;
        } else if(e.getKeyCode() == EventListener.HOTKEY_2) {
            EventListener.HK_2_PRESSED = false;
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if(e.getKeyCode() == EventListener.HOTKEY_1) {
            EventListener.HK_1_PRESSED = true;
        } else if(e.getKeyCode() == EventListener.HOTKEY_2) {
            EventListener.HK_2_PRESSED = true;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // nothing to do
    }
}
