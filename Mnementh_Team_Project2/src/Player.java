import javafx.geometry.HPos;

import java.awt.*;

public class Player extends Rectangle{
    private int x, y;

    public Player(int x, int y, int width, int height){
        setBounds (x,y,width,height);
        this.x = x;
        this.y = y;
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

    public int HP = 100;

    public int HP(int HPinput, boolean nest, boolean enemy)
    {
        if (nest)
        {
            HP = HPinput - 5;
        }

        if (enemy)
        {
            HP = HPinput - 1;
        }
        return HP;
    }

}
