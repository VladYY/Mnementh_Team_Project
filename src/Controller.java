import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    Game game;
    Random r = new Random();

    private LinkedList<Entity> e = new LinkedList<Entity>();

    Entity ent;

    public Controller(Game game) {

        this.game = game;
    }

    public void createEnemy(int count_enemy) {
        int spawnIndex = 0;
        for (int i = 0; i < count_enemy; i++) {
             if (spawnIndex == 0) {
                //Spawn from left
                addEntity(new Enemy(5, r.nextInt(800-500)+500, this.game));
                spawnIndex++;
            } else if (spawnIndex == 1) {
                //Spawn from right
                addEntity(new Enemy(1200, r.nextInt(800-500)+500, this.game));
                spawnIndex++;
            } else if (spawnIndex == 2) {
                //Spawn from down
                addEntity(new Enemy(r.nextInt(1200), 800, this.game));
                spawnIndex = 0;
            }
        }

    }

    public void tick() {
        for (int i = 0; i < e.size(); i++) {
            ent = e.get(i);

            ent.tick();
        }
    }

    public void render(Graphics graphics) {
        for (int i = 0; i < e.size(); i++) {
            ent = e.get(i);

            ent.render(graphics);
        }
    }

    public void addEntity(Entity block) {
        e.add(block);
    }

    public void RemoveEntity(Entity block) {
        e.remove(block);
    }


}
