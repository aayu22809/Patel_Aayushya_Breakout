package com.apcs.disunity.game;

import com.apcs.disunity.app.Options;

/**
 * A thread that runs a game loop
 * 
 * @author Toshiki Takeuchi
 */
public class GameThread extends Thread {

    /**
     * Creates a new GameThread with update and render calls
     * 
     * @param update The update call
     * @param draw   The render call
     */
    public GameThread(Runnable update, Runnable draw) {
        // Create the runnable
        super(() -> {
            // Track delta time
            double delta = 0;
            long prevTime = System.nanoTime(), curTime;

            // Game loop
            while (!Thread.currentThread().isInterrupted()) {
                // Calculate delta
                curTime = System.nanoTime();
                delta += (curTime - prevTime) / 1e6;
                prevTime = curTime;

                // Trigger frame
                if (delta >= Options.getMSPF()) {
                    while (delta >= Options.getMSPF()) {
                        // Update scene
                        update.run();

                        // Decrease delta
                        delta -= Options.getMSPF();
                    }

                    // Render scene
                    draw.run();
                }

                // Calculate sleep until next frame
                double sleep = Options.getMSPF() - (System.nanoTime() - prevTime) / 1e6;

                // Try to sleep
                try {
                    Thread.sleep((long) Math.max(0, sleep));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
