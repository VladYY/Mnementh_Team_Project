import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;

    public static final int SCALE = 2;
    private static final long serialVersionUID = 1L;
    public static STATE State = STATE.MENU;
    public static int direction;
    public final String TITLE = "Mnementh the game";
    private boolean running = false;
    private Thread thread;
    private BufferedImage image = ImageLoader.loadImage("/resources/gfx/Mnementh-Dragon.jpg");
    private BufferedImage dragonImage = null;
    private BufferedImage caveImage = null;
    private BufferedImage battlegroundImage = ImageLoader.loadImage("resources/gfx/battleGround.png");
    private BufferedImage spriteSheet = null;
    private BufferedImage spriteSheetDragon = null;
    private Battleground battleground;
    private Player player;
    private Cave cave;
    private Menu menu;
    private Controller controller;

    public LinkedList<FriendlyEntity> friendlyEN;
    public LinkedList<EnemyEntity> enemyEN;
    public LinkedList<CaveEntity> caveEN;

    private int count_enemy = 5;
    private int enemy_killed = 0;

    public static void main(String[] args) throws Exception {
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

        Music.music();
        game.start();
        if (Game.State == STATE.END)
        {
            game.stop();
        }
    }

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            this.dragonImage = loader.loadImage("resources/gfx/red_dragonRight.png");
            this.caveImage = loader.loadImage("resources/gfx/cave.png");
            this.spriteSheet = loader.loadImage("resources/gfx/gorgon_spriteSheet.png");
            this.spriteSheetDragon = loader.loadImage("resources/gfx/dragon_spriteSheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());

        controller = new Controller(this);
        battleground = new Battleground(this);
        player = new Player(200, 200, this, controller);
        //////////Test Cave Position//////////
        int x = 550;
        int y = 70;
        //////////////////////////////////////////////
        cave = new Cave(x, y, this, controller);
        menu = new Menu(this);


        friendlyEN = controller.getFriendly();
        enemyEN = controller.getEnemy();
        caveEN = controller.getCave();



        // Test add enemy
        controller.createEnemy(this.count_enemy);
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

    public int getCount_enemy() {
        return count_enemy;
    }

    public void setCount_enemy(int count_enemy) {
        this.count_enemy = count_enemy;
    }

    private void tick() {
        if (State == STATE.GAME) {
            player.tick();
            controller.tick();
        }


        if (this.controller.getEnemy().size() == 0) {
            this.setCount_enemy(this.getCount_enemy() + 1);
            this.controller.createEnemy(this.count_enemy);
        }
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
        if (State == STATE.GAME) {
            battleground.render(graphics);
            cave.render(graphics);
            player.render(graphics);
            controller.render(graphics);

            //Score
            graphics.setColor(Color.white);
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            graphics.setFont(fnt1);
            graphics.drawString("Kills:" + enemy_killed, 1150, 35);

            //HP BAR
            graphics.setColor(Color.RED);
            graphics.fillRect(5, 5, 200, 50);

            graphics.setColor(Color.GREEN);
            graphics.fillRect(5, 5, PlayerHealth.hp, 50);

            graphics.setColor(Color.WHITE);
            graphics.drawRect(5, 5, 200, 50);

        } else if (State == STATE.MENU) {
            menu.render(graphics);
        }
        else if (State == STATE.END)
        {
            graphics.drawImage(ImageLoader.loadImage("resources/gfx/Dragonborn6.jpg"), 0,0,getWidth(),getHeight(),null);
            graphics.setColor(Color.white);
            Font fnt1 = new Font("arial", Font.ITALIC, 60);
            graphics.setFont(fnt1);
            graphics.drawString("" + enemy_killed, WIDTH / 2 + 315, 315);
        }

        graphics.dispose();
        bs.show();
    }

    //Movement
    public void keyPressed(KeyEvent k) throws IOException {
        int key = k.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            player.setVelX(2);
            if (!this.player.getDirection().equals("right")) {
                this.player.setDirection("right");
                this.player.setDirectionChanged(true);
            }

        } else if (key == KeyEvent.VK_LEFT) {
            player.setVelX(-2);
            if (!this.player.getDirection().equals("left")) {
                this.player.setDirection("left");
                this.player.setDirectionChanged(true);
            }
        } else if (key == KeyEvent.VK_DOWN) {
            player.setVelY(2);
        } else if (key == KeyEvent.VK_UP) {
            player.setVelY(-2);
        }
        else if (key == KeyEvent.VK_D)
        {
            direction = 1;
            Music.dragonFire();
            controller.addEntity(new Fire(player.getX(), player.getY(), direction, this));
        }
        else if (key == KeyEvent.VK_A)
        {
            direction = 2;
            Music.dragonFire();
            controller.addEntity(new Fire(player.getX(), player.getY(), direction, this));
        }
        else if (key == KeyEvent.VK_S)
        {
            direction = 3;
            Music.dragonFire();
            controller.addEntity(new Fire(player.getX(), player.getY(), direction, this));
        }
        else if (key == KeyEvent.VK_W)
        {
            direction = 4;
            Music.dragonFire();
            controller.addEntity(new Fire(player.getX(), player.getY(), direction, this));
        }
    }

    public int getEnemy_killed() {
        return this.enemy_killed;
    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
    }

    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();

        if (State == STATE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                player.setVelX(0);
            } else if (key == KeyEvent.VK_LEFT) {
                player.setVelX(0);
            } else if (key == KeyEvent.VK_DOWN) {
                player.setVelY(0);
            } else if (key == KeyEvent.VK_UP) {
                player.setVelY(0);
            } else if (key == KeyEvent.VK_ESCAPE) {
                State = State.MENU;

            }
        }
    }

    public BufferedImage getDragonImage() {
        return this.dragonImage;
    }

    public BufferedImage getCaveImage() {
        return this.caveImage;
    }

    public BufferedImage getBattlegroundImage() {
        return this.battlegroundImage;
    }

    public BufferedImage getSpriteSheet() {
        return this.spriteSheet;
    }

    public BufferedImage getSpriteSheetDragon() {
        return this.spriteSheetDragon; }

    public static enum STATE {
        MENU,
        GAME,
        HELP, END
    }

}
