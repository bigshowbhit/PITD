package tile;

public class Desert extends Tile {
    public Desert() {
        image = loadImage("/main/res/dessert.png");
        collision = true;
    }
}
