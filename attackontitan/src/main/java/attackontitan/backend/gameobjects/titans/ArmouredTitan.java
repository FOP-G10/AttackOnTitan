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
        super(100, 5);
        this.extraChance = 0;
    }
    
    @Override
    public String toString() {
        return "AA";
    }
    
    public int getExtraChance() {
        return this.extraChance;
    }
    
    public int moveSideways() {
        this.extraChance = 1;
        return super.moveSideways();
    }
}
