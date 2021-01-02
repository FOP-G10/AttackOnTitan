package attackontitan.frontend.state;

import attackontitan.frontend.audio.AudioStuff;
import attackontitan.frontend.game.Game;
import attackontitan.frontend.gfx.Asset;

import java.awt.*;

public class MenuState extends State{

    private Game game;

    private AudioStuff audioStuff;

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
            audioStuff.stopMusic();
            State.setCurrentState(new GameState(game));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Asset.menu, 0, 0, null);
    }
}
