/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.gameobjects.titans;

import java.util.Random;

/**
 *
 * @author Autumn
 */
public class ArmouredTitan extends Titan<ArmouredTitan> {
    
    private int extraChance;
    
    public ArmouredTitan() {
        super(100, 5);
        this.extraChance = 0;
    }
    
    @Override
    public String toString() {
        return "AA";
    }

    public int moveForward() {
        return 1;
    }
    
    public int getExtraChance() {
        return this.extraChance;
    }
    
    public int moveSideways() {
        Random r = new Random();
        int[] result = {-1, 1};
        this.extraChance = 1;
        return result[r.nextInt(2)];
    }
}
