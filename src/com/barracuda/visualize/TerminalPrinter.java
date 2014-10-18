package com.barracuda.visualize;

/**
 * 3D grids to terminal.
 * @author Aaron
 */
public class TerminalPrinter extends Printer<Void> {

    @Override
    protected Void print(int[][][] grid, boolean details) {
        System.out.println(Printer.stringPrinter.print(grid, details));
        return null;
    }
}
