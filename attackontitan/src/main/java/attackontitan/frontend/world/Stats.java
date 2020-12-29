package attackontitan.frontend.world;

import attackontitan.backend.game.Game;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;

public class Stats {

    private Game gameProcess;

    private int startX = 0;
    private int startY = 13;

    private int hour;
    private int coin;

    public Stats(Game gameProcess) {
        this.gameProcess = gameProcess;
    }

    public void tick() {
        this.coin = gameProcess.getCoin();
        this.hour = gameProcess.getHour();
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Hour: " + this.hour + "    " + "Coin: " + this.coin, startX * Tile.TILE_WIDTH, startY * Tile.TILE_HEIGHT);
    }
}
