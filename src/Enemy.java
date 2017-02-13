import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy extends DefaultObject implements EnemyEntity{
    Random r = new Random();
    public BufferedImage[] enemy = new BufferedImage[3];
    private SpriteSheet ss;
    private double speed = (r.nextInt(2) + 1);
    private Game game;
    private Controller c;
    Animation animation;

    public Enemy(double x, double y, Game game, Controller c) {
        super(x,y);
        this.game = game;
        this.c = c;

        this.ss = new SpriteSheet(game.getSpriteSheet());
        if (this.x < 600) {
            enemy[0] = ss.grabImage(1, 1, 42, 65);
            enemy[1] = ss.grabImage(1, 2, 42, 65);
            enemy[2] = ss.grabImage(1, 3, 42, 65);
        } else {
            enemy[0] = ss.grabImage(2, 1, 42, 65);
            enemy[1] = ss.grabImage(2, 2, 42, 65);
            enemy[2] = ss.grabImage(2, 3, 42, 65);
        }


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

        if (Physics.Collision(this, game.friendlyEN)) {
            try {
                Music.enemyDie();
            } catch (IOException e) {
                e.printStackTrace();
            }
            c.RemoveEntity(this);
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

    public  Rectangle getBounds() {
        return new Rectangle((int)x , (int)y, 32, 32);
    }

    public void setY(double y) {
        this.y = y;
    }

    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, x, y, 0);
    }
}
