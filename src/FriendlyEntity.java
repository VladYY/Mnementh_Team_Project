import java.awt.*;

public interface FriendlyEntity {
    void tick();
    void render(Graphics g);
    double getX();
    double getY();
    Rectangle getBounds();
}
