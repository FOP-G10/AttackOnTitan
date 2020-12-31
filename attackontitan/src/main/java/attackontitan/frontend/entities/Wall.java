package attackontitan.frontend.entities;

import attackontitan.backend.game.Game;
import attackontitan.backend.game.Process;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Wall extends Entity {
    private attackontitan.backend.gameobjects.Wall wall;
    private MouseManager mouseManager;

    public static Wall[] walls = new Wall[10];
    public static boolean wallCondition = true;

    private boolean hovering;
    private Rectangle rect;

    public Wall(int x, int y, MouseManager mouseManager) {
        super(x, y);
        this.wall = new attackontitan.backend.gameobjects.Wall();
        this.mouseManager = mouseManager;
        walls[x] = (this);
        rect = new Rectangle(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    public static void createWalls(MouseManager mouseManager) {
        for (int i=0; i<10; i++) {
            walls[i] = new Wall(i,11, mouseManager);
            System.out.println(i);
        }
    }

    @Override
    public void tick() {
        int count = 0;
        for(ArmouredTitan armoured: ArmouredTitan.allArmoured) {
            if(armoured.getHp() > 0) {
                count ++;
            }
        }

        if(count == 0 && !ArmouredTitan.allArmoured.isEmpty()) {
            ArmouredTitan.armouredCondition = false;
        }

        count = 0;
        for(ColossusTitan colossus: ColossusTitan.allColossus) {
            if(colossus.getHp() > 0) {
                count ++;
            }
        }

        if(count == 0 && !ColossusTitan.allColossus.isEmpty()) {
            ColossusTitan.colossusCondition = false;
        }
    }

    @Override
    public void render(Graphics g, MouseManager mouseManager) {
        g.drawImage(Asset.wall, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT,null);
        g.setColor(Color.white);
        g.drawString(String.valueOf(Math.max(this.wall.getHp(), 0)), x * Tile.TILE_WIDTH, (y+1) * Tile.TILE_HEIGHT);
    }

    public void onMouseReleased(MouseEvent e) {
        System.out.println("detected");
        if(hovering) {
            System.out.println("Pressed on wall at position " + x + " and " + y);
            String upgradeHp = JOptionPane.showInputDialog("How many HP do you want to add up to the wall?");
            if (!upgradeHp.isEmpty()) {
                int upgrade = Integer.parseInt(upgradeHp);
                if(GameState.gameProcess.getCoin() >= upgrade) {
                    this.wall.upgradeWall(upgrade);
                    GameState.gameProcess.payCoin(upgrade);
                }
            }
        }
    }

    public void onMouseMove(MouseEvent e) {
        hovering = rect.contains(e.getX(), e.getY());
        if(hovering) {
            System.out.println("CAPTURE");
        }

    }

    public attackontitan.backend.gameobjects.Wall getWall() {
        return wall;
    }
}
