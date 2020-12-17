package attackontitan.frontend;

import attackontitan.frontend.game.Game;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Attack On Titan", 600, 400);
        game.start();
    }
}
