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
public interface Titan {
    public void attack();
    public Titan damage(int points);
    public int move();
}
