package models;

import java.awt.*;

public abstract class DefaultObject {

    private double x, y;

    protected DefaultObject(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public  Rectangle getBounds(int width, int height) {
        return new Rectangle((int)this.x , (int)this.y, width, height);
    }
}
