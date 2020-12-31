package attackontitan.frontend.world;

import attackontitan.backend.game.Game;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Stats {

    private Game gameProcess;

    private int startX = 0;
    private int startY = 13;

    private int hour;
    private int coin;

    private static boolean hovering = false;
    private static Rectangle rect = new Rectangle(320-60, 12 * Tile.TILE_HEIGHT, 50, 30);

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
        g.setColor(Color.black);
        g.fillRect(320 - 60, 12 * Tile.TILE_HEIGHT, 50, 30);
    }

    public static void onMouseReleased(MouseEvent e) {
        System.out.println("detected");
        if(hovering) {
            GameState.nextRound = true;
        }
    }

    public static void onMouseMove(MouseEvent e) {
        hovering = rect.contains(e.getX(), e.getY());
        if(hovering) {
            System.out.println("CAPTURE");
        }

    }
}
