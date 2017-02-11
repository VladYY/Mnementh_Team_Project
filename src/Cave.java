
import java.awt.*;
import java.awt.image.BufferedImage;

public class Cave {

    private int x, y;

    private BufferedImage cave;

    public Cave(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        CaveImage caveImage = new CaveImage(game.getCaveImage());
        cave = caveImage.grabImage();

}
    public void render(Graphics graphics) {
        graphics.drawImage(cave,  x,  y, null);
    }


}
