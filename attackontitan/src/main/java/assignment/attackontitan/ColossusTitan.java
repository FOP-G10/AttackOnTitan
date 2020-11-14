/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.attackontitan;

import java.util.Random;

/**
 *
 * @author Autumn
 */
public class ColossusTitan implements Titan {
    
    int hp;
    int attackPoint;
    
    public ColossusTitan() {
        this.hp = 50;
        this.attackPoint = 10;
    }
    
    
    @Override
    public String toString() {
        return "CC";
    }
    
    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
