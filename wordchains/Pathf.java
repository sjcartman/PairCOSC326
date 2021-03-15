package wordchains;

import java.util.ArrayList;
import java.lang.*;

public class Pathf{
    private ArrayList<String> previousNodes;
    private boolean inValid = false;
    private GraphNode head;
    private int hashy;
    
    public int getHast(){
        return hashy;
    }

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

        StringBuilder sb = new StringBuilder();

        previousNodes = new ArrayList<String>();
        for(String s: in){
            if(s.equals(value.getWord())){
                inValid = true;
            }
            else{
                previousNodes.add(s);
                sb.append(s);
            }
        }
        sb.append(value.getWord());
        previousNodes.add(value.getWord());
        head = value;
        hashy = sb.toString().hashCode();
    }
    public Pathf(GraphNode value){
        previousNodes = new ArrayList<String>();
        previousNodes.add(value.getWord());
        head = value;
        hashy = value.getWord().hashCode();
    }
    public int size(){
        return previousNodes.size();
    }
    public boolean vaild(){
        return !inValid;
    }
}
