package attackontitan.frontend.game;

import javax.swing.*;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {

//        System.out.println("Choose game mode: \nA) Easy\nB) Hard");
        boolean gameMode = JOptionPane.showInputDialog("Choose game mode: \nA) Easy\nB) Hard").toUpperCase().charAt(0) == 'B';
        attackontitan.backend.game.Game aot = new attackontitan.backend.game.Game(gameMode);
        Game game = new Game("Attack On ArmouredTitan", 320, 352, aot);
        game.start();
        do {
            aot.playerTurn(game.getFrame());
            aot.titanTurn(game.getFrame());
            aot.incrementHour(1);
        } while (aot.checkResult());

        System.out.println("Game over");
    }
}
