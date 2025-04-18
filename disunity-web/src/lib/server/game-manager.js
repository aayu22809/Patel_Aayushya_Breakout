import { spawn } from 'child_process';
import { resolve } from 'path';
import { cwd } from 'process';

/**
 * Singleton class to manage the game process
 */
class GameManager {
  constructor() {
    this.gameProcess = null;
  }

  /**
   * Start the game process
   * @returns {{success: boolean, pid?: number, message?: string, error?: string}} Process information
   */
  startGame() {
    // Check if a game is already running
    if (this.gameProcess) {
      return { 
        success: true,
        pid: this.gameProcess.pid,
        message: 'Game is already running' 
      };
    }
    
    try {
      // Get the absolute path to the project directory
      const projectDir = resolve(cwd(), '..');
      
      // Start the game process
      const process = spawn('bash', ['run.sh'], {
        cwd: projectDir,
        detached: true,
        stdio: 'inherit'
      });
      
      // Store the process
      this.gameProcess = process;
      const pid = process.pid;
      
      console.log('Game launched with PID:', pid);
      
      // Handle process exit
      process.on('exit', (code) => {
        console.log(`Game process exited with code ${code}`);
        this.gameProcess = null;
      });
      
      // Unref the process to allow it to run independently of the parent
      process.unref();
      
      return { success: true, pid };
    } catch (error) {
      console.error('Error starting game:', error);
      return { 
        success: false, 
        error: error instanceof Error ? error.message : String(error) 
      };
    }
  }

  /**
   * Stop the game process
   * @returns {{success: boolean, message: string, warning?: string}} Result of the operation
   */
  stopGame() {
    // Check if a game is running
    if (!this.gameProcess) {
      return { success: true, message: 'No game running to stop' };
    }
    
    try {
      // On Unix systems, negative PID kills the whole process group
      try {
        if (this.gameProcess && this.gameProcess.pid) {
          process.kill(-this.gameProcess.pid, 'SIGTERM');
        }
      } catch {
        // If that fails, try killing just the process
        this.gameProcess.kill();
      }
      
      console.log('Stopped game process with PID:', this.gameProcess.pid);
      this.gameProcess = null;
      return { success: true, message: 'Game stopped successfully' };
    } catch (error) {
      console.error('Error killing process:', error);
      
      // Even if we couldn't kill it, clear our reference
      this.gameProcess = null;
      return { 
        success: true, 
        message: 'Process reference cleared',
        warning: error instanceof Error ? error.message : String(error)
      };
    }
  }

  /**
   * Check if a game is currently running
   * @returns {boolean}
   */
  isGameRunning() {
    return this.gameProcess !== null;
  }

  /**
   * Get information about the running game
   * @returns {{running: boolean, pid?: number}} Game status information
   */
  getGameStatus() {
    if (!this.gameProcess) {
      return { running: false };
    }
    
    return {
      running: true,
      pid: this.gameProcess.pid
    };
  }
}

// Export singleton instance
export const gameManager = new GameManager();