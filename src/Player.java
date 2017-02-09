import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    private double x, y;
    private double velX = 0;
    private double velY = 0;

    private BufferedImage player;

    public Player(double x, double y, Game game) {
        this.x = x;
        this.y = y;

        DragonImage dragonImage = new DragonImage(game.getDragonImage());

        player = dragonImage.grabImage();
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(player, (int) x, (int) y, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

}
