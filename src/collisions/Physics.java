package collisions;

import interfaces.BossEntity;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;

public class Physics {

    public static boolean Collision(FriendlyEntity friendly, EnemyEntity enemy) {

        return friendly.getBounds().intersects(enemy.getBounds());
    }

    public static boolean Collision(FriendlyEntity friendly, BossEntity bossEntity) {

        return friendly.getBounds().intersects(bossEntity.getBounds());
    }

    public static boolean Collision(BossEntity bossEntity, FriendlyEntity friendly) {

        return bossEntity.getBounds().intersects(friendly.getBounds());
    }

    public static boolean Collision(EnemyEntity enemy, FriendlyEntity friendly) {

        return enemy.getBounds().intersects(friendly.getBounds());
    }
}
