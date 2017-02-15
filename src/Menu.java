import java.awt.*;

public class Menu {
    private Game game;

    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 270, 150, 100, 50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 270, 250, 100, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 270, 350, 100, 50);

    public Menu(Game game) {
        this.game = game;
    }

    public void render(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;

        if (game.State == Game.STATE.MENU) {


            Font fnt0 = new Font("arial", Font.BOLD, 50);
            graphics.setFont(fnt0);
            graphics.setColor(Color.white);
            graphics.drawString("Mnementh the Defender", Game.WIDTH / 2 + 50, 50);

            Font fnt1 = new Font("arial", Font.BOLD, 30);
            graphics.setFont(fnt1);
            graphics.drawString("Play", playButton.x + 19, playButton.y + 35);
            graphics.drawString("Help", helpButton.x + 19, helpButton.y + 35);
            graphics.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
            g2d.draw(playButton);
            g2d.draw(helpButton);
            g2d.draw(quitButton);
        } else if (game.State == Game.STATE.HELP) {
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            graphics.setFont(fnt1);
            graphics.setColor(Color.white);
            graphics.drawString("AAAAAAA", Game.WIDTH / 2 + 50, 150);
            graphics.drawString("TEST", Game.WIDTH / 2 + 50, 170);
            graphics.drawString("Back", 458, 239);
        }

    }
}

