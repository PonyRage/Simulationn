package worldRenderer;

import entities.Orc;


import java.awt.*;
import java.awt.image.BufferedImage;

public class OrcRenderer {
    private final int tileSize;
    BufferedImage orcImage;

    public OrcRenderer(int tileSize) {
        this.tileSize = tileSize;
        getOrcImage();
    }

    private void getOrcImage() {
        orcImage =ImageCache.getImage("/orcs/orc1.png");
    }

    public void renderOrc(Graphics2D g2d, Orc orc) {
        int x = orc.getCoordinates().x * tileSize;
        int y = orc.getCoordinates().y * tileSize;

        g2d.drawImage(orcImage, x, y, tileSize, tileSize,null);

//        g2d.setColor(Color.WHITE);
//        g2d.fillOval(x, y, tileSize, tileSize);
    }
}
