package world;

import coordinates.Coordinates;
import entities.Entity;
import entities.Sheep;


import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class World {
    public int col;
    public int row;
    private int totalSheeps = 10;
    HashMap<Coordinates, Entity> entities = new HashMap<>();

    public World(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void setupDefaultEntityPositions() {
        for (int i = 1; i <= totalSheeps; i++) {
            {
                Coordinates randomCoordinates = new Coordinates(ThreadLocalRandom.current().nextInt(col),
                        ThreadLocalRandom.current().nextInt(row));

                Sheep sheep = new Sheep();
                addEntity(randomCoordinates, sheep);
                System.out.println(randomCoordinates.x + ", " + randomCoordinates.y);
            }

        }
    }

        public HashMap<Coordinates, Entity> getEntities () {
            return entities;
        }
    }
