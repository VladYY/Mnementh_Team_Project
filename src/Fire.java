import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fire extends DefaultObject implements FriendlyEntity {

    private BufferedImage image;
    private int direction;
    private Game game;
    private Controller controller;

    public Fire(double x, double y, int direction, Game game, Controller controller) throws IOException {
        super(x,y);
        this.direction = direction;
        this.image = ImageIO.read(getClass().getResourceAsStream("resources/gfx/fire.png"));
        this.game = game;
        this.controller = controller;
    }

    public double getX() {
        return super.getX();
    }

    public double getY() {
        return super.getY();
    }

    public void tick() {
        switch (this.direction) {
            case 1:
                super.setX(super.getX() + 10);
                break;
            case 2:
                super.setX(super.getX() - 10);
                break;
            case 3:
                super.setY(super.getY() + 10);
                break;
            case 4:
                super.setY(super.getY() - 10);
                break;
        }

        for (int i = 0; i < this.game.getEnemyEntities().size(); i++) {
            EnemyEntity tempEnt = this.game.getEnemyEntities().get(i);

            if (Physics.Collision(this, tempEnt)){
                try {
                    Music.enemyDie();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.controller.removeEntity(tempEnt);
                this.controller.removeEntity(this);
                this.game.setEnemyKilled(this.game.getEnemyKilled() + 1);
            }
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int)super.getX(), (int)super.getY(), null);
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)super.getX(), (int)super.getY(), 32, 32);
    }
}
