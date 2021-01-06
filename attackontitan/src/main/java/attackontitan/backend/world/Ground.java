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
    private final Titan[][][] ground;
    private final Process process;
    private static final int[][] obstacle = {{0, 5}, {7, 4}};

    public Ground(Process process) {
        this.ground = new Titan[10][10][2];
        this.process = process;
    }

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder("On The Ground\nRow\n");
        for (int i=0; i<this.ground.length; i++) {
            sb.append(i).append(" ");
            for (int j=0; j<this.ground[i].length; j++) {
                Titan[] titans = this.ground[i][j];
                if ((titans[0] == null && titans[1] == null) || verifyObstacle(i, j)) {
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
                    sb.append("HOUR: ").append(this.process.getHour()).append("\n");
                    break;
                case 1:
                    sb.append("Coin: ").append(this.process.getCoin()).append("\n");
                    break;
                default:
                    sb.append("\n");
                    break;
            }
        }
        return sb.toString();
    }

    private static boolean verifyObstacle(int row, int col) {
        for (int[] coordinate: obstacle) {
            if (coordinate[0] == row && coordinate[1] == col) {
                return true;
            }
        }
        return false;
    }
}
