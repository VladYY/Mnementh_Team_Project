package interfaces;

import java.awt.*;

public interface EnemyEntity {

    void tick();
    boolean isHunter();
    void render(Graphics g);
    Rectangle getBounds();
}
