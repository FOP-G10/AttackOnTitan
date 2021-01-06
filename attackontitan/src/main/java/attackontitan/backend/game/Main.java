/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.game;

import java.util.Scanner;

/**
 *
 * @author Autumn
 */
public class Main{
    public static void main(String[] args) {
        System.out.println("Choose game mode: \nA) Easy\nB) Hard");
        Scanner sc = new Scanner(System.in);
        Game aot = new Game(sc.nextLine().toUpperCase().charAt(0) == 'B');  // change all inputs to upper case to make all kind of inputs uniform
        do {
            aot.playerTurn();   // initiate player turn

            System.out.print("Press enter to continue...");
            sc.nextLine();

            aot.titanTurn();    // initiate titan turn

            aot.incrementHour(1);

            System.out.print("Press enter to continue...");
            sc.nextLine();
        } while ( aot.checkResult() );  // check the current state and determine whether the game should continue
        
        System.out.println("Game over");    // print this when the game ends, regardless of which side wins
    }
}
