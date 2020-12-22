/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.game;

import attackontitan.backend.gameobjects.Wall;

/**
 *
 * @author Autumn
 */
public class Main extends Process{
    
    public Main(boolean hardMode) {
        super(hardMode);
    }
    
    public static void main(String[] args) {
        System.out.println("Choose game mode: \nA) Easy\nB) Hard");
        Main aot = new Main(sc.next().charAt(0) == 'B');
        do {
            aot.playerTurn();
            System.out.print("Press enter to continue...");
            sc.nextLine();
            aot.titanTurn();
            aot.hour += 1;
            System.out.print("Press enter to continue...");
            sc.nextLine();
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
        if (this.colossusIndex.isEmpty() && this.addedTitans && this.armouredIndex.isEmpty()) {
            System.out.println("You win. All titans are dead. ");
            return false;
        }
        return true;
    }
}
