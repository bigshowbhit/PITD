package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pipes In The Desert");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack(); //pack the frame to fit the preferred size of the game panel

        frame.setLocationRelativeTo(null);  //centre of the screen
        frame.setVisible(true);

        gamePanel.startGameThread(); //start the game thread
    }
}
