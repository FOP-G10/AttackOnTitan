package attackontitan.frontend.entities;

import attackontitan.backend.game.Game;
import attackontitan.backend.game.Process;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ColossusTitan extends attackontitan.backend.gameobjects.titans.ColossusTitan {

    private int x, y;

    protected attackontitan.backend.gameobjects.titans.ColossusTitan titan;
    private Game gameProcess;

    public static ArrayList<ColossusTitan> allColossus = new ArrayList<>();
    public static boolean colossusCondition = true;

    public ColossusTitan(int x, int y) {
        super();
        this.x = x;
        this.y = y;

        allColossus.add(this);
    }

    public void tick() {
        if(this.hp <= 0) {
            return;
        }
        colossusAttack();
        moveColossusSideways();

        for (Wall wall: Wall.walls) {
            if (wall.getWall().getHp() <= 0) {
                Wall.wallCondition = false;
                break;
            }
        }
    }

    protected void colossusAttack() {
        int count = 0;
        for (ColossusTitan colossus: ColossusTitan.allColossus) {
            int[] index = {colossus.getX(), colossus.getY()};
            if (Weapon.weapons[index[1]].getWeapon().getLevel() > 0) {
                Weapon.weapons[index[1]].getWeapon().damage();
                System.out.println("The colossus titan attacked the weapon on wall " + index[1]);
                count ++;
            } else {
                Wall.walls[index[1]].getWall().damage(colossus.attack());

                System.out.println("The colossus titan attacked the wall " + index[1]);
//                System.out.println("HP wall " + index[1] + ": " + Wall.walls[index[1]].getWall().getHp());
                count ++;
            }
        }
        if (count == 0) {
            System.out.println("The colossus titan did not launch an attack.");
        }
    }

    public void moveColossusSideways() {
        for (int i = 0; i < ColossusTitan.allColossus.size(); i++) {
            int row = ColossusTitan.allColossus.get(i).getX();
            int col = ColossusTitan.allColossus.get(i).getY();
            attackontitan.frontend.entities.ColossusTitan colTitan;
            colTitan = ColossusTitan.allColossus.get(i);

            int step;
            do {
                step = colTitan.moveSideways();
            } while (col + step < 0 || col + step >= 10);

            colTitan.setX(row);
            colTitan.setY(col + step);

            System.out.println("The colossus titan moved sideways. ");
        }
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
