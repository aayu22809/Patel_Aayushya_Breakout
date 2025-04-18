import { spawn } from 'child_process';
import { resolve } from 'path';
import { cwd } from 'process';

/**
 * Interface for the game process
 */
interface GameProcess {
  pid: number;
}

/**
 * Class to manage game process lifecycle
 */
export class GameManager {
  static gameProcess: GameProcess | null = null;
  
  /**
   * Starts the game process
   */
  static async startGame() {
    try {
      // Get the absolute path to the project directory
      const projectDir = resolve(cwd(), '..');
      
      // Start the game process
      const process = spawn('bash', ['run.sh'], {
        cwd: projectDir,
        detached: true,
        stdio: 'ignore'
      });
      
      // Unref the process to allow it to run independently of the parent
      process.unref();
      
      this.gameProcess = { pid: process.pid };
      console.log('Game launched with PID:', process.pid);
      
      return { success: true, pid: process.pid, error: null };
    } catch (error) {
      console.error('Error starting game:', error);
      return { success: false, pid: null, error: error instanceof Error ? error.message : String(error) };
    }
  }
  
  /**
   * Stops the currently running game process
   */
  static stopGame() {
    if (!this.gameProcess) return false;
    
    try {
      // On Unix systems, negative PID kills the whole process group
      process.kill(-this.gameProcess.pid, 'SIGTERM');
      console.log('Game stopped with PID:', this.gameProcess.pid);
      
      // Clear the process reference
      this.gameProcess = null;
      return true;
    } catch (error) {
      console.error('Error stopping game:', error);
      return false;
    }
  }
  
  /**
   * Checks if a game is currently running
   */
  static isGameRunning() {
    return this.gameProcess !== null;
  }
}