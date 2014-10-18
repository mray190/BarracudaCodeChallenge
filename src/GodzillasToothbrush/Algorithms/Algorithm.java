/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Point;

/**
 *
 * @author shane
 * extend this to make algorithms
 */
public abstract class Algorithm {
    
    /**
     * 
     * @param current the current board
     * @param previous the previous board
     * @return the point to be changed, null if waiting
     */
    public abstract Point makeMove(Board current, Board previous);
    
}
