import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Game game;

    public KeyInput(Game game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent k) {
        game.keyPressed(k);
    }

    public void keyReleased(KeyEvent k) {
        game.keyReleased(k);
    }
}
