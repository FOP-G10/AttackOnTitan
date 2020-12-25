package attackontitan.frontend.state;

import attackontitan.backend.game.Process;
import attackontitan.frontend.entities.ArmouredTitan;
import attackontitan.frontend.entities.ColossusTitan;
import attackontitan.frontend.game.Game;
import attackontitan.frontend.world.World;

import java.awt.*;
import java.util.Scanner;

public class GameState extends State{

    private World world;
    private ArmouredTitan armouredTitan;
    private ColossusTitan colossusTitan;

    public GameState(Game game) {
        super(game);
        world = new World("");
        armouredTitan = new ArmouredTitan(3, 0);
        colossusTitan = new ColossusTitan(0, 9);
    }

    @Override
    public void tick() {
        world.tick();
        armouredTitan.tick();
        colossusTitan.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        armouredTitan.render(g);
        colossusTitan.render(g);
    }
}
