import world.World;
import worldRenderer.WorldRenderer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        World world = new World(16, 12);
        WorldRenderer worldRenderer = new WorldRenderer(world);
        world.setupDefaultEntityPositions();


        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Simulationn");
window.add(worldRenderer);
window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


        worldRenderer.startGameThread();
        int a = 123;
    }
}
