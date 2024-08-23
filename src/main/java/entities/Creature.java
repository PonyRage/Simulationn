package entities;

import coordinates.Coordinates;
import pathfinder.PathFinding;
import world.World;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Creature extends Entity {
    private final int worldWidth;
    private final int worldHeight;
    private final PathFinding pathFinding;

    public Creature(Coordinates coordinates, int worldWidth, int worldHeight) {
        super(coordinates);
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.pathFinding = new PathFinding();
    }

    public List<Coordinates> findPath(Coordinates goal, World world) {
        return pathFinding.findPath((Sheep) this, new Flower(goal), world);
    }

    public void moveToGoal(Coordinates goal, World world) {
        List<Coordinates> path = findPath(goal, world);

        // Если путь найден
        if (!path.isEmpty()) {
            // Двигаемся по пути
            for (Coordinates nextStep : path) {
                if (!isCellOccupied(nextStep.getX(), nextStep.getY(), world.getEntities().values())) {
                    System.out.println("Moving sheep to: " + nextStep.getX() + ", " + nextStep.getY());
                    getCoordinates().setX(nextStep.getX());
                    getCoordinates().setY(nextStep.getY());
                    return; // Прекращаем выполнение после первого шага
                } else {
                    System.out.println("Cell occupied: " + nextStep.getX() + ", " + nextStep.getY());
                }
            }
        } else {
            System.out.println("No path found.");
        }
    }

    protected boolean isCellOccupied(int x, int y, Collection<Entity> entities) {
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
