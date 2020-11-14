/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.attackontitan;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Autumn
 */
public class AttackOnTitan {
    ArrayList<ArrayList<Titan>[]> ground;
    Wall[] walls;
    int hour;
    int coin;
    ArrayList<Integer[]> colossusIndex;
    ArrayList<Integer[]> armouredIndex;
    static Random r = new Random();
    static Scanner sc = new Scanner(System.in);
    
    public AttackOnTitan() {
        this.ground = new ArrayList<>();
        this.walls = this.createWalls();
        this.hour = 0;
        this.coin = 50;
        this.colossusIndex = new ArrayList<>();
        this.armouredIndex = new ArrayList<>();
        this.addGroundRow();
    }
    
    public static void main(String[] args) {
        AttackOnTitan aot = new AttackOnTitan();
//        aot.upgradeWeapon("345");
//        System.out.println("The weapon level: " + aot.walls[5].weapon.level);
//        aot.addGroundRow();
//        
//        aot.addColossus();
//        aot.addArmoured();
//        
//        aot.addColossus();
//        aot.addArmoured();
//        
//        aot.printBoard();
//        aot.moveColossusSideways();
//        aot.printBoard();
//        aot.moveArmouredForward();
//        aot.printBoard();
        do {
            aot.playerTurn();
            aot.titanTurn();
            aot.hour += 1;
        } while (aot.checkResult());
        
        System.out.println("Game over");
    }
    
