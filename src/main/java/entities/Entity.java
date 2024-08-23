package entities;


import coordinates.Coordinates;

abstract public class Entity {
    public Coordinates coordinates;
    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
}
