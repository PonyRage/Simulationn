package entities;

import coordinates.Coordinates;


import java.util.List;
import java.util.Random;

public class Sheep extends Creature {
    private static final Random RANDOM = new Random();
    private final int worldWidth;
    private final int worldHeight;

    public Sheep(int worldWidth, int worldHeight) {
        super(new Coordinates(0, 0));
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    public Sheep(Coordinates coordinates, int worldWidth, int worldHeight) {
        super(coordinates);
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    private boolean isCellOccupied(int x, int y, List<Entity> entities) {
        for (Entity entity : entities) {
            if (entity.getCoordinates().getX() == x && entity.getCoordinates().getY() == y) {
                return true;
            }
        }
        return false;
    }
        public void moveRandomly (List<Entity> entities) {
            int currentX = getCoordinates().getX();
            int currentY = getCoordinates().getY();

            // Определяем возможные направления движения
            int direction = RANDOM.nextInt(4);

            int newX = currentX;
            int newY = currentY;

            switch (direction) {
                case 0: // Вверх
                    newY = Math.max(currentY - 1, 0);
                    break;
                case 1: // Вправо
                    newX = Math.min(currentX + 1, worldWidth - 1);
                    break;
                case 2: // Вниз
                    newY = Math.min(currentY + 1, worldHeight - 1);
                    break;
                case 3: // Влево
                    newX = Math.max(currentX - 1, 0);
                    break;
            }

            if (!isCellOccupied(newX, newY, entities)) {
                // Обновляем координаты овцы
                getCoordinates().setX(newX);
                getCoordinates().setY(newY);
            }

        }
    }
