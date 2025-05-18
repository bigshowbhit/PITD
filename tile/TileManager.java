package tile;

import main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public int[][] mapTileNum;
    public Tile[][] mapTiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        mapTiles = new Tile[gp.maxScreenCol][gp.maxScreenRow];
        loadMap();
    }

    /*
    public void getTileImage()
    {
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/main/res/dessert.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/main/res/pipe.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/main/res/pump.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/main/res/cistern.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/main/res/brokenpipe.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/main/res/brokenpump.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/main/res/reservoir.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/main/res/spring.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    */

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/main/res/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while (col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    switch(num) {
                        case 0:
                            mapTiles[col][row] = new Desert();
                            break;
                        case 1:
                            mapTiles[col][row] = new Pipe();
                            break;
                        case 2:
                            mapTiles[col][row] = new Pump();
                            break;
                        case 3:
                            mapTiles[col][row] = new Cistern();
                            break;
                        case 6:
                            mapTiles[col][row] = new Reservoir();
                            break;
                        case 7:
                            mapTiles[col][row] = new Spring();
                            break;
                    }

                    col++;
                }

                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2)
    {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            // Draw the tile
            g2.drawImage(mapTiles[col][row].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

}
