package GodzillasToothbrush;



import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class Point {

    //Static
    //--------------------------------------------------------------------------
    public static ArrayList<Point> convert(int[][][] grid) {
        ArrayList<Point> points = new ArrayList<>();

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                for (int z = 0; z < grid[x][y].length; z++) {
                    points.add(new Point(x, y, z, grid[x][y][z]));
                }
            }
        }

        return points;
    }
    //--------------------------------------------------------------------------

    //Data
    //--------------------------------------------------------------------------
    public int x, y, z, data;
    //--------------------------------------------------------------------------

    //Constructor
    //--------------------------------------------------------------------------
    public Point(int x, int y, int z, int data) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
    }
    //--------------------------------------------------------------------------

    //Default Object Functions
    //--------------------------------------------------------------------------
    @Override
    public String toString() {
        return new StringBuilder()
                .append('(')
                .append(x)
                .append(',')
                .append(y)
                .append(',')
                .append(z)
                .append(')')
                .toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        if (this.data != other.data) {
            return false;
        }
        return true;
    }
    //--------------------------------------------------------------------------    

}
