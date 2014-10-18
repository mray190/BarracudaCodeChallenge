package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;

/**
 *
 * @author Aaron
 */
public class WeightedValueAlgorithm extends Algorithm{

    @Override
    public Point makeMove(Board current, Board previous) {
        if (current.opponentWaited(previous)){
            return null;
        }
        
        return AlgorithmHelper.highestValue(current.getLegalMoves());        
    }
    
}
