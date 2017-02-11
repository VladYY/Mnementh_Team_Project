import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.peer.ComponentPeer;
import java.io.IOException;


import static java.awt.event.KeyEvent.KEY_LOCATION_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 480;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    private static final long serialVersionUID = 1L;
    public static STATE State = STATE.MENU;
    public static int direction;
    public final String TITLE = "Mnementh the game";
    private boolean running = false;
    private Thread thread;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage dragonImage = null;
    private BufferedImage caveImage = null;
    private BufferedImage battlegroundImage = null;
    private Battleground battleground;
    private Player player;
    private Cave cave;
    private Menu menu;
    private Controller controller;



    private int count_enemy = 1;

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

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();

        try {
                dragonImage = loader.loadImage("resources/dragonLeft.png");
            }
            catch (IOException e) {
                e.printStackTrace();

        }
        addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());

        controller = new Controller(this);

        player = new Player(200, 200, this);
        menu = new Menu();

        // Test add enemy
        controller.createEnemy();
    }

    public void initCave() {
        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            caveImage = loader.loadImage("resources/cave.png");
        }
        catch (IOException e) {
            e.printStackTrace();

        }
        controller = new Controller(this);
        int x=180 + (int)(Math.random() * ((370 - 130) + 9));
        int y=85+(int)(Math.random() * ((275 - 75) + 6));

        cave = new Cave(x,y,this);
        menu = new Menu();
    }



    public void initBattleground() {
        BufferedImageLoader loader = new BufferedImageLoader();

        try {
          battlegroundImage = loader.loadImage("resources/battleGround.png");
        }
        catch (IOException e) {
            e.printStackTrace();

        }

      battleground = new Battleground(this);
        menu = new Menu();
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
       initBattleground();
        init();
        initCave();
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

        if (State == STATE.GAME) {
            player.tick();
            controller.tick();
        }
    }

    //Movement

    //Rendering the game.
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();

        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        if (State == STATE.GAME) {
            battleground.render(graphics);
            cave.render(graphics);
            player.render(graphics);
            controller.render(graphics);



            graphics.setColor(Color.gray);
            graphics.fillRect(5, 5, 200, 50);

            graphics.setColor(Color.GREEN);
            graphics.fillRect(5, 5, PlayerHealth.hp, 50);

            graphics.setColor(Color.WHITE);
            graphics.drawRect(5, 5, 200, 50);

        } else if (State == STATE.MENU) {
            menu.render(graphics);
        }
        graphics.dispose();
        bs.show();

    }

    public void keyPressed(KeyEvent k) throws IOException {
        int key = k.getKeyCode();

        if (key == VK_RIGHT) {
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
            controller.addFire(new Fire(player.getX(), player.getY(), direction, this));
        }
    }

    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();

        if (State == STATE.GAME) {
            if (key == VK_RIGHT) {
                player.setVelX(0);
            } else if (key == KeyEvent.VK_LEFT) {
                player.setVelX(0);
            } else if (key == KeyEvent.VK_DOWN) {
                player.setVelY(0);
            } else if (key == KeyEvent.VK_UP) {
                player.setVelY(0);
            }
        }
    }

    public BufferedImage getDragonImage() {
        return dragonImage;
    }

    public BufferedImage getCaveImage() {
        return caveImage;
    }
    public BufferedImage getBattlegroundImage() {
        return battlegroundImage;
    }

    public static enum STATE {
        MENU,
        GAME
    }





}
