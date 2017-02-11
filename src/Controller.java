import java.awt.*;
import java.util.LinkedList;

public class Controller {

    Game game;
    Fire TempFire;
    Enemy enemy;
    private LinkedList<Fire> f = new LinkedList<>();
    private LinkedList<Enemy> e = new LinkedList<>();

    public Controller(Game game) {

        this.game = game;
    }

    public void createEnemy() {

        addEnemy(new Enemy(100, 270, this.game));
    }

    public void tick() {

        for (int i = 0; i < f.size(); i++) {
            TempFire = f.get(i);

            TempFire.tick();
        }

        for (int i = 0; i < e.size(); i++) {

            enemy = e.get(i);

            enemy.tick();
        }
    }

    public void render(Graphics graphics) {
        for (int i = 0; i < f.size(); i++) {
            TempFire = f.get(i);

            TempFire.render(graphics);
        }

        for (int i = 0; i < e.size(); i++) {
            enemy = e.get(i);

            enemy.render(graphics);
        }
    }


    public void addEnemy(Enemy block) {
        e.add(block);
    }

    public void addFire(Fire block) {
        f.add(block);
    }

    public void removeFire(Fire block) {
        f.remove(block);
    }
}
