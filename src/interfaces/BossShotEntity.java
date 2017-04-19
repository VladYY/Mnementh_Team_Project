package interfaces;

import java.awt.*;

public interface BossShotEntity {

    void tick();
    void render(Graphics g);
    int getDamage();
    double getX();
    double getY();
    Rectangle getBounds();
}
