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
public class ArmouredTitan implements Titan {
    
    int hp;
    int attackPoint;
    int extraChance;
    
    public ArmouredTitan() {
        this.hp = 100;
        this.attackPoint = 5;
        this.extraChance = 0;
    }
    
    @Override
    public String toString() {
        return "AA";
    }

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArmouredTitan damage(int damagePoints) {
        this.hp -= damagePoints;
        if (this.hp <= 0) {
            return null;
        } else {
            return this;
        }
    }

    public int moveForward() {
        return 1;
    }
    
    public int moveSideways() {
        Random r = new Random();
        int[] result = {-1, 1};
        this.extraChance = 1;
        return result[r.nextInt(2)];
    }
}
