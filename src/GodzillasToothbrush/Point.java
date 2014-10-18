package GodzillasToothbrush;

/**
 *
 * @author Aaron
 */
public class Point {

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

    
    //Scores
    //--------------------------------------------------------------------------
    public int[] toMove(){
        return new int[]{x, y, z};
    }    
    
    public int score(){
        return z*(z+1)*(z+2)/6;        
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
