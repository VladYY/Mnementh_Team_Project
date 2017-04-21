package controllers;

import app.Game;
import enums.GameState;
import interfaces.BossEntity;
import interfaces.BossShotEntity;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;
import models.BossLevelOne;
import models.BossLevelTwo;
import models.Enemy;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Controller {

    private static final int BOSS_DEFAULT_DAMAGE = 1;

    private Game game;
    private Random random;

    private List<FriendlyEntity> friendlyEntities;
    private List<EnemyEntity> enemyEntities;
    private List<BossEntity> bossEntities;
    private List<BossShotEntity> bossShotEntities;

    private FriendlyEntity friendlyEntity;
    private EnemyEntity enemyEntity;
    private BossEntity bossEntity;
    private BossShotEntity bossShot;

    public Controller(Game game) {
        this.game = game;
        this.random = new Random();
        this.friendlyEntities = new ArrayList<>();
        this.enemyEntities = new ArrayList<>();
        this.bossEntities = new ArrayList<>();
        this.bossShotEntities = new ArrayList<>();
    }

    public List<FriendlyEntity> getFriendly() {
        return Collections.unmodifiableList(this.friendlyEntities);
    }

    public List<EnemyEntity> getEnemy() {
        return Collections.unmodifiableList(this.enemyEntities);
    }

    public List<BossEntity> getBoss() { return Collections.unmodifiableList(this.bossEntities); }

    public List<BossShotEntity> getBossShotEntities() { return Collections.unmodifiableList(this.bossShotEntities); }

    public void createEnemy(int count_enemy) {
        if(!this.game.isBossActive()) {
            int spawnIndex = 0;
            for (int i = 0; i < count_enemy; i++) {
                boolean isHunter = false;

                if (i % 4 == 0 && i > 0) {
                    isHunter = true;
                }

                if (spawnIndex == 0 && Game.gameState == GameState.GAME_LEVEL_ONE) {
                    //Spawn from left
                    this.addEntity(
                            new Enemy(this.random.nextInt(25-1)+1, this.random.nextInt(800-500)+500, this.game, this, isHunter));
                    spawnIndex++;
                } else if (spawnIndex == 1 && Game.gameState == GameState.GAME_LEVEL_ONE) {
                    //Spawn from right
                    this.addEntity(
                            new Enemy(this.random.nextInt(1200-1180)+1180, this.random.nextInt(800-500)+500, this.game, this, isHunter));
                    spawnIndex++;
                } else if (spawnIndex == 2 && Game.gameState == GameState.GAME_LEVEL_ONE) {
                    //Spawn from down
                    this.addEntity(new Enemy(this.random.nextInt(1200), 800, this.game, this, isHunter));

                    spawnIndex = 0;
                }

                if (Game.gameState == GameState.GAME_LEVEL_TWO) {
                    //Spawn from left
                    this.addEntity(
                            new Enemy(this.random.nextInt(1200-1180)+1180, this.random.nextInt(600-1)+1, this.game, this, isHunter));
                }
            }
        } else if(this.game.isBossActive() && !this.game.isBossSpawned() &&
                Game.gameState == GameState.GAME_LEVEL_ONE){

            this.addEntity(new BossLevelOne(1150, 400, this.game, this, 900, BOSS_DEFAULT_DAMAGE));
            this.game.setBossSpawned(true);
        } else if(this.game.isBossActive() && !this.game.isBossSpawned() &&
                Game.gameState == GameState.GAME_LEVEL_TWO){

            this.addEntity(new BossLevelTwo(1150, 400, this.game, this, 900, BOSS_DEFAULT_DAMAGE));
            this.game.setBossSpawned(true);
        }
    }

    public void tick() {
        if(this.game.isBossActive() && this.game.isBossSpawned() && this.bossEntities.size() == 0
                && Game.gameState == GameState.GAME_LEVEL_ONE){
            this.game.setBossActive(false);
            this.game.setBossSpawned(false);
            Game.gameState = GameState.GAME_LEVEL_TWO;
            this.game.setEnemyKilled(this.game.getEnemyKilled() + 1);
        } else if(this.game.isBossActive() && this.game.isBossSpawned() && this.bossEntities.size() == 0
                && Game.gameState == GameState.GAME_LEVEL_TWO) {
            this.game.setBossActive(false);
            this.game.setBossSpawned(false);
            Game.gameState = GameState.END;
            this.game.setEnemyKilled(this.game.getEnemyKilled() + 1);
        }

        //FOR FRIENDLY ENTITY
        for (int i = 0; i < this.friendlyEntities.size(); i++) {
            this.friendlyEntity = this.friendlyEntities.get(i);
            if (this.friendlyEntity.getX() < 0
                    || this.friendlyEntity.getY() < 0
                    || this.friendlyEntity.getX() > 1280
                    || this.friendlyEntity.getY() > 800) {

                this.removeEntity(this.friendlyEntity);
            }

            this.friendlyEntity.tick();
        }

        //FOR ENEMY ENTITY
        for (int i = 0; i < this.enemyEntities.size(); i++) {
            this.enemyEntity = this.enemyEntities.get(i);

            this.enemyEntity.tick();
        }

        //FOR BOSS ENTITY
        for (int i = 0; i < this.bossEntities.size(); i++) {
            this.bossEntity = this.bossEntities.get(i);

            this.bossEntity.tick();
        }

        //FOR BOSS SHOT ENTITIES
        for (int i = 0; i < this.bossShotEntities.size(); i++) {
            this.bossShot = this.bossShotEntities.get(i);
            if (this.bossShot.getX() < 0
                || this.bossShot.getY() < 0
                || this.bossShot.getX() > 1280
                || this.bossShot.getY() > 800
                || Game.gameState == GameState.END) {

                this.removeEntity(this.bossShot);
                this.bossShotEntities.clear();
                break;
            }

            this.bossShot.tick();
        }
    }

    public void render(Graphics graphics) {
        //FOR FRIENDLY ENTITY
        for (int i = 0; i < this.friendlyEntities.size(); i++) {
            this.friendlyEntity = this.friendlyEntities.get(i);

            this.friendlyEntity.render(graphics);
        }

        //FOR ENEMY ENTITY
        for (int i = 0; i < this.enemyEntities.size(); i++) {
            this.enemyEntity = this.enemyEntities.get(i);

            this.enemyEntity.render(graphics);
        }

        //FOR BOSS ENTITY
        for (int i = 0; i < this.bossEntities.size(); i++) {
            this.bossEntity = this.bossEntities.get(i);

            this.bossEntity.render(graphics);
        }

        //FOR BOSS SHOT ENTITIES
        for (int i = 0; i < this.bossShotEntities.size(); i++) {
            this.bossShot = this.bossShotEntities.get(i);

            this.bossShot.render(graphics);
        }
    }

    public void addEntity(FriendlyEntity block) {
        this.friendlyEntities.add(block);
    }

    public void removeEntity(FriendlyEntity block) {
        this.friendlyEntities.remove(block);
    }

    public void addEntity(BossShotEntity block) {
        this.bossShotEntities.add(block);
    }

    public void removeEntity(BossShotEntity block) {
        this.bossShotEntities.remove(block);
    }

    public void addEntity(EnemyEntity block) {
        this.enemyEntities.add(block);
    }

    public void addEntity(BossEntity block) {
        this.bossEntities.add(block);
    }

    public void removeEntity(BossEntity block) {
        this.bossEntities.remove(block);
    }

    public void removeEntity(EnemyEntity block) {
        this.enemyEntities.remove(block);
    }

    public void clearEnemies() {
        this.enemyEntities.clear();
    }
}
