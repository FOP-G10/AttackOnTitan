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
public class PlayerAccount {
    int coin;
    String username;
    
    public PlayerAccount() {
        this.coin = 50;
    }
    
    public void addCoin(int amount) {
        this.coin += amount;
    }
    
    public boolean checkEnough(int coinNeeded) {
        if (this.coin >= coinNeeded) {
            return true;
        } else {
            System.out.println("Not enough coin for the transaction. ");
            return false;
        }
    }
    
    public void payCoin(int amount) {
        this.coin -= amount;
    }
}
