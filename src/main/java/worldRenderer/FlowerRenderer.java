package worldRenderer;

import entities.Flower;


import java.awt.*;
import java.awt.image.BufferedImage;

public class FlowerRenderer {
    private final int tileSize;
    BufferedImage flowerImage;

    public FlowerRenderer(int tileSize) {
        this.tileSize = tileSize;
        getFlowerImage();
    }

    private void getFlowerImage() {
        flowerImage = ImageCache.getImage("/flower/flower.png");
    }

    public void renderFlower(Graphics2D g2d, Flower flower) {
        int x = flower.getCoordinates().x * tileSize;
        int y =flower.getCoordinates().y * tileSize;

        g2d.drawImage(flowerImage, x, y, tileSize, tileSize,null);

//        g2d.setColor(Color.WHITE);
//        g2d.fillOval(x, y, tileSize, tileSize);
    }
}
