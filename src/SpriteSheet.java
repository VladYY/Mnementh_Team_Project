import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * 42) - 42, (row * 65) - 65, width, height);
        return img;
    }

    public BufferedImage grabDragonImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * 72) - 72, (row * 72) - 72, width, height);
        return img;
    }
}
