package entity;

import main.Directions;
import main.GamePanel;
import main.KeyHandler;
import tile.Desert;
import tile.Pipe;
import tile.Pump;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Plumber extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    String pickedTile;

    public Plumber(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle(8,16, 32, 32);
        pickedTile = "";
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
        else if(keyH.escapePressed) {
            repairTile();
        }
        else if(keyH.qPressed) {
            // Manufacture pipe
            manufacturePipe();
        }
        else if(keyH.ePressed) {
            // Manufacture pump
            manufacturePump();
        }
        else if(keyH.zPressed) {
            // Pick up tile
            pickTile();
        }
        else if(keyH.cPressed) {
            // Place tile
            placeTile();
        }

        // Check for collision with the tile
        collisionOn = false;
        gp.cd.checkTile(this);
        gp.cd.checkEntity(this, gp.saboteur);

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

    public void repairTile() {
        Tile tile = getCurrentTile();
        tile.fixTile();
    }

    public Tile getCurrentTile() {
        int entityMiddleX = x + solidArea.x + solidArea.width / 2;
        int entityMiddleY = y + solidArea.y + solidArea.height / 2;
        int entityRow = entityMiddleY / gp.tileSize;
        int entityCol = entityMiddleX / gp.tileSize;
        return gp.Tm.mapTiles[entityCol][entityRow];
    }

    public void manufacturePipe() {
        int entityMiddleX = x + solidArea.x + solidArea.width / 2;
        int entityMiddleY = y + solidArea.y + solidArea.height / 2;
        int entityRow = entityMiddleY / gp.tileSize;
        int entityCol = entityMiddleX / gp.tileSize;
        String tileName = gp.Tm.getTileName(entityCol, entityRow);

        if(!tileName.equals("Cistern")) {
            return;
        }

        gp.Tm.mapTiles[entityCol-1][entityRow-1] = new Pipe();
        gp.Tm.mapTileNum[entityCol-1][entityRow-1] = 1;
        gp.Tm.mapTiles[entityCol-1][entityRow-1].collision = true;

        System.out.println("Manufacturing Pipe");
    }

    public void manufacturePump() {
        int entityMiddleX = x + solidArea.x + solidArea.width / 2;
        int entityMiddleY = y + solidArea.y + solidArea.height / 2;
        int entityRow = entityMiddleY / gp.tileSize;
        int entityCol = entityMiddleX / gp.tileSize;
        String tileName = gp.Tm.getTileName(entityCol, entityRow);

        if(!tileName.equals("Cistern")) {
            return;
        }

        gp.Tm.mapTiles[entityCol-1][entityRow-1] = new Pump();
        gp.Tm.mapTileNum[entityCol-1][entityRow-1] = 2;
        gp.Tm.mapTiles[entityCol-1][entityRow-1].collision = true;

        System.out.println("Manufacturing Pump");
    }

    public void pickTile() {
        int entityMiddleX = x + solidArea.x + solidArea.width / 2;
        int entityMiddleY = y + solidArea.y + solidArea.height / 2;
        int entityRow = entityMiddleY / gp.tileSize;
        int entityCol = entityMiddleX / gp.tileSize;
        String tileName = gp.Tm.getTileName(entityCol, entityRow);

        System.out.println("Pickinasdg up tile");

        if(!tileName.equals("Cistern")) {
            return;
        }

        int pickTileRow = entityRow - 1;
        int pickTileCol = entityCol - 1;
        String pickTileName = gp.Tm.getTileName(pickTileCol, pickTileRow);

        if(pickTileName.equals("Pipe") || pickTileName.equals("Pump")) {
            pickedTile = pickTileName;
            gp.Tm.mapTiles[pickTileCol][pickTileRow] = new Desert();
            gp.Tm.mapTileNum[pickTileCol][pickTileRow] = 0;
            System.out.println("Picking up tile");
        }
    }

    public void placeTile() {
        if(pickedTile.isEmpty()) {
            return;
        }

        int entityMiddleX = x + solidArea.x + solidArea.width / 2;
        int entityMiddleY = y + solidArea.y + solidArea.height / 2;
        int entityRow = entityMiddleY / gp.tileSize;
        int entityCol = entityMiddleX / gp.tileSize;
        int placeRow = entityRow;
        int placeCol = entityCol;

        switch(direction) {
            case Directions.UP:
                placeRow--;
                break;
            case Directions.DOWN:
                placeRow++;
                break;
            case Directions.LEFT:
                placeCol--;
                break;
            case Directions.RIGHT:
                placeCol++;
                break;
        }

        if(!gp.Tm.getTileName(placeCol, placeRow).equals("Desert")) {
            return;
        }

        if(pickedTile.equals("Pipe")) {
            gp.Tm.mapTiles[placeCol][placeRow] = new Pipe();
        }
        else if(pickedTile.equals("Pump")) {
            gp.Tm.mapTiles[placeCol][placeRow] = new Pump();
        }

        pickedTile = "";

        System.out.println("Entity col: " + entityCol + " row: " + entityRow);
        System.out.println("Place tile col: " + placeCol + " row: " + placeRow);
        System.out.println("Place tile name: " + gp.Tm.getTileName(placeCol, placeRow));
        System.out.println("Placing tile");
    }

    public void draw(Graphics2D g2) {
        BufferedImage pic = null;
        pic = image;
        g2.drawImage(pic,x,y,gp.tileSize,gp.tileSize,null); // Draw the image
    }
}
