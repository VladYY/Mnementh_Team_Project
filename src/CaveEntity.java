import java.awt.*;

public interface CaveEntity {
    void tick();
    void render(Graphics g);
    double getX();
    double getY();
    Rectangle getBounds();
}
