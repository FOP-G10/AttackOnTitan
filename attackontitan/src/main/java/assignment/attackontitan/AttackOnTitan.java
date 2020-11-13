/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.attackontitan;

import java.util.ArrayList;

/**
 *
 * @author Autumn
 */
public class AttackOnTitan {
    ArrayList<String[]> board;
    Wall[] walls;
    int hour;
    int coin;
    
    public AttackOnTitan() {
        this.board = new ArrayList<>();
        this.walls = this.createWalls();
        this.hour = 0;
        this.coin = 50;
    }
    
    public static void main(String[] args) {
        AttackOnTitan aot = new AttackOnTitan();
        aot.upgradeWeapon("2345");
        System.out.println("The weapon level: " + aot.walls[5].weaponLevel);
        aot.addGroundRow();
        aot.printBoard();
    }
    
    public final Wall[] createWalls() {
        Wall[] tmp = new Wall[10];
        for (int i=0; i<10; i++) {
            tmp[i] = new Wall(i);
        }
        return tmp;
    }
    
    public void printBoard() {
        System.out.println("On The Ground");
        System.out.println("Row");
        for (String[] row: this.board) {
            System.out.println(String.join(" ", row));
        }
        this.printWallsAndWeapons();
    }
    
    public void printWallsAndWeapons() {
        int maxWeaponRow = 0;
        for (Wall wall: this.walls) {
            maxWeaponRow = wall.weaponLevel > maxWeaponRow ? wall.weaponLevel : maxWeaponRow;
        }
        for (int row=maxWeaponRow; row>0; row--) {
            System.out.print("  ");
            for (Wall wall: this.walls) {
                if (wall.weaponLevel >= row) {
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
    
    public String[][] setBoard() {
        return new String[3][8];
    }
    
    public void addGroundRow() {
        for (int i=0; i<10; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(i));
            sb.append("|");
            sb.append(new String(new char[10]).replace("\0", "  |"));
            
            if (i == 0) {
                sb.append("HOUR: ");
                sb.append(String.valueOf(this.hour));
            } else if (i == 1) {
                sb.append("Coin: ");
                sb.append(String.valueOf(this.coin));
            }
            this.board.add(sb.toString().split("[|]"));
        }
    }
    
    public void addHeader() {
        this.board.add(new String[]{"On the ground"});
        this.board.add(new String[]{"Row"});
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
                if (this.checkEnough(focusWall.attackDamage.get(focusWall.weaponLevel + 1)) && focusWall.upgradeWeapon()) {
                    this.coin -= focusWall.attackDamage.get(focusWall.weaponLevel);
                }
            }catch (NullPointerException e) {
                System.out.println(e);
            }
        }
    }
}
