package world;

import coordinates.Coordinates;
import entities.Entity;
import entities.Sheep;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class World {
    public int col;
    public int row;
    private int totalSheeps = 100;
    HashMap<Coordinates, Entity> entities = new HashMap<>();

    public World(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

//    public void setupDefaultEntityPositions() {
//        for (int i = 1; i <= totalSheeps; i++) {
//            {
//                Coordinates randomCoordinates = new Coordinates(ThreadLocalRandom.current().nextInt(col),
//                        ThreadLocalRandom.current().nextInt(row));
//
//                Sheep sheep = new Sheep();
//                addEntity(randomCoordinates, sheep);
//                System.out.println(randomCoordinates.x + ", " + randomCoordinates.y);
//            }
//
//        }
//    }
public void setupDefaultEntityPositions() {
    List<Coordinates> availableCoordinates = new ArrayList<>();
    for (int x = 0; x < col; x++) {
        for (int y = 0; y < row; y++) {
            availableCoordinates.add(new Coordinates(x, y));
        }
    }

    Collections.shuffle(availableCoordinates); // Перемешиваем список

    for (int i = 0; i < totalSheeps; i++) {
        Coordinates coord = availableCoordinates.get(i);
        Sheep sheep = new Sheep(col, row);
        addEntity(coord, sheep);
        System.out.println("Sheep initialized at: " + coord.getX() + ", " + coord.getY());
    }
}
        public HashMap<Coordinates, Entity> getEntities () {
            return entities;
        }
    }
