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
    
    public int getMove() {
        return MAX_MOVES - game.moves_remaining;
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
        int z = layersSize() - val.z;
        return z*(z+1)*(z+2)/6;
    }
    //--------------------------------------------------------------------------    
}
