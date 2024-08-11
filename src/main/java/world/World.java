package world;

import coordinates.Coordinates;
import entities.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class World {
    public int col;
    public int row;
    private int totalSheeps = 10;
    private int totalTrees = 20;
    private int totalRocks = 20;
    private int totalFlowers = 10;
    private int totalOrcs = 10;
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
    List<Coordinates> availableCoordinates = new ArrayList<>();
    for (int x = 0; x < col; x++) {
        for (int y = 0; y < row; y++) {
            availableCoordinates.add(new Coordinates(x, y));
        }
    }

    Collections.shuffle(availableCoordinates);
    for (int i = 0; i < totalRocks; i++) {
        Coordinates coord = availableCoordinates.get(i);
        Rock rock = new Rock(col, row);
        addEntity(coord, rock);
        availableCoordinates.remove(i);
    }// Перемешиваем список
    for (int i = 0; i < totalTrees; i++) {
        Coordinates coord = availableCoordinates.get(i);
        Tree tree = new Tree(col, row);
        addEntity(coord, tree);
        availableCoordinates.remove(i);
    }
    for (int i = 0; i < totalFlowers; i++) {
        Coordinates coord = availableCoordinates.get(i);
        Flower flower = new Flower(col, row);
        addEntity(coord, flower);
        availableCoordinates.remove(i);
    }

    for (int i = 0; i < totalSheeps; i++) {
        Coordinates coord = availableCoordinates.get(i);
        Sheep sheep = new Sheep(col, row);
        addEntity(coord, sheep);
        System.out.println("Sheep initialized at: " + coord.getX() + ", " + coord.getY());
        availableCoordinates.remove(i);
    }

    for (int i = 0; i < totalOrcs; i++) {
        Coordinates coord = availableCoordinates.get(i);
        Orc orc = new Orc(col, row);
        addEntity(coord, orc);
        System.out.println("Orc initialized at: " + coord.getX() + ", " + coord.getY());

    }
}
        public HashMap<Coordinates, Entity> getEntities () {
            return entities;
        }
    }
