package wordchains;
/**
 * @author Sean Cartman
 * A graph node containing links all its neighbours and its value(string)
 */
import java.util.*;

public class GraphNode {
    private String value;
    private ArrayList<GraphNode> neighbours = new ArrayList<GraphNode>();
    private GraphNode previousNode;
    private boolean neighboursSet = false;

    public void resetPreviousNode(){
        previousNode = null;
    }
    public void setPreviousNode(GraphNode g){
        previousNode = g; 
    }
    public GraphNode getPreviousNode(){
        return previousNode;
    }
    public void setNeighboursSet() {
        neighboursSet = true;
    }

    public boolean getNeighboursSet() {
        return neighboursSet;
    }

    public GraphNode(String input) {
        value = input;
    }
    public void addNeighbour (GraphNode n) {
        neighbours.add(n);
    }
    public int size(){
        return neighbours.size();
    }
    public ArrayList<GraphNode> getNeighbours( ) {
        return neighbours;
    }
    public String getWord() {
        return value;
    }

}
