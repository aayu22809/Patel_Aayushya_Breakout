package disunity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import disunity.input.InputHandler;
import disunity.scenes.Scenes;

/**
 * Renders the current scene to the screen
 * 
 * @author Qinzhao Li
 */
public class Game extends JPanel implements Runnable {

    /* ================ [ FIELDS ] ================ */

    // Game thread
    private Thread game;

    // Game dimensions
    private int width, height;

    // Buffer for scaling the game
    private BufferedImage buffer;
    private Graphics2D bufferG;

    // Input handler
    private InputHandler input;

    // Constructor
    public Game(int width, int height, String scene) {
        // Game dimensions
        this.width = width;
        this.height = height;

        // Panel background
        setBackground(Color.BLACK);
        
        // Double buffering
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferG = buffer.createGraphics();
        setDoubleBuffered(true);

        // White background
        bufferG.setBackground(Color.WHITE);

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
    }

    /* ================ [ METHODS ] ================ */
    
    // Start game thread
    public void start() {
        game = new Thread(this);
        game.start();
    }

    /* ================ [ JPANEL ] ================ */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fix window stretching
        int w = getWidth(), h = getHeight();
        w = Math.min(w, h * width / height);
        h = Math.min(h, w * height / width);

        // Draw to screen
        g.drawImage(buffer, (getWidth()-w) / 2, (getHeight()-h) / 2, w, h, null);
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
                bufferG.clearRect(0, 0, width, height);
                
                // Update scene
                Scenes.updateScene();
                Scenes.drawScene(bufferG);

                // Draw buffer to screen
                repaint();

                // Decrease delta
                delta -= Options.getMSPF();

            }

        }

    }

}
