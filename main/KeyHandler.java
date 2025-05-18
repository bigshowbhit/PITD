package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean qPressed, ePressed, zPressed, cPressed; // Changed to Z and C keys

    //for saboteur
    public boolean upArrowPressed, downArrowPressed, leftArrowPressed, rightArrowPressed;
    public boolean enterPressed, escapePressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();
        if(c == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(c == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(c == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(c == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(c == KeyEvent.VK_Q) {
            qPressed = true;
        }
        if(c == KeyEvent.VK_E) {
            ePressed = true;
        }
        if(c == KeyEvent.VK_Z) { // Z key
            zPressed = true;
        }
        if(c == KeyEvent.VK_C) { // C key
            cPressed = true;
        }

        // Saboteur controls
        if (c == KeyEvent.VK_UP) upArrowPressed = true;
        if (c == KeyEvent.VK_DOWN) downArrowPressed = true;
        if (c == KeyEvent.VK_LEFT) leftArrowPressed = true;
        if (c == KeyEvent.VK_RIGHT) rightArrowPressed = true;

        //Enter for breaking the pipe/pump
        if (c == KeyEvent.VK_ENTER) enterPressed = true;
        //Escape for fixing the pipe/pump
        if (c == KeyEvent.VK_ESCAPE) escapePressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int c = e.getKeyCode();
        if(c == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(c == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(c == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(c == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(c == KeyEvent.VK_Q) {
            qPressed = false;
        }
        if(c == KeyEvent.VK_E) {
            ePressed = false;
        }
        if(c == KeyEvent.VK_Z) { // Z key
            zPressed = false;
        }
        if(c == KeyEvent.VK_C) { // C key
            cPressed = false;
        }

        // Saboteur controls
        if (c == KeyEvent.VK_UP) upArrowPressed = false;
        if (c == KeyEvent.VK_DOWN) downArrowPressed = false;
        if (c == KeyEvent.VK_LEFT) leftArrowPressed = false;
        if (c == KeyEvent.VK_RIGHT) rightArrowPressed = false;

        //Enter for breaking the pipe/pump
        if (c == KeyEvent.VK_ENTER) enterPressed = false;
        //Escape for fixing the pipe/pump
        if (c == KeyEvent.VK_ESCAPE) escapePressed = false;
    }
}