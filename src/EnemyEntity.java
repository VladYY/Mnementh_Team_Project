import java.awt.*;


public interface EnemyEntity {
    public void tick();
    public void render(Graphics g);
    public double getX();
    public double getY();

    public Rectangle getBounds();
}
