package app;

import audio.Music;
import controllers.Controller;
import enums.GameState;
import enums.StateSound;
import image.loaders.BufferedImageLoader;
import image.loaders.ImageLoader;
import inputs.KeyInput;
import inputs.MouseInput;
import interfaces.EnemyEntity;
import interfaces.FriendlyEntity;
import menu.Menu;
import models.Battleground;
import models.Fire;
import models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;

    private static final String TITLE = "Mnementh the game";
    private static final int SCALE = 2;
    private static final long serialVersionUID = 1L;

    public static GameState gameState = GameState.MENU;
    public static StateSound stateSound = StateSound.ON;

    private Thread thread;

    private BufferedImage image = ImageLoader.loadImage("/resources/gfx/Mnementh-Dragon.jpg");
    private BufferedImage imageDead = ImageLoader.loadImage("/resources/gfx/Dragonborn6.jpg");
    private BufferedImage imageHelp = ImageLoader.loadImage("/resources/gfx/Dragon3.jpg");
    private BufferedImage battlegroundImage = ImageLoader.loadImage("/resources/gfx/battleGround.png");
    private BufferedImage spriteSheetGorgon = null;
    private BufferedImage spriteSheetDragon = null;

    private Battleground battleground;
    private Player player1;
    private Menu menu;
    private Controller controller;

    private boolean isShooting = false;
    private boolean running = false;

    private LinkedList<FriendlyEntity> friendlyEntities;
    private LinkedList<EnemyEntity> enemyEntities;

    private int countEnemy = 5;
    private int enemyKilled = 0;

    private String highScore = "";

    public int getCountEnemy() {
        return this.countEnemy;
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
        return this.spriteSheetDragon;
    }

    public LinkedList<EnemyEntity> getEnemyEntities() {
        return this.enemyEntities;
    }

    public int getPlayer1Health() {
        return this.player1.getHealth();
    }

    public void setPlayer1Health(int health) {
        this.player1.setHealth(health);
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(Game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Music.music();
        game.start();
        if (Game.gameState == GameState.END) {
            game.stop();
        }
    }

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            this.spriteSheetGorgon = loader.loadImage("/resources/gfx/fixed_gorgon_sheet.png");
            this.spriteSheetDragon = loader.loadImage("/resources/gfx/fixed_dragon_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());

        this.controller = new Controller(this);
        this.battleground = new Battleground(this);
        this.player1 = new Player(200, 200, this, this.controller, 200);

        this.menu = new Menu(this);

        this.friendlyEntities = this.controller.getFriendly();
        this.enemyEntities = this.controller.getEnemy();

        this.controller.createEnemy(this.countEnemy);
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
        while (this.running) {
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

    //Movement
    public void keyPressed(KeyEvent k) throws IOException {
        int key = k.getKeyCode();

        int direction;
        if (key == KeyEvent.VK_RIGHT) {
            this.player1.setVelX(2);
            if (!this.player1.getDirection().equals("right")) {
                this.player1.setDirection("right");
                this.player1.setDirectionChanged();
            }
        } else if (key == KeyEvent.VK_LEFT) {
            this.player1.setVelX(-2);
            if (!this.player1.getDirection().equals("left")) {
                this.player1.setDirection("left");
                this.player1.setDirectionChanged();
            }
        } else if (key == KeyEvent.VK_DOWN) {
            this.player1.setVelY(2);
        } else if (key == KeyEvent.VK_UP) {
            this.player1.setVelY(-2);
        } else if (key == KeyEvent.VK_D && !this.isShooting) {
            this.isShooting = true;
            direction = 1;
            Music.dragonFire();
            this.controller.addEntity(
                    new Fire(this.player1.getX(), this.player1.getY(), direction, this, this.controller));
        } else if (key == KeyEvent.VK_A && !this.isShooting) {
            this.isShooting = true;
            direction = 2;
            Music.dragonFire();
            this.controller.addEntity(
                    new Fire(this.player1.getX(), this.player1.getY(), direction, this, this.controller));
        } else if (key == KeyEvent.VK_S && !this.isShooting) {
            this.isShooting = true;
            direction = 3;
            Music.dragonFire();
            this.controller.addEntity(
                    new Fire(this.player1.getX(), this.player1.getY(), direction, this, this.controller));
        } else if (key == KeyEvent.VK_W && !this.isShooting) {
            this.isShooting = true;
            direction = 4;
            Music.dragonFire();
            this.controller.addEntity(
                    new Fire(this.player1.getX(), this.player1.getY(), direction, this, this.controller));
        }
    }

    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();

        if (Game.gameState == GameState.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                this.player1.setVelX(0);
            } else if (key == KeyEvent.VK_LEFT) {
                this.player1.setVelX(0);
            } else if (key == KeyEvent.VK_DOWN) {
                this.player1.setVelY(0);
            } else if (key == KeyEvent.VK_UP) {
                this.player1.setVelY(0);
            } else if (key == KeyEvent.VK_ESCAPE) {
                Game.gameState = GameState.MENU;
            } else if (key == KeyEvent.VK_D) {
                this.isShooting = false;
            } else if (key == KeyEvent.VK_A) {
                this.isShooting = false;
            } else if (key == KeyEvent.VK_S) {
                this.isShooting = false;
            } else if (key == KeyEvent.VK_W) {
                this.isShooting = false;
            }
        }
    }

    private synchronized void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    private synchronized void stop() {
        if (!this.running) {
            return;
        }
        this.running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    private void tick() {
        if (Game.gameState == GameState.GAME) {
            this.player1.tick();
            this.controller.tick();
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
            this.createBufferStrategy(3);
            return;
        }

        if (highScore.equals("")) {
            highScore = this.getHighScore();
        }

        Graphics graphics = bs.getDrawGraphics();

        if (Game.gameState == GameState.GAME) {
            this.battleground.render(graphics);
            this.player1.render(graphics);
            this.controller.render(graphics);

            //Score
            graphics.setColor(Color.white);
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            graphics.setFont(fnt1);
            graphics.drawString("Kills:" + this.enemyKilled, 1150, 35);

            //HightScore
            graphics.setColor(Color.white);
            Font fnt2 = new Font("arial", Font.BOLD, 25);
            graphics.setFont(fnt2);
            graphics.drawString("Highscore: " + this.highScore, 1100, 65);


            //HP BAR
            graphics.setColor(Color.RED);
            graphics.fillRect(5, 5, 200, 50);

            graphics.setColor(Color.GREEN);
            graphics.fillRect(5, 5, this.player1.getHealth(), 50);

            graphics.setColor(Color.WHITE);
            graphics.drawRect(5, 5, 200, 50);

            graphics.setColor(Color.white);
            graphics.setFont(fnt1);
            graphics.drawString(this.player1.getHealth() / 2 + "%", 75, 42);

        } else if (Game.gameState == GameState.MENU) {
            graphics.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
            this.menu.render(graphics);
        } else if (Game.gameState == GameState.HELP) {
            graphics.drawImage(this.imageHelp, 0, 0, this.getWidth(), this.getHeight(), this);
            this.menu.render(graphics);
        } else if (Game.gameState == GameState.END) {
            this.player1.setVelX(0);
            this.player1.setVelY(0);
            this.player1.setX(200);
            this.player1.setY(200);
            this.player1.setHealth(200);
            this.setCountEnemy(5);
            this.enemyEntities.clear();
            graphics.drawImage(this.imageDead, 0, 0, this.getWidth(), this.getHeight(), this);
            this.menu.render(graphics);
            if (Game.gameState == GameState.GAME) {
                setEnemyKilled(0);
            }
        }

        graphics.dispose();
        bs.show();
    }

    public String getHighScore() {
        //format: Brandon:100
        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("highscore.dat");
            reader = new BufferedReader(readFile);
            return reader.readLine();
        } catch (Exception e) {
            return "0";
        } finally {
            try {
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
