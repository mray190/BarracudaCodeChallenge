package com.barracuda.visualize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class FilePrinter extends Printer<File> {

    @Override
    protected File print(int[][][] grid, boolean details) {
        String filename = "src\\GodzillasToothbrush\\log.txt";
        
        PrintWriter out;
        try {
            out = new PrintWriter(filename);
            out.println(Printer.stringPrinter.print(grid, details));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilePrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new File(filename);
    }
    
}
