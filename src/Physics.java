import interfaces.CaveEntity;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;

import java.util.LinkedList;

public class Physics {

    public static boolean Collision(FriendlyEntity friendly, LinkedList<EnemyEntity> enemies) {

        for (int i = 0; i < enemies.size(); i++) {
            if(friendly.getBounds().intersects(enemies.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static boolean Collision(EnemyEntity friendly, LinkedList<FriendlyEntity> enemies) {

        for (int i = 0; i < enemies.size(); i++) {
            if(friendly.getBounds().intersects(enemies.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static boolean CaveAttacked(CaveEntity friendly, LinkedList<EnemyEntity> enemies) {

        for (int i = 0; i < enemies.size(); i++) {
            if(friendly.getBounds().intersects(enemies.get(i).getBounds())) {
                enemies.remove(enemies.get(i));
                return true;
            }
        }
        return false;
    }
}
