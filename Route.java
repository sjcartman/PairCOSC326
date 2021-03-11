/**
 * Stores and displays information about the word chains
 */

public class Route {
    private String value;
    private String target;
    private int hops;
    private boolean hopsSet;
    private GraphNode valueNode;
    public boolean possible = false;

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
    public Route (String inValue, String inTarget) {
        
        value = inValue;
        target = inTarget;
        hopsSet = false;
    }

      /** sets the value of data field to input parameter value with int input
     * @param inValue value of the word
     * @param inTarget target of the word
     * @param inHops hops of the integer value
     */

    public Route(String inValue,String inTarget, int inHops) {
        value = inValue;
        target = inTarget;
        hopsSet = true;
        hops = inHops;
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
