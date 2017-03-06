import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends DefaultObject implements EnemyEntity {

    private Random rnd;
    private BufferedImage[] enemyImages;
    private SpriteSheet spriteSheet;
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
        this.enemyImages = new BufferedImage[3];
        this.speed = 1;
        this.speedRandom = (this.rnd.nextInt(100) + 1);
        this.spriteSheet = new SpriteSheet(this.game.getSpriteSheetGorgon());

        if (this.x < 600) {
            this.enemyImages[0] = this.spriteSheet.grabImage(1, 1, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 1, 42, 65);
            this.enemyImages[2] = spriteSheet.grabImage(3, 1, 42, 65);
        } else {
            this.enemyImages[0] = this.spriteSheet.grabImage(1, 2, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 2, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 2, 42, 65);
        }

        this.animation = new Animation(5, this.enemyImages[0], this.enemyImages[1], this.enemyImages[2]);
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
                this.controller.removeEntity(this);
                int playerHealth = this.game.getPlayer1().getHealth();
                this.game.getPlayer1().setHealth(playerHealth - 20);
            }
        }

        this.animation.runAnimation();
    }

    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, this.x, this.y, 0);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.x , (int)this.y, 42, 65);
    }
}
