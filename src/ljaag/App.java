package ljaag;

import javax.swing.JFrame;

import ljaag.util.Settings;

public class App extends JFrame {

    /* ================ [ FIELDS ] ================ */

    /** Game instance */
    private final Game game = new Game();

    /** Construct an App. */
    public App() {
        // Window setup
        setTitle(Settings.APP_TITLE);
        setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);

        // Exit when closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add game
        add(game);
        
        // Display window
        setLocationRelativeTo(null);
        setVisible(true);

        // Start game
        game.start();
    }

    /* ================ [ DRIVER ] ================ */

    public static void main(String[] args) { App _ = new App(); }
    
}
