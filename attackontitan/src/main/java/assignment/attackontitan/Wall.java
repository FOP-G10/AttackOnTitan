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
    int index;
    int hp;
    Weapon weapon;
    
    public Wall(int index){
        this.index = index;
        this.hp = 50;
        this.weapon = new Weapon();
    }
    
    public void upgradeWall(int val) {
        this.hp += val;
    }
    
    public boolean checkCondition(){
        if (this.hp > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void damage(int damagePoint) {
        this.hp -= damagePoint;
    }
}
