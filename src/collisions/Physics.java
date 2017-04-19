package collisions;

import interfaces.BossEntity;
import interfaces.BossShotEntity;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;

public class Physics {

    public static boolean collision(FriendlyEntity friendly, EnemyEntity enemy) {

        return friendly.getBounds().intersects(enemy.getBounds());
    }

    public static boolean collision(FriendlyEntity friendly, BossShotEntity bossShot) {

        return friendly.getBounds().intersects(bossShot.getBounds());
    }

    public static boolean collision(FriendlyEntity friendly, BossEntity bossEntity) {

        return friendly.getBounds().intersects(bossEntity.getBounds());
    }

    public static boolean collision(BossEntity bossEntity, FriendlyEntity friendly) {

        return bossEntity.getBounds().intersects(friendly.getBounds());
    }

    public static boolean collision(EnemyEntity enemy, FriendlyEntity friendly) {

        return enemy.getBounds().intersects(friendly.getBounds());
    }
}
