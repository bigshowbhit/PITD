package entity;

import main.Directions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Entity {
    public int x, y;
    public int speed;
    public int direction = Directions.UP;
    public Rectangle solidArea;
    public boolean collisionOn  = false;
    public BufferedImage image;
}
