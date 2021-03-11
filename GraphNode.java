/**
 * @author Sean Cartman
 * A graph node containing links all its neighbours and its value(string)
 */
import java.util.*;

public class GraphNode {
    private String value;
    private ArrayList<GraphNode> neighbours = new ArrayList<GraphNode>();
    private boolean neighboursSet = false;

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
    public GraphNode  getNeighbour(int index) {
        return neighbours.get(index);
    }
    public String getWord() {
        return value;
    }

}
