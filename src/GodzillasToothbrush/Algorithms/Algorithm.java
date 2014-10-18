package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author shane extend this to make algorithms
 */
public abstract class Algorithm {

    //Abstract
    //--------------------------------------------------------------------------
    /**
     *
     * @param current the current board
     * @param previous the previous board
     * @return the point to be changed, null if waiting
     */
    public abstract Point makeMove(Board current, Board previous);
    
    public abstract void endGame();
    //--------------------------------------------------------------------------
    
    //Helper Calculations
    //--------------------------------------------------------------------------
    public static int scoreOf(Point p) {
        return p.score();
    }
    //--------------------------------------------------------------------------

    //Sorting
    //--------------------------------------------------------------------------
    protected static void sort(ArrayList<Point> points, Comparator<Point> comp) {
        Collections.sort(points, comp);
    }
    //--------------------------------------------------------------------------

    //Comparators
    //--------------------------------------------------------------------------
    public static class MasterCompare implements Comparator<Point> {

        private final boolean isPlayer;
        private final Point firstMove;

        public MasterCompare(boolean isPlayer){
            this.isPlayer = isPlayer;
            this.firstMove = null;
        }
        
        public MasterCompare(boolean isPlayer, Point firstMove){
            this.isPlayer = isPlayer;
            this.firstMove = firstMove;
        }
        
        //Normally returns negative when lhs < rhs
        //We want to sort reverse ascending
        //Therefore we return negative when rhs < lhs
        //
        //Return positive if lhs > rhs
        @Override
        public int compare(Point lhs, Point rhs) {
            //Always compare based on the max score that can be achieved
            //with this point
            int volumeDiff;
            if (isPlayer) volumeDiff = rhs.volumePlayer - lhs.volumePlayer;
            else          volumeDiff = rhs.volumeOpp - lhs.volumeOpp;
            if (volumeDiff != 0) return volumeDiff;
            
            //When the max scores are equal, new logic:
            //Points closer to the middle are better
            if (rhs.scoreMiddle(firstMove) > lhs.scoreMiddle(firstMove)) return 1;
            else if (rhs.scoreMiddle(firstMove) < lhs.scoreMiddle(firstMove)) return -1;
            else return 0;
        }

    }
    //--------------------------------------------------------------------------    
}
