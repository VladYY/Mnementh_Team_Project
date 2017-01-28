import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mnementh");
        setSize(800,800);
        setResizable(false);
        init();
    }

    public void init(){
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1,0,0));
        Screen s = new Screen();

        add(s);

        setVisible(true);
    }

    public static void main(String[] args){
        Frame f = new Frame();
    }


}
