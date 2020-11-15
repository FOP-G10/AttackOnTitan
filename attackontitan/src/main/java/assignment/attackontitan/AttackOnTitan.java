/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.attackontitan;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        do {
            aot.playerTurn();
            System.out.print("Press enter to continue...");
            sc.nextLine();
            aot.clearConsole();
            aot.titanTurn();
            aot.hour += 1;
            System.out.print("Press enter to continue...");
            sc.nextLine();
            aot.clearConsole();
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
        int count = 0;
        String[] indices = wallIndices.trim().split("");
        for (String index: indices) {
            System.out.print("upgrading weapon on wall " + index + "\t");
            Wall focusWall = this.walls[Integer.parseInt(index)];
            try {
                if (focusWall.weapon.validUpgrade() && this.checkEnough(focusWall.weapon.attackDamage.get(focusWall.weapon.level + 1)) && focusWall.weapon.upgrade()) {
                    this.coin -= focusWall.weapon.attackDamage.get(focusWall.weapon.level);
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
            System.out.println("A colossus titan is added to the ground. ");
        } else {
            System.out.println("No colossus titan added. ");
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
            System.out.println("A armoured titan is added to the ground. ");
        }else {
            System.out.println("No armoured titan added. ");
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
            System.out.println("The colossus titan moved sideways. ");
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
                System.out.println("The armoured titan moved forward.");
            } else {
                System.out.println("The armoured titan did not move forward.");
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
        System.out.println("The armoured titan moved sideways.");
    }
    
    public void attackColossus() {
        int count = 0;
        for (Integer[] index: this.colossusIndex) {
            if (this.walls[index[1]].weapon.level > 0) {
                this.walls[index[1]].weapon.damage();
                System.out.println("The colossus titan attacked the weapon on wall " + index[1]);
                count ++;
            } else {
                this.walls[index[1]].damage(((ColossusTitan)this.ground.get(index[0])[index[1]].get(index[2])).attackPoint);
                System.out.println("The colossus titan attacked the wall " + index[1]);
                count ++;
            }
        }
        if (count == 0) {
            System.out.println("The colossus titan did not launch an attack.");
        }
    }
    
    public void attackArmoured() {
        int count = 0;
        for (Integer[] index: this.armouredIndex) {
            if (index[0] == 9) {
                if (this.walls[index[1]].weapon.level > 0){
                    this.walls[index[1]].weapon.damage();
                    System.out.println("The armoured titan attacked the weapon on wall " + index[1]);
                }else {
                    ArmouredTitan focus = (ArmouredTitan)this.ground.get(index[0])[index[1]].get(index[2]);
                    if (focus.extraChance == 0){
                        this.moveArmouredSideways(index);
                        System.out.println("The armoured titan reached line 9 but did not attack. ");
                    }
                    else {
                        this.walls[index[1]].damage(focus.attackPoint);
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
    
    public void weaponAttack() {
        int count = 0;
        for (int i=0; i<this.walls.length; i++) {
            if (this.walls[i].weapon.level > 0) {
                for (ArrayList[] row: this.ground) {
                    if (row[i] != null && row[i].size() > 0){
                        for (Object titan: row[i]) {
                            System.out.println("The weapon on wall " + i + " attacks");
                            try {
                                ColossusTitan focus = (ColossusTitan) titan;
                                focus.damage(this.walls[i].weapon.attackDamage.get(this.walls[i].weapon.level));
                                count ++;
                            } catch (ClassCastException ex) {
                                ArmouredTitan focus = (ArmouredTitan) titan;
                                focus.damage(this.walls[i].weapon.attackDamage.get(this.walls[i].weapon.level));
                                count ++;
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
    
    public void upgradeWall(String wallIndex, String hpUpgrade) {
        String[] index = wallIndex.trim().replace(" ", "").split("");
        String[] hpUp = hpUpgrade.trim().replace(" ", "").split("");
        for (int i=0; i<index.length; i++) {
            int indexInt = Integer.parseInt(index[i]);
            int hpUpInt = Integer.parseInt(hpUp[i]);
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
    
    public void clearConsole(){
        try {
            Robot pressbot = new Robot();
            pressbot.keyPress(17); // Holds CTRL key.
            pressbot.keyPress(76); // Holds L key.
            pressbot.keyRelease(17); // Releases CTRL key.
            pressbot.keyRelease(76); // Releases L key.
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (AWTException | InterruptedException ex) {
            Logger.getLogger(AttackOnTitan.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            this.clearConsole();

            System.out.println("Titan's turn...");
            System.out.println("The board after the titans move...");
            this.printBoard();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
            this.clearConsole();

            System.out.println("Titan's turn...");
            this.attackArmoured();
            this.attackColossus();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
            this.clearConsole();

            System.out.println("The board after the titan's turn... ");
            this.printBoard();

            System.out.println();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
            this.clearConsole();
        } else {
            System.out.println("No titan on the ground.");
            System.out.println("Titan's turn skipped.");
            System.out.println();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
            this.clearConsole();
        }
        this.addColossus();
        this.addArmoured();
    }
    
    public void playerTurn() {
        System.out.println("Player's turn...");
        this.printBoard();
        System.out.println("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
        String weaponString = sc.nextLine();
        this.clearConsole();
        if (!weaponString.isEmpty()) {
            this.upgradeWeapon(weaponString);
        } else {
            System.out.println("No weapon upgraded. ");
        }
        System.out.print("Press enter to continue... ");
        sc.nextLine();
        this.clearConsole();
        System.out.println("Player's turn");
        System.out.println();
        System.out.println("Your board after upgrading weapon: ");
        this.printBoard();
        System.out.print("Press Enter to continue... ");
        sc.nextLine();
        this.clearConsole();
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
        this.clearConsole();
        if (!upgradeWalls.isEmpty() && upgradeWalls.length() == upgradeHp.length()) {
            this.upgradeWall(upgradeWalls, upgradeHp);
        } else {
            System.out.println("No wall upgraded. ");
        }
//        this.printBoard();
        System.out.print("Press enter to continue... ");
        sc.nextLine();
        
        this.clearConsole();
        System.out.println("Your board after upgrading walls: ");
        this.printBoard();
        System.out.println();
        System.out.print("Press Enter to continue... ");
        sc.nextLine();
        this.clearConsole();
        
        this.weaponAttack();
        
        this.updataColossusIndex();
        this.updateArmouredIndex();
        
        System.out.println("Your board after attacking... ");
        this.printBoard();
        this.checkAddCoin();
        System.out.println();
    }
}
