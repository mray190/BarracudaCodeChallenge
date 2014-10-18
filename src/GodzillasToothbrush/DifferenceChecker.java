package GodzillasToothbrush;

import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author mray
 */
public class DifferenceChecker {

    private Board currentBoard;
    private Board previousBoard;
    private ArrayList<Point> changes;

    public DifferenceChecker(Board currentBoard) {
        //HANDLE IF CURRENTBOARD AND PREVIOUS BOARD ARE NULL (FIRST TIME THROUGH)
        this.currentBoard = currentBoard;
        this.previousBoard = null;
    }

    public void calcDifferences() {
        int size = currentBoard.getBoard().size();
        for (int z = 0; z < size; z++) {
            for (int x = 0; x < size - z; x++) {
                for (int y = 0; y < size - z - y; y++) {
                    int difference = abs(currentBoard.get(x, y, z).data - previousBoard.get(x, y, z).data);
                    if (difference != 0) {
                        changes.add(currentBoard.get(x,y,z));
                    }
                }
            }
        }
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }
    
    public void setPreviousBoard(Board previousBoard) {
        this.previousBoard = previousBoard;
    }
    
    public Board getCurrentBoard() {
        return this.currentBoard;
    }
    
    public Board getPreviousBoard() {
        return this.previousBoard;
    }

    public ArrayList<Point> getDifferences() {
        return this.changes;
    }

}
