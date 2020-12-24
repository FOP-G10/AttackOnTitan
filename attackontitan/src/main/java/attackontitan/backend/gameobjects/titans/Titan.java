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
 * @param <T>
 */
public class Titan<T extends Titan<T>> {
    
    protected int hp;
    protected final int attackPoint;
    
    public Titan(int hp, int attackPoint) {
        this.hp = hp;
        this.attackPoint = attackPoint;
    }
    
    public int attack() {
        return this.attackPoint;
    }
    
    public T damage(int damagePoints) {
        this.hp -= damagePoints;
        System.out.println("The titan take damage: " + damagePoints + " points");
        System.out.println("Current HP for armoured titan: " + this.hp + " points");
        System.out.println();
        if (this.hp <= 0) {
            System.out.println("Colossus titan died. ");
            return null;
        } else {
            return (T)this;
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
