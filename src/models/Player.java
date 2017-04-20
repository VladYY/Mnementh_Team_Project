package models;

import animation.Animation;
import app.Game;
import audio.Music;
import collisions.Physics;
import controllers.Controller;
import enums.GameState;
import interfaces.BossShotEntity;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class Player extends DefaultObject implements FriendlyEntity {

    private double velX;
    private double velY;
    private int health;
    private int damage;
    private Game game;
    private String direction;
    private Controller controller;

    private SpriteSheet spriteSheet;
    private BufferedImage[] dragon;
    private Animation animationRight;
    private Animation animationLeft;

    public Player(double x, double y, Game game, Controller controller, int health, int damage) {
        super(x,y);

        this.setHealth(health);
        this.setDamage(damage);
        this.setGame(game);
        this.setController(controller);
        this.getController().addEntity(this);
        this.setSpriteSheet(new SpriteSheet(this.getGame().getSpriteSheetDragon()));
        this.setDirection("right");
        this.setVelX(0);
        this.setVelY(0);
        this.setDragon(new BufferedImage[16]);
        this.setAnimation();
    }

    public int getDamage() {
        return this.damage;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void tick() {
        super.setX(super.getX() + this.velX);
        super.setY(super.getY() + this.velY);

        if (super.getX() <= 0) {
            super.setX(0);
        }
        if (super.getX() >= Game.WIDTH * 2 - 50) {
            super.setX(Game.WIDTH * 2 - 50);
        }
        if (super.getY() <= 0) {
            super.setY(0);
        }
        if (super.getY() >= Game.HEIGHT * 2 - 50) {
            super.setY(Game.HEIGHT * 2 - 50);
        }

        if (this.health <= 0) {
            Game.gameState = GameState.END;
        }

        for (int i = 0; i < this.game.getEnemyEntities().size(); i++) {
            EnemyEntity tempEnt = this.game.getEnemyEntities().get(i);

            if (Physics.collision(this, tempEnt)) {
                try {
                    Music.enemyDie();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.controller.removeEntity(tempEnt);
                this.health -= 10;
                if (this.health <= 0) {
                    Game.gameState = GameState.END;
                }

                this.game.setEnemyKilled(this.game.getEnemyKilled() + 1);
            }
        }

        for (int i = 0; i < this.controller.getBossShotEntities().size(); i++) {
            BossShotEntity tempShot = this.controller.getBossShotEntities().get(i);

            if(Physics.collision(this, tempShot)) {
                this.controller.removeEntity(tempShot);

                try {
                    Music.bossShotHit();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.setHealth(this.getHealth() - tempShot.getDamage());
            }
        }

        if (this.direction.equals("left")) {
            this.animationLeft.runAnimation();
        } else if (this.direction.equals("right")) {
            this.animationRight.runAnimation();
        }
    }

    public void render(Graphics graphics) {
        if(this.direction.equals("left")) {
            this.animationLeft.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else {
            this.animationRight.drawAnimation(graphics, super.getX(), super.getY(), 0);
        }
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)super.getX() , (int)super.getY(), 72, 72);
    }

    private Game getGame() {
        return this.game;
    }

    private Controller getController() {
        return this.controller;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    private void setGame(Game game) {
        this.game = game;
    }

    private void setController(Controller controller) {
        this.controller = controller;
    }

    private void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    private void setDragon(BufferedImage[] dragon) {
        this.dragon = dragon;
    }

    private void setAnimation() {
        int colIndex = 1;
        int rowIndex = 1;
        for (int i = 0; i < this.dragon.length; i++) {
            if(i == 8){
                colIndex = 1;
                rowIndex = 2;
            }
            this.dragon[i] = this.spriteSheet.grabDragonImage(colIndex, rowIndex, 72, 72);
            colIndex++;

        }

        this.animationLeft = new Animation(5, Arrays.copyOfRange(this.dragon, 8, this.dragon.length));
        this.animationRight = new Animation(5,Arrays.copyOfRange(this.dragon, 0, 8));
    }
}
