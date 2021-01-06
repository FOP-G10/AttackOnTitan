package attackontitan.frontend.world;

import attackontitan.backend.game.GameBackend;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Stats {

    private final GameBackend gameProcess;

    private int hour;
    private int coin;

    private static boolean hovering = false;
    private static final Rectangle rect = new Rectangle(320-80, 12 * Tile.TILE_HEIGHT, 60, 30);

    public Stats(GameBackend gameProcess) {
        this.gameProcess = gameProcess;
    }

    public void tick() {
        this.coin = gameProcess.getCoin();
        this.hour = gameProcess.getHour();
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        int startY = 13;
        g.drawString("Hour: " + this.hour + "    " + "Coin: " + this.coin, 15, startY * Tile.TILE_HEIGHT - 15);
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
