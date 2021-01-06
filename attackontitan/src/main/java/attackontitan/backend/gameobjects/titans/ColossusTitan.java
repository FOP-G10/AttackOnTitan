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
        super(50, 10);
    }
    
    @Override
    public String toString() {
        return "CC";
    }

    @Override
    public int moveForward() {
        return 0;
    }
    
}
