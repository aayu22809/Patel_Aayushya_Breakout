package ljaag.util;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Settings {
    
    /* ================ [ FIELDS ] ================ */

    /** App title */
    public static final String APP_TITLE = "Literally Just Another APCS Game";

    /** Window width */
    public static final int WINDOW_WIDTH = 1920;
    /** Window height */
    public static final int WINDOW_HEIGHT = 1080;
    /** Actual game width */
    public static final int GAME_WIDTH = 320;
    /** Actual game height */
    public static final int GAME_HEIGHT = 180;

    /** Frames per second */
    public static final double FPS = 60.0;
    /** Milliseconds per frame */
    public static final double MSPF = 1000.0 / FPS;

    /** Game keymapping */
    public static Map<Integer, String> INPUT_MAP = new HashMap<>(
        Map.ofEntries(
            Map.entry(KeyEvent.VK_W, "up"),
            Map.entry(KeyEvent.VK_S, "down"),
            Map.entry(KeyEvent.VK_A, "left"),
            Map.entry(KeyEvent.VK_D, "right")
        )
    );

}
