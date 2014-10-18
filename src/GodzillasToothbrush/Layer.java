package GodzillasToothbrush;

import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class Layer {

    private ArrayList<ArrayList<Point>> layer;

    //Constructor
    //--------------------------------------------------------------------------    
    public Layer(int[][][] data, int z) {
        layer = new ArrayList<>();
        
        int size = data.length;

        //Create Columns
        for (int y = 0; y < size - z; y++) {
            
            //Create Rows
            ArrayList<Point> row = new ArrayList<>();
            for (int x = 0; x < size - z - y; x++) {
                row.add(new Point(x, y, z, data[x][y][z]));
            }
            layer.add(row);
        }
    }
    //--------------------------------------------------------------------------    

    //Getters
    //--------------------------------------------------------------------------    
    public Point get(int x, int y) {
        return layer.get(x).get(y);
    }

    public ArrayList<Point> getRow(int y) {
        return layer.get(y);
    }
    //--------------------------------------------------------------------------

}
