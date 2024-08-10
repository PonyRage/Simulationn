package entities;

import coordinates.Coordinates;

public class Sheep extends Creature {

    public Sheep() {
        super(new Coordinates(0, 0));
    }

    public Sheep(Coordinates coordinates) {
        super(coordinates);
    }
}
