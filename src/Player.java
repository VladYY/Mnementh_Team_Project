import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DefaultObject implements FriendlyEntity {

    private double velX = 0;
    private double velY = 0;
    public int health;
    private Game game;
    private String direction = "right";
    private Controller controller;
    private boolean directionChanged = false;

    private SpriteSheet ss;
    private BufferedImage[] dragon = new BufferedImage[16];
    Animation animation;

    public Player(double x, double y, Game game, Controller controller, int health) {
        super(x,y);

        this.health = health;
        this.game = game;
        this.controller = controller;
        this.controller.addEntity(this);
        this.ss = new SpriteSheet(game.getSpriteSheetDragon());
        this.dragon[0] = this.ss.grabDragonImage(1, 1, 72, 72);
        this.dragon[1] = this.ss.grabDragonImage(2, 1, 72, 72);
        this.dragon[2] = this.ss.grabDragonImage(3, 1, 72, 72);
        this.dragon[3] = this.ss.grabDragonImage(4, 1, 72, 72);
        this.dragon[4] = this.ss.grabDragonImage(5, 1, 72, 72);
        this.dragon[5] = this.ss.grabDragonImage(6, 1, 72, 72);
        this.dragon[6] = this.ss.grabDragonImage(7, 1, 72, 72);
        this.dragon[7] = this.ss.grabDragonImage(8, 1, 72, 72);

        this.dragon[8] = this.ss.grabDragonImage(1, 2, 72, 72);
        this.dragon[9] = this.ss.grabDragonImage(2, 2, 72, 72);
        this.dragon[10] = this.ss.grabDragonImage(3, 2, 72, 72);
        this.dragon[11] = this.ss.grabDragonImage(4, 2, 72, 72);
        this.dragon[12] = this.ss.grabDragonImage(5, 2, 72, 72);
        this.dragon[13] = this.ss.grabDragonImage(6, 2, 72, 72);
        this.dragon[14] = this.ss.grabDragonImage(7, 2, 72, 72);
        this.dragon[15] = this.ss.grabDragonImage(8, 2, 72, 72);

        this.animation = new Animation(5,
                this.dragon[0],
                this.dragon[1],
                this.dragon[2],
                this.dragon[3],
                this.dragon[4],
                this.dragon[5],
                this.dragon[6],
                this.dragon[7]);
    }

    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        if (this.x <= 0) {
            this.x = 0;
        }
        if (this.x >= Game.WIDTH * 2 - 50) {
            this.x = (Game.WIDTH * 2) - 50;
        }
        if (this.y <= 0) {
            this.y = 0;
        }
        if (this.y >= Game.HEIGHT * 2 - 50) {
            this.y = Game.HEIGHT * 2 - 50;
        }

        if (this.health <= 0) {
            Game.State = Game.STATE.END;
        }

        if (Physics.Collision(this, this.game.enemyEN)) {
            this.health -= 10;
            if (this.health <= 0) {
                Game.State = Game.STATE.END;
            }
        }

        if (this.directionChanged && this.direction.equals("left")) {
            this.animation = new Animation(5,
                    this.dragon[8],
                    this.dragon[9],
                    this.dragon[10],
                    this.dragon[11],
                    this.dragon[12],
                    this.dragon[13],
                    this.dragon[14],
                    this.dragon[15]);

            this.directionChanged = false;
        } else if (this.directionChanged && this.direction.equals("right")) {
            this.animation = new Animation(5,
                    this.dragon[0],
                    this.dragon[1],
                    this.dragon[2],
                    this.dragon[3],
                    this.dragon[4],
                    this.dragon[5],
                    this.dragon[6],
                    this.dragon[7]);

            this.directionChanged = false;
        }

        this.animation.runAnimation();
    }

    public boolean isDirectionChanged() {

        return this.directionChanged;
    }

    public void setDirectionChanged(boolean directionChanged) {
        this.directionChanged = directionChanged;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, this.x, this.y, 0);
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)this.x , (int)this.y, 72, 72);
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
