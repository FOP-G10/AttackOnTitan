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
public class Titan {
    
    protected int hp;
    protected final int attackPoint;
    
    public Titan(int hp, int attackPoint) {
        this.hp = hp;
        this.attackPoint = attackPoint;
    }
    
    public int attack() {
        return this.attackPoint;
    }
    
    public Titan damage(int damagePoints) {
        String titanName;
        try {
            ArmouredTitan titan = (ArmouredTitan)this;
            titanName = "Armoured titan";
        }catch(ClassCastException e) {
            ColossusTitan titan = (ColossusTitan)this;
            titanName = "Colossus titan";
        }
        this.hp -= damagePoints;
        System.out.println("The " + titanName + " take damage: " + damagePoints + " points");
        System.out.println("Current HP for " + titanName + ": " + this.hp + " points");
        System.out.println();
        if (this.hp <= 0) {
            System.out.println( titanName + " died.");
            return null;
        } else {
            return this;
        }
    }

    public int moveSideways() {
        Random r = new Random();
        int[] result = {-1, 1};
        return result[r.nextInt(2)];
    }

    public int moveForward() {
        return 1;
    }
    
}
