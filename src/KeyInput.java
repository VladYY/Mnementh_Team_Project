import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyInput extends KeyAdapter {

    Game game;

    public KeyInput(Game game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent k) {
        if (Game.State == Game.STATE.GAME) {
            try {
                game.keyPressed(k);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void keyReleased(KeyEvent k) {
        game.keyReleased(k);
    }
}
