package interfaces;

import java.awt.*;

public interface BossEntity {

    int getHealth();
    void setHealth(int health);
    void tick();
    void render(Graphics g);
    Rectangle getBounds();
}
