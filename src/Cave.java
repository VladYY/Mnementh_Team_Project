import java.awt.*;
import java.awt.image.BufferedImage;

public class Cave extends DefaultObject implements CaveEntity{


    private BufferedImage cave;
    private Game game;
    private Controller controller;

    public Cave(int x, int y, Game game, Controller controller) {
        super(x,y);
        this.game = game;
        controller.addEntity(this);
        this.controller = controller;
    }

    @Override
    public void tick() {
        if (Physics.CaveAttacked(this, game.enemyEN)) {
            PlayerHealth.hp -= 10;
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(cave, (int)x, (int)y, null);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x , (int)y, 102, 102);
    }
}
