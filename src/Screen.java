import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JPanel implements ActionListener, KeyListener {

    Timer timer = new Timer(10, this);
    Player player = new Player(20,20,20,20);

    public Screen() {
        addKeyListener(this);
        setFocusable(true);

        timer.start();
    }

    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }

    public void paint(Graphics graphics) {
        graphics.clearRect(0,0, getWidth(), getHeight());

        player.draw(graphics);

    }

    public void keyPressed(KeyEvent k) {

        switch (k.getKeyCode()){
            case KeyEvent.VK_RIGHT: player.setDx(1); break;
            case KeyEvent.VK_DOWN: player.setDy(1); break;
            case KeyEvent.VK_LEFT: player.setDx(-1); break;
            case KeyEvent.VK_UP: player.setDy(-1); break;

        }

    }

    public void keyTyped(KeyEvent k) {


    }

    public void keyReleased(KeyEvent k) {

    }
}
