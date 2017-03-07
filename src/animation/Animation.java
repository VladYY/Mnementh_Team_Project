package animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;

    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private BufferedImage img5;
    private BufferedImage img6;
    private BufferedImage img7;
    private BufferedImage img8;


    private BufferedImage currentImg;
    
    //3 frame
    public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3){
        this.speed = speed;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        frames = 3;
    }

    public Animation(int speed,
                     BufferedImage img1,
                     BufferedImage img2,
                     BufferedImage img3,
                     BufferedImage img4,
                     BufferedImage img5,
                     BufferedImage img6,
                     BufferedImage img7,
                     BufferedImage img8){

        this.speed = speed;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
        this.img6 = img6;
        this.img7 = img7;
        this.img8 = img8;

        frames = 8;
    }


    public void runAnimation(){
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }

    public void nextFrame(){
            switch (frames) {
                case 3 :
                    if(count == 0)
                        currentImg = img1;
                    if(count == 1)
                        currentImg = img2;
                    if(count == 2)
                        currentImg = img3;

                    count++;

                    if(count > frames)
                        count = 0;
                    break;
                case 8:
                    if(count == 0)
                        currentImg = img1;
                    if(count == 1)
                        currentImg = img2;
                    if(count == 2)
                        currentImg = img3;
                    if(count == 3)
                        currentImg = img4;
                    if(count == 4)
                        currentImg = img5;
                    if(count == 5)
                        currentImg = img6;
                    if(count == 6)
                        currentImg = img7;
                    if(count == 7)
                        currentImg = img8;

                    count++;

                    if(count > frames)
                        count = 0;
                    break;
            }
    }

    public void drawAnimation(Graphics g, double x, double y, int offset){
        g.drawImage(currentImg, (int)x - offset, (int)y, null);
    }
}

