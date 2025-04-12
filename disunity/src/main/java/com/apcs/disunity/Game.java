package com.apcs.disunity;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    // Camera position to control viewport
    private Vector2 cameraPos;

    // Buffer for scaling the game
    private ScalableBuffer buffer;

    // Handles keystrokes from user
    private InputHandler input;

    // Is the host
    private boolean isHost;

    // Constructors
    public Game(Vector2 dimensions, String scene) {
        this(dimensions, scene, true);
    }

    public Game(Vector2 dimensions, String scene, boolean isHost) {
        // Set host status
        this.isHost = isHost;
        
        // Game dimensions
        this.dimensions = dimensions;

        // Camera pos
        this.cameraPos = Vector2.ZERO;

        // Panel background
        setBackground(Color.BLACK);
        
        // Double buffering
        buffer = new ScalableBuffer(dimensions);

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

    // Set camera position
    public void setCameraPos(Vector2 pos) { this.cameraPos = pos; }

    // Set buffer size
    public void setBufferSize(Vector2 size) { buffer.setSize(size); }

    // Get buffer
    public ScalableBuffer getBuffer() { return buffer; }

    // Get instance
    public static Game getInstance() { return instance; }

    // Get host status
    public boolean isHost() { return isHost; }

    /* ================ [ JPANEL ] ================ */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Clear buffer
        buffer.clear();

        // Update buffer
        Scenes.drawScene(
          cameraPos.mul(-1)
            .add(dimensions.mul(0.5)) // Center on camera
        );

        BufferedImage image = buffer.getImage();
        int w = image.getWidth();
        int h = image.getHeight();

        // Draw to screen
        g.drawImage(image, (getWidth() - w) / 2, (getHeight() - h) / 2, w, h, null);
    }

    /* ================ [ RUNNABLE ] ================ */

    @Override
    public void run() {
        // Variables
        double delta = 0;
        long prevTime = System.nanoTime(), curTime;

        // Game loop
        while (game != null) {

            // Calculate delta
            curTime = System.nanoTime();
            delta += (curTime - prevTime) / 1000000.0;
            prevTime = curTime;

            if (delta >= Options.getMSPF()) {
                // Update game
                while (delta >= Options.getMSPF()) {
                    // Update scene
                    Scenes.updateScene(Options.getSPF());

                    // Decrease delta
                    delta -= Options.getMSPF();
                }

                // Render updated frame
                repaint();
            }
        }
    }

}
