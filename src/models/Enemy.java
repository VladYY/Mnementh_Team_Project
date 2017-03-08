package models;

import animation.Animation;
import app.Game;
import controllers.Controller;
import interfaces.EnemyEntity;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends DefaultObject implements EnemyEntity {

    private Random rnd;
    private BufferedImage[] enemyImages;
    private SpriteSheet spriteSheet;
    private double speedRandom;
    private double speed;
    private Game game;
    private Controller controller;
    private Animation animation;
    private Animation animationLeft;
    private Animation animationRight;
    private boolean isHunter;
    private boolean isRight;

    public Enemy(double x, double y, Game game, Controller controller, boolean isHunter) {
        super(x,y);
        this.game = game;
        this.controller = controller;

        this.rnd = new Random();
        this.enemyImages = new BufferedImage[3];
        this.speed = 1;
        this.speedRandom = (this.rnd.nextInt(100) + 1);
        this.spriteSheet = new SpriteSheet(this.game.getSpriteSheetGorgon());
        this.isHunter = isHunter;

        if (super.getX() < 600 && !this.isHunter) {
            this.enemyImages[0] = this.spriteSheet.grabImage(1, 1, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 1, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 1, 42, 65);

            this.animation = new Animation(5, this.enemyImages[0], this.enemyImages[1], this.enemyImages[2]);
        } else if (super.getX() >= 600 && !this.isHunter){
            this.enemyImages[0] = this.spriteSheet.grabImage(1, 2, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 2, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 2, 42, 65);

            this.animation = new Animation(5, this.enemyImages[0], this.enemyImages[1], this.enemyImages[2]);
        } else {
            this.enemyImages[0] = this.spriteSheet.grabImage(1, 3, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 3, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 3, 42, 65);

            this.animationLeft = new Animation(5, this.enemyImages[0], this.enemyImages[1], this.enemyImages[2]);

            this.enemyImages[0] = this.spriteSheet.grabImage(1, 4, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 4, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 4, 42, 65);

            this.animationRight = new Animation(5, this.enemyImages[0], this.enemyImages[1], this.enemyImages[2]);
        }


    }

    public void tick() {

        if (this.speedRandom > 1 && this.speedRandom <41) {
            this.speed = 1;
        }

        if (this.speedRandom > 40 && this.speedRandom <81) {
            this.speed = 2;
        }

        if (this.speedRandom > 80 && this.speedRandom <96) {
            this.speed = 3;
        }

        if (this.speedRandom > 95 && this.speedRandom <100) {
            this.speed = 4;
        }

        int caveX = 600;
        int caveY = 120;
        if(!this.isHunter) {
            if (super.getX() < caveX && super.getY() < caveY) {
                super.setX(super.getX() + this.speed);
                super.setY(super.getY() + this.speed);
            } else if (super.getX() > caveX && super.getY() < caveY) {
                super.setX(super.getX() - this.speed);
                super.setY(super.getY() + this.speed);
            } else if (super.getY() > caveY && super.getX() > caveX) {
                super.setX(super.getX() - this.speed);
                super.setY(super.getY() - this.speed);
            } else if (super.getY() > caveY && super.getX() < caveX) {
                super.setX(super.getX() + this.speed);
                super.setY(super.getY() - this.speed);
            } else if (super.getX() > caveX) {
                super.setX(super.getX() - this.speed);
            } else if (super.getX() < caveX) {
                super.setX(super.getX() + this.speed);
            } else if (super.getY() < caveY) {
                super.setY(super.getY() + this.speed);
            } else if (super.getY() > caveY) {
                super.setY(super.getY() - this.speed);
            }

            if (super.getX() >= 550 && super.getX() <= 652) {
                if(super.getY() >= 70 && super.getY() <= 172) {
                    this.controller.removeEntity(this);
                    int playerHealth = this.game.getPlayer1Health();
                    this.game.setPlayer1Health(playerHealth - 20);
                }
            }

            this.animation.runAnimation();
        } else {
            if(super.getX() < this.game.getPlayer1X()) {
                super.setX(super.getX() + this.speed);
                this.animationLeft.runAnimation();
                this.isRight = false;
            }

            if(super.getX() > this.game.getPlayer1X()) {
                super.setX(super.getX() - this.speed);
                this.animationRight.runAnimation();
                this.isRight = true;
            }

            if(super.getY() > this.game.getPlayer1Y()) {
                super.setY(super.getY() - this.speed);
            }

            if(super.getY() < this.game.getPlayer1Y()) {
                super.setY(super.getY() + this.speed);
            }
        }
    }

    public void render(Graphics graphics) {
        if(!this.isHunter) {
            this.animation.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else {
            if(this.isRight) {
                this.animationRight.drawAnimation(graphics, super.getX(), super.getY(), 0);
            } else {
                this.animationLeft.drawAnimation(graphics, super.getX(), super.getY(), 0);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int)super.getX() , (int)super.getY(), 42, 65);
    }
}
