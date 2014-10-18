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
public class Death extends Algorithm {
    
    boolean waited, powerPlayed;
    
    public Death() {
        waited = false;
        powerPlayed = false;
    }
    
    @Override
    public Point makeMove(Board current, Board previous) {
        Point result = null;
        if(waited) {
            if(powerPlayed) {
                //We already won, just dick around from here on out
                if(current.getPlayerTokens() == 0) return null;
                result = AlgorithmHelper.highestScore(current.getLegalMoves());
            }
            else {
                //Power Play
                result = findBiggestTriangle(current, current.getPlayerNum(), current.getPlayerTokens());
                powerPlayed = true;
            }
        }
        else {
            //Wait
            if(current.getPlayerTokens() == 6) {
                waited = true;
            }
        }
        if(result!=null) System.out.println("AlgoDeath6969 chose point: "+result);
        else             System.out.println("AlgoDeath6969 strategically waited");
        return result;
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
    
    /*private Point shotsFired(Point currentPoint, Board board) {
        Point point = null;
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                point = fireShot(currentPoint.x, currentPoint.y, i, j, board);
                if(point != null){
                    if(point.equals(currentPoint)) return null;
                    break;
                }
            }
        }
        return point;
    }
    
    private Point fireShot(int x, int y, int dirX, int dirY, Board board) {
        while(inBounds(x, y, board)) {
            if(board.get(x, y, 0).data == board.getOppNum()) {
                return board.get(x-dirX, y-dirY, 0);
            }
            x += dirX;
            y += dirY;
        }
        return null;
    }
    
    private boolean inBounds(int x, int y, Board board) {
        return x < board.layersSize() && y < board.layersSize() && 
                    x + y < board.layersSize();
    }

    private Point findStart(Board current) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
