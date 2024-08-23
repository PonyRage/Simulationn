package entities;

import coordinates.Coordinates;

public class Rock extends Entity{

    private final int worldWidth;
    private final int worldHeight;

    public Rock(int worldWidth, int worldHeight) {
        super(new Coordinates(0,0));
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }
}
