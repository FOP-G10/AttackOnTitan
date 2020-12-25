package attackontitan.frontend.entities;

import attackontitan.backend.game.Game;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;

public class ColossusTitan extends Entity{

    protected attackontitan.backend.gameobjects.titans.ColossusTitan colossusTitan;
    private Game game;

    public ColossusTitan(float x, float y) {
        super(x, y);
        colossusTitan = new attackontitan.backend.gameobjects.titans.ColossusTitan();
    }

    @Override
    public void tick() {
        if (x < 9) {
            x += 0.1;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Asset.colossusTitan, (int)x * Tile.TILE_WIDTH, (int)y * Tile.TILE_HEIGHT, null);
    }
}
