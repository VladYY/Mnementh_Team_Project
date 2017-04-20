package animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;

    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage... images){
        this.speed = speed;
        this.images = images;
        this.frames = images.length;
    }

    public void runAnimation(){
        this.index++;
        if(this.index > this.speed){
            this.index = 0;
            this.nextFrame();
        }
    }

    public void drawAnimation(Graphics g, double x, double y, int offset){
        g.drawImage(this.currentImg, (int)x - offset, (int)y, null);
    }

    private void nextFrame(){
        this.currentImg = this.images[this.count];

        this.count++;

        if(this.count >= this.frames)
            this.count = 0;

    }
}

