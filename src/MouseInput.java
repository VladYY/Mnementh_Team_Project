import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Magdalena on 9.2.2017 г..
 */
public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

/** public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
 public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
 public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
 */
//Play Button
        if (mx >= Game.WIDTH / 2 + 270 && mx <= Game.WIDTH / 2 + 390) {
            if (my >= 150 && my <= 200) {
                //Pressed PlayButton
                Game.State = Game.STATE.GAME;
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
