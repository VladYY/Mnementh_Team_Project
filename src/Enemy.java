import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy {
    Random r = new Random();

    private double x, y;
    private int speed = r.nextInt(2) + 1;
    private Game game;
    BufferedImage image;

    public Enemy(double x, double y, Game game) {
            this.x = x;
            this.y = y;
            this.game = game;

            try {
                image = ImageIO.read(getClass().getResourceAsStream("resources/vulture.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void tick() {
        int centerX = (this.game.WIDTH * this.game.SCALE) / 2;
        int centerY =( this.game.HEIGHT * this.game.SCALE) / 2;

        if (this.x < centerX && this.y < centerY) {
            this.x += 1;
            this.y += 1;
        } else if (this.x > centerX && this.y < centerY) {
            this.x -= 1;
            this.y += 1;
        } else if (this.y > centerY && this.x > centerX) {
            this.x -= 1;
            this.y -= 1;
        } else if (this.y > centerY && this.x < centerX) {
            this.x += 1;
            this.y -= 1;
        } else if (this.x > centerX) {
            this.x -= 1;
        } else if (this.x < centerX) {
            this.x += 1;
        } else if (this.y < centerY) {
            this.y += 1;
        } else if (this.y > centerY) {
            this.y -= 1;
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void render (Graphics graphics) {
        graphics.drawImage(this.image, (int)this.x, (int)this.y, null);
    }
}
