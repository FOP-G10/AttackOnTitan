package attackontitan.frontend.state;

import attackontitan.frontend.audio.AudioStuff;
import attackontitan.frontend.game.Game;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuState extends State{

    private final Game game;

    private static AudioStuff audioStuff;

    private static Rectangle mute = new Rectangle(9* Tile.TILE_WIDTH, 0, 32, 32);
    private static boolean hoveringMute = false;

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
        if(!hoveringMute && game.getMouseManager().isLeftPressed()) {
            String ans = JOptionPane.showInputDialog("Choose game mode: \nA) Easy\nB) Hard");
            if (ans != null && ans.length() > 0) {
                audioStuff.stopMusic();
                State.setCurrentState(new GameState(game, ans.toUpperCase().charAt(0) == 'B'));
            }
            game.getMouseManager().noPressed();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Asset.menu, 0, 0, null);
    }

    public static void onMouseMoved(MouseEvent e) {
        hoveringMute = mute.contains(e.getX(), e.getY());
    }

    public static void onMouseReleased() {
        if(hoveringMute && audioStuff.playing) {
            audioStuff.stopMusic();
        }else if(hoveringMute) {
            audioStuff.playMusic();
        }
    }
}
