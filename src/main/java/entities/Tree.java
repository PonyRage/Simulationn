package entities;

import coordinates.Coordinates;



public class Tree extends Entity{
    private final int worldWidth;
    private final int worldHeight;

    public Tree(int worldWidth, int worldHeight) {
        super(new Coordinates(0,0));
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }


}
