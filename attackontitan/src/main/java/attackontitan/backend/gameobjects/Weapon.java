/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.gameobjects;

import java.util.HashMap;

/**
 *
 * @author Autumn
 */
public class Weapon {
    private int level;
    private final int maxWeaponLevel;
    private static final HashMap<Integer, Integer> attackDamage = new HashMap<>();
    
    public Weapon() {
        this.level = 0;
        this.maxWeaponLevel = 3;
        this.setAttackDamage();
    }
    
    public boolean upgrade() {
        if (this.level < this.maxWeaponLevel) {
            this.level += 1;
            System.out.println("Weapon upgraded to level " + this.level);
            return true;
        } else {
            System.out.println("The weapon has reached maximum level, level " + this.maxWeaponLevel + ". Upgrade failed");
            return false;
        }
    }

    public void downgrade() {
        this.level -= 1;
    }
    
    public final void setAttackDamage() {
        attackDamage.put(0, 0);
        attackDamage.put(1, 2);
        attackDamage.put(2, 5);
        attackDamage.put(3, 10);
    }
    
    public int getAttackDamage() {
        return attackDamage.get(this.level);
    }
    
    public boolean validUpgrade() {
        if (this.level < 3) {
            return true;
        }else {
            System.out.println("Your weapon has reached level 3! ");
            return false;
        }
    }
    
    public int attack() {
        return attackDamage.get(this.level);
    }
    
    public void damage() {
        this.level -= 1;
    }
    
    public int getLevel() {
        return this.level;
    }
}
