package attackontitan.frontend.state;

import attackontitan.frontend.game.Game;

import java.awt.*;

public abstract class State {

    protected Game game;
    protected static State currentState = null;

    public State(Game game) {
        this.game = game;
    }

    public static void setCurrentState(State state) {
        currentState = state;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
