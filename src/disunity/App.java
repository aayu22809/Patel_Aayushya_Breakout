package disunity;

import javax.swing.JFrame;

/**
 * Creates an app window with a game
 * 
 * @author Qinzhao Li
 */
public class App extends JFrame {

    /* ================ [ FIELDS ] ================ */

    // Constructor
    public App(String title, int width, int height, Game game) {

        // Window setup
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add game
        add(game);
        
        // Display window
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        // Start game
        game.start();
        
    }
    
}
