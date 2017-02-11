import java.awt.*;

/**
 * Created by Magdalena on 9.2.2017 г..
 */
public class Menu {

    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 200, 150, 100, 50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 200, 250, 100, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 200, 350, 100, 50);

    public void render(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        graphics.setFont(fnt0);
        graphics.setColor(Color.white);
        graphics.drawString("Mnementh - the game", Game.WIDTH / 2, 50);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        graphics.setFont(fnt1);
        graphics.drawString("Play", playButton.x + 19, playButton.y + 35);
        graphics.drawString("Help", helpButton.x + 19, helpButton.y + 35);
        graphics.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }
}
