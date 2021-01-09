package attackontitan.frontend.state;

import attackontitan.frontend.audio.AudioStuff;
import attackontitan.frontend.game.Game;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ModeState extends State{

    public static Rectangle easySelect = new Rectangle(0, 312, 160, 104);
    public static boolean hoveringEasy = false;

    public static Rectangle hardSelect = new Rectangle(161, 312, 160, 104);
    public static boolean hoveringHard = false;

    private static Rectangle backMenu = new Rectangle(9* Tile.TILE_WIDTH, 0, 32, 32);
    private static boolean hoveringMenu = false;

    public ModeState(Game game) {
        super(game);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Asset.mode, 0, 0, null);
        g.setColor(Color.white);
        g.fillRect(9*Tile.TILE_WIDTH, 0, 32, 32);
        g.drawImage(Asset.menuIcon, 9*Tile.TILE_WIDTH, 0, null);
    }

    public static void onMouseMoved(MouseEvent e) {
        hoveringEasy = easySelect.contains(e.getX(), e.getY());
        hoveringHard = hardSelect.contains(e.getX(), e.getY());
        hoveringMenu = backMenu.contains(e.getX(), e.getY());
    }

    public static void onMouseReleased() {
        if (hoveringEasy) {
            State.setCurrentState(new GameState(currentState.game, false));
        }

        if(hoveringHard) {
            State.setCurrentState(new GameState(currentState.game, true));
        }

        if(hoveringMenu) {
            State.setCurrentState(new MenuState(currentState.game));
        }
    }
}
