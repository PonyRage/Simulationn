package worldRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class ImageCache {
    private static HashMap<String, BufferedImage> imageCache = new HashMap<>();

    public static BufferedImage getImage(String path) {
        BufferedImage image = imageCache.get(path);
        if (image == null) {
            try {
                image = ImageIO.read(ImageCache.class.getResource(path));
                imageCache.put(path, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}
