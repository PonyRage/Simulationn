package tileManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;

    public void resizeImage(int width, int height) {
        if (image == null) {
           BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(image, 0, 0, width, height, null);
            g2d.dispose();
            image = resizedImage;
        }
    }
}
