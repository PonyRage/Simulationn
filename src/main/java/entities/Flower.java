package entities;

import coordinates.Coordinates;

public class Flower extends Entity{
    private final int worldWidth;
    private final int worldHeight;

    public Flower(int worldWidth, int worldHeight) {
        super(new Coordinates(0,0));
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    public Flower(Coordinates coordinates) {
        super(coordinates);
        this.worldWidth = 0; // Или другое значение, если это необходимо
        this.worldHeight = 0; // Или другое значение, если это необходимо
    }
}
