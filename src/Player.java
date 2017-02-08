
import java.awt.*;

public class Player extends Rectangle {
    private int x, y;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(this.x, this.y, this.width, this.height);

    }

    public void setDx(int x) {
        this.x = this.x + (x * 4);
    }

    public void setDy(int y) {
        this.y = this.y + (y * 4);
    }
    
    //Player HP
    static int hp = 5;

    public static int calculateHealthPoints(int inputHP, boolean nest, boolean enemy)
    {
        if (nest)
        {
            hp = inputHP - 2;
        }

        if (enemy)
        {
            hp = inputHP - 1;
        }
        return hp;
    }
}
