package attackontitan.frontend.state;

import attackontitan.frontend.audio.AudioStuff;
import attackontitan.frontend.game.Game;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuState extends State{

    private final Game game;

    private static AudioStuff audioStuff;

    private static Rectangle mute = new Rectangle(9* Tile.TILE_WIDTH, 0, 32, 32);
    private static boolean hoveringMute = false;
    private static boolean goMode = false;

    public MenuState(Game game) {
        super(game);
        this.game = game;
        audioStuff = new AudioStuff("/audiotracks/menuAudio.wav");
        audioStuff.playMusic();
    }

    @Override
    public String toString() {
        return "Menu";
    }

    @Override
    public void tick() {
        if(goMode) {
//            String ans = JOptionPane.showInputDialog("Choose game mode: \nA) Easy\nB) Hard");
//            if (ans != null && ans.length() > 0) {
                audioStuff.stopMusic();
//                State.setCurrentState(new GameState(game, ans.toUpperCase().charAt(0) == 'B'));
//            }
            game.getMouseManager().noPressed();
            goMode = false;
            State.setCurrentState(new ModeState(game));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Asset.menu, 0, 0, null);
//        g.setColor(Color.black);
//        g.fillRect(9* Tile.TILE_WIDTH, 0, 32, 32);
        if (audioStuff.playing){
            g.drawImage(Asset.volumeIcon, 9 * Tile.TILE_WIDTH, 0, null);
        } else {
            g.drawImage(Asset.muteIcon, 9 * Tile.TILE_WIDTH, 0, null);
        }
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

        if(!hoveringMute) {
            goMode = true;
        }
    }
}
