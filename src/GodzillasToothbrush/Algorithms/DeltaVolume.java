package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class DeltaVolume extends Algorithm{

    @Override
    public Point makeMove(Board current, Board previous) {
        //Gets legal moves for the player and the opponent
        ArrayList<Point> legalMovesPlayer = current.getLegalMoves(current.getPlayerNum());
        ArrayList<Point> legalMovesOpp = current.getLegalMoves(current.getOppNum());

        //Sorts based on the volume each move has
        //Volume describes how much the score will increases with this move
        //The sort puts the HIGHEST VOLUME at the START
        Algorithm.sort(legalMovesPlayer, new Algorithm.CompareVolumePlayer());
        Algorithm.sort(legalMovesOpp, new Algorithm.CompareVolumeOpp());

        //AI to wait / block / conquer
        //----------------------------------------------------------------------
        //Null pointer safety
        if (legalMovesPlayer.isEmpty()) {
            return null;
        }
        if (legalMovesOpp.isEmpty()) {
            return null;
        }

        //Game data
        int currentTokens = current.getPlayerTokens();
        int turn = current.getTurnIndex();
        
        //Attempt to block multiple opponent potential moves
        for (int i = 0; i < legalMovesOpp.size(); i++) {
            //player volume - opponent volume
            int deltaVolume = legalMovesPlayer.get(0).volumePlayer - legalMovesOpp.get(i).volumeOpp;
            
            //CONQUER: Player top volume > opponent top volume
            if (deltaVolume > 0) {
                //Need more tokens
                if (shouldWait(currentTokens, turn, deltaVolume)) {
                    return null;
                } 
                else {
                    return legalMovesPlayer.get(0);
                }
            } 
            //BLOCK: Opponent top volume > player top volume
            else {
                Point result = current.cockblock(legalMovesPlayer, legalMovesOpp.get(i));

                //Able to block
                if (result != null) {
                    return result;
                }
            }
        }

        return legalMovesPlayer.get(0);
        //----------------------------------------------------------------------        
    }

    //False if NOT waiting for a token
    private boolean shouldWait(int currentTokens, int turn, int deltaVolume) {
        //Early move, wait
        if (turn < 3){
            return true;
        }
        
        //Power move, conquer
        if (deltaVolume > 7){
            return false;
        }
        
        //tokens > 4, conquer
        if (currentTokens > 4){
            return false;
        }

        //tokens == 0, wait
        if (currentTokens < 1){
            return true;
        }
        
        return Math.random() > 0.5;
    }    
}
