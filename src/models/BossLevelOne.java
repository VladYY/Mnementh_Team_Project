package models;

import animation.Animation;
import app.Game;
import collisions.Physics;
import controllers.Controller;
import enums.GameState;
import interfaces.BossEntity;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class BossLevelOne extends DefaultObject implements BossEntity {

    private double velX;
    private double velY;
    private int health;
    private int damage;
    private Game game;
    private Controller controller;

    private SpriteSheet spriteSheet;
    private BufferedImage[] bossImages;

    private Animation animationLeft;
    private Animation animationRight;
    private Animation animationAttackLeft;
    private Animation animationAttackRight;

    private boolean isAttackingLeft;
    private boolean isAttackingRight;

    public BossLevelOne(double x, double y, Game game, Controller controller, int health, int damage) {
        super(x,y);

        this.setHealth(health);
        this.setDamage(damage);
        this.setGame(game);
        this.setVelX(2D);
        this.setVelY(2D);
        this.setController(controller);
        this.setSpriteSheet(new SpriteSheet(this.getGame().getSpriteSheetBoss()));
        this.setBossImages(new BufferedImage[16]);

        this.isAttackingLeft = false;
        this.isAttackingRight = false;

        this.setAnimation();
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void tick() {

        this.isAttackingLeft = false;
        this.isAttackingRight = false;

        // Set bounds for game field
        if (super.getX() <= 0) {
            super.setX(0);
        }
        if (super.getX() >= Game.WIDTH * 2 - 134) {
            super.setX(Game.WIDTH * 2 - 134);
        }
        if (super.getY() <= 0) {
            super.setY(0);
        }
        if (super.getY() >= Game.HEIGHT * 2 - 120) {
            super.setY(Game.HEIGHT * 2 - 120);
        }

        if(super.getX() <= this.game.getPlayer1X()) {
            super.setX(super.getX() + this.velX);
            this.animationLeft.runAnimation();
        }

        if(super.getX() > this.game.getPlayer1X()) {
            super.setX(super.getX() - this.velX);
            this.animationRight.runAnimation();
        }

        if(super.getY() > this.game.getPlayer1Y()) {
            super.setY(super.getY() - this.velY);
        }

        if(super.getY() < this.game.getPlayer1Y()) {
            super.setY(super.getY() + this.velY);
        }

        if(Physics.collision(this, this.game.getPlayer1())) {
            int currentPlayerHealth = this.game.getPlayer1Health();
            this.game.setPlayer1Health((currentPlayerHealth - this.damage));

            if(super.getX() < this.game.getPlayer1X()) {
                this.isAttackingLeft = true;
                this.animationAttackLeft.runAnimation();
            } else {
                this.isAttackingRight = true;
                this.animationAttackRight.runAnimation();
            }
        }

        if(this.game.getPlayer1Health() <= 0) {
            this.controller.removeEntity(this);
        }
    }

    public void render(Graphics graphics) {
        if(this.isAttackingRight) {
            this.animationAttackRight.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else if(this.isAttackingLeft) {
            this.animationAttackLeft.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else if(super.getX() > this.game.getPlayer1X()) {
            this.animationRight.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else {
            this.animationLeft.drawAnimation(graphics, super.getX(), super.getY(), 0);
        }

        // BossLevelOne HP BAR
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        Font fnt2 = new Font("arial", Font.BOLD, 20);

        graphics.setColor(Color.RED);
        graphics.fillRect(505, 5, 300, 50);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(505, 5, this.health / 3, 50);

        graphics.setColor(Color.WHITE);
        graphics.drawRect(505, 5, 300, 50);

        graphics.setColor(Color.white);
        graphics.setFont(fnt1);
        graphics.drawString(this.health / 9 + "%", 620, 42);
        graphics.setFont(fnt2);
        graphics.drawString("Gnarl", 820, 37);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)super.getX() , (int)super.getY(), 144, 100);
    }

    private void setVelX(double velX) {
        this.velX = velX;
    }

    private void setVelY(double velY) {
        this.velY = velY;
    }

    private void setDamage(int damage) {
        this.damage = damage;
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

    private void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    private void setBossImages(BufferedImage[] bossImages) {
        this.bossImages = bossImages;
    }

    private void setAnimation() {
        int colIndex = 1;
        int rowIndex = 1;
        this.fillBossImages(colIndex, rowIndex);

        this.animationLeft = new Animation(5, Arrays.copyOfRange(this.bossImages, 0, 8));
        this.animationRight = new Animation(5, Arrays.copyOfRange(this.bossImages, 8, this.bossImages.length));

        rowIndex = 3;
        this.fillBossImages(colIndex, rowIndex);

        this.animationAttackLeft = new Animation(5, Arrays.copyOfRange(this.bossImages, 0, 8));
        this.animationAttackRight = new Animation(5, Arrays.copyOfRange(this.bossImages, 8, this.bossImages.length));
    }

    private void fillBossImages(int colIndex, int rowIndex) {
        for (int i = 0; i < this.bossImages.length; i++) {
            if(i == 8){
                colIndex = 1;
                rowIndex += 1;
            }
            this.bossImages[i] = this.spriteSheet.grabBossImage(colIndex, rowIndex, 144, 144);
            colIndex++;
        }
    }
}
