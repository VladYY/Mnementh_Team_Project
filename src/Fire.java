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

    public void tick() {
        switch (this.direction) {
            case 1:
                this.x += 10;
                break;
            case 2:
                this.x -= 10;
                break;
            case 3:
                this.y += 10;
                break;
            case 4:
                this.y -= 10;
                break;
        }

        for (int i = 0; i < this.game.enemyEN.size(); i++) {
            EnemyEntity tempEnt = this.game.enemyEN.get(i);
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
        graphics.drawImage(this.image, (int)this.x, (int)this.y, null);
    }

    public double getX() {
        return this.x;
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)this.x, (int)this.y, 32, 32);
    }

    public double getY() {
        return this.y;
    }
}
