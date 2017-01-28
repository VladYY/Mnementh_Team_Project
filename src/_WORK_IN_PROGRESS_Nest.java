import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class _WORK_IN_PROGRESS_Nest extends Rectangle {

       private int x, y;

       public _WORK_IN_PROGRESS_Nest(int x, int y, int width, int height){
         setBounds(x,y,width,height);
           this.x = x;
           this.y = y;
       }

       public void draw(Graphics graphics) {
           String imgPath = "\\Mnementh_Team_Project\\resources\\abyss-egg.png";
           BufferedImage img = null;
           try {
               img = ImageIO.read(new File("abyss-egg.png"));
           } catch (IOException e) {
           }
           graphics.drawImage(img,this.x,this.y,null);
       }

   //   public void setDx(int x) {
   //       this.x = this.x + (x * 4);
   ////   }
////
  //   //  public void setDy(int y) {
       //    this.y = this.y + (y * 4);
    //   }
   //}
}
