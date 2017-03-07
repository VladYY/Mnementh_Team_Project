package controllers;

import app.Game;
import enums.GameState;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;
import models.Enemy;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private Game game;
    private Random random;

    private LinkedList<FriendlyEntity> friendlyEntities;
    private LinkedList<EnemyEntity> enemyEntities;

    private FriendlyEntity friendlyEntity;
    private EnemyEntity enemyEntity;

    public Controller(Game game) {
        this.game = game;
        this.random = new Random();
        this.friendlyEntities = new LinkedList<>();
        this.enemyEntities = new LinkedList<>();
    }

    public LinkedList<FriendlyEntity> getFriendly() {
        return this.friendlyEntities;
    }

    public LinkedList<EnemyEntity> getEnemy() {
        return this.enemyEntities;
    }

    public void createEnemy(int count_enemy) {
        int spawnIndex = 0;
        for (int i = 0; i < count_enemy; i++) {
            boolean isHunter = false;

            if (i % 5 == 0 && i > 0) {
                isHunter = true;
            }

            if (spawnIndex == 0 && Game.gameState == GameState.GAME_LEVEL_ONE) {
                //Spawn from left
                addEntity(
                        new Enemy(this.random.nextInt(25-1)+1, this.random.nextInt(800-500)+500, this.game, this, isHunter));
                spawnIndex++;
            } else if (spawnIndex == 1 && Game.gameState == GameState.GAME_LEVEL_ONE) {
                //Spawn from right
                addEntity(
                        new Enemy(this.random.nextInt(1200-1180)+1180, this.random.nextInt(800-500)+500, this.game, this, isHunter));
                spawnIndex++;
            } else if (spawnIndex == 2 && Game.gameState == GameState.GAME_LEVEL_ONE) {
                //Spawn from down
                addEntity(new Enemy(this.random.nextInt(1200), 800, this.game, this, isHunter));

                spawnIndex = 0;
            }

            if (Game.gameState == GameState.GAME_LEVEL_TWO) {
                //Spawn from left
                addEntity(
                        new Enemy(this.random.nextInt(25-1)+1, this.random.nextInt(800-500)+500, this.game, this, isHunter));
            }


        }
    }

    public void tick() {
        //FOR FRIENDLY ENTITY
        for (int i = 0; i < this.friendlyEntities.size(); i++) {
            this.friendlyEntity = this.friendlyEntities.get(i);
            if (this.friendlyEntity.getX() < 0
                    || this.friendlyEntity.getY() < 0
                    || this.friendlyEntity.getX() > 1280
                    || this.friendlyEntity.getY() > 800) {

                this.removeEntity(this.friendlyEntity);
                break;
            }

            this.friendlyEntity.tick();
        }

        //FOR ENEMY ENTITY
        for (int i = 0; i < this.enemyEntities.size(); i++) {
            this.enemyEntity = this.enemyEntities.get(i);

            this.enemyEntity.tick();
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
    }

    public void addEntity(FriendlyEntity block) {
        this.friendlyEntities.add(block);
    }

    public void removeEntity(FriendlyEntity block) {
        this.friendlyEntities.remove(block);
    }

    public void addEntity(EnemyEntity block) {
        this.enemyEntities.add(block);
    }

    public void removeEntity(EnemyEntity block) {
        this.enemyEntities.remove(block);
    }
}
