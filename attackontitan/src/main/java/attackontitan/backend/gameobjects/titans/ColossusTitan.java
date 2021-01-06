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
public class ColossusTitan extends Titan {
    public ColossusTitan() {
        this.hp = 50;
        this.attackPoint = 10;
    }
    
    @Override
    public String toString() {
        return "CC";
    }

    @Override
    public Titan damage(int damagePoints) {
        this.hp -= damagePoints;
        System.out.println("The Colossus Titan take damage: " + damagePoints + " points");
        System.out.println("Current HP for Colossus Titan: " + this.hp + " points");
        System.out.println();
        if (this.hp <= 0) {
            System.out.println("Colossus Titan died.");
            return null;
        } else {
            return this;
        }
    }

    @Override
    public int moveForward() {
        return 0;
    }
}
