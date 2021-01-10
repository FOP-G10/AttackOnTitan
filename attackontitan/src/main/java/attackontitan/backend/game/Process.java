/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.game;

import attackontitan.backend.gameobjects.Wall;
import attackontitan.backend.player.PlayerAccount;
import attackontitan.backend.world.Ground;

import java.util.ArrayList;

/**
 *
 * @author Autumn
 */
public class Process extends PlayerAccount {
    protected Ground ground;
    public static Wall[] walls;

    public static int hour;
    public ArrayList<Integer[]> colossusIndex;
    public ArrayList<Integer[]> armouredIndex;

    private final boolean hardMode;

    public Process(boolean hardMode) {
        super(hardMode);
        this.ground = new Ground(this);
        walls = this.createWalls();
        hour = 0;
        this.colossusIndex = new ArrayList<>();
        this.armouredIndex = new ArrayList<>();
        this.hardMode = hardMode;
    }

    // Methods below creates the walls and weapon and print them out in the console
    
    private Wall[] createWalls() {
        Wall[] tmp = new Wall[10];
        for (int i=0; i<10; i++) {
            tmp[i] = new Wall();
        }
        return tmp;
    }
    
    public void checkAddCoin() {
        this.addCoin();
    }

    public int getHour() {
        return hour;
    }

    public void incrementHour(int hour) {
        Process.hour += hour;
    }

    public boolean isHardMode() {
        return hardMode;
    }
}
