/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.attackontitan;

/**
 *
 * @author Autumn
 */
public class ArmouredTitan implements Titan {
    
    int hp;
    int attackPoint;
    
    public ArmouredTitan() {
        this.hp = 100;
        this.attackPoint = 5;
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

    @Override
    public int move() {
        return 1;
    }
}
