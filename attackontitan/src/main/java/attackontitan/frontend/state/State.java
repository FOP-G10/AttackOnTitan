package attackontitan.frontend.state;

import attackontitan.frontend.game.Game;

import java.awt.*;

public abstract class State {

    protected Game game;
    protected static State currentState = null;
    protected static State previousState = null;

    public State(Game game) {
        this.game = game;
    }

    public static void setCurrentState(State state) {
        previousState = currentState;
        currentState = state;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void returnPreviousState() {
        State temp = currentState;
        currentState = previousState;
        previousState = currentState;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
