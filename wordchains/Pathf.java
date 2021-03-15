package wordchains;

import java.util.ArrayList;

public class Pathf{
    private ArrayList<String> previousNodes;
    private boolean inValid;
    private GraphNode head;
    
    public String toString(){
        String s = "";
        for(String i:previousNodes){
            s +=i+" ";
        }
        return s;
    }

    public ArrayList<String> getPath(){
        return previousNodes;
    }

    public GraphNode getHead(){
        return head;
    }

    public Pathf(ArrayList<String> in,GraphNode value){
        previousNodes = new ArrayList<String>();
        for(String s: in){
            if(s.equals(value)){
                inValid = true;
            }
            else{
                previousNodes.add(s);
            }
        }
        previousNodes.add(value.getWord());
        head = value;
    }
    public Pathf(GraphNode value){
        previousNodes = new ArrayList<String>();
        previousNodes.add(value.getWord());
        head = value;
    }
    public int size(){
        return previousNodes.size();
    }
    public boolean vaild(){
        return !inValid;
    }
}
