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
public class Wall {
    int index;
    int hp;
    int weaponLevel;
    int maxWeaponLevel;
    HashMap<Integer, Integer> attackDamage = new HashMap<>();
    public Wall(int index){
        this.index = index;
        this.hp = 50;
        this.weaponLevel = 0;
        this.maxWeaponLevel = 3;
        this.setAttackDamage();
    }
    
    public void upgradeWall(int val) {
        this.hp += val;
    }
    
    public boolean upgradeWeapon() {
        if (this.weaponLevel < this.maxWeaponLevel) {
            this.weaponLevel += 1;
            return true;
        } else {
            System.out.println("Wall " + this.index + " has reached maximum level, level " + this.maxWeaponLevel + ". Upgrade failed");
            return false;
        }
    }
    
    public final void setAttackDamage() {
        attackDamage.put(0, 0);
        attackDamage.put(1, 2);
        attackDamage.put(2, 5);
        attackDamage.put(3, 10);
    }
    
    public boolean checkCondition(){
        if (this.hp > 0) {
            return true;
        } else {
            return false;
        }
    }
}
