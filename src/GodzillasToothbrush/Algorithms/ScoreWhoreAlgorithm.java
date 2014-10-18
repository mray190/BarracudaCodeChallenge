package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;

/**
 *
 * @author Aaron
 */
public class ScoreWhoreAlgorithm extends Algorithm{

    @Override
    public Point makeMove(Board current, Board previous) {
        return AlgorithmHelper.highestScore(current.getLegalMoves());
    }
    
}
