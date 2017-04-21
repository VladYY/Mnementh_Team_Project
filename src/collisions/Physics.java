package collisions;

import interfaces.*;

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

    public static boolean collision(FriendlyEntity friendlyEntity, HealthRestorer healthRestorer) {

        return friendlyEntity.getBounds().intersects(healthRestorer.getBounds());
    }
}
