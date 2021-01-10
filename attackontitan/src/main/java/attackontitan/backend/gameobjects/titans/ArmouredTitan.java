/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.gameobjects.titans;

/**
 *
 * @author Autumn
 */
public class ArmouredTitan extends Titan {
    
    private int extraChance;
    
    public ArmouredTitan() {
        this.hp = 100;
        this.attackPoint = 5;
        this.extraChance = 0;
    }
    
    @Override
    public String toString() {
        return "AA";
    }
    
    public int getExtraChance() {
        return this.extraChance;
    }

    @Override
    public Titan damage(int damagePoints) {
        this.hp -= damagePoints;
//        System.out.println("The Armoured Titan take damage: " + damagePoints + " points");
//        System.out.println("Current HP for Armoured Titan: " + this.hp + " points");
//        System.out.println();
        if (this.hp <= 0) {
//            System.out.println("Armoured Titan died.");
            return null;
        } else {
            return this;
        }
    }

    @Override
    public int moveSideways() {
        this.extraChance = 1;
        return super.moveSideways();
    }
}
