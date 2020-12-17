/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.gameobjects;

import java.util.Random;

/**
 *
 * @author Autumn
 */
public class ColossusTitan implements Titan {
    
    private int hp;
    private final int attackPoint;
    
    public ColossusTitan() {
        this.hp = 50;
        this.attackPoint = 10;
    }
    
    @Override
    public String toString() {
        if (this == null) {
            return "  ";
        }
        return "CC";
    }
    
    @Override
    public int attack() {
        return this.attackPoint;
    }

    @Override
    public ColossusTitan damage(int damagePoints) {
        this.hp -= damagePoints;
        System.out.println("The colossus titan take damage: " + damagePoints + " points");
        System.out.println("Current HP for colossus titan: " + this.hp + " points");
        System.out.println();
        if (this.hp <= 0) {
            System.out.println("Colossus titan died. ");
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
    
}
