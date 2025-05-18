package main;

public class Directions {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static String getDirectionName(int direction) {
        return switch (direction) {
            case UP -> "UP";
            case DOWN -> "DOWN";
            case LEFT -> "LEFT";
            case RIGHT -> "RIGHT";
            default -> "UNKNOWN";
        };
    }
}
