package worldRenderer;

import coordinates.Coordinates;
import entities.Entity;
import world.World;

import javax.swing.*;
import java.awt.*;

public class WorldRenderer extends JPanel implements Runnable{

    private final World world;
    Thread gameThread;
    int fps = 60;
    int tileSize = 32;

    public WorldRenderer(World world) {
        this.world = world;

        this.setPreferredSize(new Dimension(world.col * tileSize, world.row * tileSize));
        this.setBackground(Color.YELLOW);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void render() {

for(Coordinates coord : world.getEntities().keySet())
{
    Entity entity = world.getEntities().get(coord);
    System.out.println("Entity " + entity.getClass().getSimpleName() + " at " + coord.x + ", " + coord.y);
}
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

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < world.row; i++) {
            for (int j = 0; j < world.col; j++) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
        render();
        g2d.dispose();
    }
}
