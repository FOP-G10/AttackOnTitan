package attackontitan.frontend.entities;

import attackontitan.backend.game.Game;
import attackontitan.backend.gameobjects.titans.Titan;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class ArmouredTitan extends attackontitan.backend.gameobjects.titans.ArmouredTitan {

    private float x, y;

    private attackontitan.backend.gameobjects.titans.ArmouredTitan titan;

    protected Game gameProcess;

    public static ArrayList<ArmouredTitan> allArmoured = new ArrayList<>();

    public ArmouredTitan(float x, float y) {
        super();
        this.x = x;
        this.y = y;
        allArmoured.add(this);
    }

    public void tick() {

    }

    public void render(Graphics g, MouseManager mouseManager) {
        if (this.hp <= 0) {
            return;
        }
        g.drawImage(Asset.armouredTitan, (int)y * Tile.TILE_WIDTH, (int)x * Tile.TILE_HEIGHT, null);


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