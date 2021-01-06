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
public abstract class Titan {
    
    protected int hp;
    protected int attackPoint;
    
    public int attack() {
        return this.attackPoint;
    }
    
    public abstract Titan damage(int damagePoints);

    public int moveSideways() {
        Random r = new Random();
        int[] result = {-1, 1};
        return result[r.nextInt(2)];
    }

    public int moveForward() {
        return 1;
    }
    
}
