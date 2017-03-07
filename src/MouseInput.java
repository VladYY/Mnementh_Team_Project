import enums.GameState;
import enums.StateSound;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        //Play Button
        if (Game.gameState == GameState.MENU) {
            if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 370) {
                if (my >= 150 && my <= 200) {
                    //Pressed Play Button
                    try {
                        Music.dragonRoar();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Game.gameState = GameState.GAME;
                }
            }

            //Help Button
            if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 370) {
                if (my >= 250 && my <= 300) {
                    //Pressed Help Button
                    Game.gameState = GameState.HELP;
                    if (mx >= 900 && mx <= 1000) {
                        if (my >= 600 && my <= 650) {
                            //Pressed Back Button
                            Game.gameState = GameState.MENU;
                        }
                    }
                }
            }

            //Quit Button
            if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 370) {
                if (my >= 350 && my <= 400) {
                    //Pressed Quit Button
                    System.exit(1);
                }
            }

            //Sound Button
            if (mx >= 1050 && mx <= 1250) {
                if (my >= 25 && my <= 75) {
                    if (Game.stateSound == StateSound.ON) {
                        Game.stateSound = StateSound.OFF;
                    } else {
                        Game.stateSound = StateSound.ON;
                    }
                }
            }
        } else if (Game.gameState == GameState.HELP) {
            //Back Button
            if (mx >= 900 && mx <= 1000) {
                if (my >= 600 && my <= 650) {
                    //Pressed Back Button
                    Game.gameState = GameState.MENU;
                }
            }
        } else if (Game.gameState == GameState.END) {
            //PlayAgain Button
            if (mx >= Game.WIDTH / 2 + 190 && mx <= Game.WIDTH / 2 + 490) {
                if (my >= 340 && my <= 440) {
                    Game.gameState = GameState.GAME;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
