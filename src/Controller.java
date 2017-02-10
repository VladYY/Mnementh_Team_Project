import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private LinkedList<Fire> f = new LinkedList<>();
    private LinkedList<Enemy> e = new LinkedList<>();

    Game game;
    Fire TempFire;
    Enemy TempEnemy;

    public Controller(Game game){

        this.game = game;
    }



    public void tick(){

        for (int i = 0; i < f.size(); i++) {
            TempFire = f.get(i);

            TempFire.tick();
        }

        for (int i = 0; i < e.size(); i++) {


        }
    }

    public void render (Graphics graphics) {
        for (int i = 0; i < f.size(); i++) {
            TempFire = f.get(i);

            TempFire.render(graphics);
        }

        for (int i = 0; i < e.size(); i++) {
            TempEnemy = e.get(i);

            TempEnemy.render(graphics);
        }
    }

    public void createEnemy(Enemy block){
        e.add(block);
    }


    public void addFire(Fire block){
        f.add(block);
    }

    public void removeFire(Fire block){
        f.remove(block);
    }
}
