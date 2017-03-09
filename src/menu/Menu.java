package menu;

import app.Game;
import audio.Music;
import enums.GameState;
import enums.StateSound;
import sun.audio.AudioPlayer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    private Game game;

    private Rectangle playButton;
    private Rectangle helpButton;
    private Rectangle quitButton;
    private Rectangle backButton;
    private Rectangle soundButton;

    public Menu(Game game) {
        this.game = game;

        this.playButton = new Rectangle(Game.WIDTH / 2 + 270, 150, 100, 50);
        this.helpButton = new Rectangle(Game.WIDTH / 2 + 270, 250, 100, 50);
        this.quitButton = new Rectangle(Game.WIDTH / 2 + 270, 350, 100, 50);
        this.backButton = new Rectangle(900, 600, 100, 50);
        this.soundButton = new Rectangle(1050, 25, 200, 50);
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
        ArrayList<String> scores = new ArrayList<>();
        this.game.getScores(scores);

        if (Game.gameState == GameState.MENU) {
            Font fnt0 = new Font("arial", Font.BOLD, 50);
            graphics.setFont(fnt0);
            graphics.setColor(Color.white);
            graphics.drawString("Mnementh the Defender", Game.WIDTH / 2 + 50, 50);

            Font fnt1 = new Font("arial", Font.BOLD, 30);

            graphics.setFont(fnt1);

            //Main Menu's buttons
            graphics.drawString("Play", this.playButton.x + 19, this.playButton.y + 35);
            graphics.drawString("Help", this.helpButton.x + 19, this.helpButton.y + 35);
            graphics.drawString("Quit", this.quitButton.x + 19, this.quitButton.y + 35);
            g2d.draw(this.playButton);
            g2d.draw(this.helpButton);
            g2d.draw(this.quitButton);

            //Sound ON/OFF
            if (Game.stateSound == StateSound.ON) {
                graphics.drawString("SOUND ON", this.soundButton.x + 19, this.soundButton.y + 35);
                AudioPlayer.player.start(Music.audioStream);
            }

            if (Game.stateSound == StateSound.OFF) {
                graphics.drawString("SOUND OFF", this.soundButton.x + 19, this.soundButton.y + 35);
                AudioPlayer.player.stop(Music.audioStream);
            }

            g2d.draw(this.soundButton);

        } else if (Game.gameState == GameState.HELP) {
            String helpString =
                "The player1 (a bronze dragon named Mnementh) have the task to protect the dragon cave" +
                    " (the object that must be defended) and avoid his own death. Each time the dragon hit" +
                    " directly an enemy, losing 5% of his total health, but of course the enemy dies – you " +
                    "can’t hit a dragon like this. When an enemy reach the cave, it disappear in the deep " +
                    "darkness and takes 10% of dragon’s total health. Every next wave is bigger and bigger. " +
                    "You must slay all enemies you can. Protect the cave! Protect the treasure in it!";

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
            graphics.drawString("Back", this.backButton.x + 14, this.backButton.y + 35);
            g2d.draw(this.backButton);
        }else if (Game.gameState == GameState.END) {
            graphics.setColor(Color.white);
            Font fnt1 = new Font("arial", Font.ITALIC, 60);
            graphics.setFont(fnt1);
            graphics.drawString("" + this.game.getEnemyKilled(), Game.WIDTH / 2 + 315, 340);
            graphics.setColor(Color.white);
            graphics.drawString("Play again", Game.WIDTH / 2 + 200, 430);
            graphics.drawRect(Game.WIDTH / 2 + 190, 355, 300, 100);

            graphics.drawString("HIGH SCORES", Game.WIDTH / 2 + 100, 520);
            fnt1 = new Font("arial", Font.ITALIC, 45);
            graphics.setFont(fnt1);
            scores = scores.stream().limit(5).collect(Collectors.toCollection(ArrayList::new));
            int placeIndex = 1;
            int size = 50;
            for (String score : scores) {
                int t = 540 + size;
                graphics.drawString(placeIndex + ". " + score, Game.WIDTH / 2 + 150, t);
                placeIndex++;
                size += 50;
            }


        }
    }
}



