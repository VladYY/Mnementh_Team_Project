import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {

    public boolean menuMode;
    public Screen screen;

    public Frame() throws IOException {

            String path = "resources\\Mnementh-Dragon.jpg";
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            JLabel label = new JLabel(new ImageIcon(image));
            JFrame frame = new JFrame();
            frame.setSize(1920, 1080);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(label);
            frame.pack();
            frame.setLocation(0,0);
            frame.setVisible(true);


            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Mnementh");
            setSize(800, 800);
            setResizable(false);
            init();

    }

    public void init() {
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1, 0, 0));
        Screen s = new Screen();

        add(s);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {


        Frame f = new Frame();
    }

}
