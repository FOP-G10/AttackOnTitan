/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attackontitan.backend.world;

import attackontitan.backend.gameobjects.titans.Titan;
import attackontitan.backend.game.Process;

/**
 *
 * @author Autumn
 */
public class Ground {
    private Titan[][][] ground;
    private Process process;

    public Ground(Process process) {
        this.ground = new Titan[10][10][2];
        this.process = process;
    }

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder("On The Ground\nRow\n");
        for (int i=0; i<this.ground.length; i++) {
            sb.append(i).append(" ");
            for (Titan[] titans: this.ground[i]) {
                if (titans[0] == null && titans[1] == null) {
                    sb.append("  ");
                } else if (titans[1] == null) {
                    sb.append(titans[0].toString());
                } else if (titans[0] == null){
                    sb.append(titans[1].toString());
                }else {
                    sb.append("AC");
                }
                sb.append(" ");
            }
            switch (i) {
                case 0:
                    sb.append("HOUR: " + this.process.getHour());
                    break;
                case 1:
                    sb.append("Coin: " + this.process.getCoin());
                    break;
                default:
                    sb.append("\n");
                    break;
            }
        }
        return sb.toString();
    }

    public Titan[][] getElementOnGround(int x) {
        return this.ground[x];
    }

    public Titan[] getElementOnGround(int x, int y) {
        return this.ground[x][y];
    }

    public Titan getElementOnGround(int x, int y, int z) {
        return this.ground[x][y][z];
    }

    public void setElementOnGround(int x, int y, int z, Titan val) {
        this.ground[x][y][z] = val;
    }

    public int getNumberOfRows() {
        return this.ground.length;
    }

    public Titan[][][] getGround() {
        return this.ground;
    }
}
