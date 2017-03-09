package interfaces;

import java.awt.*;

/**
 * Created by dvikt on 8.3.2017 г..
 */
public interface BossEntity {

    int getHealth();
    void setHealth(int health);
    void tick();
    void render(Graphics g);
    Rectangle getBounds();
}
