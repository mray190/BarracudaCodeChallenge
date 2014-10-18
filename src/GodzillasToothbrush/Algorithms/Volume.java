package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class Volume extends Algorithm{

    @Override
    public Point makeMove(Board current, Board previous) {
        //Gets legal moves for the player and the opponent
        ArrayList<Point> legalMovesPlayer = current.getLegalMoves(current.getPlayerNum());
        ArrayList<Point> legalMovesOpp = current.getLegalMoves(current.getOppNum());
        
        //Sorts based on the volume each move has
        //Volume describes how much the score will increases with this move
        //The sort puts the HIGHEST VOLUME at the end
        Algorithm.sort(legalMovesPlayer, new Algorithm.CompareVolumePlayer());
        Algorithm.sort(legalMovesOpp, new Algorithm.CompareVolumeOpp());
        
        //AI to wait / block / conquer
        //Pseudo idea:
        //  Block the opponent if my top volume > opponent top volume
        return null;
    }
    
}
