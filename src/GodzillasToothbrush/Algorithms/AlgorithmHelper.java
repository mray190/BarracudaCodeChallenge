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
    
    public static Point highestScore(ArrayList<Point> points){
        if (points.isEmpty()) return null;
        
        Collections.sort(points, new CompareScore());

        return points.get(0);
    }
    
    public static class CompareScore implements Comparator<Point>{

        @Override
        public int compare(Point arg0, Point arg1) {
            return scoreOf(arg1) - scoreOf(arg0);
        }
        
    }
}
