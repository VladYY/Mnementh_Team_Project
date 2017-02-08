
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
    static int HP = 5;

    public static int calculateHealthPoints(int HPinput, boolean nest, boolean enemy)
    {
        if (nest)
        {
            HP = HPinput - 2;
        }

        if (enemy)
        {
            HP = HPinput - 1;
        }
        return HP;
    }
}
