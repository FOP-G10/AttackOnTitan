package attackontitan.frontend.world;

import attackontitan.backend.game.GameBackend;
import attackontitan.backend.world.Ground;
import attackontitan.backend.game.Process;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class World {

    private final int width, height;
    private int[][] tileDisplay;

    private final GameBackend gameProcess;

    private static boolean hovering = false;
    private static final Rectangle rect = new Rectangle(320-80, 12 * Tile.TILE_HEIGHT, 60, 30);

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
        g.drawImage(Asset.endturn, 320-80, 12 * Tile.TILE_HEIGHT, null);
    }

    public Tile getTile(int x, int y) {
        return Tile.tiles[tileDisplay[y][x]];
    }

    private void loadWorld() {
        tileDisplay = new int[height][width];

        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                tileDisplay[y][x] = 0;
            }
        }
    }

    public static void onMouseReleased() {
        if(hovering) {
            GameState.nextRound = true;
        }
    }

    public static void onMouseMove(MouseEvent e) {
        hovering = rect.contains(e.getX(), e.getY());
    }
}
