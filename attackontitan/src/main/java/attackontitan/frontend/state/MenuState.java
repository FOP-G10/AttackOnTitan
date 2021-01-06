package attackontitan.frontend.state;

import attackontitan.frontend.audio.AudioStuff;
import attackontitan.frontend.game.Game;
import attackontitan.frontend.gfx.Asset;

import javax.swing.*;
import java.awt.*;

public class MenuState extends State{

    private final Game game;

    private final AudioStuff audioStuff;

    public MenuState(Game game) {
        super(game);
        this.game = game;
        audioStuff = new AudioStuff("/audiotracks/menuAudio2.wav");
        audioStuff.playMusic();
    }

    @Override
    public String toString() {
        return "Menu";
    }

    @Override
    public void tick() {
        if(game.getMouseManager().isLeftPressed()) {
            String ans = JOptionPane.showInputDialog("Choose game mode: \nA) Easy\nB) Hard");
            if (ans != null && ans.length() > 0) {
                audioStuff.stopMusic();
                State.setCurrentState(new GameState(game, ans.toUpperCase().charAt(0) == 'B'));
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Asset.menu, 0, 0, null);
    }
}
