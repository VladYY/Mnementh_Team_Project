import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends DefaultObject implements FriendlyEntity{
    private double velX = 0;
    private double velY = 0;
    private Game game;
    private Controller c;
    private BufferedImage player;

    public Player(double x, double y, Game game, Controller c) {
        super(x,y);

        DragonImage dragonImage = new DragonImage(game.getDragonImage());

        player = dragonImage.grabImage();
        this.game = game;
        c.addEntity(this);
        this.c = c;
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
                //TODO
                System.exit(1);
            }
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(player, (int) x, (int) y, null);
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
