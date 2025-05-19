package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    public BufferedImage image;

    public boolean collision = false;

    protected BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null or a default image if loading fails
        }
    }

    public void breakTile() {};

    public void fixTile() {};
}