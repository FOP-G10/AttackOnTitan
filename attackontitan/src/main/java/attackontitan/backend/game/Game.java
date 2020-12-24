package attackontitan.backend.game;

public class Game extends Process{

    public Game(boolean hardMode) {
        super(hardMode);
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

            System.out.println("Titan's turn...");
            System.out.println("The board after the titans move...");
            this.printBoard();
            System.out.print("Press enter to continue... ");

            sc.nextLine();

            System.out.println("Titan's turn...");
            this.armouredAttack();
            this.colossusAttack();
            System.out.print("Press enter to continue... ");
            sc.nextLine();

            System.out.println("The board after the titan's turn... ");
            this.printBoard();
            System.out.println();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
        } else {
            System.out.println("No titan on the ground.");
            System.out.println("Titan's turn skipped.");
            System.out.println();
            System.out.print("Press enter to continue... ");
            sc.nextLine();
        }
        this.addColossus();
        this.addArmoured();
    }

    public void playerTurn() {
        System.out.println("Player's turn...");

        this.printBoard();
        System.out.println("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
        String weaponString = sc.nextLine();
        if (!weaponString.isEmpty()) {
            this.upgradeWeapon(weaponString);
        } else {
            System.out.println("No weapon upgraded. ");
        }
        System.out.print("Press enter to continue... ");
        sc.nextLine();

        System.out.println("Player's turn");
        System.out.println();
        System.out.println("Your board after upgrading weapon: ");
        this.printBoard();
        System.out.print("Press Enter to continue... ");
        sc.nextLine();

        this.printBoard();
        System.out.println("Do you want to upgrade all walls? (press 1 if yes, press Enter if no) Current coin number: " + this.coin);
        String upgradeWalls = sc.nextLine();
        if (upgradeWalls.isEmpty()) {
            System.out.println("Choose the wall you would like to upgrade (Type a string of integer or hit Enter ot skip)");
            upgradeWalls = sc.nextLine();
        }else {
            upgradeWalls = "0123456789";
        }
        if (!upgradeWalls.isEmpty()) {
            System.out.println("How many HP do you want to add up to the wall(s)? Current coin number: " + this.coin);
            String upgradeHp = sc.nextLine();
            if (upgradeWalls.length() == upgradeHp.length()) {
                this.upgradeWall(upgradeWalls, upgradeHp);
            } else {
                System.out.println("No wall upgraded. ");
            }
        } else {
            System.out.println("No wall upgraded. ");
        }
        System.out.print("Press enter to continue... ");
        sc.nextLine();

        System.out.println("Your board after upgrading walls: ");
        this.printBoard();
        System.out.println();
        System.out.print("Press Enter to continue... ");
        sc.nextLine();

        this.weaponAttack();
        System.out.println("Your board after attacking... ");
        this.printBoard();

        this.checkAddCoin();

        System.out.println();
    }
}
