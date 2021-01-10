package attackontitan.frontend.state;

import attackontitan.backend.game.GameBackend;
import attackontitan.backend.game.Process;
import attackontitan.frontend.audio.AudioStuff;
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
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends State{

    private World world = null;
    private Stats stats = null;

    public static boolean gameOver = false;
    public static boolean nextRound = false;

    public static GameBackend gameProcess;
    public static AudioStuff audioStuff;

    private static Rectangle mute = new Rectangle(9* Tile.TILE_WIDTH, 0, 32, 32);
    private static boolean hoveringMute = false;

    private static Rectangle backMenu = new Rectangle(8* Tile.TILE_WIDTH, 0, 32, 32);
    private static boolean hoveringMenu = false;

    private Font gameFont;
    private final InputStream fontStream = GameState.class.getResourceAsStream("/fonts/gameFont.ttf");

    public GameState(Game game, boolean hardMode) {
        super(game);

        gameProcess = new GameBackend(hardMode);
        world = new World(gameProcess);
        stats = new Stats(gameProcess);

        Wall.wallCondition = true;
        ArmouredTitan.armouredCondition = true;
        ColossusTitan.colossusCondition = true;
        Weapon.fireballHeight = new double[Weapon.weapons.length];
        nextRound = false;
        gameOver = false;


        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            gameFont = gameFont.deriveFont(12f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        Wall.walls = new Wall[10];
        ColossusTitan.allColossus = new ArrayList<>();
        ArmouredTitan.allArmoured = new ArrayList<>();

        audioStuff = new AudioStuff("/audiotracks/gameAudio.wav");
        audioStuff.playMusic();

        Wall.createWalls();
        Weapon.init();

        JOptionPane.showMessageDialog(null,"Click on the wall or weapon to upgrade. \nClick on the end turn button to end your turn. ");
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

            // player's turn
            for (Wall wall : Wall.walls) {
                wall.tick();
            }

            //button to complete
            if (nextRound) {
                // action here after user complete upgrading
                for (Weapon weapon: Weapon.weapons) {
                    weapon.tick();
                }
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
        } else{
            audioStuff.stopMusic();

            if(!Wall.wallCondition) {
                JOptionPane.showMessageDialog(null,"Game Over\nYou lose.");
            }else{
                JOptionPane.showMessageDialog(null,"Game Over\nYou win.");
            }

            State.setCurrentState(new MenuState(this.game));
        }
    }

    @Override
    public void render(Graphics g) {
        if (gameFont != null) {
            g.setFont(gameFont);
        }
        world.render(g);
        stats.render(g);
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

//        g.setColor(Color.black);
//        g.fillRect(8*Tile.TILE_WIDTH, 0, 32, 32);
//        g.setColor(Color.white);
//        g.fillRect(9* Tile.TILE_WIDTH, 0, 32, 32);

        g.drawImage(Asset.menuIcon, 8*Tile.TILE_WIDTH, 0, null);
        if (audioStuff.playing) {
            g.drawImage(Asset.volumeIcon, 9 * Tile.TILE_WIDTH, 0, null);
        } else {
            g.drawImage(Asset.muteIcon, 9 * Tile.TILE_WIDTH, 0, null);
        }
    }

    public static void addArmoured(boolean hardMode) {
//        System.out.println(Process.hour);
        if (Process.hour > 0 && ((!hardMode && Process.hour == 5) || (hardMode && Process.hour % 5 == 0))) {
            Random r = new Random();

            int randomInt;
            randomInt = r.nextInt(10);
            attackontitan.frontend.entities.ArmouredTitan arTitan = new attackontitan.frontend.entities.ArmouredTitan(0, randomInt);

//            System.out.println("A armoured titan is added to the ground. ");

        }else {
//            System.out.println("No armoured titan added. ");
        }
    }

    public static void addColossus() {
        Random r = new Random();
        if (Process.hour == 5) {
            int randomInt;
            randomInt = r.nextInt(10);

            attackontitan.frontend.entities.ColossusTitan newCol = new attackontitan.frontend.entities.ColossusTitan(9, randomInt);
//            System.out.println("A colossus titan is added to the ground. ");
        } else {
//            System.out.println("No colossus titan added. ");
        }
    }

    public static void onMouseMoved(MouseEvent e) {
        hoveringMute = mute.contains(e.getX(), e.getY());
        hoveringMenu = backMenu.contains(e.getX(), e.getY());
    }

    public static void onMouseReleased() {
        if(hoveringMute && audioStuff.playing) {
            audioStuff.stopMusic();
        }else if(hoveringMute) {
            audioStuff.playMusic();
        }

        if(hoveringMenu) {
            audioStuff.stopMusic();
            State.setCurrentState(new MenuState(currentState.game));
        }
    }
}
