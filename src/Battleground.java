import java.awt.*;
import java.awt.image.BufferedImage;

public class Battleground {

    private BufferedImage battleground;
    private int x, y;

    public Battleground(Game game) {

        this.x = 0;
        this.y = 0;
        DragonImage battlegroundImage = new DragonImage(game.getBattlegroundImage());
        battleground = battlegroundImage.grabImage();

    }
    public void render(Graphics graphics) {
        graphics.drawImage(battleground, x, y, null);
    }
}
