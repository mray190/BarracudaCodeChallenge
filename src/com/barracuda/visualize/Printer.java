package com.barracuda.visualize;

/**
 *
 * @author Aaron
 */
public abstract class Printer<T> {
    
    //Static
    //--------------------------------------------------------------------------
    public static final FilePrinter filePrinter = new FilePrinter();
    public static final TerminalPrinter terminalPrinter = new TerminalPrinter();
    public static final StringPrinter stringPrinter = new StringPrinter();
    //--------------------------------------------------------------------------
    
    //Functions
    //--------------------------------------------------------------------------
    protected abstract T print(int[][][] grid, boolean details);
    
    public T print(int[][][] grid){
        return print(grid, false);
    }
    
    public T printDetails(int[][][] grid){
        return print(grid, true);
    }    
    //--------------------------------------------------------------------------
    
}
