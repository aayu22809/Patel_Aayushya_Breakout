package disunity;

/**
 * All of the configurable options for the game
 * 
 * @author Qinzhao Li
 */
public class Options {

    /* ================ [ FIELDS ] ================ */

    // Frame rate
    public static double FPS = 60.0;

    /* ================ [ METHODS ] ================ */

    // Milliseconds per frame
    public static double getMSPF() { return 1000.0 / FPS; }
    
}
