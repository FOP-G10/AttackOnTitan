package attackontitan.frontend.world;

import attackontitan.backend.game.GameBackend;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;

public class Stats {

    private final GameBackend gameProcess;
    private int hour;
    private int coin;

    public Stats(GameBackend gameProcess) {
        this.gameProcess = gameProcess;
    }

    public void tick() {
        this.coin = gameProcess.getCoin();
        this.hour = gameProcess.getHour();
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        int startY = 13;
        g.drawString("Hour: " + this.hour + "    " + "Coin: " + this.coin, 15, startY * Tile.TILE_HEIGHT - 15);
    }
}
