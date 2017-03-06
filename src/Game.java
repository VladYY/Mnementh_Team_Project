import enums.GameState;
import enums.StateSound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {

    private final String TITLE = "Mnementh the game";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;
    public static final int SCALE = 2;
    private static final long serialVersionUID = 1L;

    public static GameState gameState = GameState.MENU;
    public static StateSound stateSound = StateSound.ON;
    private static int direction;

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = ImageLoader.loadImage("/resources/gfx/Mnementh-Dragon.jpg");
    private BufferedImage imageDead = ImageLoader.loadImage("resources/gfx/Dragonborn6.jpg");
    private BufferedImage imageHelp = ImageLoader.loadImage("/resources/gfx/Dragon3.jpg");
    private BufferedImage battlegroundImage = ImageLoader.loadImage("resources/gfx/battleGround.png");
    private BufferedImage spriteSheetGorgon = null;
    private BufferedImage spriteSheetDragon = null;

    private Battleground battleground;
    private Player player1;
    private Menu menu;
    private Controller controller;

    private boolean isShooting = false;

    public LinkedList<FriendlyEntity> friendlyEN;
    public LinkedList<EnemyEntity> enemyEN;

    private  int countEnemy = 5;
    private int enemyKilled = 0;

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
        if (Game.gameState == GameState.END)
        {
            game.stop();
        }
    }

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            this.spriteSheetGorgon = loader.loadImage("resources/gfx/fixed_gorgon_sheet.png");
            this.spriteSheetDragon = loader.loadImage("resources/gfx/fixed_dragon_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());

        controller = new Controller(this);
        battleground = new Battleground(this);
        player1 = new Player(200, 200, this, controller, 200);

        menu = new Menu(this);

        friendlyEN = controller.getFriendly();
        enemyEN = controller.getEnemy();

        // Test add enemy
        controller.createEnemy(this.countEnemy);
    }

    public Player getPlayer1() {
        return this.player1;
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
        if (gameState == GameState.GAME) {
            player1.tick();
            controller.tick();
        }



        if (this.controller.getEnemy().size() == 0) {
            this.setCountEnemy(this.getCountEnemy() + 1);
            this.controller.createEnemy(this.countEnemy);
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


        if (gameState == GameState.GAME) {
            battleground.render(graphics);
            player1.render(graphics);
            controller.render(graphics);

            //Score
            graphics.setColor(Color.white);
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            graphics.setFont(fnt1);
            graphics.drawString("Kills:" + enemyKilled, 1150, 35);

            //HP BAR
            graphics.setColor(Color.RED);
            graphics.fillRect(5, 5, 200, 50);

            graphics.setColor(Color.GREEN);
            graphics.fillRect(5, 5, player1.getHealth(), 50);

            graphics.setColor(Color.WHITE);
            graphics.drawRect(5, 5, 200, 50);

            graphics.setColor(Color.white);
            graphics.setFont(fnt1);
            graphics.drawString(player1.getHealth()/2 + "%", 75, 42);

        } else if (gameState == GameState.MENU) {
            graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            menu.render(graphics);
        } else if (gameState == GameState.HELP) {
            graphics.drawImage(imageHelp, 0, 0, getWidth(), getHeight(), this);
            menu.render(graphics);
        } else if (gameState == GameState.END) {
            player1.setVelX(0);
            player1.setVelY(0);
            player1.setX(200);
            player1.setY(200);
            player1.setHealth(200);
            setCountEnemy(5);
            enemyEN.clear();
            graphics.drawImage(imageDead, 0, 0, getWidth(), getHeight(), this);
            menu.render(graphics);
            if (gameState == GameState.GAME){
                setEnemyKilled(0);
            }
        }

        graphics.dispose();
        bs.show();
    }

    //Movement
    public void keyPressed(KeyEvent k) throws IOException {
        int key = k.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            player1.setVelX(2);
            if (!this.player1.getDirection().equals("right")) {
                this.player1.setDirection("right");
                this.player1.setDirectionChanged(true);
            }

        } else if (key == KeyEvent.VK_LEFT) {
            player1.setVelX(-2);
            if (!this.player1.getDirection().equals("left")) {
                this.player1.setDirection("left");
                this.player1.setDirectionChanged(true);
            }
        } else if (key == KeyEvent.VK_DOWN) {
            player1.setVelY(2);
        } else if (key == KeyEvent.VK_UP) {
            player1.setVelY(-2);
        } else if (key == KeyEvent.VK_D && !isShooting) {
            isShooting = true;
            direction = 1;
            Music.dragonFire();
            controller.addEntity(new Fire(player1.getX(), player1.getY(), direction, this, this.controller));
        } else if (key == KeyEvent.VK_A && !isShooting) {
            isShooting = true;
            direction = 2;
            Music.dragonFire();
            controller.addEntity(new Fire(player1.getX(), player1.getY(), direction, this, this.controller));
        } else if (key == KeyEvent.VK_S && !isShooting) {
            isShooting = true;
            direction = 3;
            Music.dragonFire();
            controller.addEntity(new Fire(player1.getX(), player1.getY(), direction, this, this.controller));
        } else if (key == KeyEvent.VK_W && !isShooting) {
            isShooting = true;
            direction = 4;
            Music.dragonFire();
            controller.addEntity(new Fire(player1.getX(), player1.getY(), direction, this, this.controller));
        }
    }

    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();

        if (gameState == GameState.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                player1.setVelX(0);
            } else if (key == KeyEvent.VK_LEFT) {
                player1.setVelX(0);
            } else if (key == KeyEvent.VK_DOWN) {
                player1.setVelY(0);
            } else if (key == KeyEvent.VK_UP) {
                player1.setVelY(0);
            } else if (key == KeyEvent.VK_ESCAPE) {
                gameState = gameState.MENU;
            }else if (key == KeyEvent.VK_D) {
                isShooting = false;
            } else if (key == KeyEvent.VK_A) {
                isShooting = false;
            } else if (key == KeyEvent.VK_S) {
                isShooting = false;
            } else if (key == KeyEvent.VK_W) {
                isShooting = false;
            }
        }
    }

    public int getCountEnemy() {
        return countEnemy;
    }

    public void setCountEnemy(int countEnemy) {
        this.countEnemy = countEnemy;
    }

    public int getEnemyKilled() {
        return this.enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    public BufferedImage getBattlegroundImage() {
        return this.battlegroundImage;
    }

    public BufferedImage getSpriteSheetGorgon() {
        return this.spriteSheetGorgon;
    }

    public BufferedImage getSpriteSheetDragon() {
        return this.spriteSheetDragon; }


}
