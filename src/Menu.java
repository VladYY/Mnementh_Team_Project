import sun.audio.AudioPlayer;

import java.awt.*;

public class Menu {
    Game game = new Game();

    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 270, 150, 100, 50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 270, 250, 100, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 270, 350, 100, 50);
    public Rectangle backButton = new Rectangle(900, 600, 100, 50);
    public Rectangle soundButton = new Rectangle(1050, 25, 200, 50);

    public Menu(Game game) {
        this.game = game;
    }

    public static void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y) {
        FontMetrics m = g.getFontMetrics();
        if (m.stringWidth(text) < lineWidth) {
            g.drawString(text, x, y);
        } else {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for (int i = 1; i < words.length; i++) {
                if (m.stringWidth(currentLine + words[i]) < lineWidth) {
                    currentLine += " " + words[i];
                } else {
                    g.drawString(currentLine, x, y);
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            if (currentLine.trim().length() > 0) {
                g.drawString(currentLine, x, y);
            }
        }
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

            //Main Menu
            graphics.drawString("Play", playButton.x + 19, playButton.y + 35);
            graphics.drawString("Help", helpButton.x + 19, helpButton.y + 35);
            graphics.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
            g2d.draw(playButton);
            g2d.draw(helpButton);
            g2d.draw(quitButton);

            //Sound ON/OFF
            if (Game.StateSound == Game.STATESOUND.ON) {
                graphics.drawString("SOUND ON", soundButton.x + 19, soundButton.y + 35);
                AudioPlayer.player.start(Music.audioStream);
            }

            if (Game.StateSound == Game.STATESOUND.OFF) {
                graphics.drawString("SOUND OFF", soundButton.x + 19, soundButton.y + 35);
                AudioPlayer.player.stop(Music.audioStream);
            }
            g2d.draw(soundButton);


        } else if (game.State == Game.STATE.HELP) {
            String helpString = "The player1 (a bronze dragon named Mnementh) have the task to protect the dragon cave (the object that must be defended) and avoid his own death. Each time the dragon hit directly an enemy, losing 5% of his total health, but of course the enemy dies – you can’t hit a dragon like this. When an enemy reach the cave, it disappear in the deep darkness and takes 10% of dragon’s total health. Every next wave is bigger and bigger. You must slay all enemies you can. Protect the cave! Protect the treasure in it!";

            Font fnt1 = new Font("arial", Font.BOLD, 30);

            graphics.setFont(fnt1);
            graphics.setColor(Color.white);
            graphics.drawString("HELP", (Game.WIDTH / 2) + 280, 100);

            Font fnt2 = new Font("arial", Font.CENTER_BASELINE, 25);


            graphics.setFont(fnt2);
            graphics.setColor(Color.white);
            drawStringMultiLine((Graphics2D) graphics, helpString, helpString.length() * 2 - (helpString.length() / 2), (game.WIDTH / 2), 250);

            graphics.setFont(fnt1);
            graphics.setColor(Color.white);
            graphics.drawString("Back", backButton.x + 14, backButton.y + 35);
            g2d.draw(backButton);
        }
        if (game.State == Game.STATE.END) {

            graphics.setColor(Color.white);
            Font fnt1 = new Font("arial", Font.ITALIC, 60);
            graphics.setFont(fnt1);
            graphics.drawString("" + game.getEnemyKilled(), Game.WIDTH / 2 + 315, 340);
            graphics.setColor(Color.white);
            graphics.drawString("Play again", Game.WIDTH / 2 + 200, 430);
            graphics.drawRect(Game.WIDTH / 2 + 190, 355, 300, 100);
        }

    }

}



