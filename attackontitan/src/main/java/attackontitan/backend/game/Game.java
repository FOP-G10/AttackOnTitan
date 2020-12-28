package attackontitan.backend.game;

import attackontitan.backend.gameobjects.Wall;

import javax.swing.*;

public class Game extends Process{

    public Game(boolean hardMode) {
        super(hardMode);
    }
    public void titanTurn(JFrame parentFrame) {
        if (this.armouredIndex.size() + this.colossusIndex.size() > 0){
            System.out.println("ArmouredTitan's turn...");
            System.out.println();

            System.out.println("ArmouredTitan start to move");
            this.moveArmouredForward();
            this.moveColossusSideways();
//            System.out.print("Press enter to continue... ");
//            sc.nextLine();

            System.out.println("ArmouredTitan's turn...");
            System.out.println("The board after the titans move...");
            this.printBoard();
//            System.out.print("Press enter to continue... ");
//            sc.nextLine();

            System.out.println("ArmouredTitan's turn...");
            this.armouredAttack();
            this.colossusAttack();
//            System.out.print("Press enter to continue... ");
//            sc.nextLine();

            System.out.println("The board after the titan's turn... ");
            this.printBoard();
            System.out.println();
//            System.out.print("Press enter to continue... ");
//            sc.nextLine();
        } else {
            System.out.println("No titan on the ground.");
            System.out.println("ArmouredTitan's turn skipped.");
            System.out.println();
//            System.out.print("Press enter to continue... ");
//            sc.nextLine();
        }
        this.addColossus();
        this.addArmoured();
    }

    public void playerTurn(JFrame parentFrame) {
        System.out.println("Player's turn...");

        this.printBoard();
//        System.out.println("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
//        String weaponString = sc.nextLine();
//        JOptionPane jOptionPane = new JOptionPane();
//        String weaponString = JOptionPane.showInputDialog(parentFrame, "Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
        String weaponString = getInput("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)", parentFrame);
        if (!weaponString.isEmpty()) {
            this.upgradeWeapon(weaponString);
        } else {
            System.out.println("No weapon upgraded. ");
        }
//        System.out.print("Press enter to continue... ");
//        sc.nextLine();

        System.out.println("Player's turn");
        System.out.println();
        System.out.println("Your board after upgrading weapon: ");
        this.printBoard();
//        System.out.print("Press Enter to continue... ");
//        sc.nextLine();

        this.printBoard();
//        System.out.println("Do you want to upgrade all walls? (press 1 if yes, press Enter if no) Current coin number: " + this.coin);
//        String upgradeWalls = sc.nextLine();
//        String upgradeWalls = JOptionPane.showInputDialog(parentFrame, "Do you want to upgrade all walls? (press 1 if yes, press Enter if no) Current coin number: " + this.coin);
        String upgradeWalls = getInput("Do you want to upgrade all walls? (press 1 if yes, press Enter if no) Current coin number: " + this.coin, parentFrame);
        if (upgradeWalls.isEmpty()) {
//            System.out.println("Choose the wall you would like to upgrade (Type a string of integer or hit Enter ot skip)");
//            upgradeWalls = sc.nextLine();
//            upgradeWalls = JOptionPane.showInputDialog("Choose the wall you would like to upgrade (Type a string of integer or hit Enter ot skip)");
            upgradeWalls = getInput("Choose the wall you would like to upgrade (Type a string of integer or hit Enter ot skip)", parentFrame);
        }else {
            upgradeWalls = "0123456789";
        }
        if (!upgradeWalls.isEmpty()) {
//            System.out.println("How many HP do you want to add up to the wall(s)? Current coin number: " + this.coin);
//            String upgradeHp = sc.nextLine();
//            String upgradeHp = JOptionPane.showInputDialog("How many HP do you want to add up to the wall(s)? Current coin number: " + this.coin);
            String upgradeHp = getInput("How many HP do you want to add up to the wall(s)? Current coin number: " + this.coin, parentFrame);
            if (upgradeWalls.length() == upgradeHp.length()) {
                this.upgradeWall(upgradeWalls, upgradeHp);
            } else {
                System.out.println("No wall upgraded. ");
            }
        } else {
            System.out.println("No wall upgraded. ");
        }
//        System.out.print("Press enter to continue... ");
//        sc.nextLine();

        System.out.println("Your board after upgrading walls: ");
        this.printBoard();
        System.out.println();
//        System.out.print("Press Enter to continue... ");
//        sc.nextLine();

        this.weaponAttack();
        System.out.println("Your board after attacking... ");
        this.printBoard();

        this.checkAddCoin();

        System.out.println();
    }

    public boolean checkResult() {
        return this.checkWalls() && this.checkTitans();
    }

    private boolean checkWalls() {
        for (Wall wall: this.walls) {
            if (!wall.checkCondition()) {
                JFrame jframe = new JFrame();
                JOptionPane.showMessageDialog(null,"Game over. You lose. ");
                return false;
            }
        }
        return true;
    }

    private boolean checkTitans() {
        if (this.colossusIndex.isEmpty() && this.addedTitans && this.armouredIndex.isEmpty()) {
            JFrame jframe = new JFrame();
            JOptionPane.showMessageDialog(null,"You win. All titans are dead. ");
            return false;
        }
        return true;
    }

    public static String getInput(String message, JFrame parent) {
        final JOptionPane pane = new JOptionPane(message);
        pane.setWantsInput(true);
        final JDialog d = pane.createDialog((JFrame)null, "Title");
//        d.setLocation(10,10);
        d.setLocation(parent.getWidth(), parent.getHeight() + d.getHeight());
        d.setVisible(true);

        return (String)pane.getInputValue();
    }


}
