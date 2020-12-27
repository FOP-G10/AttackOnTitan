package attackontitan.frontend.state;

import attackontitan.backend.game.Process;
import attackontitan.frontend.entities.ArmouredTitan;
import attackontitan.frontend.entities.ColossusTitan;
import attackontitan.frontend.game.Game;
import attackontitan.frontend.world.Stats;
import attackontitan.frontend.world.World;

import java.awt.*;
import java.util.Scanner;

public class GameState extends State{

    private World world;
    private Stats stats;

    private ArmouredTitan armouredTitan;
    private ColossusTitan colossusTitan;

    private attackontitan.backend.game.Game gameProcess;

    public GameState(Game game) {
        super(game);
        world = new World(game.gameProcess);
        stats = new Stats(game.gameProcess);
//        this.gameProcess = new attackontitan.backend.game.Game(false);
//        armouredTitan = new ArmouredTitan(game.gameProcess, 3, 0);
//        colossusTitan = new ColossusTitan(game.gameProcess, 0, 9);
    }

    @Override
    public void tick() {
        world.tick();
        stats.tick();
//        this.gameProcess.playerTurn();
//        this.gameProcess.titanTurn();
//        this.gameProcess.incrementHour(1);
        if(!ArmouredTitan.allArmoured.isEmpty()) {
            for(ArmouredTitan titan: ArmouredTitan.allArmoured) {
                titan.tick();
            }
        }

        if(!ColossusTitan.allColossus.isEmpty()) {
            for(ColossusTitan titan: ColossusTitan.allColossus) {
                titan.tick();
            }
        }

    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        stats.render(g);
        if(!ArmouredTitan.allArmoured.isEmpty()) {
            for(ArmouredTitan titan: ArmouredTitan.allArmoured) {
                titan.render(g);
            }
        }

        if(!ColossusTitan.allColossus.isEmpty()) {
            for(ColossusTitan titan: ColossusTitan.allColossus) {
                titan.render(g);
            }
        }
    }
}
