import java.awt.*;

/**
 * Created by George on 11-Feb-17.
 */

public interface FriendlyEntity {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();
    public double getX();
    public double getY();


}
