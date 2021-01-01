package attackontitan.frontend.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioStuff {

    public static Clip clip;

    public AudioStuff(String path) {
        this.setUpMusic(path);
    }

    public void setUpMusic(String musicLocation) {
        try {
            File musicFile = new File(musicLocation);

            if(musicFile.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
                clip = AudioSystem.getClip();

                clip.open(audioInputStream);
            }
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
