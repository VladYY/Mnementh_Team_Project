package models;

import animation.Animation;
import app.Game;
import controllers.Controller;
import enums.GameState;
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
        this.setGame(game);
        this.setController(controller);

        this.setRnd(new Random());
        this.setEnemyImages(new BufferedImage[3]);
        this.setSpeed(1);
        this.setSpeedRandom(this.getRnd().nextInt(100) + 1);
        this.setSpriteSheet(new SpriteSheet(this.getGame().getSpriteSheetGorgon()));
        this.setHunter(isHunter);

        this.setAnimation();
    }

    public void tick() {

        if (this.getSpeedRandom() > 1 && this.getSpeedRandom() < 41) {
            this.setSpeed(1);
        }

        if (this.getSpeedRandom() > 40 && this.getSpeedRandom() < 81) {
            this.setSpeed(2);
        }

        if (this.getSpeedRandom() > 80 && this.getSpeedRandom() < 96) {
            this.setSpeed(3);
        }

        if (this.getSpeedRandom() > 95 && this.getSpeedRandom() < 100) {
            this.setSpeed(4);
        }

        int caveX = 0;
        int caveY = 0;

        if (Game.gameState == GameState.GAME_LEVEL_ONE) {
            caveX = 600;
            caveY = 120;
        }

        if (Game.gameState == GameState.GAME_LEVEL_TWO) {
            caveX = 0;
            caveY = this.getRnd().nextInt(700-100)+100;
        }

        if(!this.isHunter) {
            if (super.getX() < caveX && super.getY() < caveY) {
                super.setX(super.getX() + this.getSpeed());
                super.setY(super.getY() + this.getSpeed());
            } else if (super.getX() > caveX && super.getY() < caveY) {
                super.setX(super.getX() - this.getSpeed());
                super.setY(super.getY() + this.getSpeed());
            } else if (super.getY() > caveY && super.getX() > caveX) {
                super.setX(super.getX() - this.getSpeed());
                super.setY(super.getY() - this.getSpeed());
            } else if (super.getY() > caveY && super.getX() < caveX) {
                super.setX(super.getX() + this.getSpeed());
                super.setY(super.getY() - this.getSpeed());
            } else if (super.getX() > caveX) {
                super.setX(super.getX() - this.getSpeed());
            } else if (super.getX() < caveX) {
                super.setX(super.getX() + this.getSpeed());
            } else if (super.getY() < caveY) {
                super.setY(super.getY() + this.getSpeed());
            } else if (super.getY() > caveY) {
                super.setY(super.getY() - this.getSpeed());
            }

            if (Game.gameState == GameState.GAME_LEVEL_ONE) {
            if (super.getX() >= 550 && super.getX() <= 652) {
                if(super.getY() >= 70 && super.getY() <= 172) {
                    this.controller.removeEntity(this);
                    int playerHealth = this.getGame().getPlayer1Health();
                    this.getGame().setPlayer1Health(playerHealth - 20);
                }
            }
            }

            if (Game.gameState == GameState.GAME_LEVEL_TWO) {
                if (super.getX() >= 0 && super.getX() <= 20) {
                    if(super.getY() >= 100 && super.getY() <= 700) {
                        this.controller.removeEntity(this);
                        int playerHealth = this.getGame().getPlayer1Health();
                        this.getGame().setPlayer1Health(playerHealth - 20);
                    }
                }
            }

            this.animation.runAnimation();
        } else {
            if(super.getX() < this.getGame().getPlayer1X()) {
                super.setX(super.getX() + this.getSpeed());
                this.animationLeft.runAnimation();
                this.setRight(false);
            }

            if(super.getX() > this.getGame().getPlayer1X()) {
                super.setX(super.getX() - this.getSpeed());
                this.animationRight.runAnimation();
                this.setRight(true);
            }

            if(super.getY() > this.getGame().getPlayer1Y()) {
                super.setY(super.getY() - this.getSpeed());
            }

            if(super.getY() < this.getGame().getPlayer1Y()) {
                super.setY(super.getY() + this.getSpeed());
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

    private Random getRnd() {
        return this.rnd;
    }

    private void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    private void setEnemyImages(BufferedImage[] enemyImages) {
        this.enemyImages = enemyImages;
    }

    private void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    private double getSpeedRandom() {
        return this.speedRandom;
    }

    private void setSpeedRandom(double speedRandom) {
        this.speedRandom = speedRandom;
    }

    private double getSpeed() {
        return this.speed;
    }

    private void setSpeed(double speed) {
        this.speed = speed;
    }

    private Game getGame() {
        return this.game;
    }

    private void setGame(Game game) {
        this.game = game;
    }

    private void setController(Controller controller) {
        this.controller = controller;
    }

    private void setHunter(boolean hunter) {
        this.isHunter = hunter;
    }

    public void setRight(boolean right) {
        this.isRight = right;
    }

    private void setAnimation() {
        if (super.getX() < 600 && !this.isHunter) {
            this.enemyImages[0] = this.spriteSheet.grabImage(1, 1, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 1, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 1, 42, 65);

            this.animation = new Animation(5, this.enemyImages);
        } else if (super.getX() >= 600 && !this.isHunter){
            this.enemyImages[0] = this.spriteSheet.grabImage(1, 2, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 2, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 2, 42, 65);

            this.animation = new Animation(5, this.enemyImages);
        } else {
            this.enemyImages[0] = this.spriteSheet.grabImage(1, 3, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 3, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 3, 42, 65);

            this.animationLeft = new Animation(5, this.enemyImages);

            this.enemyImages[0] = this.spriteSheet.grabImage(1, 4, 42, 65);
            this.enemyImages[1] = this.spriteSheet.grabImage(2, 4, 42, 65);
            this.enemyImages[2] = this.spriteSheet.grabImage(3, 4, 42, 65);

            this.animationRight = new Animation(5, this.enemyImages);
        }
    }
}
