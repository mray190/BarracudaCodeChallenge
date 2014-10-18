package GodzillasToothbrush;

import java.util.ArrayList;

/**
 *
 * @author mray
 */
public class DifferenceChecker {
    private int[][][] currentBoard;
    private int[][][] previousBoard;
    private int[][][] differenceBoard;
    private int size;
    private ArrayList<Point> changes;
    
    public DifferenceChecker(int[][][] currentBoard) {
        this.size = this.currentBoard.length;
        this.currentBoard = currentBoard;
        this.previousBoard = new int[this.size][this.size][this.size];
        this.differenceBoard = new int[this.size][this.size][this.size];
    }
    
    public void calcDifferences() {
        for (int z = 0; z < this.size; z++) {
            for (int x = 0; x < this.size - z; x++) {
                for (int y = 0; y < this.size - z; y++) {
                    differenceBoard[x][y][z] = currentBoard[x][y][z] - previousBoard[x][y][z];
                    if (differenceBoard[x][y][z]!=0)
                        changes.add(new Point(x,y,z,differenceBoard[x][y][z]));
                }
            }
        }
    }
    
    public int[][][] getDifferenceBoard() { return this.differenceBoard; }
    public ArrayList<Point> getDifferences() { return this.changes; }
}
