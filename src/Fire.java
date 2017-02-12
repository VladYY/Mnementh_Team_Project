import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fire extends DefaultObject implements FriendlyEntity{

    BufferedImage image;
    private int direction;
    private Game game;

    public Fire(double x, double y, int direction, Game game) throws IOException {
        super(x,y);
        this.direction = direction;
        image = ImageIO.read(getClass().getResourceAsStream("resources/fire.png"));
        this.game = game;
    }

    public void tick() {
        switch (direction) {
            case 1:
                x += 10;
                break;
            case 2:
                x -= 10;
                break;
            case 3:
                y += 10;
                break;
            case 4:
                y -= 10;
                break;
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(image, (int) x, (int) y, null);
    }

    public double getX() {
        return x;
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)x , (int)y, 32, 32);
    }

    public double getY() {
        return y;
    }
}
