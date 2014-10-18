package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;
import com.barracuda.visualize.Printer;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class DeltaVolumeV2 extends Algorithm{
    
    private final static boolean debug = false;

    @Override
    public Point makeMove(Board current, Board previous) {
        //Gets legal moves for the player and the opponent
        ArrayList<Point> legalMovesPlayer = current.getLegalMoves(current.getPlayerNum());
        ArrayList<Point> legalMovesOpp = current.getLegalMoves(current.getOppNum());

        //Sorts based on the volume each move has
        //Volume describes how much the score will increases with this move
        //The sort puts the HIGHEST VOLUME at the START
        Algorithm.sort(legalMovesPlayer, new Algorithm.MasterCompare(true));
        Algorithm.sort(legalMovesOpp, new Algorithm.MasterCompare(false));
        

        //DEBUG
        //----------------------------------------------------------------------
        if (debug) {
            System.out.println("Turn index: " + current.getTurnIndex());
            System.out.println("Our tokens: " + current.getPlayerTokens());
            System.out.println("Opp tokens: " + current.getOppTokens());
            System.out.println("Num legal moves: " + legalMovesPlayer.size());
            System.out.println("Num opp legal moves: " + legalMovesOpp.size());
            System.out.println(""); 
            Printer.terminalPrinter.printDetails(current.getRawGrid());
        }
        //----------------------------------------------------------------------
        
        //AI to wait / block / conquer
        //----------------------------------------------------------------------
        
        //We have no moves to make - wait until we have a move
        if (legalMovesPlayer.isEmpty()) {
            return null;
        }
        
        //We are the first player, wait until 4 turns have passed to play
        //Guarentees that we will get a z=4 tetra
        if (current.getPlayerNum()==1 && current.getTurnIndex()<=4) {
            return null;
        }
        //We are the second player, wait until 3 turns have passed to play
        //Guarentees that we will get a z=4 tetra
        else if (current.getPlayerNum()==2 && current.getTurnIndex()<=3) {
            return null;
        }
        //Not the first set of moves - use other logic
        else {
            
            //NEED LOGIC HERE TO DETERMINE IF THE OPP PLAYED A TOKEN LAST ROUND
            if (legalMovesOpp.isEmpty()) {
                return null;
            }
            
            //Tokens are greater than 4, play regardless of opponents
            if (current.getPlayerTokens()>=4) {
                //OPTIMIZE FOR MIDDLE
                return legalMovesPlayer.get(0);
            }
            //We don't have enough tokens to make a power play
            //Use our other logic to determine what
            else {
                int deltaVolume = legalMovesPlayer.get(0).volumePlayer - legalMovesOpp.get(0).volumeOpp;
                
                //Our move is substantially greater than the opponent
                //Capitalize while you can
                if (deltaVolume>11) {
                    return legalMovesPlayer.get(0);
                }
                //The opponents move is greater than ours by a lot
                //Block it while you can
                //If we can't block the opponent, wait to get a token
                else if (deltaVolume < -4) {
                    for (int i=0; i<legalMovesOpp.size(); i++) {
                        Point move = current.cockblock(legalMovesPlayer, legalMovesOpp.get(i));
                        if (move!=null)
                            return move;
                    }
                    return null;
                }
                //No substantial moves - use other logic to determine next move
                else {
                    //
                    if (legalMovesPlayer.size()<5) {
                        return legalMovesPlayer.get(0);
                    }
                    //We have a move that can be played on the second level,
                    //  play it since we have no other moves
                    else if (legalMovesPlayer.get(0).z >= 2) {
                        return legalMovesPlayer.get(0);
                    }
                    //No substaintial moves that will benefit us - therefore wait
                    else {
                        return null;
                    }
                }
            }
        }
        //----------------------------------------------------------------------        
    }  

    @Override
    public void endGame() {
    }
}
