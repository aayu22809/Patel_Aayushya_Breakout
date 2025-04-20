package com.apcs.disunity.input;

import java.awt.event.*;

/**
 * Listens for inputs and updates them
 * 
 * @author Qinzhao Li
 */
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, FocusListener {

    /* ================ [ KEYLISTENER ] ================ */

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { Inputs.press(Input.getFromKey(e.getKeyCode())); }

    @Override
    public void keyReleased(KeyEvent e) { Inputs.release(Input.getFromKey(e.getKeyCode())); }

    /* ================ [ MOUSELISTENER ] ================ */

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { Inputs.press(Input.getFromMouse(e.getButton())); }

    @Override
    public void mouseReleased(MouseEvent e) { Inputs.release(Input.getFromMouse(e.getButton())); }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    /* ================ [ MOUSEMOTIONLISTENER ] ================ */

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {
        Inputs.mouseX = e.getX();
        Inputs.mouseY = e.getY();
    }

    /* ================ [ FOCUSLISTENER ] ================ */

    @Override
    public void focusGained(FocusEvent e) { }

    @Override
    public void focusLost(FocusEvent e) {
        Inputs.releaseAll();
    }
}
