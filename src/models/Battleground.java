package models;

import app.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Battleground {

    private BufferedImage battleground;
    private int x, y;

    public Battleground(BufferedImage battleground) {

        this.x = 0;
        this.y = 0;
        this.battleground = battleground;

    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.battleground, this.x, this.y, null);
    }
}
