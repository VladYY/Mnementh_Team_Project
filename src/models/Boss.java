package models;

import animation.Animation;
import app.Game;
import controllers.Controller;
import interfaces.EnemyEntity;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss  extends DefaultObject implements EnemyEntity {

    private double velX = 0;
    private double velY = 0;
    private int health;
    private Game game;
    private Controller controller;

    private SpriteSheet spriteSheet;
    private BufferedImage[] boss = new BufferedImage[16];
    private Animation animation;

    public Boss(double x, double y, Game game, Controller controller, int health) {
        super(x,y);

        this.health = health;
        this.game = game;
        this.controller = controller;
        this.controller.addEntity(this);
        this.spriteSheet = new SpriteSheet(this.game.getSpriteSheetDragon());
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

        this.animation = new Animation(5,
                this.boss[0],
                this.boss[1],
                this.boss[2],
                this.boss[3],
                this.boss[4],
                this.boss[5],
                this.boss[6],
                this.boss[7]);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
