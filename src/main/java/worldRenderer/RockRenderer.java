package worldRenderer;

import entities.Rock;


import java.awt.*;
import java.awt.image.BufferedImage;

public class RockRenderer {
    private final int tileSize;
    BufferedImage rockImage;

    public RockRenderer(int tileSize) {
        this.tileSize = tileSize;
        getRockImage();
    }

    private void getRockImage() {
        rockImage = ImageCache.getImage("/rock/rock.png");
    }

    public void renderRock(Graphics2D g2d, Rock rock) {
        int x = rock.getCoordinates().x * tileSize;
        int y = rock.getCoordinates().y * tileSize;

        g2d.drawImage(rockImage, x, y, tileSize, tileSize,null);

//        g2d.setColor(Color.WHITE);
//        g2d.fillOval(x, y, tileSize, tileSize);
    }
}
