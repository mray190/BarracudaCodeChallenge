package com.barracuda.visualize;

/**
 * 3D grid as a string.
 * 
 * @author Aaron
 */
public class StringPrinter extends Printer<String> {
    
    @Override
    protected String print(int[][][] grid, boolean details){
        StringBuilder out = new StringBuilder();
        
        if (details) out.append("GRID PRINT");

        int maxZ = grid[0][0].length;
        
        //All Layers
        for (int z = 0; z < maxZ; z++) {
            
            //New Layer
            if (details) out.append("LEVEL: ").append(z).append('\n');
            
            //All Rows
            for (int y = 0; y < maxZ - z; y++){
                
                //All Columns
                for (int x = 0; x < maxZ - z - y; x++){
                    out.append(grid[x][y][z]).append(' ');
                }
                //New Line (Next Row)
                out.append('\n');
            }
            //New Lines (Next Layer)
            out.append('\n');
            if (details) out.append('\n');
        }
        
        return out.toString();
    }
    
}
