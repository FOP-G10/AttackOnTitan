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
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Autumn
 */
public class Process extends PlayerAccount {
    Titan[][][] ground;
    Wall[] walls;
    int hour;
    ArrayList<Integer[]> colossusIndex;
    ArrayList<Integer[]> armouredIndex;
    static Random r = new Random();
    static Scanner sc = new Scanner(System.in);
    boolean addedTitans = false;
    boolean hardMode;
    
    public Process(boolean hardMode) {
        super();
        this.ground = new Titan[10][10][2];
        this.walls = this.createWalls();
        this.hour = 0;
        this.colossusIndex = new ArrayList<>();
        this.armouredIndex = new ArrayList<>();
        this.hardMode = hardMode;
    }
    
    public void titanTurn() {
        if (this.armouredIndex.size() + this.colossusIndex.size() > 0){
            System.out.println("Titan's turn...");
            System.out.println();
            System.out.println("Titan start to move");
            this.moveArmouredForward();
            this.moveColossusSideways();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
            
            this.updateArmouredIndex();
            this.updateColossusIndex();

            System.out.println("Titan's turn...");
            System.out.println("The board after the titans move...");
            this.printBoard();
            System.out.print("Press enter to continue... ");
            sc.nextLine();

            System.out.println("Titan's turn...");
            this.armouredAttack();
            this.colossusAttack();
            System.out.print("Press enter to continue... ");
            sc.nextLine();

            System.out.println("The board after the titan's turn... ");
            this.printBoard();

            System.out.println();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
        } else {
            System.out.println("No titan on the ground.");
            System.out.println("Titan's turn skipped.");
            System.out.println();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
        }
        this.addColossus();
        this.addArmoured();
    }
    
