package entity;

import main.Directions;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Plumber extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Plumber(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle(8,16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        SetDefaultValues();
        GetPlayerImage();
    }

    public void GetPlayerImage() {
        try {
            // Load the image for the player
            image = ImageIO.read(getClass().getResourceAsStream("/main/res/plumber.png"));
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    public void SetDefaultValues() {
        x = gp.tileSize * 14;
        y = gp.tileSize * 10;
        speed = 4;
    }

    public void update()
    {
        if(keyH.upPressed) {
            direction = Directions.UP;
        }
        else if(keyH.downPressed) {
            direction = Directions.DOWN;
        }
        else if(keyH.leftPressed) {
            direction = Directions.LEFT;
        }
        else if(keyH.rightPressed) {
            direction = Directions.RIGHT;
        }

        // Check for collision with the tile
        collisionOn = false;
        gp.cd.checkTile(this);

        // If collision if false, and player can move
        if(!collisionOn && (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
            switch (direction) {
                case Directions.UP:
                    y -= speed;
                    break;
                case Directions.DOWN:
                    y += speed;
                    break;
                case Directions.LEFT:
                    x -= speed;
                    break;
                case Directions.RIGHT:
                    x += speed;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage pic = null;
        pic = image;
        g2.drawImage(pic,x,y,gp.tileSize,gp.tileSize,null); // Draw the image
    }
}
