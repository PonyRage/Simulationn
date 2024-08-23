package worldRenderer;

import entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityRenderer {
    private final int tileSize;

    public EntityRenderer(int tileSize) {
        this.tileSize = tileSize;
    }

    public void renderEntity(Graphics2D g2s, Entity entity, BufferedImage image) {
        int x = (int) entity.getCoordinates().x * tileSize;
        int y = (int) entity.getCoordinates().y * tileSize;
        g2s.drawImage(image, x, y, tileSize, tileSize, null);
    }
}
