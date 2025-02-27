package attackontitan.frontend.entities;

import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Wall extends Entity {
    private final attackontitan.backend.gameobjects.Wall wall;

    public static Wall[] walls = new Wall[10];
    public static boolean wallCondition = true;

    private boolean hovering;
    private final Rectangle rect;

    public Wall(int x, int y) {
        super(x, y);
        this.wall = new attackontitan.backend.gameobjects.Wall();
        walls[x] = (this);
        rect = new Rectangle(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    public static void createWalls() {
        for (int i=0; i<10; i++) {
            walls[i] = new Wall(i,11);
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
        if (this.wall.getHp() > 25) {
            g.drawImage(Asset.wall, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, null);
        } else if (this.wall.getHp() > 10) {
            g.drawImage(Asset.wallDamage1, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, null);
        } else {
            g.drawImage(Asset.wallDamage2, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, null);
        }
        if(this.wall.getHp() > 0) {
            g.setColor(Color.white);
        }else {
            g.setColor(Color.red);
        }

        g.drawString(String.valueOf(Math.max(this.wall.getHp(), 0)), x * Tile.TILE_WIDTH, (y+1) * Tile.TILE_HEIGHT);
    }

    public void onMouseReleased() {
        if(hovering) {
            String upgradeHp = JOptionPane.showInputDialog("How many HP do you want to add up to the wall?");
            if (upgradeHp != null && !upgradeHp.isEmpty()) {
                try {
                    int upgrade = Integer.parseInt(upgradeHp);
                    if (GameState.gameProcess.getCoin() >= upgrade) {
                        this.wall.upgradeWall(upgrade);
                        GameState.gameProcess.payCoin(upgrade);
                    }
                } catch (NumberFormatException ignored) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }
            }
        }
    }

    public void onMouseMove(MouseEvent e) {
        hovering = rect.contains(e.getX(), e.getY());
    }

    public attackontitan.backend.gameobjects.Wall getWall() {
        return wall;
    }
}
