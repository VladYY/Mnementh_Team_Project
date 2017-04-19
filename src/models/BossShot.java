package models;

import animation.Animation;
import app.Game;
import interfaces.BossShotEntity;
import spitesheets.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BossShot extends DefaultObject implements BossShotEntity {

    private static final int SHOT_INCREASE_DAMAGE_MULTIPLIER = 20;
    private static final int RIGHT_DIRECTION = 1;
    private static final int LEFT_DIRECTION = 2;
    private static final int VELOCITY = 5;

    private BufferedImage[] shotImages;
    private SpriteSheet spriteSheet;
    private Animation animation;
    private int direction;
    private Game game;
    private int damage;

    protected BossShot(double x, double y, int direction, Game game, int damage) {
        super(x, y);
        this.setDirection(direction);
        this.setGame(game);
        this.setDamage(damage);
        this.setSpriteSheet();
        this.setShotImages();
        this.setAnimation();
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public double getY() {
        return super.getY();
    }

    public int getDamage(){
        return this.damage;
    }

    @Override
    public void tick() {
        switch (this.direction) {
            case RIGHT_DIRECTION:
                super.setX(super.getX() + VELOCITY);
                break;
            case LEFT_DIRECTION:
                super.setX(super.getX() - VELOCITY);
                break;
        }

        this.animation.runAnimation();
    }

    @Override
    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, super.getX(), super.getY(), 0);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)super.getX(), (int)super.getY(), 96, 48);
    }

    private void setAnimation() {
        int col = 1;
        int row = 1;
        if(this.direction == LEFT_DIRECTION) {
            row += 1;
            for (int i = 0; i < this.shotImages.length; i++) {
                this.shotImages[i] = this.spriteSheet.grabBossShotImage(col, row, 96, 48);
                col++;
            }
        } else {
            for (int i = 0; i < this.shotImages.length; i++) {
                this.shotImages[i] = this.spriteSheet.grabBossShotImage(col, row, 96, 48);
                col++;
            }
        }

        this.animation = new Animation(5, this.shotImages[0], this.shotImages[1], this.shotImages[2], this.shotImages[3]);
    }

    private void setShotImages() {
        this.shotImages = new BufferedImage[4];
    }

    private void setSpriteSheet() {
        this.spriteSheet = new SpriteSheet(this.game.getSpriteSheetBossShot());
    }

    private void setDirection(int direction) {
        this.direction = direction;
    }

    private void setGame(Game game) {
        this.game = game;
    }

    private void setDamage(int damage) {
        this.damage = damage * SHOT_INCREASE_DAMAGE_MULTIPLIER;
    }
}
