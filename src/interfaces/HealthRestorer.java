package interfaces;

import java.awt.*;

public interface HealthRestorer {

    int getHealthRestoreQuantity();
    void render(Graphics graphics);
    Rectangle getBounds();
    void tick();
}
