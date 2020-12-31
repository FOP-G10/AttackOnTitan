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
        System.out.println(this.weapon.getLevel());
        if (this.weapon.getLevel() == 0) {
            g.drawImage(Asset.weapon0, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
        }else if (this.weapon.getLevel() == 1) {
            g.drawImage(Asset.weapon1, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
        }else if (this.weapon.getLevel() == 2) {
            g.drawImage(Asset.weapon2, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
        }else if (this.weapon.getLevel() == 3) {
            g.drawImage(Asset.weapon3, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
        }
    }

    public void onMouseReleased(MouseEvent e) {
        System.out.println("detected");
        if(hovering) {
            if (this.weapon.upgrade() && GameState.gameProcess.checkEnough(this.weapon.attack())) {
                GameState.gameProcess.payCoin(this.weapon.attack());
            }
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
