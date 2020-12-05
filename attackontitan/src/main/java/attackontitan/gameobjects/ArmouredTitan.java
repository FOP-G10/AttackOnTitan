/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.gameobjects;

import java.util.Random;

/**
 *
 * @author Autumn
 */
public class ArmouredTitan implements Titan {
    
    private int hp;
    private final int attackPoint;
    private int extraChance;
    
    public ArmouredTitan() {
        this.hp = 100;
        this.attackPoint = 5;
        this.extraChance = 0;
    }
    
    @Override
    public String toString() {
        if (this == null) {
            return "  ";
        }
        return "AA";
    }

    @Override
    public int attack() {
        return this.attackPoint;
    }

    @Override
    public ArmouredTitan damage(int damagePoints) {
        this.hp -= damagePoints;
        System.out.println("The armoured titan take damage: " + damagePoints + " points");
        System.out.println("Current HP for armoured titan: " + this.hp + " points");
        System.out.println();
        if (this.hp <= 0) {
            System.out.println("Colossus titan died. ");
            return null;
        } else {
            return this;
        }
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
