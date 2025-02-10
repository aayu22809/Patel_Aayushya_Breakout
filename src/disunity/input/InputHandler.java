package disunity.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens for keyboard inputs
 * 
 * @author Qinzhao Li
 */
public class InputHandler implements KeyListener {

    /* ================ [ KEYLISTENER ] ================ */

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { Inputs.press(e.getKeyCode()); }

    @Override
    public void keyReleased(KeyEvent e) { Inputs.release(e.getKeyCode()); }
    
}
