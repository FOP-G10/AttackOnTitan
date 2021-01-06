package attackontitan.frontend.world;

import attackontitan.backend.game.GameBackend;
import attackontitan.backend.world.Ground;
import attackontitan.backend.game.Process;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;

public class World {

    private int width, height;
    private int[][] tileDisplay;

    private GameBackend gameProcess;

    public World(GameBackend gameProcess) {
        this.width = 10;
        this.height = 12;
        this.gameProcess = gameProcess;
        loadWorld();
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return Tile.tiles[tileDisplay[y][x]];
    }

    private void loadWorld() {
        Process process = new Process(false);
        Ground ground = new Ground(process);
        tileDisplay = new int[height][width];

        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                tileDisplay[y][x] = 0;
            }
        }
    }
}
