/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.game;

import attackontitan.backend.gameobjects.titans.ArmouredTitan;
import attackontitan.backend.gameobjects.titans.ColossusTitan;
import attackontitan.backend.gameobjects.titans.Titan;
import attackontitan.backend.gameobjects.Wall;
import attackontitan.backend.player.PlayerAccount;
import attackontitan.backend.world.Ground;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Autumn
 */
public class Process extends PlayerAccount {
    protected Ground ground;
    protected Wall[] walls;

    protected int hour;
    protected ArrayList<Integer[]> colossusIndex;
    protected ArrayList<Integer[]> armouredIndex;

    public boolean addedTitans = false;
    private final boolean hardMode;

    private static final Random r = new Random();

    public Process(boolean hardMode) {
        super(hardMode);
        this.ground = new Ground(this);
        this.walls = this.createWalls();
        this.hour = 0;
        this.colossusIndex = new ArrayList<>();
        this.armouredIndex = new ArrayList<>();
        this.hardMode = hardMode;
    }

    // Methods below creates the walls and weapon and print them out in the console
    
    protected void printBoard() {
        System.out.println();
        System.out.println(this.ground);
        this.printWallsAndWeapons();
        System.out.println();
    }
    
    private Wall[] createWalls() {
        Wall[] tmp = new Wall[10];
        for (int i=0; i<10; i++) {
            tmp[i] = new Wall();
        }
        return tmp;
    }

