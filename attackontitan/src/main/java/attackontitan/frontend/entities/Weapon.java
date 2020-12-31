package attackontitan.frontend.entities;

import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Weapon extends Entity{

    private attackontitan.backend.gameobjects.Weapon weapon;

    public static Weapon[] weapons = new Weapon[10];

    private boolean hovering;
    private Rectangle rect;

    public Weapon(int x, int y) {
        super(x, y);
        this.weapon = new attackontitan.backend.gameobjects.Weapon();
        weapons[x] = this;
        rect = new Rectangle(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    public static void init() {
        for(int i=0; i<10; i++) {
            weapons[i] = new Weapon(i, 10);
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g, MouseManager mouseManager) {
        for (Weapon weapon: weapons) {
            g.drawImage(Asset.weapon, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
            g.setColor(Color.WHITE);
            g.drawString("Lvl " + this.weapon.getLevel(), x * Tile.TILE_WIDTH, (y-1) * Tile.TILE_HEIGHT);
      }
    }

    public void onMouseReleased(MouseEvent e) {
        System.out.println("detected");
        if(hovering) {
            this.weapon.upgrade();
            GameState.gameProcess.payCoin(1);
        }
    }

    public void onMouseMove(MouseEvent e) {
        hovering = rect.contains(e.getX(), e.getY());
        if(hovering) {
            System.out.println("CAPTURE");
        }

    }

    public attackontitan.backend.gameobjects.Weapon getWeapon() {
        return weapon;
    }
}
