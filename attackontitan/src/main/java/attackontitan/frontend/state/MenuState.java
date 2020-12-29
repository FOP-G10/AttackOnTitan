package attackontitan.frontend.state;

import attackontitan.frontend.game.Game;

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

    }
}
