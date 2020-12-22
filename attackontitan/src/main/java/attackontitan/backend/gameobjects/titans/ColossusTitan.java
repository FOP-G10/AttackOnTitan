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
public class ColossusTitan extends Titan<ColossusTitan> {
    
    public ColossusTitan() {
        super(50, 10);
    }
    
    @Override
    public String toString() {
        return "CC";
    }

    public int moveSideways() {
        Random r = new Random();
        int[] result = {-1, 1};
        return result[r.nextInt(2)];
    }
    
}
