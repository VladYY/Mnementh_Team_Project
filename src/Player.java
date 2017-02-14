import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DefaultObject implements FriendlyEntity{
    private double velX = 0;
    private double velY = 0;
    private Game game;
    private Controller c;

    private SpriteSheet ss;
    private BufferedImage[] dragon = new BufferedImage[8];
    Animation animation;

    public Player(double x, double y, Game game, Controller c) {
        super(x,y);

        this.game = game;
        c.addEntity(this);
        this.c = c;
        this.ss = new SpriteSheet(game.getSpriteSheetDragon());
        this.dragon[0] = this.ss.grabDragonImage(1, 1, 72, 72);
        this.dragon[1] = this.ss.grabDragonImage(1, 2, 72, 72);
        this.dragon[2] = this.ss.grabDragonImage(1, 3, 72, 72);
        this.dragon[3] = this.ss.grabDragonImage(1, 4, 72, 72);
        this.dragon[4] = this.ss.grabDragonImage(1, 5, 72, 72);
        this.dragon[5] = this.ss.grabDragonImage(1, 6, 72, 72);
        this.dragon[6] = this.ss.grabDragonImage(1, 7, 72, 72);
        this.dragon[7] = this.ss.grabDragonImage(1, 8, 72, 72);

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
        x += velX;
        y += velY;

        if (x <= 0) {
            x = 0;
        }
        if (x >= Game.WIDTH * 2 - 50) {
            x = (Game.WIDTH * 2) - 50;
        }
        if (y <= 0) {
            y = 0;
        }
        if (y >= Game.HEIGHT * 2 - 50) {
            y = Game.HEIGHT * 2 - 50;
        }

        if (Physics.Collision(this, game.enemyEN)) {
            PlayerHealth.hp -= 10;
            if (PlayerHealth.hp <= 0)
            {
                Game.State = Game.STATE.END;
            }
        }

        this.animation.runAnimation();
    }

    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, x, y, 0);
        //graphics.drawImage(player, (int) x, (int) y, null);
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

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)x , (int)y, 32, 32);
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

}
