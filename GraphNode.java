/**
 *@author Sean Cartman
 * A graph node containing links all its neighbours and its value(string)
 */
import java.util.*;

public class GraphNode{
    private String value;
    private ArrayList<GraphNode> neighbours = new ArrayList<GraphNode>();
    private boolean neighboursSet = false;

    public void setNeighboursSet(){
        neighboursSet = true;
    }

    public boolean getNeighboursSet(){
        return neighboursSet;
    }

    public GraphNode(String input){
        value = input;
    }
    public void addNeighbour (GraphNode n){
        neighbours.add(n);
    }
    public GraphNode[] getNeighbours(){
        GraphNode[] out = new GraphNode[neighbours.size()];
        out = neighbours.toArray(out);
        return out;
    }
    public String getWord(){
        return value;
    }

}