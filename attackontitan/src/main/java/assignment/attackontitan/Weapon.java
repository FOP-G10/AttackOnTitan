/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.attackontitan;

import java.util.HashMap;

/**
 *
 * @author Autumn
 */
public class Weapon {
    int level;
    int maxWeaponLevel;
    HashMap<Integer, Integer> attackDamage = new HashMap<>();
    
    public Weapon() {
        this.level = 0;
        this.maxWeaponLevel = 3;
        this.setAttackDamage();
    }
    
    public boolean upgrade() {
        if (this.level < this.maxWeaponLevel) {
            this.level += 1;
            return true;
        } else {
            System.out.println("The weapon has reached maximum level, level " + this.maxWeaponLevel + ". Upgrade failed");
            return false;
        }
    }
    
    public final void setAttackDamage() {
        attackDamage.put(0, 0);
        attackDamage.put(1, 2);
        attackDamage.put(2, 5);
        attackDamage.put(3, 10);
    }
}
