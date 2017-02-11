import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy implements Entity{
    Random r = new Random();
    public BufferedImage[] enemy = new BufferedImage[3];
    private SpriteSheet ss;
    private double x, y;
    private int speed = r.nextInt(2) + 1;
    private Game game;
    Animation animation;

    public Enemy(double x, double y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;

        this.ss = new SpriteSheet(game.getSpriteSheet());

        enemy[0] = ss.grabImage(3, 1, 32, 32);
        enemy[1] = ss.grabImage(3, 2, 32, 32);
        enemy[2] = ss.grabImage(3, 3, 32, 32);

        this.animation = new Animation(5, this.enemy[0], this.enemy[1], this.enemy[2]);
    }

    public void tick() {
        int centerX = 600;
        int centerY = 120;

        if (this.x < centerX && this.y < centerY) {
            this.x += speed;
            this.y += speed;
        } else if (this.x > centerX && this.y < centerY) {
            this.x -= speed;
            this.y += speed;
        } else if (this.y > centerY && this.x > centerX) {
            this.x -= speed;
            this.y -= speed;
        } else if (this.y > centerY && this.x < centerX) {
            this.x += speed;
            this.y -= speed;
        } else if (this.x > centerX) {
            this.x -= speed;
        } else if (this.x < centerX) {
            this.x += speed;
        } else if (this.y < centerY) {
            this.y += speed;
        } else if (this.y > centerY) {
            this.y -= speed;
        }

        this.animation.runAnimation();
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

    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, x, y, 0);
        //graphics.drawImage(this.image, (int) this.x, (int) this.y, null);
    }
}
