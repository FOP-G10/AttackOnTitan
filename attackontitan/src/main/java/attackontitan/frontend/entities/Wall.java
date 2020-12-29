package attackontitan.frontend.entities;

import attackontitan.backend.game.Game;
import attackontitan.backend.game.Process;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Wall extends Entity {
    private attackontitan.backend.gameobjects.Wall wall;
    private MouseManager mouseManager;

    public static ArrayList<Wall> walls = new ArrayList<>();

    public Wall(int x, int y, MouseManager mouseManager) {
        super(x, y);
        this.wall = new attackontitan.backend.gameobjects.Wall();
        this.mouseManager = mouseManager;
        walls.add(this);
    }

    public static void createWalls(MouseManager mouseManager) {
        for (int i=0; i<10; i++) {
            new Wall(i,10, mouseManager);
            System.out.println(i);
        }
        System.out.println(walls.size());
    }

    @Override
    public void tick() {
        if(this.mouseManager.isLeftPressed()) {
            System.out.println("pressed");
            boolean xRange = this.mouseManager.getMouseX() >= x * Tile.TILE_WIDTH && this.mouseManager.getMouseX() <= x * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
            boolean yRange = this.mouseManager.getMouseY() >= y * Tile.TILE_HEIGHT && this.mouseManager.getMouseY() <= y * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT;
            if(xRange && yRange) {
                System.out.println("Pressed on wall at position " + x + " and " + y);
                String upgradeHp = JOptionPane.showInputDialog("How many HP do you want to add up to the wall?");
                if (!upgradeHp.isEmpty()) {
                    this.wall.upgradeWall(Integer.parseInt(upgradeHp));
                }
            }
        }
    }

    @Override
    public void render(Graphics g, MouseManager mouseManager) {
        g.drawImage(Asset.wall, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,null);
        g.setColor(Color.white);
        g.drawString(String.valueOf(this.wall.getHp()), x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
    }
}
