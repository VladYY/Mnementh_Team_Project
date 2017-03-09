package models;

import animation.Animation;
import app.Game;
import collisions.Physics;
import controllers.Controller;
import interfaces.BossEntity;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss extends DefaultObject implements BossEntity {

    private double velX;
    private double velY;
    private int health;
    private int damage;
    private Game game;
    private Controller controller;

    private SpriteSheet spriteSheet;
    private BufferedImage[] boss;

    private Animation animationLeft;
    private Animation animationRight;
    private Animation animationAttackLeft;
    private Animation animationAttackRight;

    private boolean isAttackingLeft;
    private boolean isAttackingRight;

    public Boss(double x, double y, Game game, Controller controller, int health, int damage) {
        super(x,y);

        this.health = health;
        this.damage = damage;
        this.game = game;
        this.velX = 1.5;
        this.velY = 1.5;
        this.controller = controller;
        this.spriteSheet = new SpriteSheet(this.game.getSpriteSheetBoss());
        this.boss = new BufferedImage[16];

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

        if(Physics.Collision(this, this.game.getPlayer1())) {
            int currentPlayerHealth = this.game.getPlayer1Health();
            this.game.setPlayer1Health((currentPlayerHealth - this.damage));

            if(super.getX() < this.game.getPlayer1X()) {
                this.isAttackingLeft = true;
                this.animationAttackLeft.runAnimation();
            } else {
                this.isAttackingRight = true;
                this.animationAttackRight.runAnimation();
            }

            if(this.game.getPlayer1Health() <= 0) {
                this.controller.removeEntity(this);
            }
        }

    }

    public void render(Graphics graphics) {
        if(isAttackingRight) {
            this.animationAttackRight.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else if(isAttackingLeft) {
            this.animationAttackLeft.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else if(super.getX() > this.game.getPlayer1X()) {
            this.animationRight.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else {
            this.animationLeft.drawAnimation(graphics, super.getX(), super.getY(), 0);
        }

        // Boss HP BAR
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

    private void setAnimation() {
        this.boss[0] = this.spriteSheet.grabBossImage(1, 1, 144, 144);
        this.boss[1] = this.spriteSheet.grabBossImage(2, 1, 144, 144);
        this.boss[2] = this.spriteSheet.grabBossImage(3, 1, 144, 144);
        this.boss[3] = this.spriteSheet.grabBossImage(4, 1, 144, 144);
        this.boss[4] = this.spriteSheet.grabBossImage(5, 1, 144, 144);
        this.boss[5] = this.spriteSheet.grabBossImage(6, 1, 144, 144);
        this.boss[6] = this.spriteSheet.grabBossImage(7, 1, 144, 144);
        this.boss[7] = this.spriteSheet.grabBossImage(8, 1, 144, 144);
        this.boss[8] = this.spriteSheet.grabBossImage(1, 2, 144, 144);
        this.boss[9] = this.spriteSheet.grabBossImage(2, 2, 144, 144);
        this.boss[10] = this.spriteSheet.grabBossImage(3, 2, 144, 144);
        this.boss[11] = this.spriteSheet.grabBossImage(4, 2, 144, 144);
        this.boss[12] = this.spriteSheet.grabBossImage(5, 2, 144, 144);
        this.boss[13] = this.spriteSheet.grabBossImage(6, 2, 144, 144);
        this.boss[14] = this.spriteSheet.grabBossImage(7, 2, 144, 144);
        this.boss[15] = this.spriteSheet.grabBossImage(8, 2, 144, 144);

        this.animationLeft = new Animation(5,
            this.boss[0],
            this.boss[1],
            this.boss[2],
            this.boss[3],
            this.boss[4],
            this.boss[5],
            this.boss[6],
            this.boss[7]);

        this.animationRight = new Animation(5,
            this.boss[8],
            this.boss[9],
            this.boss[10],
            this.boss[11],
            this.boss[12],
            this.boss[13],
            this.boss[14],
            this.boss[15]);

        this.boss[0] = this.spriteSheet.grabBossImage(1, 3, 144, 144);
        this.boss[1] = this.spriteSheet.grabBossImage(2, 3, 144, 144);
        this.boss[2] = this.spriteSheet.grabBossImage(3, 3, 144, 144);
        this.boss[3] = this.spriteSheet.grabBossImage(4, 3, 144, 144);
        this.boss[4] = this.spriteSheet.grabBossImage(5, 3, 144, 144);
        this.boss[5] = this.spriteSheet.grabBossImage(6, 3, 144, 144);
        this.boss[6] = this.spriteSheet.grabBossImage(7, 3, 144, 144);
        this.boss[7] = this.spriteSheet.grabBossImage(8, 3, 144, 144);
        this.boss[8] = this.spriteSheet.grabBossImage(1, 4, 144, 144);
        this.boss[9] = this.spriteSheet.grabBossImage(2, 4, 144, 144);
        this.boss[10] = this.spriteSheet.grabBossImage(3, 4, 144, 144);
        this.boss[11] = this.spriteSheet.grabBossImage(4, 4, 144, 144);
        this.boss[12] = this.spriteSheet.grabBossImage(5, 4, 144, 144);
        this.boss[13] = this.spriteSheet.grabBossImage(6, 4, 144, 144);
        this.boss[14] = this.spriteSheet.grabBossImage(7, 4, 144, 144);
        this.boss[15] = this.spriteSheet.grabBossImage(8, 4, 144, 144);

        this.animationAttackLeft = new Animation(5,
            this.boss[0],
            this.boss[1],
            this.boss[2],
            this.boss[3],
            this.boss[4],
            this.boss[5],
            this.boss[6],
            this.boss[7]);

        this.animationAttackRight = new Animation(5,
            this.boss[8],
            this.boss[9],
            this.boss[10],
            this.boss[11],
            this.boss[12],
            this.boss[13],
            this.boss[14],
            this.boss[15]);
    }
}
