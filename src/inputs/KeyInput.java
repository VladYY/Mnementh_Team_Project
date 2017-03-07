package inputs;

import app.Game;
import enums.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyInput extends KeyAdapter {

    private Game game;

    public KeyInput(Game game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent k) {
        if (Game.gameState == GameState.GAME_LEVEL_ONE) {
            try {
                this.game.keyPressed(k);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void keyReleased(KeyEvent k) {
        this.game.keyReleased(k);
    }
}
