import java.awt.*;

/**
 * Created by George on 11-Feb-17.
 */
public interface Entity {
    public void tick();
    public void render(Graphics g);

    public double getX();
    public double getY();

}
