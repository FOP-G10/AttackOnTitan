package attackontitan.frontend.audio;

import javax.sound.sampled.*;
import java.io.*;

public class AudioStuff {

    public static Clip clip;

    public AudioStuff(String path) {
        this.setUpMusic(path);
    }

    public void setUpMusic(String musicLocation) {
        try{
            InputStream inputStream = AudioStuff.class.getResourceAsStream(musicLocation);
            InputStream br = new BufferedInputStream(inputStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(br);
            clip = AudioSystem.getClip();

            clip.open(audioInputStream);
        }catch(IOException| UnsupportedAudioFileException| LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {
        clip.setFramePosition(0);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic() {
        clip.stop();
    }
}
