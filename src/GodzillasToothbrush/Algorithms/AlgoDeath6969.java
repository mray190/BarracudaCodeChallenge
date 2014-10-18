/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;
import java.util.ArrayList;

/**
 *
 * @author shane
 */
public class AlgoDeath6969 extends Algorithm {
    @Override
    public Point makeMove(Board current, Board previous) {
        return null;
    }
    
    private Point findBiggestTriangle(Board board, int player, int max) {
        Point result = null;
        //Error checking your fucking input
        if(max < 2) return result;
        if(player != 1 && player != 2) return result;
        
        //Find biggest triangles starting with i
        for(int i = 2; i <= max; i++) {
            //Iterate through the layer
            Point find = null;
            for(int x = 0; x < 10-i; x++) {
                for(int y = 0; y < 10-x-i; y++) {
                    //Check the subtriangle
                    if(checkTriangle(board, i, player, board.get(x,y,0))) {
                        find = board.get(x,y,i);
                        result = find;
                    }
                }
            }
            if(find == null) break;
        }
        return result;
    }

    private boolean checkTriangle(Board board, int size, int player, Point point) {
        //Iterate through subtriangle
        for(int x = point.x; x < point.x + size; x++) {
            for(int y = point.y; y < point.y + size; y++) {
                int datum = board.get(x,y,0).data;
                if(datum != 0 && datum != player) return false;
            }
        }
        return true;
    }
}
