package models;

import animation.Animation;
import app.Game;
import audio.Music;
import collisions.Physics;
import controllers.Controller;
import enums.GameState;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends DefaultObject implements FriendlyEntity {

    private double velX = 0;
    private double velY = 0;
    private int health;
    private Game game;
    private String direction;
    private Controller controller;

    private SpriteSheet spriteSheet;
    private BufferedImage[] dragon = new BufferedImage[16];
    private Animation animationRight;
    private Animation animationLeft;

    public Player(double x, double y, Game game, Controller controller, int health) {
        super(x,y);

        this.health = health;
        this.game = game;
        this.controller = controller;
        this.controller.addEntity(this);
        this.spriteSheet = new SpriteSheet(this.game.getSpriteSheetDragon());
        this.direction = "right";
        this.setAnimation();
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

            if (Physics.Collision(this, tempEnt)) {
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

        if (this.direction.equals("left")) {
            this.animationLeft.runAnimation();
        } else if (this.direction.equals("right")) {
            this.animationRight.runAnimation();
        }
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getX() {
        return super.getX();
    }

    public void setX(double x) {
        super.setX(x);
    }

    public double getY() {
        return super.getY();
    }

    public void setY(double y) {
        super.setY(y);
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

    private void setAnimation() {
        this.dragon[0] = this.spriteSheet.grabDragonImage(1, 1, 72, 72);
        this.dragon[1] = this.spriteSheet.grabDragonImage(2, 1, 72, 72);
        this.dragon[2] = this.spriteSheet.grabDragonImage(3, 1, 72, 72);
        this.dragon[3] = this.spriteSheet.grabDragonImage(4, 1, 72, 72);
        this.dragon[4] = this.spriteSheet.grabDragonImage(5, 1, 72, 72);
        this.dragon[5] = this.spriteSheet.grabDragonImage(6, 1, 72, 72);
        this.dragon[6] = this.spriteSheet.grabDragonImage(7, 1, 72, 72);
        this.dragon[7] = this.spriteSheet.grabDragonImage(8, 1, 72, 72);

        this.dragon[8] = this.spriteSheet.grabDragonImage(1, 2, 72, 72);
        this.dragon[9] = this.spriteSheet.grabDragonImage(2, 2, 72, 72);
        this.dragon[10] = this.spriteSheet.grabDragonImage(3, 2, 72, 72);
        this.dragon[11] = this.spriteSheet.grabDragonImage(4, 2, 72, 72);
        this.dragon[12] = this.spriteSheet.grabDragonImage(5, 2, 72, 72);
        this.dragon[13] = this.spriteSheet.grabDragonImage(6, 2, 72, 72);
        this.dragon[14] = this.spriteSheet.grabDragonImage(7, 2, 72, 72);
        this.dragon[15] = this.spriteSheet.grabDragonImage(8, 2, 72, 72);

        this.animationLeft = new Animation(5,
            this.dragon[8],
            this.dragon[9],
            this.dragon[10],
            this.dragon[11],
            this.dragon[12],
            this.dragon[13],
            this.dragon[14],
            this.dragon[15]);

        this.animationRight = new Animation(5,
            this.dragon[0],
            this.dragon[1],
            this.dragon[2],
            this.dragon[3],
            this.dragon[4],
            this.dragon[5],
            this.dragon[6],
            this.dragon[7]);
    }
}
