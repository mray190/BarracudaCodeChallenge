package GodzillasToothbrush;

import com.barracuda.contest2014.GameState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Aaron
 */
public class Board {

    private ArrayList<Layer> layers;
    private GameState game;
    private final int MAX_MOVES = 110;

    //Constructor
    //--------------------------------------------------------------------------
    public Board(ArrayList<Layer> layers, GameState game) {
        this.layers = layers;
        this.game = game;
    }

    public Board(int[][][] data, GameState game) {
        this.game = game;
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
    public Point top(){
        return layers.get(layers.size() - 1).get(0, 0);
    }
    
    public Point get(int x, int y, int z) {
        return layers.get(z).get(x, y);
    }

    public ArrayList<Point> getRow(int z, int y) {
        return layers.get(z).getRow(y);
    }

    public Layer getLayer(int z) {
        return layers.get(z);
    }

    public ArrayList<Layer> getBoard() {
        return layers;
    }
    
    public int[][][] getRawGrid() { 
        return game.board; 
    }
    //--------------------------------------------------------------------------

    //Game State Information
    //--------------------------------------------------------------------------    
    public int getTurnIndex() {
        return MAX_MOVES - game.moves_remaining;
    }

    public int getPlayerNum() {
        return game.player;
    }

    public int getOppNum() {
        if (game.player == 2) {
            return 1;
        }
        return 2;
    }

    public int getPlayerTokens() {
        return game.tokens;
    }

    public int getOppTokens(){
        return game.opponent_tokens;
    }
    
    public boolean didOppWait(Board previous) {
        return game.opponent_tokens > previous.game.opponent_tokens;
    }
    //--------------------------------------------------------------------------

    //TODO
    //--------------------------------------------------------------------------
    //Gets all squares that are below (within the triangle) of the parameter
    public ArrayList<Point> getWaterfall(Point p, boolean resetFreq) {
        ArrayList<Point> points = new ArrayList<>();
        for (int z = p.z; z >= 0; z--) {
            for (int x = p.x; x <= p.x + p.z - z; x++){
                for (int y = p.y; y <= p.y + p.z - z +p.x - x; y++){
                    Point temp = get(x,y,z);
                    if (resetFreq) temp.frequency = 0;
                    else temp.frequency++;
                    points.add(temp);
                }
            }
        }
        return points;
    }
    
    public ArrayList<Point> getLargestBottom(int playerNum){
        //All moves, all legal moves (ignoring token count)
        ArrayList<Point> allMoves = getWaterfall(top(), false);
        ArrayList<Point> legal = new ArrayList<>();
        
        //Gets all moves that are legal (only 0 or our space)
        for (Point move: allMoves){
            ArrayList<Point> waterfall = getWaterfall(move, false);
            
            boolean valid = true;
            for (Point drop: waterfall){
                if (drop.data != 0 && drop.data != playerNum){
                    valid = false;
                    break;
                }
            }
            if (valid) legal.add(move);
        }
        
        //Sort (descending) with most spots on floor first
        Collections.sort(legal, new Comparator<Point>(){

            @Override
            public int compare(Point o1, Point o2) {
                //Size of free bottom layer
                int diff = countSpotsBottom(o2) - countSpotsBottom(o1);
                if (diff != 0) return diff;
                
                //Side length of the bottom
                return o2.z - o1.z;
            }
            
        });
        
        //Gets the bottom triangle of our best option
        if (legal.isEmpty()) return null;
        return getSpotsBottom(legal.get(0));
    }
    
    //Gets all legal moves for the given player number
    public ArrayList<Point> getLegalMoves(int playerNum) {
        //Containers
        ArrayList<Point> allPoints = getWaterfall(top(),true);
        ArrayList<Point> legalPoints = new ArrayList<>();
        
        //Tokens
        int tokens;
        if (playerNum == game.player){
            tokens = game.tokens;
        }
        else{
            tokens = game.opponent_tokens;
        }
        
        //Iterate
        for (Point p: allPoints){
            //Valid tokens to access
            if (p.z < tokens){
                int volume = countSpotsFree(getWaterfall(p,false), playerNum, p);
                
                if (playerNum == game.player){
                    p.volumePlayer = volume;
                }
                else{
                    p.volumeOpp = volume;
                }

                //Valid to take
                if (volume != -1){
                    legalPoints.add(p);
                }
            }
        }
        return legalPoints;
    }    

    //Return -1 if not legal move    
    public int countSpotsFree(ArrayList<Point> points, int playerNum, Point parent) {
        int count = 0;
        if(parent.data != 0) return -1;
        for (Point p : points) {
            if (p.data == 0){
                count++;
            }
            else if (p.data != playerNum){
                return -1;
            }
        }
        
        return count;
    }
    
    //In the waterfall of the point, all subpoints with z == 0 and data == 0
    public int countSpotsBottom(Point point){
        ArrayList<Point> waterfall = getWaterfall(point, false);
        int count = 0;
        for (Point x: waterfall){
            if (x.z == 0 && x.data == 0) count++;
        }
        return count;
    }
    
    public ArrayList<Point> getSpotsBottom(Point point){
        ArrayList<Point> waterfall = getWaterfall(point, false);
        ArrayList<Point> triangle = new ArrayList<>();
        for (Point x: waterfall){
            if (x.z == 0) triangle.add(x);
        }
        return triangle;
    }
    
    //Returns null if unable to block
    //Precondition: playerMoves is sorted with best move FIRST
    public Point cockblock(ArrayList<Point> playerMoves, Point oppMove){
        ArrayList<Point> waterfall = getWaterfall(oppMove, false);
        
        for (Point p: playerMoves){
            if (waterfall.contains(p)){
                return p;
            }
        }
        
        return null;
    }
    //--------------------------------------------------------------------------

    //Point System
    //--------------------------------------------------------------------------
    public int scoreOf(Point val) {
        return val.score();
    }
    //--------------------------------------------------------------------------    
}
