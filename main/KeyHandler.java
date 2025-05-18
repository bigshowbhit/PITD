package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    //for saboteur
    public boolean upArrowPressed, downArrowPressed, leftArrowPressed, rightArrowPressed;

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

        // Saboteur controls
        if (c == KeyEvent.VK_UP) upArrowPressed = true;
        if (c == KeyEvent.VK_DOWN) downArrowPressed = true;
        if (c == KeyEvent.VK_LEFT) leftArrowPressed = true;
        if (c == KeyEvent.VK_RIGHT) rightArrowPressed = true;
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
        // Saboteur controls
        if (c == KeyEvent.VK_UP) upArrowPressed = false;
        if (c == KeyEvent.VK_DOWN) downArrowPressed = false;
        if (c == KeyEvent.VK_LEFT) leftArrowPressed = false;
        if (c == KeyEvent.VK_RIGHT) rightArrowPressed = false;
    }
}