    public void printBoard() {
        System.out.println();
        this.printGround();
        this.printWallsAndWeapons();
        System.out.println();
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
            for (ArrayList titans: this.ground.get(i)) {
                if (titans == null || titans.size() == 0) {
                    System.out.print("  ");
                } else if (titans.size() == 1) {
                    System.out.print(titans.get(0));
                } else {
                    System.out.println("AC");
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
    
    public final void addGroundRow() {
        for (int i=0; i<10; i++) {
            this.ground.add(new ArrayList[10]);
        }
    }
    
    public void checkAddCoin() {
        if (this.hour > 0 && this.hour % 5 == 0) {
            this.coin += 5;
            System.out.println("Coin +5");
        }
    }
    
    public boolean checkEnough(int coinNeeded) {
        if (this.coin >= coinNeeded) {
            return true;
        } else {
            System.out.println("Not enough coin for the transaction. ");
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
            System.out.print("upgrading weapon on wall " + index + "\t");
            Wall focusWall = this.walls[Integer.parseInt(index)];
            try {
                if (focusWall.weapon.validUpgrade() && this.checkEnough(focusWall.weapon.attackDamage.get(focusWall.weapon.level + 1)) && focusWall.weapon.upgrade()) {
                    this.coin -= focusWall.weapon.attackDamage.get(focusWall.weapon.level);
                }
            }catch (NullPointerException e) {
                System.out.println(e);
            }
        }
    }
    
    public void addColossus() {
        if (this.hour > 0 && this.hour == 5) {
            int randomInt = r.nextInt(10);
            ColossusTitan newCol = new ColossusTitan();
            if (this.ground.get(9)[randomInt] == null) {
                this.ground.get(9)[randomInt] = new ArrayList();
            }
            this.ground.get(9)[randomInt].add(newCol);
            Integer[] coor = {9, randomInt, this.ground.get(9)[randomInt].indexOf(newCol)};
            this.colossusIndex.add(coor);
        }
    }
    
    public void addArmoured() {
        if (this.hour > 0 && this.hour == 5) {
            int randomInt = r.nextInt(10);
            ArmouredTitan arTitan = new ArmouredTitan();
            if (this.ground.get(0)[randomInt] == null) {
                this.ground.get(0)[randomInt] = new ArrayList();
            }
            this.ground.get(0)[randomInt].add(arTitan);
            Integer[] coor = {0, randomInt, this.ground.get(0)[randomInt].indexOf(arTitan)};
            this.armouredIndex.add(coor);
        }
    }
    
    public void moveColossusSideways() {
        for (int i=0; i<this.colossusIndex.size(); i++) {
            int row = this.colossusIndex.get(i)[0];
            int col = this.colossusIndex.get(i)[1];
            int position = this.colossusIndex.get(i)[2];
            ColossusTitan colTitan;
            colTitan = (ColossusTitan)this.ground.get(row)[col].get(position);
            
            int step;
            do {
                step = colTitan.moveSideways();
            } while (col + step < 0 || col + step >= this.ground.get(row).length);
            
            
            this.ground.get(row)[col].remove(colTitan);
            if (this.ground.get(row)[col + step] == null) {
                this.ground.get(row)[col + step] = new ArrayList();
            }
            this.ground.get(row)[col + step].add(colTitan);
            this.colossusIndex.get(i)[1] = col + step;
            this.colossusIndex.get(i)[2] = this.ground.get(row)[col + step].indexOf(colTitan);
        }
    }
    
    public void moveArmouredForward() {
        for (int i=0; i<this.armouredIndex.size(); i++) {
            int row = this.armouredIndex.get(i)[0];
            int col = this.armouredIndex.get(i)[1];
            int position = this.armouredIndex.get(i)[2];
            ArmouredTitan arTitan = (ArmouredTitan)this.ground.get(row)[col].get(position);
            int step;
            step = arTitan.moveForward();
            
            if (step + row < this.ground.size()) {
                this.ground.get(row)[col].remove(arTitan);
                if (this.ground.get(row + step)[col] == null) {
                    this.ground.get(row + step)[col] = new ArrayList();
                }
                this.ground.get(row + step)[col].add(arTitan);
                this.armouredIndex.get(i)[0] = row + step;
                this.armouredIndex.get(i)[2] = this.ground.get(row + step)[col].indexOf(arTitan);
            }
        }
    }
    
    public void moveArmouredSideways(Integer[] index) {
        int row = index[0];
        int col = index[1];
        int position = index[2];

        ArmouredTitan arTitan = (ArmouredTitan)this.ground.get(row)[col].get(position);
        int step;
        do {
            step = arTitan.moveSideways();
        } while (col + step < 0 || col + step >= this.ground.get(row).length);

        this.ground.get(row)[col].remove(arTitan);
        if (this.ground.get(row)[col + step] == null) {
            this.ground.get(row)[col + step] = new ArrayList();
        }
        this.ground.get(row)[col + step].add(arTitan);
        index[1] = col + step;
        index[2] = this.ground.get(row)[col + step].indexOf(arTitan);
    }
    
    public void attackColossus() {
        for (Integer[] index: this.colossusIndex) {
            if (this.walls[index[1]].weapon.level > 0) {
                this.walls[index[1]].weapon.damage();
            } else {
                this.walls[index[1]].damage(((ColossusTitan)this.ground.get(index[0])[index[1]].get(index[2])).attackPoint);
            }
        }
    }
    
    public void attackArmoured() {
        for (Integer[] index: this.armouredIndex) {
            if (index[0] == 0) {
                if (this.walls[index[1]].weapon.level > 0){
                    this.walls[index[1]].weapon.damage();
                }else {
                    ArmouredTitan focus = (ArmouredTitan)this.ground.get(index[0])[index[1]].get(index[2]);
                    if (focus.extraChance == 0) this.moveArmouredSideways(index);
                    else this.walls[index[1]].damage(focus.attackPoint);
                }
            }
        }
    }
    
    public void weaponAttack() {
        for (int i=0; i<this.walls.length; i++) {
            if (this.walls[i].weapon.level > 0) {
                for (ArrayList[] row: this.ground) {
                    if (row[i] != null && row[i].size() > 0){
                        for (Object titan: row[i]) {
                            Titan focus = (Titan) titan;
                            focus.damage(this.walls[i].weapon.attackDamage.get(this.walls[i].weapon.level));
                            System.out.println("The weapon on wall " + i + " attack " + focus);
                        }
                    }
                }
            }
        }
    }
    
    public void upgradeWall(String wallIndex, String hpUpgrade) {
        String[] index = wallIndex.trim().replace(" ", "").split("");
        String[] hpUp = hpUpgrade.trim().replace(" ", "").split("");
        for (int i=0; i<index.length; i++) {
            int indexInt = Integer.parseInt(index[i]);
            int hpUpInt = Integer.parseInt(hpUp[i]);
            System.out.println(indexInt);
            if (this.checkEnough(hpUpInt)) {
                this.walls[indexInt].upgradeWall(hpUpInt);
                this.coin -= hpUpInt;
                System.out.println("Wall " + indexInt + " upgrade successfully. ");
            } 
        }
    }
    
    public boolean checkResult() {
        for (Wall wall: this.walls) {
            if (!wall.checkCondition()) {
                return false;
            }
        }
        return true;
    }
    
    public void updataColossusIndex() {
        
        for (int i=0; i<this.colossusIndex.size(); i++) {
            Integer[] index = this.colossusIndex.get(i);
            if (this.ground.get(index[0])[index[1]] == null) {
                this.colossusIndex.remove(index);
                i --;
            }
        }

    }
    
    public void updateArmouredIndex() {
        
        for (int i=0; i<this.armouredIndex.size(); i++) {
            Integer[] index = this.armouredIndex.get(i);
            if (this.ground.get(index[0])[index[1]] == null) {
                this.armouredIndex.remove(index);
                i --;
            }
        }
    }
    
    public boolean checkClear() {
        if (this.colossusIndex.isEmpty() && this.armouredIndex.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public void titanTurn() {
        System.out.println("Titan's turn...");
        this.moveArmouredForward();
        this.moveColossusSideways();
        this.attackArmoured();
        this.attackColossus();
        this.addColossus();
        this.addArmoured();
        this.printBoard();
        System.out.println();
    }
    
    public void playerTurn() {
        System.out.println("Player's turn...");
        this.printBoard();
        System.out.println("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
        String weaponString = sc.nextLine();
        if (!weaponString.isEmpty()) {
            this.upgradeWeapon(weaponString);
        }
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
        if (!upgradeWalls.isEmpty() && upgradeWalls.length() == upgradeHp.length()) this.upgradeWall(upgradeWalls, upgradeHp);
        this.printBoard();
        
        this.weaponAttack();
        
        this.updataColossusIndex();
        this.updateArmouredIndex();
        
        this.printBoard();
        this.checkAddCoin();
        System.out.println();
    }
}
