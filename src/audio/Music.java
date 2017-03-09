package audio;

import app.Game;
import enums.StateSound;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by MARIO on 2/13/2017.
 */
public class Music {

    public static AudioStream audioStream;

    public static void music() throws IOException {
        String gongFile = "./resources/audio/Forest_Night.wav";
        InputStream in = new FileInputStream(gongFile);
        audioStream = new AudioStream(in);
    }

    public static void dragonFire() throws IOException {
        String dr = "./resources/audio/DragonFire.wav";
        InputStream in = new FileInputStream(dr);
        AudioStream audioStream2 = new AudioStream(in);

        if (Game.stateSound == StateSound.ON) {
        AudioPlayer.player.start(audioStream2);
        }
    }

    public static void enemyDie() throws IOException {
        String enDie = "./resources/audio/EnemyDie.wav";
        InputStream in = new FileInputStream(enDie);
        AudioStream audioStream3 = new AudioStream(in);

        if (Game.stateSound == StateSound.ON) {
        AudioPlayer.player.start(audioStream3);
        }
    }

    public static void dragonRoar() throws IOException {
        String dragonRoar = "./resources/audio/DragonRoar.wav";
        InputStream in = new FileInputStream(dragonRoar);
        AudioStream audioStream4 = new AudioStream(in);

        if (Game.stateSound == StateSound.ON) {
        AudioPlayer.player.start(audioStream4);
        }
    }

    public static void bossDies() throws IOException {
        String bossDies = "./resources/audio/BossDies.wav";
        InputStream in  = new FileInputStream(bossDies);
        AudioStream audioStream5 = new AudioStream(in);

        if (Game.stateSound == StateSound.ON) {
            AudioPlayer.player.start(audioStream5);
        }
    }
}

