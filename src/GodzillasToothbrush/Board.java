package GodzillasToothbrush;

import com.barracuda.contest2014.GameState;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class Board {

    private ArrayList<Layer> layers;
    private GameState game;
    private final int MAX_MOVES = 110;

    //Constructor
    //--------------------------------------------------------------------------
    public Board(ArrayList<Layer> layers, GameState game) {
        this.layers = layers;
        this.game = game;
    }

    public Board(int[][][] data, GameState game) {
        this.game = game;
        layers = new ArrayList<>();

        int size = data.length;

        //Adds all layers
        for (int z = 0; z < size; z++) {
            layers.add(new Layer(data, z));
        }
    }
    //--------------------------------------------------------------------------

    //Getters
    //--------------------------------------------------------------------------
    public Point get(int x, int y, int z) {
        return layers.get(z).get(x, y);
    }

    public ArrayList<Point> getRow(int z, int y) {
        return layers.get(z).getRow(y);
    }

    public Layer getLayer(int z) {
        return layers.get(z);
    }

    public ArrayList<Layer> getBoard() {
        return layers;
    }
    //--------------------------------------------------------------------------

    //Game State Information
    //--------------------------------------------------------------------------    
    public int getMove() {
        return MAX_MOVES - game.moves_remaining;
    }
    
    public ArrayList<Point> getLegalMoves(){
        ArrayList<Point> legalMoves = new ArrayList<>();
        
        for (int[] move : game.legal_moves) {
            //Convert to point
            legalMoves.add(
                    new Point(
                            move[0], 
                            move[1], 
                            move[2], 
                            game.board[move[0]][move[1]][move[2]]
                    )
            );
        }
        
        return legalMoves;
    }
    
    public boolean isValidMove(Point point){
        return getLegalMoves().contains(point);
    }
    
    public int getPlayerNum() {
        return game.player;
    }
    
    public int getOppNum() {
        if (game.player==2)
            return 1;
        return 2;
    }
    //--------------------------------------------------------------------------

    //Sizes
    //--------------------------------------------------------------------------    
    public int layersSize(){
        return layers.size();
    }
    //--------------------------------------------------------------------------
   
    //Point System
    //--------------------------------------------------------------------------
    public int scoreOf(Point val){
        return val.score();
    }
    //--------------------------------------------------------------------------    
}
