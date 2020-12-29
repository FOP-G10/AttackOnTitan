package attackontitan.frontend.state;

import attackontitan.backend.game.Process;
import attackontitan.frontend.entities.ArmouredTitan;
import attackontitan.frontend.entities.ColossusTitan;
import attackontitan.frontend.entities.Wall;
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
        Wall.createWalls(game.getMouseManager());
    }

    @Override
    public void tick() {
        world.tick();
        stats.tick();
//        this.gameProcess.playerTurn();
//        this.gameProcess.titanTurn();
//        this.gameProcess.incrementHour(1);

        // player's turn
        if(!Wall.walls.isEmpty()) {
            for(Wall wall: Wall.walls) {
                wall.tick();
            }
        }


        //button to complete


        // titan's turn
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
        int i = 0;
        if(!Wall.walls.isEmpty()) {
            for(Wall wall: Wall.walls) {
                wall.render(g, this.game.getMouseManager());
            }
        }

        if(!ArmouredTitan.allArmoured.isEmpty()) {
            for(ArmouredTitan titan: ArmouredTitan.allArmoured) {
                titan.render(g, this.game.getMouseManager());
            }
        }

        if(!ColossusTitan.allColossus.isEmpty()) {
            for(ColossusTitan titan: ColossusTitan.allColossus) {
                titan.render(g, this.game.getMouseManager());
            }
        }
    }
}
