import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    Game game;
    Fire TempFire;
    Enemy enemy;
    Random r = new Random();
    private LinkedList<Fire> f = new LinkedList<>();
    private LinkedList<Enemy> e = new LinkedList<>();

    public Controller(Game game) {

        this.game = game;
    }

    public void createEnemy(int count_enemy) {
        int spawnIndex = 0;
        for (int i = 0; i < count_enemy; i++) {
            if (spawnIndex == 0) {
                //Spawn from up
                addEnemy(new Enemy(r.nextInt(930), 5, this.game));
                spawnIndex++;
            } else if (spawnIndex == 1) {
                //Spawn from left
                addEnemy(new Enemy(5, r.nextInt(720), this.game));
                spawnIndex++;
            } else if (spawnIndex == 2) {
                //Spawn from right
                addEnemy(new Enemy(930, r.nextInt(720), this.game));
                spawnIndex++;
            } else if (spawnIndex == 3) {
                //Spawn from down
                addEnemy(new Enemy(r.nextInt(930), 720, this.game));
                spawnIndex = 0;
            }
        }

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

    public void removeEnemy(Enemy block) {
        e.remove(block);
    }

    public void addFire(Fire block) {
        f.add(block);
    }

    public void removeFire(Fire block) {
        f.remove(block);
    }
}
