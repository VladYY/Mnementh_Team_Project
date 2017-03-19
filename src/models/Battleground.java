package models;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Battleground {

    private BufferedImage battleground;
    private int x, y;

    public Battleground(BufferedImage battleground) {

        this.setX(0);
        this.setY(0);
        this.setBattleground(battleground);

    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.battleground, this.x, this.y, null);
    }

    private void setBattleground(BufferedImage battleground) {
        this.battleground = battleground;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }
}
