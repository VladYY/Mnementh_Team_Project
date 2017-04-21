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

    private static final String IMAGE_PATH = "/resources/gfx/fire.png";
    private static final int RIGHT_DIRECTION = 1;
    private static final int LEFT_DIRECTION = 2;
    private static final int DOWN_DIRECTION = 3;
    private static final int UP_DIRECTION = 4;
    private static final int VELOCITY = 10;

    private BufferedImage image;
    private int direction;
    private Game game;
    private Controller controller;
    private int damage;

    public Fire(double x, double y, int direction, Game game, Controller controller, int damage) throws IOException {
        super(x,y);
        this.setDirection(direction);
        this.setImage();
        this.setGame(game);
        this.setController(controller);
        this.setDamage(damage);
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
            case RIGHT_DIRECTION:
                super.setX(super.getX() + VELOCITY);
                break;
            case LEFT_DIRECTION:
                super.setX(super.getX() - VELOCITY);
                break;
            case DOWN_DIRECTION :
                super.setY(super.getY() + VELOCITY);
                break;
            case UP_DIRECTION:
                super.setY(super.getY() - VELOCITY);
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

    private void setImage() throws IOException {
        this.image = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH));
    }

    private void setDirection(int direction) {
        this.direction = direction;
    }

    private void setGame(Game game) {
        this.game = game;
    }

    private void setController(Controller controller) {
        this.controller = controller;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    private void detectBossCollision() {
        for (int i = 0; i < this.game.getBossEntities().size(); i++) {
            BossEntity tempEnt = this.game.getBossEntities().get(i);

            if (Physics.collision(this, tempEnt)){
                this.controller.removeEntity(this);
                tempEnt.setHealth(tempEnt.getHealth() - this.getDamage());
            }

            if(tempEnt.getHealth() <= 0) {
                try {
                    Music.bossDies();
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

            if (Physics.collision(this, tempEnt)){
                try {
                    Music.enemyDie();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.controller.removeEntity(tempEnt);
                this.controller.removeEntity(this);
                this.game.setEnemyKilled(this.game.getEnemyKilled() + 1);

                if(tempEnt.isHunter()) {
                   this.controller.addEntity(new HealthRestorerImpl(this.getX(), this.getY(), this.controller, this.game, 30));
                }
            }
        }
    }
}
