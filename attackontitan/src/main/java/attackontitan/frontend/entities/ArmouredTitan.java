package attackontitan.frontend.entities;

import attackontitan.backend.game.Game;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;

public class ArmouredTitan extends Entity {

    protected attackontitan.backend.gameobjects.titans.Titan titan;

    public ArmouredTitan(float x, float y) {
        super(x, y);
        titan = new attackontitan.backend.gameobjects.titans.ArmouredTitan();
    }

    public void tick() {
//        if(!this.gameProcess.armouredIndex.isEmpty()) {
//            x = this.gameProcess.armouredIndex.get(0)[0];
//            y = this.gameProcess.armouredIndex.get(0)[1];
//        }else {
//            x = -1;
//            y = -1;
//        }
        if(y < 9) {
            y += 0.1;
        }

    }

    public void render(Graphics g) {
        g.drawImage(Asset.armouredTitan, (int)x * Tile.TILE_WIDTH, (int)y * Tile.TILE_HEIGHT, null);
    }
}