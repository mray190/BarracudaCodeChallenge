package com.barracuda.visualize;

/**
 * 3D grids to terminal.
 * @author Aaron
 */
public class TerminalPrinter extends Printer<Void> {

    private final StringPrinter printer;
    
    public TerminalPrinter(){
        printer = new StringPrinter();
    }
    
    @Override
    protected Void print(int[][][] grid, boolean details) {
        System.out.println(printer.print(grid, details));
        return null;
    }
}
