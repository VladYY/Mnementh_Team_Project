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
                    //Pressed PlayButton
                    try {
                        Music.dragonRoar();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Game.State = Game.STATE.GAME;
                }
            }

            if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 390) {
                if (my >= 250 && my <= 300) {
                    //Pressed HelpButton
                    Game.State = Game.STATE.HELP;
                }
            }
            //Quit Button
            if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 390) {
                if (my >= 350 && my <= 400) {
                    //Pressed QuitButton
                    System.exit(1);

                }
            }
        }
        else if (Game.State == Game.STATE.END)
        {
            if (mx >= Game.WIDTH / 2 + 190 && mx <= Game.WIDTH / 2 + 490){
                if (my >= 340 && my <= 440)
                {
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
