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

        this.setAnimation();
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
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

        // Boss HP BAR
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        Font fnt2 = new Font("arial", Font.BOLD, 20);

        graphics.setColor(Color.RED);
        graphics.fillRect(505, 5, 300, 50);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(505, 5, this.health, 50);

        graphics.setColor(Color.WHITE);
        graphics.drawRect(505, 5, 300, 50);

        graphics.setColor(Color.white);
        graphics.setFont(fnt1);
        graphics.drawString(this.health / 2 + "%", 620, 42);
        graphics.setFont(fnt2);
        graphics.drawString("Gnarl", 820, 37);

    }

    public Rectangle getBounds() {
        return new Rectangle((int)super.getX() , (int)super.getY(), 144, 144);
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
    }
}
