
import java.awt.image.BufferedImage;


public class BattlegroundImage {

    private BufferedImage image;

    public BattlegroundImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage() {
        BufferedImage img = image;
        return img;
    }
}
