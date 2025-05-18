package main;

import entity.Entity;

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

        int tileNum1, tileNum2;

        switch(Directions.getDirectionName(entity.direction)) {
            case "UP":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.Tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.Tm.mapTileNum[entityRightCol][entityTopRow];
                if(gp.Tm.tile[tileNum1].collision || gp.Tm.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "DOWN":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.Tm.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.Tm.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.Tm.tile[tileNum1].collision || gp.Tm.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "LEFT":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.Tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.Tm.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.Tm.tile[tileNum1].collision || gp.Tm.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "RIGHT":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.Tm.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.Tm.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.Tm.tile[tileNum1].collision || gp.Tm.tile[tileNum2].collision)
                {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
