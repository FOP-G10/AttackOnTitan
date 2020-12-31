package attackontitan.frontend.state;

import attackontitan.frontend.game.Game;
import attackontitan.frontend.gfx.Asset;

import java.awt.*;

public class MenuState extends State{

    private Game game;

    public MenuState(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public String toString() {
        return "Menu";
    }

    @Override
    public void tick() {
        if(game.getMouseManager().isLeftPressed()) {
            State.setCurrentState(new GameState(game));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Asset.menu, 0, 0, null);
    }
}
