import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by MARIO on 2/13/2017.
 */
public class Music {

    public static void music() throws IOException {
        String gongFile = "./resources/audio/defense line.wav";
        InputStream in = new FileInputStream(gongFile);
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    public static void dragonFire()throws IOException {
        String dr = "./resources/audio/DragonFire.wav";
        InputStream in = new FileInputStream(dr);
        AudioStream audioStream2 = new AudioStream(in);
        AudioPlayer.player.start(audioStream2);
    }
}

