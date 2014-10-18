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
    public static class CompareScore implements Comparator<Point> {

        @Override
        public int compare(Point arg0, Point arg1) {
            return scoreOf(arg1) - scoreOf(arg0);
        }

    }
    
    public static class CompareVolumePlayer implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return o2.volumePlayer - o1.volumePlayer;
        }
    }    
    
    public static class CompareVolumeOpp implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return o2.volumeOpp - o1.volumeOpp;
        }
    }        
    //--------------------------------------------------------------------------    
}
