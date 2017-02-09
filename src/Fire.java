import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fire {

    private double x,y;
    private int direction;
    BufferedImage image;

    public Fire(double x, double y,int direction, Game game) throws IOException {
        this.x = x;
        this.y = y;
        this.direction = direction;
//        DragonImage dragonImage = new DragonImage(game.getDragonImage());
        image = ImageIO.read(getClass().getResourceAsStream("resources/fire.png"));
    }
    public void tick(){
        switch (direction){
            case 1:
                x += 10;
                break;
            case 2:
                x -= 10;
                break;
            case 3:
                y += 10;
                break;
            case 4:
                y -= 10;
                break;
        }
    }
    public void render(Graphics graphics){
        graphics.drawImage(image, (int)x, (int)y, null);
    }
}
