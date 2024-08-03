import coordinates.Coordinates;
import entities.Entity;

import java.util.HashMap;

public class World {

    HashMap<Coordinates, Entity> entities= new HashMap<>();

    public void addEntity(Coordinates coordinates, Entity entity ) {
        entity.coordinates=coordinates;
        entities.put(coordinates, entity);
    }

    public void setupDefaultEntityPositions() {

    }
}
