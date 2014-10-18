package GodzillasToothbrush;

import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class Board {
    private ArrayList<Layer> layers;
    
    //Constructor
    //--------------------------------------------------------------------------
    public Board(ArrayList<Layer> layers){
        this.layers = layers;
    }
    
    public Board(int[][][] data){
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
    public Point get(int x, int y, int z){
        return layers.get(z).get(x, y);
    }

    public ArrayList<Point> getRow(int z, int y){
        return layers.get(z).getRow(y);
    }
    
    public Layer getLayer(int z){
        return layers.get(z);
    }
    
    public ArrayList<Layer> getBoard(){
        return layers;
    }
    //--------------------------------------------------------------------------
    
}
