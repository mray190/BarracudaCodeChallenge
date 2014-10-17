package com.barracuda.visualize;

/**
 *
 * @author Aaron
 */
public abstract class Printer<T> {
    
    protected abstract T print(int[][][] grid, boolean details);
    
    public T print(int[][][] grid){
        return print(grid, false);
    }
    
    public T printDetails(int[][][] grid){
        return print(grid, true);
    }    
    
}
