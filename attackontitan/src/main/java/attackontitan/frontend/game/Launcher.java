package attackontitan.frontend.game;

import javax.swing.*;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {

//        System.out.println("Choose game mode: \nA) Easy\nB) Hard");
        attackontitan.backend.game.Game aot = new attackontitan.backend.game.Game(JOptionPane.showInputDialog("Choose game mode: \nA) Easy\nB) Hard").toUpperCase().charAt(0) == 'B');
        Game game = new Game("Attack On ArmouredTitan", 320, 352, aot);
        game.start();
        do {
            aot.playerTurn();
            aot.titanTurn();
            aot.incrementHour(1);
        } while (aot.checkResult());

        System.out.println("Game over");
    }
}
