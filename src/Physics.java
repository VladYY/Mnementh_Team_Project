public class Physics {

    public static boolean Collision(FriendlyEntity friendly, EnemyEntity enemy) {

        return friendly.getBounds().intersects(enemy.getBounds());
    }

    public static boolean Collision(EnemyEntity enemy, FriendlyEntity friendly) {

        return enemy.getBounds().intersects(friendly.getBounds());
    }
}
