package worldRenderer;

import coordinates.Coordinates;
import entities.Entity;
import entities.Sheep;
import tileManager.TileManager;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WorldRenderer extends JPanel implements Runnable {

    private final World world;
    Thread gameThread;
    int fps = 10;
    public int tileSize = 48;
    TileManager tileManager;
    private final SheepRenderer sheepRenderer;

    public WorldRenderer(World world) {
        this.world = world;

        this.setPreferredSize(new Dimension(world.col * tileSize, world.row * tileSize));
        this.setBackground(Color.YELLOW);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        tileManager = new TileManager(world, this);
        this.sheepRenderer = new SheepRenderer(tileSize);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }



    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        List<Entity> entities = new ArrayList<>(world.getEntities().values());
for (Entity entity : world.getEntities().values()) {
    if (entity instanceof Sheep) {
        Sheep sheep = (Sheep) entity;
        // Добавляем отладочную информацию о текущих координатах овцы
        System.out.println("Updating sheep at: " + sheep.getCoordinates().getX() + ", " + sheep.getCoordinates().getY());
         sheep.moveRandomly(entities);
        // Проверяем координаты после перемещения
        System.out.println("Moved sheep to: " + sheep.getCoordinates().getX() + ", " + sheep.getCoordinates().getY());
    }
}
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.drawTiles(g2d);

        g2d.setColor(Color.RED);
        for (int y = 0; y <= world.row; y++) {
            g2d.drawLine(0, y * tileSize, world.col * tileSize, y * tileSize);
        }
        for (int x = 0; x <= world.col; x++) {
            g2d.drawLine(x * tileSize, 0, x * tileSize, world.row * tileSize);
        }

        for (Entity entity : world.getEntities().values()) {
            if (entity instanceof Sheep) {
                Sheep sheep = (Sheep) entity;
                Coordinates coord = sheep.getCoordinates();
                // Добавляем отладочную информацию для координат овцы
                System.out.println("Rendering sheep at: " + coord.getX() + ", " + coord.getY());
                sheepRenderer.renderSheep(g2d, sheep);
            }
        }
        g2d.dispose();
    }
}
