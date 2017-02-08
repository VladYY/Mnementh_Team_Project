import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JPanel implements ActionListener, KeyListener {

    Timer timer = new Timer(10, this);
    Player player = new Player(25,25,20,20);

    Player HP1 = new Player(5,0,10,20);
    Player HP2 = new Player(20,0,10,20);
    Player HP3 = new Player(35,0,10,20);
    Player HP4 = new Player(50,0,10,20);
    Player HP5 = new Player(65,0,10,20);

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

        graphics.setColor(Color.ORANGE);
        player.draw(graphics);

        graphics.setColor(Color.GREEN);

        switch (Player.calculateHealthPoints(Player.hp, false, false))
        {
            case 5: HP5.draw(graphics);
            case 4: HP4.draw(graphics);
            case 3: HP3.draw(graphics);
            case 2: HP2.draw(graphics);
            case 1: HP1.draw(graphics);break;
        }
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
