import java.awt.image.BufferedImage;

public class CaveImage {

    private BufferedImage image;

    public CaveImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage() {
        BufferedImage img = image;
        return img;
    }
    }

