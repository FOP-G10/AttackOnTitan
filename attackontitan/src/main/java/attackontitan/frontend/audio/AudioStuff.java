package attackontitan.frontend.audio;

import javax.sound.sampled.*;
import java.io.*;

public class AudioStuff {

    public static Clip clip;
//    public static BooleanControl volume;
    public boolean playing;

    public AudioStuff(String path) {
        this.setUpMusic(path);
        this.playing = false;
    }

    public void setUpMusic(String musicLocation) {
        try{
            InputStream inputStream = AudioStuff.class.getResourceAsStream(musicLocation);
            InputStream br = new BufferedInputStream(inputStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(br);
            clip = AudioSystem.getClip();
//            volume = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);

            clip.open(audioInputStream);
        }catch(IOException| UnsupportedAudioFileException| LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {
        clip.setFramePosition(0);
        clip.start();
        this.playing = true;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic() {
        clip.stop();
        this.playing = false;
    }

//    public void muteMusic() {
//        volume.setValue(true);
//    }
//
//    public void unmuteMusic() {
//        volume.setValue(false);
//    }
}
