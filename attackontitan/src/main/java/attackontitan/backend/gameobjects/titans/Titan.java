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

    public Titan damage(int damagePoints) {
        this.hp -= damagePoints;
//        System.out.println("The titan take damage: " + damagePoints + " points");
//        System.out.println("Current HP for armoured titan: " + this.hp + " points");
//        System.out.println();
        if (this.hp <= 0) {
//            System.out.println("Colossus titan died. ");
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

    public int getHp() {
        return hp;
    }
}
