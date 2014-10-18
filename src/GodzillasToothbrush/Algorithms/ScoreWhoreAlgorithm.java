package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;

/**
 *
 * @author Aaron
 */
public class ScoreWhoreAlgorithm extends Algorithm {

    @Override
    public Point makeMove(Board current, Board previous) {
        if (current.getMove() <= 5){
            return null;
        }
        
        if (current.opponentWaited(previous)){
            return null;
        }
        
        return AlgorithmHelper.highestScore(current.getLegalMoves());
    }
}