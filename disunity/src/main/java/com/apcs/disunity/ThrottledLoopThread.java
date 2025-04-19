package com.apcs.disunity;

public class ThrottledLoopThread extends Thread {
    /// @param delay delay per tick in milliseconds
    /// @param onTick code to run for every delay past
    /// @param onUpdate code to run for every time interval was past delay
    public ThrottledLoopThread(double delay, Runnable onTick, Runnable onUpdate) {
        super(() -> {
            double delta = 0;
            long prevTime = System.nanoTime(), curTime;

            // Game loop
            while (!Thread.currentThread().isInterrupted()) {

                // Calculate delta
                curTime = System.nanoTime();
                delta += (curTime - prevTime) / 1e6;
                prevTime = curTime;

                if (delta >= delay) {
                    // Update game
                    while (delta >= delay) {
                        // Update scene
                        onTick.run();

                        // Decrease delta
                        delta -= delay;
                    }

                    // Render updated frame
                    onUpdate.run();
                }

                double sleepMs = delay - (System.nanoTime() - prevTime) / 1e6;

                try {
                    Thread.sleep((long) Math.max(0,sleepMs));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
