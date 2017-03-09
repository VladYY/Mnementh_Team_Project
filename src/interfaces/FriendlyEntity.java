package interfaces;

import java.awt.*;

public interface FriendlyEntity {

    int getDamage();
    void tick();
    void render(Graphics g);
    double getX();
    double getY();
    Rectangle getBounds();
}
