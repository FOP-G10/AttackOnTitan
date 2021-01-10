package attackontitan.frontend.entities;

import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class ArmouredTitan extends attackontitan.backend.gameobjects.titans.ArmouredTitan {

    private int x, y;

    public static ArrayList<ArmouredTitan> allArmoured = new ArrayList<>();
    public static boolean armouredCondition = true;

    public ArmouredTitan(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        allArmoured.add(this);
    }

    public void tick(boolean hardMode) {
        if(this.hp <= 0) {
            return;
        }
        this.armouredAttack();
        this.moveArmouredForward();
        for (Wall wall: Wall.walls) {
            if (wall.getWall().getHp() <= 0) {
                Wall.wallCondition = false;
                break;
            }
        }
    }

    public void moveArmouredForward() {
        int row = this.getX();
        int col = this.getY();
        attackontitan.frontend.entities.ArmouredTitan arTitan = this;
        int step;
        step = arTitan.moveForward();

        if (step + row < 10) {
            arTitan.setX(row+step);
            arTitan.setY(col);

//            System.out.println("The armoured titan moved forward.");
        } else {
//            System.out.println("The armoured titan did not move forward.");
        }
    }

    private void moveArmouredSideways() {
        int row = this.getX();
        int col = this.getY();

        attackontitan.frontend.entities.ArmouredTitan arTitan = this;

        int step;
        do {
            step = arTitan.moveSideways();
        } while (col + step < 0 || col + step >= 10);

        arTitan.setX(row);
        arTitan.setY(col+step);

//        System.out.println("The armoured titan moved sideways.");

    }

    protected void armouredAttack() {
        int[] index = {this.getX(), this.getY()};
        if (index[0] == 9) {
            if (Weapon.weapons[index[1]].getWeapon().getLevel() > 0) {
                Weapon.weapons[index[1]].getWeapon().damage();
//                System.out.println("The armoured titan attacked the weapon on wall " + index[1]);
            } else {
                attackontitan.frontend.entities.ArmouredTitan focus;

                focus = this;

                if ((focus.getExtraChance() == 0)) {
                    this.moveArmouredSideways();
//                    System.out.println("The armoured titan reached line 9 but did not attack. ");
                } else {
                    Wall.walls[index[1]].getWall().damage(focus.attack());
//                    System.out.println("The armoured titan attacked the wall " + index[1]);
                }
            }
        }
    }



    public void render(Graphics g, MouseManager mouseManager) {
        if (this.hp <= 0) {
            return;
        }
        g.drawImage(Asset.armouredTitan, y * Tile.TILE_WIDTH, x * Tile.TILE_HEIGHT, null);


        int mouseX = mouseManager.getMouseX();
        int mouseY = mouseManager.getMouseY();

        boolean checkX = mouseX >= y * Tile.TILE_WIDTH && mouseX <= y * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
        boolean checkY = mouseY >= x * Tile.TILE_HEIGHT && mouseY <= x * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT;

        if(checkX && checkY) {
            g.setColor(Color.lightGray);
            g.drawString("Armoured Titan", y * Tile.TILE_WIDTH, (x * Tile.TILE_HEIGHT) - 40);
            g.drawString("HP: " + this.hp, y * Tile.TILE_WIDTH, (x * Tile.TILE_HEIGHT) - 20);
            g.drawString("Attack Point: " + this.attackPoint, y * Tile.TILE_WIDTH, (x * Tile.TILE_HEIGHT));

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