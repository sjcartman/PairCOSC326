public class Route{
    private String value;
    private String target;
    private int hops;
    private boolean hopsSet;

    public Route (String inValue, String inTarget){
        
        value = inValue;
        target = inTarget;
        hopsSet = false;
    }

    public Route(String inValue,String inTarget, int inHops){
        value = inValue;
        target = inTarget;
        hopsSet = true;
        hops = inHops;
    }

    public String getValue(){
        return value;
    }

    public String getTarget(){
        return target;
    }
    public int getHops(){
        return hops;
    }
    public boolean isHopsSet(){
        return hopsSet;
    }

}