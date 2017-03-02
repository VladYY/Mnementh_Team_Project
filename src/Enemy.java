import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy extends DefaultObject implements EnemyEntity{

    private Random rnd = new Random();
    public BufferedImage[] enemy = new BufferedImage[3];
    private SpriteSheet ss;
    private double speedRandom = (this.rnd.nextInt(100) + 1);
    private double speed = 1;
    private Game game;
    private Controller controller;
    private Animation animation;

    public Enemy(double x, double y, Game game, Controller controller) {
        super(x,y);
        this.game = game;
        this.controller = controller;

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

        if (this.speedRandom > 1 && this.speedRandom <41)
        {
            this.speed = 1;
        }
        if (this.speedRandom > 40 && this.speedRandom <81)
        {
            this.speed = 2;
        }
        if (this.speedRandom > 80 && this.speedRandom <96)
        {
            this.speed = 3;
        }
        if (this.speedRandom > 95 && this.speedRandom <100)
        {
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

        if (Physics.Collision(this, this.game.friendlyEN)) {
            try {
                Music.enemyDie();
            } catch (IOException e) {
                e.printStackTrace();
            }
            controller.RemoveEntity(this);
            this.game.setEnemy_killed(this.game.getEnemy_killed() + 1);
        }

        this.animation.runAnimation();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)this.x , (int)this.y, 32, 32);
    }

    public void render(Graphics graphics) {

        this.animation.drawAnimation(graphics, this.x, this.y, 0);
    }
}
