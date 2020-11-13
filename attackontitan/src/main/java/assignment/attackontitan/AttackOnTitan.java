/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.attackontitan;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Autumn
 */
public class AttackOnTitan {
    ArrayList<Titan[]> ground;
    Wall[] walls;
    int hour;
    int coin;
    ArrayList<Integer[]> colossusIndex;
    ArrayList<Integer[]> armouredIndex;
    static Random r = new Random();
    
    public AttackOnTitan() {
        this.ground = new ArrayList<>();
        this.walls = this.createWalls();
        this.hour = 0;
        this.coin = 50;
        this.colossusIndex = new ArrayList<>();
        this.armouredIndex = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        AttackOnTitan aot = new AttackOnTitan();
        aot.upgradeWeapon("345");
        System.out.println("The weapon level: " + aot.walls[5].weapon.level);
        aot.addGroundRow();
        
        aot.addColossus();
        aot.addArmoured();
        
        aot.addColossus();
        aot.addArmoured();
        
        aot.printBoard();
        aot.moveColossus();
        aot.printBoard();
        aot.moveArmoured();
        aot.printBoard();
    }
    
    public void printBoard() {
        this.printGround();
        this.printWallsAndWeapons();
    }
    
    public final Wall[] createWalls() {
        Wall[] tmp = new Wall[10];
        for (int i=0; i<10; i++) {
            tmp[i] = new Wall(i);
        }
        return tmp;
    }
    
    public void printGround() {
        System.out.println("On The Ground");
        System.out.println("Row");
        for (int i=0; i<this.ground.size(); i++) {
            System.out.print(i + " ");
            for (Titan titan: this.ground.get(i)) {
                if (titan == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(titan);
                }
                System.out.print(" ");
            }
            if (i == 0) {
                System.out.println("HOUR: " + this.hour);
            } else if (i == 1) {
                System.out.println("Coin: " + this.coin);
            } else {
                System.out.println();
            }
        }
    }
    
    public void printWallsAndWeapons() {
        int maxWeaponRow = 0;
        for (Wall wall: this.walls) {
            maxWeaponRow = wall.weapon.level > maxWeaponRow ? wall.weapon.level : maxWeaponRow;
        }
        for (int row=maxWeaponRow; row>0; row--) {
            System.out.print("  ");
            for (Wall wall: this.walls) {
                if (wall.weapon.level >= row) {
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
            System.out.printf("%2d ", wall.hp);
        }
        System.out.println("HP");
        System.out.print("  ");
        for (Wall wall: this.walls) {
            System.out.print("-- ");
        }
    }
    
    public void addGroundRow() {
        for (int i=0; i<10; i++) {
            this.ground.add(new Titan[10]);
        }
    }
    
    public void checkAddCoin() {
        if (this.hour % 5 == 0) {
            this.coin += 5;
        }
    }
    
    public boolean checkEnough(int coinNeeded) {
        if (this.coin >= coinNeeded) {
            return true;
        } else {
            System.out.println("Not enough coin to upgrade weapon. ");
            return false;
        }
    }
    
    public void payCoin(int amount) {
        this.coin -= amount;
    }
    
    public void incrementHour() {
        this.hour ++;
    }
    
    public void upgradeWeapon(String wallIndices) {
        String[] indices = wallIndices.trim().split("");
        for (String index: indices) {
            System.out.println(index);
            Wall focusWall = this.walls[Integer.parseInt(index)];
            try {
                if (this.checkEnough(focusWall.weapon.attackDamage.get(focusWall.weapon.level + 1)) && focusWall.weapon.upgrade()) {
                    this.coin -= focusWall.weapon.attackDamage.get(focusWall.weapon.level);
                }
            }catch (NullPointerException e) {
                System.out.println(e);
            }
        }
    }
    
    public void addColossus() {
        if (this.hour % 5 == 0) {
            int randomInt = r.nextInt(10);
            System.out.println(randomInt);
            this.ground.get(9)[randomInt] = new ColossusTitan();
            Integer[] coor = {9, randomInt};
            this.colossusIndex.add(coor);
        }
    }
    
    public void addArmoured() {
        if (this.hour % 5 == 0) {
            int randomInt = r.nextInt(10);
            System.out.println(randomInt);
            this.ground.get(0)[randomInt] = new ArmouredTitan();
            Integer[] coor = {0, randomInt};
            this.armouredIndex.add(coor);
        }
    }
    
    public void moveColossus() {
        for (int i=0; i<this.colossusIndex.size(); i++) {
            int row = this.colossusIndex.get(i)[0];
            int col = this.colossusIndex.get(i)[1];
            
            ColossusTitan colTitan = (ColossusTitan)this.ground.get(row)[col];
            int step = colTitan.move();
            
            System.out.println(step);
            this.ground.get(row)[col] = null;
            this.ground.get(row)[col + step] = colTitan;
            this.colossusIndex.get(i)[1] = col + step;
        }
    }
    
    public void moveArmoured() {
        for (int i=0; i<this.armouredIndex.size(); i++) {
            int row = this.armouredIndex.get(i)[0];
            int col = this.armouredIndex.get(i)[1];
            ArmouredTitan arTitan = (ArmouredTitan)this.ground.get(row)[col];
            int step = arTitan.move();
            System.out.println(step);
            this.ground.get(row)[col] = null;
            this.ground.get(row + step)[col] = arTitan;
            this.armouredIndex.get(i)[0] = row + step;
        }
    }
}