    private void printWallsAndWeapons() {
        int maxWeaponRow = 0;

        // Get the maximum level of all weapons on all walls
        // To know how many lines is used for printing the weapons
        for (Wall wall: this.walls) {
            maxWeaponRow = Math.max(wall.showWeapon().getLevel(), maxWeaponRow);
        }
        for (int row=maxWeaponRow; row>0; row--) {
            System.out.print("  ");
            for (Wall wall: this.walls) {
                if (wall.showWeapon().getLevel() >= row) {
                    System.out.print("** ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }

        // Leave a few space because we don't know to print at the same column as the numbering
        System.out.print("  ");

        // Print walls
        for (int i=0; i<this.walls.length; i++) {
            System.out.print("-- ");
        }

        // Print the labels indicating the -- are walls
        System.out.println("The Wall");

        // Leave a few space because we don't know to print at the same column as the numbering
        System.out.print("  ");

        // Print the index of the walls
        for (int i=0; i<this.walls.length; i++) {
            System.out.printf("%2d ", i);
        }

        // Add a label indicating that the row is index
        System.out.println("index");

        // Leave a few space because we don't know to print at the same column as the numbering
        System.out.print("  ");

        // Print the hp of the walls
        for (Wall wall: walls) {
            System.out.printf("%2d ", wall.getHp());
        }

        // Add a label indicating that the row is HP
        System.out.println("HP");

        // Leave a few space because we don't know to print at the same column as the numbering
        System.out.print("  ");

        // THIS IS NOT PRINTING WALLS
        // Just printing the ending lines
        for (int i=0; i<this.walls.length; i++) {
            System.out.print("-- ");
        }
    }

    // Methods below controls the game flow which is by controlling the hour

    public int getHour() {
        return hour;
    }

    public void incrementHour(int hour) {
        this.hour += hour;
    }

    // Methods describing the process for the player's turn below
    
    protected void upgradeWeapon(String wallIndices) {
        int count = 0;
        String[] indices = wallIndices.trim().split("");
        for (String index: indices) {
            System.out.print("upgrading weapon on wall " + index + "\t");
            Wall focusWall = this.walls[Integer.parseInt(index)];
            boolean weaponUpgraded = focusWall.showWeapon().upgrade();
            if (weaponUpgraded && this.checkCoinEnough(focusWall.showWeapon().attack())) {
                this.payCoin(focusWall.showWeapon().attack());
                count ++;
            } else if(weaponUpgraded && !this.checkCoinEnough(focusWall.showWeapon().attack())) {
                focusWall.showWeapon().downgrade();
            }
        }
        
        if (count == 0) {
            System.out.println("No weapon upgraded. ");
        }
    }

    protected void weaponAttack() {
        int count = 0;
        for (int i=0; i<this.walls.length; i++) {
            if (this.walls[i].showWeapon().getLevel() > 0) {
                for (Titan[][] row: this.ground.getGround()) {
                    if (row[i] != null && row[i].length > 0){
                        for (int j=0; j<row[i].length; j++) {
                            Titan focus = row[i][j];
                            if(focus != null) {
                                focus = focus.damage(this.walls[i].showWeapon().attack());
                                count++;
                                row[i][j] = focus;
                                System.out.println("The weapon on wall " + i + " attacks");
                            }
                        }
                    }
                }
            }
        }
        this.updateArmouredIndex();
        this.updateColossusIndex();
        if (count == 0) {
            System.out.println("The weapons on all walls did not launch an attack... ");
        }
    }

    protected void upgradeWall(String wallIndex, String hpUpgrade) {
        String[] index = wallIndex.trim().replace(" ", "").split("");
        String[] hpUp = hpUpgrade.trim().replace(" ", "").split("");
        for (int i=0; i<index.length; i++) {
            int indexInt = Integer.parseInt(index[i]);
            int hpUpInt = Integer.parseInt(hpUp[i]);
            if (this.checkCoinEnough(hpUpInt)) {
                this.walls[indexInt].upgradeWall(hpUpInt);
                this.payCoin(hpUpInt);
                System.out.println("Wall " + indexInt + " upgrade successfully. ");
            }
        }
    }

    // Methods describing the titans' turn below starting from this line
    
    protected void addColossus() {
        if (this.hour == 5) {
            int randomInt;
            do {
                randomInt = r.nextInt(10);
            }while(this.ground.getElementOnGround(9, randomInt, 1) != null);
            ColossusTitan newCol = new ColossusTitan();
            int position = 1;
            this.ground.setElementOnGround(9, randomInt, 1, newCol);
            Integer[] coor = {9, randomInt, position};
            this.colossusIndex.add(coor);
            System.out.println("A colossus titan is added to the ground. ");
            this.addedTitans = true;
        } else {
            System.out.println("No colossus titan added. ");
        }
    }
    
    protected void addArmoured() {
        if (this.hour > 0 && ((!this.hardMode && this.hour == 5) || (this.hardMode && this.hour % 5 == 0))) {
            int randomInt;
            do {
                randomInt = r.nextInt(10);
            }while(this.ground.getElementOnGround(0, randomInt, 0) != null);
            ArmouredTitan arTitan = new ArmouredTitan();
            int position = 0;
            this.ground.setElementOnGround(0, randomInt, 0, arTitan);
            Integer[] coor = {0, randomInt, position};
            this.armouredIndex.add(coor);
            System.out.println("A armoured titan is added to the ground. ");
            this.addedTitans = true;
        } else {
            System.out.println("No armoured titan added. ");
        }
    }
    
    protected void moveColossusSideways() {
        for (Integer[] index : this.colossusIndex) {
            int row = index[0];
            int col = index[1];
            int position = index[2];
            ColossusTitan colTitan;
            colTitan = (ColossusTitan) this.ground.getElementOnGround(row, col, position);

            int step;
            do {
                step = colTitan.moveSideways();
            } while (col + step < 0 || col + step >= this.ground.getElementOnGround(row).length);

            if (this.ground.getElementOnGround(row, col + step, 1) == null) {
                this.ground.setElementOnGround(row, col, position, null);
                this.ground.setElementOnGround(row, col + step, 1, colTitan);
                index[2] = 1;
                index[1] = col + step;
                System.out.println("The colossus titan moved sideways. ");
            } else {
                System.out.println("The colossus titan does not move sideways. ");
            }
        }
    }
    
    protected void moveArmouredForward() {
        System.out.println(this.armouredIndex.size());
        for (Integer[] index : this.armouredIndex) {
            int row = index[0];
            int col = index[1];
            int position = index[2];
            ArmouredTitan arTitan = (ArmouredTitan) this.ground.getElementOnGround(row, col, position);
            int step;
            step = arTitan.moveForward();

            if (step + row < this.ground.getNumberOfRows()) {

                if (this.ground.getElementOnGround(row + step, col, 0) == null) {
                    this.ground.setElementOnGround(row, col, position, null);
                    this.ground.setElementOnGround(row + step, col, 0, arTitan);
                    index[2] = 0;
                    index[0] = row + step;
                    System.out.println("The armoured titan moved forward.");
                } else {
                    System.out.println("The armoured titan did not move forward.");
                }
            } else {
                System.out.println("The armoured titan did not move forward.");
            }
        }
    }
    
    private void moveArmouredSideways(Integer[] index) {
        int row = index[0];
        int col = index[1];
        int position = index[2];

        ArmouredTitan arTitan = (ArmouredTitan)this.ground.getElementOnGround(row, col, position);
        int step;
        do {
            step = arTitan.moveSideways();
        } while (col + step < 0 || col + step >= this.ground.getNumberOfRows());
        
        if (this.ground.getElementOnGround(row, col + step, 0) == null) {
            this.ground.setElementOnGround(row, col, position, null);
            this.ground.setElementOnGround(row, col+step, 0, arTitan);
            index[1] = col + step;
            index[2] = 0;
            System.out.println("The armoured titan moved sideways.");
        } else {
            System.out.println("The armoured titan does not move sideways.");
        }
    }
    
    protected void colossusAttack() {
        int count = 0;
        for (Integer[] index: this.colossusIndex) {
            this.walls[index[1]].damage((this.ground.getElementOnGround(index[0], index[1], index[2])).attack());

            System.out.println("The colossus titan attacked the wall " + index[1]);
            count ++;
        }
        if (count == 0) {
            System.out.println("No colossus launched an attack.");
        }
    }
    
    protected void armouredAttack() {
        int count = 0;
        for (Integer[] index: this.armouredIndex) {
            if (index[0] == 9) {
                if (this.walls[index[1]].showWeapon().getLevel() > 0){
                    this.walls[index[1]].showWeapon().damage();
                    System.out.println("The armoured titan attacked the weapon on wall " + index[1]);
                } else {
                    ArmouredTitan focus;
                    focus = (ArmouredTitan)this.ground.getElementOnGround(index[0], index[1], index[2]);

                    if (focus.getExtraChance() == 0){
                        this.moveArmouredSideways(index);
                        System.out.println("The armoured titan reached line 9 but did not attack. ");
                    } else {
                        this.walls[index[1]].damage(focus.attack());
                        System.out.println("The armoured titan attacked the wall " + index[1]);
                        count ++;
                    }
                }
            }
        }
        
        if (count == 0) {
            System.out.println("No armoured titan launched an attack.");
        }
    }

    // util function just to get things done and prevent errors

    private void updateColossusIndex() {

        for (int i=0; i<this.colossusIndex.size(); i++) {
            Integer[] index = this.colossusIndex.get(i);
            try {
                if (this.ground.getElementOnGround(index[0], index[1], index[2]) == null) {
                    this.colossusIndex.remove(index);
                    i --;
                }
            } catch (IndexOutOfBoundsException e) {
                index[2] = 0;
            }
        }

    }

    private void updateArmouredIndex() {

        for (int i=0; i<this.armouredIndex.size(); i++) {
            Integer[] index = this.armouredIndex.get(i);
            try {
                if (this.ground.getElementOnGround(index[0], index[1], index[2]) == null) {
                    this.armouredIndex.remove(index);
                    i --;
                }
            }catch (IndexOutOfBoundsException e) {
                index[2] = 0;
            }
        }
    }
}
