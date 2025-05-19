package main;

import entity.Entity;
import tile.Tile;

public class CollisionDetector {
    GamePanel gp;

    public CollisionDetector(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        Tile tile1, tile2;

        switch (Directions.getDirectionName(entity.direction)) {
            case "UP":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tile1 = gp.Tm.mapTiles[entityLeftCol][entityTopRow];
                tile2 = gp.Tm.mapTiles[entityRightCol][entityTopRow];
                if (tile1.collision || tile2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "DOWN":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tile1 = gp.Tm.mapTiles[entityLeftCol][entityBottomRow];
                tile2 = gp.Tm.mapTiles[entityRightCol][entityBottomRow];
                if (tile1.collision || tile2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "LEFT":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tile1 = gp.Tm.mapTiles[entityLeftCol][entityTopRow];
                tile2 = gp.Tm.mapTiles[entityLeftCol][entityBottomRow];
                if (tile1.collision || tile2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "RIGHT":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tile1 = gp.Tm.mapTiles[entityRightCol][entityTopRow];
                tile2 = gp.Tm.mapTiles[entityRightCol][entityBottomRow];
                if (tile1.collision || tile2.collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public void checkEntity(Entity entity1, Entity entity2) {
        int entity1LeftX = entity1.x + entity1.solidArea.x;
        int entity1RightX = entity1.x + entity1.solidArea.x + entity1.solidArea.width;
        int entity1TopY = entity1.y + entity1.solidArea.y;
        int entity1BottomY = entity1.y + entity1.solidArea.y + entity1.solidArea.height;
        int entity2LeftX = entity2.x + entity2.solidArea.x;
        int entity2RightX = entity2.x + entity2.solidArea.x + entity2.solidArea.width;
        int entity2TopY = entity2.y + entity2.solidArea.y;
        int entity2BottomY = entity2.y + entity2.solidArea.y + entity2.solidArea.height;

        switch (Directions.getDirectionName(entity1.direction)) {
            case "UP":
                entity1TopY -= entity1.speed;
                if (entity1LeftX < entity2RightX && entity1RightX > entity2LeftX && entity1TopY < entity2BottomY) {
                    entity1.collisionOn = true;
                }
                break;
            case "DOWN":
                entity1BottomY += entity1.speed;
                if (entity1LeftX < entity2RightX && entity1RightX > entity2LeftX && entity1BottomY > entity2TopY) {
                    entity1.collisionOn = true;
                }
                break;
            case "LEFT":
                entity1LeftX -= entity1.speed;
                if (entity1LeftX < entity2RightX && entity1RightX > entity2LeftX && entity1TopY < entity2BottomY) {
                    entity1.collisionOn = true;
                }
                break;
            case "RIGHT":
                entity1RightX += entity1.speed;
                if (entity1LeftX < entity2RightX && entity1RightX > entity2LeftX && entity1TopY < entity2BottomY) {
                    entity1.collisionOn = true;
                }
                break;
        }
    }
}
