import java.awt.*;

/**
 * Created by George on 11-Feb-17.
 */

public class DefaultObject {

    public double x,y;
    public DefaultObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public  Rectangle getBounds(int width, int height) {
        return new Rectangle((int)x , (int)y, width, height);
    }
}
