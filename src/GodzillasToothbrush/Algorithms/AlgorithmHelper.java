package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Point;
import java.util.ArrayList;
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
        
        points.sort(new Comparator<Point>(){

            @Override
            public int compare(Point o1, Point o2) {
                return scoreOf(o2) - scoreOf(o1);
            }
            
        });
        
        return points.get(0);
    }
}
