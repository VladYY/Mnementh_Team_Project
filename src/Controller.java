import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    Game game;
    Random r = new Random();

    private LinkedList<FriendlyEntity> friendlyEn = new LinkedList<>(); //ea
    private LinkedList<EnemyEntity> enemyEn = new LinkedList<>();       //eb
    private LinkedList<CaveEntity> caveEn = new LinkedList<>();       //eb

    FriendlyEntity entFR;
    EnemyEntity entEN;

    public LinkedList<EnemyEntity> getEnemyEn() {
        return enemyEn;
    }

    CaveEntity entCav;

    public Controller(Game game) {

        this.game = game;
    }

    public void createEnemy(int count_enemy) {
        int spawnIndex = 0;
        for (int i = 0; i < count_enemy; i++) {
             if (spawnIndex == 0) {
                //Spawn from left
                addEntity(new Enemy(r.nextInt(25-1)+1, r.nextInt(800-500)+500, this.game, this));
                spawnIndex++;
            } else if (spawnIndex == 1) {
                //Spawn from right
                addEntity(new Enemy(r.nextInt(1200-1180)+1180, r.nextInt(800-500)+500, this.game, this));
                spawnIndex++;
            } else if (spawnIndex == 2) {
                //Spawn from down
                addEntity(new Enemy(r.nextInt(1200), 800, this.game, this));

                spawnIndex = 0;
            }
        }

    }

    public void tick() {
        //FOR FRIENDLY ENTITY
        for (int i = 0; i < friendlyEn.size(); i++) {
            entFR = friendlyEn.get(i);
            if (entFR.getX() < 0 || entFR.getY() < 0 || entFR.getX() > 1280 || entFR.getY() > 800) {
                this.RemoveEntity(entFR);
            }
            entFR.tick();
        }

        //FOR ENEMY ENTITY
        for (int i = 0; i < enemyEn.size(); i++) {
            entEN = enemyEn.get(i);

            entEN.tick();
        }

        //FOR ENEMY ENTITY
        for (int i = 0; i < caveEn.size(); i++) {
            entCav = caveEn.get(i);

            entCav.tick();
        }
    }

    public void render(Graphics graphics) {
        //FOR FRIENDLY ENTITY
        for (int i = 0; i < friendlyEn.size(); i++) {
            entFR = friendlyEn.get(i);

            entFR.render(graphics);
        }

        //FOR ENEMY ENTITY
        for (int i = 0; i < enemyEn.size(); i++) {
            entEN = enemyEn.get(i);

            entEN.render(graphics);
        }


        for (int i = 0; i < caveEn.size(); i++) {
            entCav = caveEn.get(i);

            entCav.render(graphics);
        }
    }


    public void addEntity(FriendlyEntity block) {
        friendlyEn.add(block);
    }

    public void RemoveEntity(FriendlyEntity block) {
        friendlyEn.remove(block);
    }

    public void addEntity(EnemyEntity block) {
        enemyEn.add(block);
    }

    public void RemoveEntity(EnemyEntity block) {
        enemyEn.remove(block);
    }

    public void addEntity(CaveEntity block) {
        caveEn.add(block);
    }

    public void RemoveEntity(CaveEntity block) {
        caveEn.remove(block);
    }

    public LinkedList<FriendlyEntity> getFriendly() {
        return  friendlyEn;
    }

    public LinkedList<EnemyEntity> getEnemy() {
        return  enemyEn;
    }

    public LinkedList<CaveEntity> getCave() {
        return  caveEn;
    }


}
