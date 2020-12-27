package attackontitan.frontend.world;

import attackontitan.backend.game.Game;
import attackontitan.backend.world.Ground;
import attackontitan.backend.game.Process;
import attackontitan.frontend.tiles.Tile;

import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;

public class World {

    private int width, height;
    private int[][] tileDisplay;

    private Game gameProcess;

    public World(Game gameProcess) {
        this.width = 10;
        this.height = 11;
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

        for (int x=0; x<width; x++) {
            if (gameProcess.getWalls()[x].showWeapon().getLevel() > 0) {
                Tile.tiles[2].render(g, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT - 16);
            }
            g.setColor(Color.white);
            g.drawString(String.valueOf(gameProcess.getWalls()[x].getHp()), x * Tile.TILE_WIDTH, 11 * Tile.TILE_HEIGHT);
        }

        g.drawString("HOUR: " + gameProcess.getHour(), (this.width-2) * Tile.TILE_WIDTH - 10, Tile.TILE_HEIGHT);
        g.drawString("Coin: " + gameProcess.getCoin(), (this.width-2) * Tile.TILE_WIDTH - 10, Tile.TILE_HEIGHT * 2);
    }

    public Tile getTile(int x, int y) {
        return Tile.tiles[tileDisplay[y][x]];
    }

    private void loadWorld() {
        Process process = new Process(false);
        Ground ground = new Ground(process);
        tileDisplay = new int[height][width];

        for(int y=0; y<height-1; y++) {
            for(int x=0; x<width; x++) {
                tileDisplay[y][x] = 0;
            }
        }
        for (int x=0; x<width; x++) {
            tileDisplay[height-1][x] = 1;
        }
    }
}
