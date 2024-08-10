package worldRenderer;

import entities.Sheep;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SheepRenderer {

    private final int tileSize;
    BufferedImage sheepImage;

    public SheepRenderer(int tileSize) {
        this.tileSize = tileSize;
getSheepImage();
    }

    private void getSheepImage() {
        sheepImage =ImageCache.getImage("/sheeps/run.png");
    }

    public void renderSheep(Graphics2D g2d, Sheep sheep) {
int x = sheep.getCoordinates().x * tileSize;
int y = sheep.getCoordinates().y * tileSize;

g2d.drawImage(sheepImage, x, y, tileSize, tileSize,null);

//        g2d.setColor(Color.WHITE);
//        g2d.fillOval(x, y, tileSize, tileSize);
    }
}
