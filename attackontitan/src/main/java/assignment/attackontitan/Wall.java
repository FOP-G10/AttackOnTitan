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
public class Wall {
    int hp;
    private final Weapon weapon;
    
    public Wall(){
        this.hp = 50;
        this.weapon = new Weapon();
    }
    
    public void upgradeWall(int val) {
        this.hp += val;
    }
    
    public boolean checkCondition(){
        return this.hp > 0;
    }
    
    public void damage(int damagePoint) {
        this.hp -= damagePoint;
    }
    
    public Weapon showWeapon() {
        return this.weapon;
    }
    
}
