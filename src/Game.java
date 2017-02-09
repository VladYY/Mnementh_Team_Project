import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 480;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Mnementh the game";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage dragonImage = null;

    private Player player;
    private Controller controller;

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            dragonImage = loader.loadImage("resources/red_dragon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this));

        player = new Player(200, 200, this);
        controller = new Controller(this);
    }

    private synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    // GAME LOOP.
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double nanoSeconds = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSeconds;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Ticks " + updates + " Frames " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        player.tick();
        controller.tick();
    }


    //Rendering the game.
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();

        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        player.render(graphics);
        controller.render(graphics);

        graphics.dispose();
        bs.show();

    }

    //Movement

    public static int direction;

    public void keyPressed(KeyEvent k) throws IOException {
        int key = k.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            player.setVelX(5);
            direction = 1;
        } else if (key == KeyEvent.VK_LEFT) {
            player.setVelX(-5);
            direction = 2;
        } else if (key == KeyEvent.VK_DOWN) {
            player.setVelY(5);
            direction = 3;
        } else if (key == KeyEvent.VK_UP) {
            player.setVelY(-5);
            direction = 4;
        } else if (key == KeyEvent.VK_SPACE) {
            controller.addFire(new Fire(player.getX(), player.getY(),direction, this ));
        }
    }

    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            player.setVelX(0);
        } else if (key == KeyEvent.VK_LEFT) {
            player.setVelX(0);
        } else if (key == KeyEvent.VK_DOWN) {
            player.setVelY(0);
        } else if (key == KeyEvent.VK_UP) {
            player.setVelY(0);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public BufferedImage getDragonImage() {
        return dragonImage;
    }
}
