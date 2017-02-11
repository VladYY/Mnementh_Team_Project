import java.awt.*;

/**
 * Created by George on 11-Feb-17.
 */
public interface Entity {
    void tick();
    void render(Graphics g);

    double getX();
    double getY();

}
