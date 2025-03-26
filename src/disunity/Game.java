package disunity;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import disunity.camera.Camera;
import disunity.input.InputHandler;
import disunity.math.Vector2;
import disunity.rendering.ScalableBuffer;
import disunity.scenes.Scenes;

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

    // Camera to control viewport
    private Camera camera;

    // Input handler
    private InputHandler input;

    // Constructors
    public Game(Vector2 dimensions, String scene, Camera camera) {
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

        // Set camera
        this.camera = camera;

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
    public void updateBuffer(Vector2 target) { buffer.refresh(target); }

    // Get buffer
    public ScalableBuffer getBuffer() { return buffer; }

    // Get camera
    public Camera getCamera() { return camera; }

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
        double delta = 0;
        long prevTime = System.nanoTime(), curTime;

        // Game loop
        while (game != null) {

            // Calculate delta
            curTime = System.nanoTime();
            delta += (curTime - prevTime) / 1000000.0;
            prevTime = curTime;

            // Update game
            while (delta >= Options.getMSPF()) {
                
                // Clear buffer
                buffer.clear();
                
                // Update scene
                Scenes.updateScene(Options.getSPF());
                Scenes.drawScene(
                    camera.getPos().mul(-1)
                        .add(dimensions.mul(0.5)) // Center camera
                );

                // Draw buffer to screen
                repaint();

                // Decrease delta
                delta -= Options.getMSPF();

            }

        }

    }

}
