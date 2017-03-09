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
        this.frames = 3;
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

        this.frames = 8;
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
        switch (this.frames) {
            case 3 :
                if(this.count == 0)
                    this.currentImg = this.img1;
                if(this.count == 1)
                    this.currentImg = this.img2;
                if(this.count == 2)
                    this.currentImg = this.img3;

                this.count++;

                if(this.count > this.frames)
                    this.count = 0;
                break;
            case 8:
                if(this.count == 0)
                    this.currentImg = this.img1;
                if(this.count == 1)
                    this.currentImg = this.img2;
                if(this.count == 2)
                    this.currentImg = this.img3;
                if(this.count == 3)
                    this.currentImg = this.img4;
                if(this.count == 4)
                    this.currentImg = this.img5;
                if(this.count == 5)
                    this.currentImg = this.img6;
                if(this.count == 6)
                    this.currentImg = this.img7;
                if(this.count == 7)
                    this.currentImg = this.img8;

                this.count++;

                if(this.count > this.frames)
                    this.count = 0;
                break;
        }
    }
}

