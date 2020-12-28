package attackontitan.frontend.entities;

import attackontitan.backend.game.Game;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class ColossusTitan extends attackontitan.backend.gameobjects.titans.ColossusTitan {

    private float x, y;

    protected attackontitan.backend.gameobjects.titans.ColossusTitan titan;
    private Game gameProcess;

    public static ArrayList<ColossusTitan> allColossus = new ArrayList<>();

    public ColossusTitan(float x, float y) {
        super();
        this.x = x;
        this.y = y;

        allColossus.add(this);
    }

    public void tick() {

    }

    public void render(Graphics g, MouseManager mouseManager) {
        if(this.hp <= 0) {
            return;
        }
        g.drawImage(Asset.colossusTitan, (int)y * Tile.TILE_WIDTH, (int)x * Tile.TILE_HEIGHT, null);

        int mouseX = mouseManager.getMouseX();
        int mouseY = mouseManager.getMouseY();

        boolean checkX = mouseX >= (int)y * Tile.TILE_WIDTH && mouseX <= (int)y * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
        boolean checkY = mouseY >= (int)x * Tile.TILE_HEIGHT && mouseY <= (int)x * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT;

        if(checkX && checkY) {
            g.setColor(Color.lightGray);
            g.drawString("HP: " + this.hp, ((int)y) * Tile.TILE_WIDTH, ((int)x) * Tile.TILE_HEIGHT);
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
