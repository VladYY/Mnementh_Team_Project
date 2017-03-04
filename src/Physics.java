import java.util.LinkedList;

public class Physics {

    public static boolean Collision(FriendlyEntity friendly, EnemyEntity enemy) {

        if (friendly.getBounds().intersects(enemy.getBounds())) {
            return true;
        }

        return false;
    }

    public static boolean Collision(EnemyEntity enemy, FriendlyEntity friendly) {

        if (enemy.getBounds().intersects(friendly.getBounds())) {
            return true;
        }

        return false;
    }
}
