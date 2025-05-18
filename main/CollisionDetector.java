package main;

import entity.Entity;
import tile.Tile;

public class CollisionDetector {
    GamePanel gp;

    public CollisionDetector(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        Tile tile1, tile2;

        switch(Directions.getDirectionName(entity.direction)) {
            case "UP":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tile1 = gp.Tm.mapTiles[entityLeftCol][entityTopRow];
                tile2 = gp.Tm.mapTiles[entityRightCol][entityTopRow];
                if(tile1.collision || tile2.collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "DOWN":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tile1 = gp.Tm.mapTiles[entityLeftCol][entityBottomRow];
                tile2 = gp.Tm.mapTiles[entityRightCol][entityBottomRow];
                if(tile1.collision || tile2.collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "LEFT":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tile1 = gp.Tm.mapTiles[entityLeftCol][entityTopRow];
                tile2 = gp.Tm.mapTiles[entityLeftCol][entityBottomRow];
                if(tile1.collision || tile2.collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "RIGHT":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tile1 = gp.Tm.mapTiles[entityRightCol][entityTopRow];
                tile2 = gp.Tm.mapTiles[entityRightCol][entityBottomRow];
                if(tile1.collision || tile2.collision)
                {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
