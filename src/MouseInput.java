import javafx.scene.control.Alert;

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
        if (Game.State == Game.STATE.MENU) {
            if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 390) {
                if (my >= 150 && my <= 200) {
                    //Pressed Play Button
                    try {
                        Music.dragonRoar();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Game.State = Game.STATE.GAME;
                }
            }
//Help Button
            if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 390) {
                if (my >= 250 && my <= 300) {
                    //Pressed Help Button
                    Game.State = Game.STATE.HELP;
                    if (mx >= 900 && mx <= 1100) {
                        if (my >= 600 && my <= 650) {
                            //Pressed Back Button
                            Game.State = Game.STATE.MENU;
                        }
                }
            }
        }
//Quit Button
        if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 390) {
            if (my >= 350 && my <= 400) {
                //Pressed Quit Button
                    System.exit(1);

                }
            }
        }
        else if (Game.State == Game.STATE.HELP){
            if (mx >= 900 && mx <= 1100) {
                if (my >= 600 && my <= 650) {
                    //Pressed Back Button
                    Game.State = Game.STATE.MENU;
                }
            }
        }
            else if (Game.State == Game.STATE.END) {
            if (mx >= Game.WIDTH / 2 + 190 && mx <= Game.WIDTH / 2 + 490) {
                if (my >= 340 && my <= 440) {
                    //TODO
                    System.exit(1);
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
