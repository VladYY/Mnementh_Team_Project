package models;

import interfaces.EnemyEntity;

import java.awt.*;

public class Boss  extends DefaultObject implements EnemyEntity {


    public Boss(double x, double y) {
        super(x, y);
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
