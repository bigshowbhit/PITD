package tile;

public class Pump extends Tile {

    public boolean broken;

    public Pump() {
        image = loadImage("/main/res/pump.png");
        broken = false;
    }

    @Override
    public void breakTile() {
        broken = true;
        image = loadImage("/main/res/brokenpump.png");
    }

    @Override
    public void fixTile() {
        broken = false;
        image = loadImage("/main/res/pump.png");
    }
}