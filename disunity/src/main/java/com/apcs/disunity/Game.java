package com.apcs.disunity;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.apcs.disunity.input.InputHandler;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.rendering.ScalableBuffer;
import com.apcs.disunity.scenes.Scenes;

/**
 * Renders the current scene to the screen
 * 
 * @author Qinzhao Li
 */
public class Game extends JPanel implements Runnable {

    /* ================ [ FIELDS ] ================ */

    // Singleton instance
    private static Game instance;

    // Game thread
    private Thread game;

    // Game dimensions
    private Vector2 dimensions;

    // Buffer for scaling the game
    private ScalableBuffer buffer;

    // Camera position to control viewport
    private Vector2 cameraPos;

    // Input handler
    private InputHandler input;

    // Constructors
    public Game(Vector2 dimensions, String scene) {
        // Game dimensions
        this.dimensions = dimensions;

        // Panel background
        setBackground(Color.BLACK);
        
        // Double buffering
        buffer = new ScalableBuffer(dimensions, dimensions);
        setDoubleBuffered(true);

        // Focus window for input
        setFocusable(true);
        requestFocusInWindow();

        // Attach input handler
        input = new InputHandler();
        addKeyListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);

        // Set current scene
        Scenes.setScene(scene);

        // Set singleton instance
        instance = this;
    }

    /* ================ [ METHODS ] ================ */
    
    // Start game thread
    public void start() {
        game = new Thread(this);
        game.start();
    }

    // Update buffer
    public void updateBuffer(Vector2 size) { buffer.refresh(size); }

    // Get buffer
    public ScalableBuffer getBuffer() { return buffer; }

    // Set camera position
    public void setCameraPos(Vector2 pos) { cameraPos = pos; }

    // Get instance
    public static Game getInstance() { return instance; }

    /* ================ [ JPANEL ] ================ */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fix window stretching
        int w = getWidth(), h = getHeight();
        w = (int) Math.min(w, h * dimensions.x / dimensions.y);
        h = (int) Math.min(h, w * dimensions.y / dimensions.x);

        // Draw to screen
        g.drawImage(buffer.getImage(), (getWidth()-w) / 2, (getHeight()-h) / 2, w, h, null);
    }

    /* ================ [ RUNNABLE ] ================ */

    @Override
    public void run() {

        // Variables
        double delta = 0, ddelta = 0;
        long prevTime = System.nanoTime(), curTime;

        // Game loop
        while (game != null) {

            // Calculate delta
            curTime = System.nanoTime();
            delta += (curTime - prevTime) / 1000000.0;
            ddelta += (curTime - prevTime) / 1000000.0;
            prevTime = curTime;

            // Update game
            while (delta >= Options.getMSPF()) {
                // Update scene
                Scenes.updateScene(Options.getSPF());

                // Decrease delta
                delta -= Options.getMSPF();
            }

            // Draw game
            while (ddelta >= Options.getMSPD()) {
                // Clear buffer
                buffer.clear();
                
                // Draw scene
                Scenes.drawScene(
                    cameraPos.mul(-1)
                        .add(dimensions.mul(0.5)) // Center on camera
                );

                // Draw buffer to screen
                repaint();

                // Decrease delta
                ddelta -= Options.getMSPD();
            }

        }

    }

}
