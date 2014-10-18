/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;

/**
 *
 * @author mray
 */
public class WeightPickerAlgorithm extends Algorithm{
    @Override
    public Point makeMove(Board current, Board previous) {
        return AlgorithmHelper.highestScore(current.getLegalMoves());
    }
}
