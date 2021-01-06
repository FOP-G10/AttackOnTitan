/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.gameobjects;

/**
 *
 * @author Autumn
 */
public class Wall {
    private int hp;

    public Wall(){
        this.hp = 50;
    }
    
    public void upgradeWall(int val) {
        this.hp += val;
    }
    
    public void damage(int damagePoint) {
        this.hp -= damagePoint;
    }
    
    public int getHp() {
        return this.hp;
    }
    
}
