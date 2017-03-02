package interfaces;

import java.awt.*;

public interface EnemyEntity {

    void tick();

    void render(Graphics g);

    double getX();
    double getY();

    Rectangle getBounds();
}
