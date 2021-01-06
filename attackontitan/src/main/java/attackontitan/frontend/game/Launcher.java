package attackontitan.frontend.game;

import javax.swing.*;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Attack On ArmouredTitan", 320, 416);
        game.start();
    }
}
