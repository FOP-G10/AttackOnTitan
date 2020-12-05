/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.game;

import attackontitan.gameobjects.Wall;

/**
 *
 * @author Autumn
 */
public class Main extends Process{
    
    public Main() {
        super();
    }
    
    public static void main(String[] args) {
        Main aot = new Main();
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
    
    private boolean checkResult() {
        return this.checkWalls() && this.checkTitans();
    }
    
    private boolean checkWalls() {
        for (Wall wall: this.walls) {
            if (!wall.checkCondition()) {
                System.out.println("Game over. You lose. ");
                return false;
            }
        }
        return true;
    }
    
    private boolean checkTitans() {
        if (this.colossusIndex.isEmpty() && this.armouredIndex.isEmpty()) {
            System.out.println("You win. All titans are dead. ");
            return false;
        }
        return true;
    }
}
