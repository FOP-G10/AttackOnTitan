package attackontitan.frontend.state;

import attackontitan.backend.game.Process;
import attackontitan.backend.gameobjects.titans.Titan;
import attackontitan.frontend.entities.ArmouredTitan;
import attackontitan.frontend.entities.ColossusTitan;
import attackontitan.frontend.entities.Wall;
import attackontitan.frontend.entities.Weapon;
import attackontitan.frontend.game.Game;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.tiles.Tile;
import attackontitan.frontend.world.Stats;
import attackontitan.frontend.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameState extends State{

    private World world;
    private Stats stats;

    private ArmouredTitan armouredTitan;
    private ColossusTitan colossusTitan;

    public static boolean gameOver = false;

    public static boolean nextRound = false;

    public static attackontitan.backend.game.Game gameProcess;

    public GameState(Game game) {
        super(game);

        gameProcess = new attackontitan.backend.game.Game(JOptionPane.showInputDialog("Choose game mode: \nA) Easy\nB) Hard").toUpperCase().charAt(0) == 'B');
        world = new World(gameProcess);
        stats = new Stats(gameProcess);


        Wall.wallCondition = true;
        ArmouredTitan.armouredCondition = true;
        ColossusTitan.colossusCondition = true;
        nextRound = false;
        gameOver = false;

        Wall.walls = new Wall[10];
        ColossusTitan.allColossus = new ArrayList<>();
        ArmouredTitan.allArmoured = new ArrayList<>();

        Wall.createWalls(game.getMouseManager());
        Weapon.init();
    }

    @Override
    public String toString() {
        return "Game";
    }

    @Override
    public void tick() {
        if(Wall.wallCondition && (ArmouredTitan.armouredCondition || ColossusTitan.colossusCondition)){
            world.tick();
            stats.tick();
//        this.gameProcess.playerTurn();
//        this.gameProcess.titanTurn();
//        this.gameProcess.incrementHour(1);

            // player's turn
            for (Wall wall : Wall.walls) {
                wall.tick();
            }

            for (Weapon weapon : Weapon.weapons) {
                weapon.tick();
            }

            //button to complete
            if (nextRound) {
                // action here after user complete upgrading
                weaponAttack();
                gameProcess.checkAddCoin();
                // titan's turn

                for (ArmouredTitan titan : ArmouredTitan.allArmoured) {
                    titan.tick(gameProcess.isHardMode());
                }

                for (ColossusTitan titan : ColossusTitan.allColossus) {
                    titan.tick();
                }

                addArmoured(gameProcess.isHardMode());
                addColossus();

                gameProcess.incrementHour(1);
                nextRound = false;
            }
            System.out.println("Run as usual");
        }else{
            State.setCurrentState(game.menuState);

            if(!Wall.wallCondition) {
                JOptionPane.showMessageDialog(null,"Game Over\nYou lose.");
//                showMessage("Game Over\nYou lose.", this.game.getFrame());
            }else{
                JOptionPane.showMessageDialog(null,"Game Over\nYou win.");
            }
            System.out.println("Game over");

        }

    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        stats.render(g);
        int i = 0;
        for (Wall wall : Wall.walls) {
            wall.render(g, this.game.getMouseManager());
        }

        for (Weapon weapon : Weapon.weapons) {
            weapon.render(g, this.game.getMouseManager());
        }

        if (!ArmouredTitan.allArmoured.isEmpty()) {
            for (ArmouredTitan titan : ArmouredTitan.allArmoured) {
                titan.render(g, this.game.getMouseManager());
            }
        }

        if (!ColossusTitan.allColossus.isEmpty()) {
            for (ColossusTitan titan : ColossusTitan.allColossus) {
                titan.render(g, this.game.getMouseManager());
            }
        }

        g.drawImage(Asset.endturn, 320-80, 12 * Tile.TILE_HEIGHT, null);
        System.out.println("Everything renders once. ");
//        if(!(Wall.wallCondition && (ArmouredTitan.armouredCondition || ColossusTitan.colossusCondition))){
//            JOptionPane.showMessageDialog(null,"Game Over\nYou lose.");
//        }
    }

    protected void weaponAttack() {
        int count = 0;
        for (int i=0; i<Weapon.weapons.length; i++) {
            if (Weapon.weapons[i].getWeapon().getLevel() > 0) {
                for (ArmouredTitan armoured : ArmouredTitan.allArmoured) {
                    if (armoured.getY() == i) {
                        armoured.damage(Weapon.weapons[i].getWeapon().attack());
                    }
                }
                for (ColossusTitan colossus : ColossusTitan.allColossus) {
                    if (colossus.getY() == i) {
                        colossus.damage(Weapon.weapons[i].getWeapon().attack());
                    }
                }
            }
        }
    }

    public static void addArmoured(boolean hardMode) {
        System.out.println(Process.hour);
        if (Process.hour > 0 && ((!hardMode && Process.hour == 5) || (hardMode && Process.hour % 5 == 0))) {
            Random r = new Random();

            int randomInt;
            randomInt = r.nextInt(10);
            attackontitan.frontend.entities.ArmouredTitan arTitan = new attackontitan.frontend.entities.ArmouredTitan(0, randomInt);

            System.out.println("A armoured titan is added to the ground. ");

        }else {
            System.out.println("No armoured titan added. ");
        }
    }

    public static void addColossus() {
        Random r = new Random();
        if (Process.hour == 5) {
            int randomInt;
            randomInt = r.nextInt(10);

            attackontitan.frontend.entities.ColossusTitan newCol = new attackontitan.frontend.entities.ColossusTitan(9, randomInt);
            System.out.println("A colossus titan is added to the ground. ");
        } else {
            System.out.println("No colossus titan added. ");
        }
    }

//    public static void onEnter(KeyEvent e) {
//        nextRound = e.getKeyCode() == KeyEvent.VK_ENTER;
//    }

    public static String showMessage(String message, JFrame parent) {
        final JOptionPane pane = new JOptionPane(message);
        pane.setWantsInput(false);
        final JDialog d = pane.createDialog((JFrame)null, "Title");
//        d.setLocation(10,10);
        d.setLocation(parent.getWidth(), parent.getHeight() + d.getHeight());
        d.setAutoRequestFocus(false);
        d.setVisible(true);

        return (String)pane.getInputValue();
    }
}
