package entities;

import coordinates.Coordinates;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Creature extends Entity {
    private final int worldWidth;
    private final int worldHeight;

    public Creature(Coordinates coordinates, int worldWidth, int worldHeight) {
        super(coordinates);
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    protected boolean isCellOccupied(int x, int y, List<Entity> entities) {
        for (Entity entity : entities) {
            if (entity.getCoordinates().getX() == x && entity.getCoordinates().getY() == y) {
                return true;
            }
        }
        return false;
    }

    public void moveRandomly(List<Entity> entities) {
        int currentX = getCoordinates().getX();
        int currentY = getCoordinates().getY();
        int direction = ThreadLocalRandom.current().nextInt(4);
        int newX = currentX;
        int newY = currentY;

        switch (direction) {
            case 0 -> newY = Math.max(currentY - 1, 0);
            case 1 -> newX = Math.min(currentX + 1, worldWidth - 1);
            case 2 -> newY = Math.min(currentY + 1, worldHeight - 1);
            case 3 -> newX = Math.max(currentX - 1, 0);
        }

        if (!isCellOccupied(newX, newY, entities)) {
            getCoordinates().setX(newX);
            getCoordinates().setY(newY);

        }
    }
}
