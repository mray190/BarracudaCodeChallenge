package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Aaron
 */
public class Volume extends Algorithm{

    boolean waited;
    boolean powerPlayed;
    
    public Volume() {
        endGame();
    }
    
    @Override
    public Point makeMove(Board current, Board previous) {
        System.out.println("\nTOKENS us="+current.getPlayerTokens()+" op="+current.getOppTokens());
        if(!waited) {
            if(current.getPlayerTokens() >= 5-current.getPlayerNum()) waited = true;
            System.out.println("strategically waited");
            return null;
        }
        if(current.getPlayerTokens() == 0) {System.out.println("strategically waited");return null;}
        if(current.getOppTokens() == 0)    {System.out.println("strategically waited");return null;}
        //Gets legal moves for the player and the opponent
        ArrayList<Point> legalMovesPlayer = current.getLegalMoves(current.getPlayerNum());
        ArrayList<Point> legalMovesOpp    = current.getLegalMoves(current.getOppNum());
        
        
        
        //Sorts based on the volume each move has
        //Volume describes how much the score will increases with this move
        //The sort puts the HIGHEST VOLUME at the end
        

        Collections.shuffle(legalMovesPlayer);
        Collections.shuffle(legalMovesOpp);

        Algorithm.sort(legalMovesPlayer, new Algorithm.CompareVolumePlayer());
        Algorithm.sort(legalMovesOpp,    new Algorithm.CompareVolumeOpp());
        
        //AI to wait / block / conquer
        //Pseudo idea:
        //  Block the opponent if my top volume > opponent top volume
        Point bestMovePlayer = legalMovesPlayer.get(0);
        Point bestMoveOpp    = legalMovesOpp.get(0);
        
        System.out.println("Current best move (us): "+bestMovePlayer);
        System.out.println("Current best move (op): "+bestMoveOpp);
        
        if(powerPlayed && bestMovePlayer.volumePlayer >= bestMoveOpp.volumeOpp) {
            //We play our move (score whore)
            if(bestMovePlayer!=null) System.out.println("chose point: "+bestMovePlayer);
            else             System.out.println("strategically waited");
            return bestMovePlayer;
        }
        else if(!powerPlayed) {
            powerPlayed = true;
            System.out.println("Powerplayed point: "+bestMovePlayer);
            return bestMovePlayer;
        }
        else {
            //Block their move (cock block)
            bestMoveOpp = findCockBlock(bestMoveOpp, current);
            //bestMoveOpp = current.cockblock(legalMovesPlayer, bestMoveOpp);
            if(bestMoveOpp == null || bestMoveOpp.data != 0) {System.out.println("chose point: "+bestMovePlayer);return bestMovePlayer;}
            System.out.println("chose cockblock: "+bestMoveOpp);
            return bestMoveOpp;
        }
    }

    @Override
    public void endGame() {
        waited = false;
        powerPlayed = false;
    }
    //finds a solid cock block that only costs 1 token
    private Point findCockBlock(Point bestMoveOpp, Board board) {
        int size = bestMoveOpp.z;
        Point test;
        //We drew it on paper trust us
        switch(size) {
            case 3:
                test = board.get(bestMoveOpp.x+1, bestMoveOpp.y+1, 0);
                if(test.data == 0) return test;
                break;
            case 4:
                test = board.get(bestMoveOpp.x+1, bestMoveOpp.y+1, 0);
                if(test.data == 0) return test;
                test = board.get(bestMoveOpp.x+2, bestMoveOpp.y+1, 0);
                if(test.data == 0) return test;
                test = board.get(bestMoveOpp.x+1, bestMoveOpp.y+2, 0);
                if(test.data == 0) return test;
                break;
            case 5:
                test = board.get(bestMoveOpp.x+2, bestMoveOpp.y+1, 0);
                if(test.data == 0) return test;
                test = board.get(bestMoveOpp.x+1, bestMoveOpp.y+2, 0);
                if(test.data == 0) return test;
                test = board.get(bestMoveOpp.x+1, bestMoveOpp.y+1, 0);
                if(test.data == 0) return test;
                test = board.get(bestMoveOpp.x+3, bestMoveOpp.y+1, 0);
                if(test.data == 0) return test;
                test = board.get(bestMoveOpp.x+2, bestMoveOpp.y+2, 0);
                if(test.data == 0) return test;
                test = board.get(bestMoveOpp.x+1, bestMoveOpp.y+3, 0);
                if(test.data == 0) return test;
                break;
            default:
                break;
        }
        for(int x = bestMoveOpp.x; x < bestMoveOpp.x + size; x++) {
            for(int y = bestMoveOpp.y; y < bestMoveOpp.y + size + bestMoveOpp.x - x; y++) {
                if(board.get(x, y, 0).data == 0) return board.get(x, y, 0);
            }
        }
        return board.get(bestMoveOpp.x, bestMoveOpp.y, 0);
    }
    
}