    public void playerTurn() {
        System.out.println("Player's turn...");
        this.printBoard();
        System.out.println("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
        String weaponString = sc.nextLine();
        if (!weaponString.isEmpty()) {
            this.upgradeWeapon(weaponString);
        } else {
            System.out.println("No weapon upgraded. ");
        }
        System.out.print("Press enter to continue... ");
        sc.nextLine();
        System.out.println("Player's turn");
        System.out.println();
        System.out.println("Your board after upgrading weapon: ");
        this.printBoard();
        System.out.print("Press Enter to continue... ");
        sc.nextLine();
        this.printBoard();
        System.out.println("Do you want to upgrade all walls? (press 1 if yes, press Enter if no) Current coin number: " + this.coin);
        String upgradeWalls = sc.nextLine();
        if (upgradeWalls.isEmpty()) {
            System.out.println("Choose the wall you would like to upgrade (Type a string of integer or hit Enter ot skip)");
            upgradeWalls = sc.nextLine();
        }else {
            upgradeWalls = "0123456789";
        }
        System.out.println("How many HP do you want to add up to the wall(s)? Current coin number: " + this.coin);
        String upgradeHp = sc.nextLine();
        if (!upgradeWalls.isEmpty() && upgradeWalls.length() == upgradeHp.length()) {
            this.upgradeWall(upgradeWalls, upgradeHp);
        } else {
            System.out.println("No wall upgraded. ");
        }

        System.out.print("Press enter to continue... ");
        sc.nextLine();
        
        System.out.println("Your board after upgrading walls: ");
        this.printBoard();
        System.out.println();
        System.out.print("Press Enter to continue... ");
        sc.nextLine();
        
        this.weaponAttack();
        
        this.updateColossusIndex();
        this.updateArmouredIndex();
        
        System.out.println("Your board after attacking... ");
        this.printBoard();
        this.checkAddCoin();
        System.out.println();
    }
    
    private void printBoard() {
        System.out.println();
        this.printGround();
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
    
    private void printGround() {
        System.out.println("On The Ground");
        System.out.println("Row");
        for (int i=0; i<this.ground.length; i++) {
            System.out.print(i + " ");
            for (Titan[] titans: this.ground[i]) {
                if (titans[0] == null && titans[1] == null) {
                    System.out.print("  ");
                } else if (titans[1] == null) {
                    System.out.print(titans[0]);
                } else if (titans[0] == null){
                    System.out.print(titans[1]);
                }else {
                    System.out.print("AC");
                }
                System.out.print(" ");
            }
            switch (i) {
                case 0:
                    System.out.println("HOUR: " + this.hour);
                    break;
                case 1:
                    System.out.println("Coin: " + this.coin);
                    break;
                default:
                    System.out.println();
                    break;
            }
        }
    }
    
    private void printWallsAndWeapons() {
        int maxWeaponRow = 0;
        for (Wall wall: this.walls) {
            maxWeaponRow = wall.showWeapon().getLevel() > maxWeaponRow ? wall.showWeapon().getLevel() : maxWeaponRow;
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
        System.out.print("  ");
        for (Wall wall: this.walls) {
            System.out.print("-- ");
        }
        System.out.println("The Wall");
        System.out.print("  ");
        for (int i=0; i<this.walls.length; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println("index");
        System.out.print("  ");
        for (Wall wall: walls) {
            System.out.printf("%2d ", wall.getHp());
        }
        System.out.println("HP");
        System.out.print("  ");
        for (Wall wall: this.walls) {
            System.out.print("-- ");
        }
    }
    
    private void checkAddCoin() {
        if (this.hour > 0 && this.hour % 5 == 0) {
            this.addCoin(5);
            System.out.println("Coin +5");
        }
    }
    
    private void upgradeWeapon(String wallIndices) {
        int count = 0;
        String[] indices = wallIndices.trim().split("");
        for (String index: indices) {
            System.out.print("upgrading weapon on wall " + index + "\t");
            Wall focusWall = this.walls[Integer.parseInt(index)];
            try {
                if (focusWall.showWeapon().upgrade() && this.checkEnough(focusWall.showWeapon().attack())) {
                    this.payCoin(focusWall.showWeapon().attack());
                    count ++;
                }
            }catch (NullPointerException e) {
                System.out.println(e);
            }
        }
        
        if (count == 0) {
            System.out.println("No weapon upgraded. ");
        }
    }
    
    private void addColossus() {
        if (this.hour > 0 && ((!this.hardMode && this.hour == 5) || (this.hardMode && this.hour % 5 == 0))) {
            int randomInt;
            do {
                randomInt = r.nextInt(10);
            }while(this.ground[9][randomInt][0] != null && this.ground[9][randomInt][1] != null);
            ColossusTitan newCol = new ColossusTitan();
            int position;
            if (this.ground[9][randomInt][0] == null){
                this.ground[9][randomInt][0] = newCol;
                position = 0;
            }else {
                this.ground[9][randomInt][1] = newCol;
                position = 1;
            }
            Integer[] coor = {9, randomInt, position};
            this.colossusIndex.add(coor);
            System.out.println("A colossus titan is added to the ground. ");
            this.addedTitans = true;
        } else {
            System.out.println("No colossus titan added. ");
        }
    }
    
    private void addArmoured() {
        if (this.hour > 0 && ((!this.hardMode && this.hour == 5) || (this.hardMode && this.hour % 5 == 0))) {
            int randomInt;
            do {
                randomInt = r.nextInt(10);
            }while(this.ground[0][randomInt][0] != null && this.ground[0][randomInt][1] != null);
            ArmouredTitan arTitan = new ArmouredTitan();
            int position;
            if (this.ground[0][randomInt][0] == null){
                this.ground[0][randomInt][0] = arTitan;
                position = 0;
            }else {
                this.ground[0][randomInt][1] = arTitan;
                position = 1;
            }
            Integer[] coor = {0, randomInt, position};
            this.armouredIndex.add(coor);
            System.out.println(this.armouredIndex.size());
            System.out.println("A armoured titan is added to the ground. ");
            this.addedTitans = true;
        }else {
            System.out.println("No armoured titan added. ");
        }
    }
    
    private void moveColossusSideways() {
        for (int i=0; i<this.colossusIndex.size(); i++) {
            int row = this.colossusIndex.get(i)[0];
            int col = this.colossusIndex.get(i)[1];
            int position = this.colossusIndex.get(i)[2];
            ColossusTitan colTitan;
            colTitan = (ColossusTitan)this.ground[row][col][position];
            
            int step;
            do {
                step = colTitan.moveSideways();
            } while (col + step < 0 || col + step >= this.ground[row].length);
            
            if (this.ground[row][col+step][0] == null){
                this.ground[row][col][position] = null;
                this.ground[row][col+step][0] = colTitan;
                this.colossusIndex.get(i)[2] = 0;
                this.colossusIndex.get(i)[1] = col + step;
            }else if (this.ground[row][col+step][1] == null){
                this.ground[row][col][position] = null;
                this.ground[row][col+step][1] = colTitan;
                this.colossusIndex.get(i)[2] = 1;
                this.colossusIndex.get(i)[1] = col + step;
            }else {
                System.out.println("The colossus titan does not move sideways. ");
            }
            
            System.out.println("The colossus titan moved sideways. ");
        }
    }
    
    private void moveArmouredForward() {
        System.out.println(this.armouredIndex.size());
        for (int i=0; i<this.armouredIndex.size(); i++) {
            int row = this.armouredIndex.get(i)[0];
            int col = this.armouredIndex.get(i)[1];
            int position = this.armouredIndex.get(i)[2];
            ArmouredTitan arTitan = (ArmouredTitan)this.ground[row][col][position];
            int step;
            step = arTitan.moveForward();
            
            System.out.println(step + row);
            if (step + row < this.ground.length) {
                
                if (this.ground[row+step][col][0] == null) {
                    this.ground[row][col][position] = null;
                    this.ground[row+step][col][0] = arTitan;
                    this.armouredIndex.get(i)[2] = 0;
                    this.armouredIndex.get(i)[0] = row + step;
                }else if (this.ground[row+step][col][1] == null){
                    this.ground[row][col][position] = null;
                    this.ground[row+step][col][1] = arTitan;
                    this.armouredIndex.get(i)[2] = 1;
                    this.armouredIndex.get(i)[0] = row + step;
                }else {
                    System.out.println("The armoured titan did not move forward.");
                }
                
                System.out.println("The armoured titan moved forward.");
            } else {
                System.out.println("The armoured titan did not move forward.");
            }
        }
    }
    
    private void moveArmouredSideways(Integer[] index) {
        int row = index[0];
        int col = index[1];
        int position = index[2];

        ArmouredTitan arTitan = (ArmouredTitan)this.ground[row][col][position];
        int step;
        do {
            step = arTitan.moveSideways();
        } while (col + step < 0 || col + step >= this.ground[row].length);
        
        if (this.ground[row][col + step][0] == null) {
            this.ground[row][col][position] = null;
            this.ground[row][col + step][0] = arTitan;
            index[1] = col + step;
            index[2] = 0;
        }else if (this.ground[row][col + step][1] == null){
            this.ground[row][col][position] = null;
            this.ground[row][col + step][1] = arTitan;
            index[1] = col + step;
            index[2] = 1;
        } else {
            System.out.println("The armoured titan does not move sideways.");
        }
        
        System.out.println("The armoured titan moved sideways.");
    }
    
    private void colossusAttack() {
        int count = 0;
        for (Integer[] index: this.colossusIndex) {
            if (this.walls[index[1]].showWeapon().getLevel() > 0) {
                this.walls[index[1]].showWeapon().damage();
                System.out.println("The colossus titan attacked the weapon on wall " + index[1]);
                count ++;
            } else {
                try {
                    this.walls[index[1]].damage(((ColossusTitan)this.ground[index[0]][index[1]][index[2]]).attack());
                } catch(IndexOutOfBoundsException e) {
                    this.walls[index[1]].damage(((ColossusTitan)this.ground[index[0]][index[1]][index[2]]).attack());
                }
                
                System.out.println("The colossus titan attacked the wall " + index[1]);
                count ++;
            }
        }
        if (count == 0) {
            System.out.println("The colossus titan did not launch an attack.");
        }
    }
    
    private void armouredAttack() {
        int count = 0;
        for (Integer[] index: this.armouredIndex) {
            if (index[0] == 9) {
                if (this.walls[index[1]].showWeapon().getLevel() > 0){
                    this.walls[index[1]].showWeapon().damage();
                    System.out.println("The armoured titan attacked the weapon on wall " + index[1]);
                }else {
                    ArmouredTitan focus;
                    try {
                        focus = (ArmouredTitan)this.ground[index[0]][index[1]][index[2]];
                    }catch (IndexOutOfBoundsException e) {
                        focus = (ArmouredTitan)this.ground[index[0]][index[1]][0];
                    }
                    
                    if (focus.getExtraChance() == 0){
                        this.moveArmouredSideways(index);
                        System.out.println("The armoured titan reached line 9 but did not attack. ");
                    }
                    else {
                        this.walls[index[1]].damage(focus.attack());
                        System.out.println("The armoured titan attacked the wall " + index[1]);
                        count ++;
                    }
                }
            }
        }
        
        if (count == 0) {
            System.out.println("The armoured titan did not launch an attack.");
        }
    }
    
    private void weaponAttack() {
        int count = 0;
        for (int i=0; i<this.walls.length; i++) {
            if (this.walls[i].showWeapon().getLevel() > 0) {
                for (Titan[][] row: this.ground) {
                    if (row[i] != null && row[i].length > 0){
                        for (int j=0; j<row[i].length; j++) {
                            System.out.println("The weapon on wall " + i + " attacks");
                            Object titan = row[i].length;
                            try {
                                ColossusTitan focus = (ColossusTitan) titan;
                                focus = focus.damage(this.walls[i].showWeapon().attack());
                                count ++;
                                row[i][j] = focus;
                            } catch (ClassCastException ex) {
                                ArmouredTitan focus = (ArmouredTitan) titan;
                                focus = focus.damage(this.walls[i].showWeapon().attack());
                                count ++;
                                row[i][j] = focus;
                            }
                        }
                    }
                }
            }
        }
        if (count == 0) {
                System.out.println("The weapons on all walls did not launch an attack... ");
        }
    }
    
    private void upgradeWall(String wallIndex, String hpUpgrade) {
        String[] index = wallIndex.trim().replace(" ", "").split("");
        String[] hpUp = hpUpgrade.trim().replace(" ", "").split("");
        for (int i=0; i<index.length; i++) {
            int indexInt = Integer.parseInt(index[i]);
            int hpUpInt = Integer.parseInt(hpUp[i]);
            if (this.checkEnough(hpUpInt)) {
                this.walls[indexInt].upgradeWall(hpUpInt);
                this.payCoin(hpUpInt);
                System.out.println("Wall " + indexInt + " upgrade successfully. ");
            } 
        }
    }
    
    private void updateColossusIndex() {
        
        for (int i=0; i<this.colossusIndex.size(); i++) {
            Integer[] index = this.colossusIndex.get(i);
            try {
                if (this.ground[index[0]][index[1]][index[2]] == null) {
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
                if (this.ground[index[0]][index[1]][index[2]] == null) {
                    this.armouredIndex.remove(index);
                    System.out.println("remove");
                    i --;
                }
            }catch (IndexOutOfBoundsException e) {
                index[2] = 0;
            }
        }
    }
}
