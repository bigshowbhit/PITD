package entity;

import main.Directions;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Saboteur extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Saboteur(GamePanel gp, KeyHandler keyH) {
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
            image = ImageIO.read(getClass().getResourceAsStream("/main/res/saboteur.png"));
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    public void SetDefaultValues() {
        x = gp.tileSize * 1;
        y = gp.tileSize * 1;
        speed = 4;
    }

    public void update()
    {
        if(keyH.upArrowPressed) {
            direction = Directions.UP;
        }
        else if(keyH.downArrowPressed) {
            direction = Directions.DOWN;
        }
        else if(keyH.leftArrowPressed) {
            direction = Directions.LEFT;
        }
        else if(keyH.rightArrowPressed) {
            direction = Directions.RIGHT;
        }
        // Check for collision with the tile
        collisionOn = false;
        gp.cd.checkTile(this);

        // If collision if false, and player can move
        if(!collisionOn && (keyH.upArrowPressed || keyH.downArrowPressed || keyH.leftArrowPressed || keyH.rightArrowPressed)) {
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
        g2.drawImage(pic,x,y,gp.tileSize,gp.tileSize,null);
    }
}
