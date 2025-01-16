package ljaag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ljaag.components.Component;
import ljaag.components.PlayerComponent;
import ljaag.input.InputHandler;
import ljaag.util.Settings;
import ljaag.util.Vector2;

public class Game extends JPanel implements Runnable {

    /* ================ [ FIELDS ] ================ */

    /** Main game thread */
    private Thread mainThread;

    /** Buffer for scaling the game panel */
    private BufferedImage buffer;
    /** Graphics of the buffer **/
    private Graphics2D bufferG;

    /** Handles game input */
    private InputHandler input = new InputHandler();

    /** List of all the game components */
    private List<Component> components = new ArrayList<>();

    /** Construct a Game. */
    public Game() {
        // Panel setup
        setBackground(Color.BLACK);
        
        // Double buffering
        setDoubleBuffered(true);
        buffer = new BufferedImage(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        bufferG = buffer.createGraphics();

        // TODO: Remove
        bufferG.setBackground(Color.WHITE);

        // Add input handler
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(input);

        // Add components
        addComponent(new PlayerComponent(new Vector2(0, 0)));
    }

    /* ================ [ METHODS ] ================ */
    
    /** Start game thread. */
    public void start() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    /**
     * Add a component.
     * @param component The component to add
     */
    public void addComponent(Component component) {
        components.add(component);
    }

    /** Update all components. */
    public void update() {
        for (Component component : components)
            component.update();
    }

    /** Draw all components to the buffer. */
    public void drawToBuffer() {
        for (Component component : components)
            component.draw(bufferG);
    }

    /* ================ [ JPANEL ] ================ */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fix window stretching
        int w = getWidth(), h = getHeight();
        w = Math.min(w, h * Settings.GAME_WIDTH / Settings.GAME_HEIGHT);
        h = Math.min(h, w * Settings.GAME_HEIGHT / Settings.GAME_WIDTH);

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
        while (mainThread != null) {

            // Calculate delta
            curTime = System.nanoTime();
            delta += (curTime - prevTime) / 1000000.0;
            prevTime = curTime;

            // Update game
            while (delta >= Settings.MSPF) {
                // System.out.println(InputHandler.inputs);
                
                // Update components
                update();
                
                // Draw component to buffer
                bufferG.clearRect(0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
                drawToBuffer();

                // Draw buffer to screen
                repaint();

                // Decrease delta
                delta -= Settings.MSPF;

            }

        }

    }

}
