package ljaag.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import ljaag.util.Settings;

public class InputHandler implements KeyListener {

    /* ================ [ FIELDS ] ================ */

    /** Detected inputs */
    public static Map<String, Boolean> inputs = new HashMap<>();

    /* ================ [ KEYLISTENER ] ================ */

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Settings.INPUT_MAP.containsKey(e.getKeyCode())) {
            inputs.put(Settings.INPUT_MAP.get(e.getKeyCode()), true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (Settings.INPUT_MAP.containsKey(e.getKeyCode())) {
            inputs.put(Settings.INPUT_MAP.get(e.getKeyCode()), false);
        }
    }

}
