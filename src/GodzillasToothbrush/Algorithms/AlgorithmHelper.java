package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Aaron
 */
public class AlgorithmHelper {
    
    public static int scoreOf(Point p){
        return p.score();
    }

    //Comparators
    //--------------------------------------------------------------------------
    public static Point highestScore(ArrayList<Point> points){
        return sort(points, new CompareScore());
    }
    
    public static Point highestValue(ArrayList<Point> points){
        return sort(points, new CompareWeightedValue());      
    }
    
    private static Point sort(ArrayList<Point> points, Comparator<Point> comp){
        if (points.isEmpty()) return null;
        Collections.sort(points, comp);
        return points.get(points.size() - 1);        
    }
    //--------------------------------------------------------------------------
    
    //Comparator
    //--------------------------------------------------------------------------
    public static class CompareScore implements Comparator<Point>{

        @Override
        public int compare(Point arg0, Point arg1) {
            return scoreOf(arg0) - scoreOf(arg1);
        }
        
    }
    
    public static class CompareWeightedValue implements Comparator<Point>{

        @Override
        public int compare(Point o1, Point o2) {
            return (int) (o1.calcValWeight() - o2.calcValWeight());
        }
        
    }
    //--------------------------------------------------------------------------
}