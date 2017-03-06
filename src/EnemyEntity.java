import java.awt.*;

public interface EnemyEntity {

    void tick();
    void render(Graphics g);
    Rectangle getBounds();
}
