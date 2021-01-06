/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.player;

import java.util.HashMap;

/**
 *
 * @author Autumn
 */
public class PlayerAccount {
    protected int coin;
    protected int coinLevel;
    protected int coinSpent;
    protected boolean hardMode;
    private static final HashMap<Integer, Integer> coinScheme = setCoinScheme();
    String username;
    
    public PlayerAccount(boolean hardMode) {
        this.coin = 50;
        this.coinLevel = 0;
        this.coinSpent = 0;
        this.hardMode = hardMode;
    }

    private static HashMap<Integer, Integer> setCoinScheme() {
        HashMap<Integer, Integer> result = new HashMap<>();

        result.put(0, 5);
        result.put(1, 10);
        result.put(2, 15);

        return result;
    }

    private void upgradeCoinLevel() {
        if(this.coinLevel < 2 && this.coinSpent >= coinScheme.get(this.coinLevel)) {
            this.coinSpent -= coinScheme.get(this.coinLevel);
            this.coinLevel += 1;
        }
    }
    
    public void addCoin() {
        if (this.hardMode) {
            this.upgradeCoinLevel();
        }
        this.coin += coinScheme.get(this.coinLevel);
        System.out.println("Coin +" + coinScheme.get(this.coinLevel));
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

    public int getCoin() {
        return coin;
    }
}
