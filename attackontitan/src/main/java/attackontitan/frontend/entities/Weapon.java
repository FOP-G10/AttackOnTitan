package attackontitan.frontend.entities;

import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Weapon extends Entity{

    private final attackontitan.backend.gameobjects.Weapon weapon;

    public static Weapon[] weapons = new Weapon[10];

    public static double[] fireballHeight = new double[weapons.length];

    private boolean hovering;
    private final Rectangle rect;

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
        if (this.getWeapon().getLevel() > 0) {
            for (ArmouredTitan armoured : ArmouredTitan.allArmoured) {
                if (armoured.getY() == this.x && armoured.getHp() > 0) {
                    armoured.damage(this.getWeapon().attack());
                    fireballHeight[this.x] = fireballHeight[this.x] == 0 ? 10 : fireballHeight[this.x];
                }
            }
            for (ColossusTitan colossus : ColossusTitan.allColossus) {
                if (colossus.getY() == this.x && colossus.getHp() > 0) {
                    colossus.damage(this.getWeapon().attack());
                    fireballHeight[this.x] = fireballHeight[this.x] == 0 ? 10 : fireballHeight[this.x];
                }
            }
        }
    }

    @Override
    public void render(Graphics g, MouseManager mouseManager) {
        if (this.weapon.getLevel() == 0) {
            g.drawImage(Asset.weapon0, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
        }else if (this.weapon.getLevel() == 1) {
            g.drawImage(Asset.weapon1, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
        }else if (this.weapon.getLevel() == 2) {
            g.drawImage(Asset.weapon2, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
        }else if (this.weapon.getLevel() == 3) {
            g.drawImage(Asset.weapon3, x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, null);
        }

        for(int i=0; i<fireballHeight.length; i++) {
            if(fireballHeight[i] > 0) {
                if ((int)fireballHeight[i] % 2 == 0) {
                    g.drawImage(Asset.fireball1, i * Tile.TILE_WIDTH, (int) fireballHeight[i] * Tile.TILE_HEIGHT, null);
                }else {
                    g.drawImage(Asset.fireball2, i * Tile.TILE_WIDTH, (int) fireballHeight[i] * Tile.TILE_HEIGHT, null);
                }
                fireballHeight[i] -= 0.01;
            }else {
                fireballHeight[i] = (int)fireballHeight[i];
            }
        }

        if (hovering) {
            g.setColor(Color.lightGray);
            g.drawString("Lvl " + this.weapon.getLevel(), x * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT);
        }
    }

    public void onMouseReleased() {
        if(hovering) {
            boolean upgrade = this.weapon.upgrade();
            if (upgrade && GameState.gameProcess.checkCoinEnough(this.weapon.attack())) {
                GameState.gameProcess.payCoin(this.weapon.attack());
            }else if(upgrade) {
                this.weapon.downgrade();
            }
        }
    }

    public void onMouseMove(MouseEvent e) {
        hovering = rect.contains(e.getX(), e.getY());
    }

    public attackontitan.backend.gameobjects.Weapon getWeapon() {
        return weapon;
    }
}
