package tile;

public class Pipe extends Tile {

    public boolean broken;

    public Pipe() {
        image = loadImage("/main/res/pipe.png");
        broken = false;
    }

    @Override
    public void breakTile() {
        broken = true;
        image = loadImage("/main/res/brokenpipe.png");
    }

    @Override
    public void fixTile() {
        broken = false;
        image = loadImage("/main/res/pipe.png");
    }
}
