package main;

import entity.Plumber;
import entity.Saboteur;
import tile.TileManager;
import main.timer.Timer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    KeyHandler keyHandler = new KeyHandler();
    int FPS = 60; // frames per second
    private Timer gameTimer;
    private int timeRemaining;
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tiles
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768
    public final int screenHeight = tileSize * maxScreenRow; // 576

    Thread gameThread;
    public Plumber plumber = new Plumber(this, keyHandler);
    public Saboteur saboteur = new Saboteur(this, keyHandler);
    public TileManager Tm = new TileManager(this);
    public CollisionDetector cd = new CollisionDetector(this);

    public GamePanel() {
        gameTimer = new Timer(60); // 60 seconds countdown
        timeRemaining = 60; // Set the initial time remaining to 60 seconds
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler); // add the key listener to the game panel
        this.setFocusable(true); // set the game panel to be focusable so it can receive key events
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // start the thread, which runs the run method
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;

                // Update the timer every second
                timeRemaining = (int) gameTimer.getTimeRemaining();
                if (timeRemaining <= 0) {
                    System.out.println("Time's up!");
                    stopGameThread();
                }
            }
        }
    }

    public void update() {
        plumber.update();
        saboteur.update();
    }

    public void stopGameThread() {
        gameThread = null; // Stop the game loop
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Tm.draw(g2); // draw the tiles
        plumber.draw(g2);
        saboteur.draw(g2);

        // Draw the timer on the screen
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.setColor(Color.BLACK);
        g2.drawString("Time Remaining: " + timeRemaining + "s", 50, 50);

        g2.dispose();
    }
}