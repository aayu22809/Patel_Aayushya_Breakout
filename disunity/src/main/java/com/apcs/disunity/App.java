package com.apcs.disunity;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import com.apcs.disunity.math.Vector2;

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

        // Window resize listener
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                game.updateBuffer(
                    Vector2.of(getWidth(), getHeight())
                );
            }
        });

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
