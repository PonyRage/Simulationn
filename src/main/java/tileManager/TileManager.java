package tileManager;

import world.World;
import worldRenderer.WorldRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class TileManager {
    World world;
    WorldRenderer worldRenderer;
    Tile[] tiles;

    public TileManager(World world, WorldRenderer worldRenderer) {
        this.world = world;
        this.worldRenderer = worldRenderer;
        tiles = new Tile[world.col * world.row];
        getTileImage();
    }

    public void getTileImage() {
        for (int i = 0; i < tiles.length; i++) {
            try {
                int randomTile = ThreadLocalRandom.current().nextInt(1, 65);
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + randomTile + ".png"));
                tiles[i].resizeImage(worldRenderer.tileSize, worldRenderer.tileSize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void drawTiles(Graphics2D g2d) {
        int index = 0;
        for (int row = 0; row < world.row; row++) {
            for (int col = 0; col < world.col; col++) {
                if (index < tiles.length && tiles[index] != null && tiles[index].image != null) {
                    g2d.drawImage(tiles[index].image, col * worldRenderer.tileSize,
                            row * worldRenderer.tileSize, worldRenderer.tileSize, worldRenderer.tileSize,null);
                }
                index++;
            }
        }
    }
}
