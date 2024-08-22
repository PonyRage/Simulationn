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
    private int totalOrcs = 5;
    HashMap<Coordinates, Entity> entities = new HashMap<>();
    public List<Tree> trees = new ArrayList<>();
    public List<Rock> rocks = new ArrayList<>();
    public List<Sheep> sheeps = new ArrayList<>();
    public List<Flower> flowers = new ArrayList<>();
    public List<Orc> orcs = new ArrayList<>();

    public World(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);

        if (entity instanceof Rock) {
            rocks.add((Rock) entity);
        } else if (entity instanceof Tree) {
            trees.add((Tree) entity);
        } else if (entity instanceof Flower) {
            flowers.add((Flower) entity);
        } else if (entity instanceof Orc) {
            orcs.add((Orc) entity);
        } else if (entity instanceof Sheep) {
            sheeps.add((Sheep) entity);
        }
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
            Coordinates coord = availableCoordinates.remove(0);
            addEntity(coord, new Rock(col, row));
//            Coordinates coord = availableCoordinates.get(i);
//            Rock rock = new Rock(col, row);
//            addEntity(coord, rock);
//            availableCoordinates.remove(i);
        }// Перемешиваем список
        for (int i = 0; i < totalTrees; i++) {
            Coordinates coord = availableCoordinates.remove(0);
            addEntity(coord, new Tree(col, row));
        }
        for (int i = 0; i < totalFlowers; i++) {
            Coordinates coord = availableCoordinates.remove(0);
            addEntity(coord, new Flower(col, row));
        }

        for (int i = 0; i < totalSheeps; i++) {
            Coordinates coord = availableCoordinates.remove(0);
           addEntity(coord, new Sheep(col, row));
        }

        for (int i = 0; i < totalOrcs; i++) {
            Coordinates coord = availableCoordinates.remove(0);
            Orc orc = new Orc(col, row);
            addEntity(coord, orc);
        }
    }

    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }
}
