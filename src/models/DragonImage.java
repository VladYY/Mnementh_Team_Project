package models;

import java.awt.image.BufferedImage;

public class DragonImage {

    private BufferedImage image;

    public DragonImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage() {
        return this.image;
    }
}
