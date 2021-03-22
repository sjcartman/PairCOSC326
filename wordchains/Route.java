package wordchains;
/**
 * Stores and displays information about the word chains
 */
public class Route {
    private String value;
    private String target;
    private int hops;
    private boolean hopsSet = false;
    private GraphNode valueNode;
    private boolean possible = true;
    private String line;



    public void setValues(String a, String b){
        value = a;
        target = b;
    }
    public void setHops(int i){
        hops = i;
        hopsSet = true;
    }

    public boolean isPossible(){
        return possible;
    }

    public void impossible(){
        possible = false;
    }

    public String toString(){
        if(line != null && !possible){
            return "Invalid : "+line;
        }
        else if(!possible){
            return "Invalid";
        }
        
        StringBuilder s = new StringBuilder(value);
        s.append(" ");
        s.append(target);
        if(hopsSet){
            s.append(" ");
            s.append(hops);
        }
        s.append(" impossible");
        return s.toString();
        
    }

    public void setValuenode(GraphNode g) {
        valueNode = g;
    }

    public GraphNode getValueNode() {
        return valueNode;
    }

    /** sets the value of data field to input parameter value with no int input
     * @param inValue value of the word
     * @param inTarget target of the word
     */
    public Route (String inValue) {
        line = inValue;
    }

  
    /**
     * returns the value of the data field value
     * @return the value of the word
     */
    public String getValue() {
        return value;
    }

    /** 
     * returns the value of the data field target
     * @return target word
     */ 

    public String getTarget() {
        return target;
    }

    /**
     * returns the value of the data field hops
     * @return the number of hops
     */
    
    public int getHops(){
        return hops;
    }

    /**
     * returns a boolean value of the data field hopsSet
     * @return true or false
     */
    public boolean isHopsSet() {
        return hopsSet;
    }

}
