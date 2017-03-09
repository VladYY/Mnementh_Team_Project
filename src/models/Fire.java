package models;

import app.Game;
import audio.Music;
import collisions.Physics;
import controllers.Controller;
import interfaces.BossEntity;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fire extends DefaultObject implements FriendlyEntity {

    private BufferedImage image;
    private int direction;
    private Game game;
    private Controller controller;
    private int damage;

    public Fire(double x, double y, int direction, Game game, Controller controller, int damage) throws IOException {
        super(x,y);
        this.direction = direction;
        this.image = ImageIO.read(getClass().getResourceAsStream("/resources/gfx/fire.png"));
        this.game = game;
        this.controller = controller;
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public double getX() {
        return super.getX();
    }

    public double getY() {
        return super.getY();
    }

    public void tick() {
        switch (this.direction) {
            case 1:
                super.setX(super.getX() + 10);
                break;
            case 2:
                super.setX(super.getX() - 10);
                break;
            case 3:
                super.setY(super.getY() + 10);
                break;
            case 4:
                super.setY(super.getY() - 10);
                break;
        }

        this.detectEnemyCollision();

        this.detectBossCollision();

    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, (int)super.getX(), (int)super.getY(), null);
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)super.getX(), (int)super.getY(), 32, 32);
    }

    private void detectBossCollision() {
        for (int i = 0; i < this.game.getBossEntities().size(); i++) {
            BossEntity tempEnt = this.game.getBossEntities().get(i);

            if (Physics.Collision(this, tempEnt)){
                this.controller.removeEntity(this);
                tempEnt.setHealth(tempEnt.getHealth() - this.getDamage());
            }

            if(tempEnt.getHealth() <= 0) {
                try {
                    Music.enemyDie();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.controller.removeEntity(tempEnt);
            }
        }
    }

    private void detectEnemyCollision() {
        for (int i = 0; i < this.game.getEnemyEntities().size(); i++) {
            EnemyEntity tempEnt = this.game.getEnemyEntities().get(i);

            if (Physics.Collision(this, tempEnt)){
                try {
                    Music.enemyDie();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.controller.removeEntity(tempEnt);
                this.controller.removeEntity(this);
                this.game.setEnemyKilled(this.game.getEnemyKilled() + 1);
            }
        }
    }
}
