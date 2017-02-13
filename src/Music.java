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
        String gongFile = "./resources/audio/defense line.au";
        InputStream in = new FileInputStream(gongFile);
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

}
