package GodzillasToothbrush.Algorithms;

import GodzillasToothbrush.Board;
import GodzillasToothbrush.Point;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class Volume extends Algorithm{

    boolean waited;
    
    public Volume() {
        endGame();
    }
    
    @Override
    public Point makeMove(Board current, Board previous) {
        if(!waited) {
            if(current.getPlayerTokens() == 4) waited = true;
            return null;
        }
        if(current.getPlayerTokens() == 0) return null;
        if(current.getOppTokens() == 0)    return null;
        //Gets legal moves for the player and the opponent
        ArrayList<Point> legalMovesPlayer = current.getLegalMoves(current.getPlayerNum());
        ArrayList<Point> legalMovesOpp    = current.getLegalMoves(current.getOppNum());
        
        //Sorts based on the volume each move has
        //Volume describes how much the score will increases with this move
        //The sort puts the HIGHEST VOLUME at the end
        Algorithm.sort(legalMovesPlayer, new Algorithm.CompareVolumePlayer());
        Algorithm.sort(legalMovesOpp,    new Algorithm.CompareVolumeOpp());
        
        //AI to wait / block / conquer
        //Pseudo idea:
        //  Block the opponent if my top volume > opponent top volume
        Point bestMovePlayer = legalMovesPlayer.remove(0);
        Point bestMoveOpp    = legalMovesOpp.remove(0);
        
        if(bestMovePlayer.volumePlayer >= bestMoveOpp.volumeOpp) {
            //We play our move (score whore)
            return bestMovePlayer;
        }
        else {
            //Block their move (cock block)
            bestMoveOpp = findCockBlock(bestMoveOpp, current);
            if(bestMoveOpp.data != 0) return bestMovePlayer;
            return bestMoveOpp;
        }
    }

    @Override
    public void endGame() {
        waited = false;
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
            for(int y = bestMoveOpp.y; y < bestMoveOpp.y + size - x; y++) {
                if(board.get(x, y, 0).data == 0) return board.get(x, y, 0);
            }
        }
        return bestMoveOpp;
    }
    
}
