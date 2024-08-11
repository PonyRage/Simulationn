package worldRenderer;

import coordinates.Coordinates;
import entities.*;
import tileManager.TileManager;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class WorldRenderer extends JPanel implements Runnable, KeyListener {

    private final World world;
    Thread gameThread;
    int fps = 1;
    public int tileSize = 48;
    TileManager tileManager;
    private final SheepRenderer sheepRenderer;
    private final TreeRenderer treeRenderer;
    private final RockRenderer rockRenderer;
    private final FlowerRenderer flowerRenderer;
    private final OrcRenderer orcRenderer;
    private boolean isPaused = false;

    public WorldRenderer(World world) {
        this.world = world;

        this.setPreferredSize(new Dimension(world.col * tileSize, world.row * tileSize));
        this.setBackground(Color.YELLOW);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        tileManager = new TileManager(world, this);
        this.sheepRenderer = new SheepRenderer(tileSize);
        this.treeRenderer = new TreeRenderer(tileSize);
        this.rockRenderer = new RockRenderer(tileSize);
        this.flowerRenderer = new FlowerRenderer(tileSize);
        this.orcRenderer = new OrcRenderer(tileSize);
        this.addKeyListener(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private synchronized void togglePause() {
        isPaused = !isPaused;
        if (!isPaused) {
            notify();
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
            synchronized (this) {
                while (isPaused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
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
            if (entity instanceof Tree) {
                Tree tree = (Tree) entity;
            }
            if (entity instanceof Rock) {
                Rock rock = (Rock) entity;
            }
            if (entity instanceof Flower) {
                Flower flower = (Flower) entity;
            }
            if (entity instanceof Sheep) {
                Sheep sheep = (Sheep) entity;
                // Добавляем отладочную информацию о текущих координатах овцы
                System.out.println("Updating sheep at: " + sheep.getCoordinates().getX() + ", " + sheep.getCoordinates().getY());
                sheep.moveRandomly(entities);
                // Проверяем координаты после перемещения
                System.out.println("Moved sheep to: " + sheep.getCoordinates().getX() + ", " + sheep.getCoordinates().getY());
            }
            if (entity instanceof Orc) {
                Orc orc = (Orc) entity;
                // Добавляем отладочную информацию о текущих координатах овцы
                System.out.println("Updating orc at: " + orc.getCoordinates().getX() + ", " + orc.getCoordinates().getY());
                orc.moveRandomly(entities);
                // Проверяем координаты после перемещения
                System.out.println("Moved orc at: " + orc.getCoordinates().getX() + ", " + orc.getCoordinates().getY());
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.drawTiles(g2d);


        for (Entity entity : world.getEntities().values()) {

            if (entity instanceof Tree) {
                Tree tree = (Tree) entity;
                treeRenderer.renderTree(g2d, tree);
            }
            if (entity instanceof Rock) {
                Rock rock = (Rock) entity;
                rockRenderer.renderRock(g2d, rock);
            }
            if (entity instanceof Flower) {
                Flower flower = (Flower) entity;
                flowerRenderer.renderFlower(g2d, flower);
            }
            if (entity instanceof Sheep) {
                Sheep sheep = (Sheep) entity;
                Coordinates coord = sheep.getCoordinates();
                // Добавляем отладочную информацию для координат овцы
                System.out.println("Rendering sheep at: " + coord.getX() + ", " + coord.getY());
                sheepRenderer.renderSheep(g2d, sheep);
            }
            if (entity instanceof Orc) {
                Orc orc = (Orc) entity;
                Coordinates coord = orc.getCoordinates();
                // Добавляем отладочную информацию для координат овцы
                System.out.println("Rendering sheep at: " + coord.getX() + ", " + coord.getY());
                orcRenderer.renderOrc(g2d, orc);
            }
        }
        g2d.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
