package attackontitan.frontend.tiles;

import attackontitan.backend.gameobjects.Wall;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    protected BufferedImage texture;
    protected final int id;

    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

    public static Tile[] tiles = new Tile[256];
    public static Tile groundTile = new GroundTile(0);
    public static Tile wallTile = new WallTile(1);
    public static Tile weaponTile = new WeaponTile(2);

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public int getId() {
        return id;
    }
}
