package models;

import animation.Animation;
import app.Game;
import controllers.Controller;
import interfaces.BossEntity;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss extends DefaultObject implements BossEntity {

    private double velX = 0;
    private double velY = 0;
    private int health;
    private Game game;
    private Controller controller;

    private SpriteSheet spriteSheet;
    private BufferedImage[] boss = new BufferedImage[16];

    private Animation animationLeft;
    private Animation animationRight;

    public Boss(double x, double y, Game game, Controller controller, int health) {
        super(x,y);

        this.health = health;
        this.game = game;
        this.controller = controller;
        this.spriteSheet = new SpriteSheet(this.game.getSpriteSheetBoss());

        this.boss[0] = this.spriteSheet.grabDragonImage(1, 1, 72, 72);
        this.boss[1] = this.spriteSheet.grabDragonImage(2, 1, 72, 72);
        this.boss[2] = this.spriteSheet.grabDragonImage(3, 1, 72, 72);
        this.boss[3] = this.spriteSheet.grabDragonImage(4, 1, 72, 72);
        this.boss[4] = this.spriteSheet.grabDragonImage(5, 1, 72, 72);
        this.boss[5] = this.spriteSheet.grabDragonImage(6, 1, 72, 72);
        this.boss[6] = this.spriteSheet.grabDragonImage(7, 1, 72, 72);
        this.boss[7] = this.spriteSheet.grabDragonImage(8, 1, 72, 72);
        this.boss[8] = this.spriteSheet.grabDragonImage(1, 2, 72, 72);
        this.boss[9] = this.spriteSheet.grabDragonImage(2, 2, 72, 72);
        this.boss[10] = this.spriteSheet.grabDragonImage(3, 2, 72, 72);
        this.boss[11] = this.spriteSheet.grabDragonImage(4, 2, 72, 72);
        this.boss[12] = this.spriteSheet.grabDragonImage(5, 2, 72, 72);
        this.boss[13] = this.spriteSheet.grabDragonImage(6, 2, 72, 72);
        this.boss[14] = this.spriteSheet.grabDragonImage(7, 2, 72, 72);
        this.boss[15] = this.spriteSheet.grabDragonImage(8, 2, 72, 72);

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
    }

    public void tick() {

        if(super.getX() <= this.game.getPlayer1X()) {
            super.setX(super.getX() + 1);
            this.animationLeft.runAnimation();
        }

        if(super.getX() > this.game.getPlayer1X()) {
            super.setX(super.getX() - 1);
            this.animationRight.runAnimation();
        }

        if(super.getY() > this.game.getPlayer1Y()) {
            super.setY(super.getY() - 1);
        }

        if(super.getY() < this.game.getPlayer1Y()) {
            super.setY(super.getY() + 1);
        }

    }

    public void render(Graphics graphics) {

        if(super.getX() > this.game.getPlayer1X()) {
            this.animationRight.drawAnimation(graphics, super.getX(), super.getY(), 0);
        } else {
            this.animationLeft.drawAnimation(graphics, super.getX(), super.getY(), 0);
        }

    }

    public Rectangle getBounds() {
        return new Rectangle((int)super.getX() , (int)super.getY(), 72, 72);
    }
}
