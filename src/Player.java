import enums.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends DefaultObject implements FriendlyEntity {

    private double velX = 0;
    private double velY = 0;
    public int health;
    private Game game;
    private String direction = "right";
    private Controller controller;
    private boolean directionChanged = false;

    private SpriteSheet ss;
    private BufferedImage[] dragon = new BufferedImage[16];
    private Animation animation;

    public Player(double x, double y, Game game, Controller controller, int health) {
        super(x,y);

        this.health = health;
        this.game = game;
        this.controller = controller;
        this.controller.addEntity(this);
        this.ss = new SpriteSheet(game.getSpriteSheetDragon());
        this.dragon[0] = this.ss.grabDragonImage(1, 1, 72, 72);
        this.dragon[1] = this.ss.grabDragonImage(2, 1, 72, 72);
        this.dragon[2] = this.ss.grabDragonImage(3, 1, 72, 72);
        this.dragon[3] = this.ss.grabDragonImage(4, 1, 72, 72);
        this.dragon[4] = this.ss.grabDragonImage(5, 1, 72, 72);
        this.dragon[5] = this.ss.grabDragonImage(6, 1, 72, 72);
        this.dragon[6] = this.ss.grabDragonImage(7, 1, 72, 72);
        this.dragon[7] = this.ss.grabDragonImage(8, 1, 72, 72);

        this.dragon[8] = this.ss.grabDragonImage(1, 2, 72, 72);
        this.dragon[9] = this.ss.grabDragonImage(2, 2, 72, 72);
        this.dragon[10] = this.ss.grabDragonImage(3, 2, 72, 72);
        this.dragon[11] = this.ss.grabDragonImage(4, 2, 72, 72);
        this.dragon[12] = this.ss.grabDragonImage(5, 2, 72, 72);
        this.dragon[13] = this.ss.grabDragonImage(6, 2, 72, 72);
        this.dragon[14] = this.ss.grabDragonImage(7, 2, 72, 72);
        this.dragon[15] = this.ss.grabDragonImage(8, 2, 72, 72);

        this.animation = new Animation(5,
                this.dragon[0],
                this.dragon[1],
                this.dragon[2],
                this.dragon[3],
                this.dragon[4],
                this.dragon[5],
                this.dragon[6],
                this.dragon[7]);
    }

    public void tick() {
        super.setX(super.getX() + this.velX);
        super.setY(super.getY() + this.velY);

        if (super.getX() <= 0) {
            super.setX(0);
        }
        if (super.getX() >= Game.WIDTH * 2 - 50) {
            super.setX(Game.WIDTH * 2 - 50);
        }
        if (super.getY() <= 0) {
            super.setY(0);
        }
        if (super.getY() >= Game.HEIGHT * 2 - 50) {
            super.setY(Game.HEIGHT * 2 - 50);
        }

        if (this.health <= 0) {
            Game.gameState = GameState.END;
        }


        for (int i = 0; i < this.game.getEnemyEntities().size(); i++) {
            EnemyEntity tempEnt = this.game.getEnemyEntities().get(i);

            if (Physics.Collision(this, tempEnt)) {
                try {
                    Music.enemyDie();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.controller.removeEntity(tempEnt);
                this.health -= 10;
                if (this.health <= 0) {
                    Game.gameState = GameState.END;
                }
                this.game.setEnemyKilled(this.game.getEnemyKilled() + 1);
            }
        }

        if (this.directionChanged && this.direction.equals("left")) {
            this.animation = new Animation(5,
                    this.dragon[8],
                    this.dragon[9],
                    this.dragon[10],
                    this.dragon[11],
                    this.dragon[12],
                    this.dragon[13],
                    this.dragon[14],
                    this.dragon[15]);

            this.directionChanged = false;
        } else if (this.directionChanged && this.direction.equals("right")) {
            this.animation = new Animation(5,
                    this.dragon[0],
                    this.dragon[1],
                    this.dragon[2],
                    this.dragon[3],
                    this.dragon[4],
                    this.dragon[5],
                    this.dragon[6],
                    this.dragon[7]);

            this.directionChanged = false;
        }

        this.animation.runAnimation();
    }

    public boolean isDirectionChanged() {

        return this.directionChanged;
    }

    public void setDirectionChanged(boolean directionChanged) {
        this.directionChanged = directionChanged;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void render(Graphics graphics) {
        this.animation.drawAnimation(graphics, super.getX(), super.getY(), 0);
    }

    public double getX() {
        return super.getX();
    }

    public void setX(double x) {
        super.setX(x);
    }

    public double getY() {
        return super.getY();
    }

    public void setY(double y) {
        super.setY(y);
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public  Rectangle getBounds() {
        return new Rectangle((int)super.getX() , (int)super.getY(), 72, 72);
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }



}
