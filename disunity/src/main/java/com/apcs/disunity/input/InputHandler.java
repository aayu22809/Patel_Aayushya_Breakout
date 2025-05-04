package com.apcs.disunity.input;

import com.apcs.disunity.Game;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.rendering.ScalableBuffer;

import java.awt.*;
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
        ScalableBuffer buffer = Game.getInstance().getBuffer();
        Rectangle bounds = Game.getInstance().getBounds();
        Vector2 pos = Vector2.of(e.getX(), e.getY());
        Vector2 viewDim = Vector2.of(bounds.width, bounds.height);

        Inputs.mousePos = pos.sub(viewDim.div(2)).div(buffer.getScale());
    }

    /* ================ [ FOCUSLISTENER ] ================ */

    @Override
    public void focusGained(FocusEvent e) { }

    @Override
    public void focusLost(FocusEvent e) {
        Inputs.releaseAll();
    }
}
