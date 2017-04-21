package models;

import animation.Animation;
import app.Game;
import controllers.Controller;
import interfaces.HealthRestorer;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthRestorerImpl extends DefaultObject implements HealthRestorer {

    private BufferedImage[] images;
    private Game game;
    private Controller controller;
    private SpriteSheet spriteSheet;
    private Animation animation;
    private int health;
    private int ticksToExist;

    protected HealthRestorerImpl(double x, double y, Controller controller, Game game, int health) {
        super(x, y);
        this.game = game;
        this.health = health;
        this.controller = controller;
        this.ticksToExist = 250;
        this.setImages();
        this.setSpriteSheet();
        this.setAnimation();
    }

    @Override
    public int getHealthRestoreQuantity() {
        return this.health;
    }

    @Override
    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, super.getX(), super.getY(), 0);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)super.getX(), (int)super.getY(), 20, 20);
    }

    @Override
    public void tick() {
        this.animation.runAnimation();
        this.ticksToExist--;

        if(this.ticksToExist <= 0) {
            this.controller.removeEntity(this);
        }
    }

    private void setImages() {
        this.images = new BufferedImage[4];
    }

    private void setSpriteSheet() {
        this.spriteSheet = new SpriteSheet(this.game.getSpriteSheetHealth());
    }

    private void setAnimation() {
        int row = 1;
        int col = 1;
        for (int i = 0; i < this.images.length; i++) {
            this.images[i] = this.spriteSheet.grabHealthImage(col, row, 20, 20);
            col++;
        }

        this.animation = new Animation(5, this.images);
    }
}
