import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy extends DefaultObject implements EnemyEntity {

    private Random rnd;
    private BufferedImage[] enemy;
    private SpriteSheet ss;
    private double speedRandom;
    private double speed;
    private Game game;
    private Controller controller;
    private Animation animation;

    public Enemy(double x, double y, Game game, Controller controller) {
        super(x,y);
        this.game = game;
        this.controller = controller;

        this.rnd = new Random();
        this.enemy = new BufferedImage[3];
        this.speed = 1;
        this.speedRandom = (this.rnd.nextInt(100) + 1);
        this.ss = new SpriteSheet(this.game.getSpriteSheetGorgon());

        if (this.x < 600) {
            this.enemy[0] = ss.grabImage(1, 1, 42, 65);
            this.enemy[1] = ss.grabImage(2, 1, 42, 65);
            this.enemy[2] = ss.grabImage(3, 1, 42, 65);
        } else {
            this.enemy[0] = ss.grabImage(1, 2, 42, 65);
            this.enemy[1] = ss.grabImage(2, 2, 42, 65);
            this.enemy[2] = ss.grabImage(3, 2, 42, 65);
        }

        this.animation = new Animation(5, this.enemy[0], this.enemy[1], this.enemy[2]);
    }

    public void tick() {

        if (this.speedRandom > 1 && this.speedRandom <41) {
            this.speed = 1;
        }

        if (this.speedRandom > 40 && this.speedRandom <81) {
            this.speed = 2;
        }

        if (this.speedRandom > 80 && this.speedRandom <96) {
            this.speed = 3;
        }

        if (this.speedRandom > 95 && this.speedRandom <100) {
            this.speed = 4;
        }

        int caveX = 600;
        int caveY = 120;

        if (this.x < caveX && this.y < caveY) {
            this.x += this.speed;
            this.y += this.speed;
        } else if (this.x > caveX && this.y < caveY) {
            this.x -= this.speed;
            this.y += this.speed;
        } else if (this.y > caveY && this.x > caveX) {
            this.x -= this.speed;
            this.y -= this.speed;
        } else if (this.y > caveY && this.x < caveX) {
            this.x += this.speed;
            this.y -= this.speed;
        } else if (this.x > caveX) {
            this.x -= this.speed;
        } else if (this.x < caveX) {
            this.x += this.speed;
        } else if (this.y < caveY) {
            this.y += this.speed;
        } else if (this.y > caveY) {
            this.y -= this.speed;
        }

        if (this.x >= 550 && this.x <= 652) {
            if(this.y >= 70 && this.y <= 172) {
                this.game.enemyEN.remove(this);
                int playerHealth = this.game.getPlayer1().getHealth();
                this.game.getPlayer1().setHealth(playerHealth - 20);
            }
        }

        this.animation.runAnimation();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.x , (int)this.y, 42, 65);
    }

    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, this.x, this.y, 0);
    }
}
