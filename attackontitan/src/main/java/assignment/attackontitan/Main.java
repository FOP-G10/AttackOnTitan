/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.attackontitan;

import java.util.Scanner;

/**
 *
 * @author Autumn
 */
public class Main{
    public static void main(String[] args) {
        AttackOnTitan aot = new AttackOnTitan();
        Scanner sc = new Scanner(System.in);
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
}
