package attackontitan.frontend.state;

import attackontitan.frontend.game.Game;

import java.awt.*;

public class MenuState extends State{

    public MenuState(Game game) {
        super(game);
    }

    @Override
    public void tick() {
        if(game.getMouseManager().isLeftPressed()) {
            State.setCurrentState(game.getGameState());
        }
    }

    @Override
    public void render(Graphics g) {

    }
}
