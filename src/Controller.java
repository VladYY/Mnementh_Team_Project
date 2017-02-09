import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private LinkedList<Fire> f = new LinkedList<>();

    Game game;

    public Controller(Game game){
        this.game = game;
    }

    Fire TempFire;

    public void tick(){

        for (int i = 0; i < f.size(); i++) {
            TempFire = f.get(i);

            TempFire.tick();
        }
    }

    public void render (Graphics graphics) {
        for (int i = 0; i < f.size(); i++) {
            TempFire = f.get(i);

            TempFire.render(graphics);
        }
    }

    public void addFire(Fire block){
        f.add(block);
    }

    public void removeFire(Fire block){
        f.remove(block);
    }
}
