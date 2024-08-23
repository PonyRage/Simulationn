package worldRenderer;

import entities.Tree;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TreeRenderer {
    private final int tileSize;
    BufferedImage treeImage;

    public TreeRenderer(int tileSize) {
        this.tileSize = tileSize;
        getTreeImage();
    }

    private void getTreeImage() {
        treeImage = ImageCache.getImage("/tree/tree.png");
    }

    public void renderTree(Graphics2D g2d, Tree tree) {
        int x = tree.getCoordinates().x * tileSize;
        int y = tree.getCoordinates().y * tileSize;

        g2d.drawImage(treeImage, x, y, tileSize, tileSize,null);

//        g2d.setColor(Color.WHITE);
//        g2d.fillOval(x, y, tileSize, tileSize);
    }
}
