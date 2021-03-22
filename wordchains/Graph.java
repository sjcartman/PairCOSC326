package wordchains;
/**
 * Etude02 Word Chains
 * Filename: Graph.java
 * COSC326 Semester 1
 * @author Sean Cartman 3157705
 * @author Susie Tay 5717090
 */
import java.util.*;
import java.util.Map.Entry;


 public class Graph {
     // declaration for input
    private static TreeMap<String,GraphNode> nodes = new TreeMap<String,GraphNode>();
     // delcaration for the chain
    private static ArrayList<Route> chains = new ArrayList<Route>();
    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
   

    //  private static void link() {
    //      for(GraphNode i :nodes){
    //          if (!i.getNeighboursSet()) {
    //              for (GraphNode j:nodes){
    // 	    // convert string to character
    //                int totalDifferences = 0;
    //                char[] jChars = j.getWord().toCharArray();
    //                 char[] iChars = i.getWord().toCharArray();
    //                 if (iChars.length == jChars.length) {
    //                     for (int k = 0; k < jChars.length;k++) {
    //                          if (jChars[k] != iChars[k]) {
    //                            totalDifferences++;
    //                          }
    //                      }
    //  			// Add to counter if there is a change in character
    //                      if (totalDifferences == 1) {
    //                          i.addNeighbour(j);
    //                      }   
    //                  }
                    
    //              }
    //          }
    //          i.setNeighboursSet();
    //      }
    // }

        private static void link() {
            for(Entry<String, GraphNode> n: nodes.entrySet()){
                //System.out.println("ssss");
                String a = n.getValue().getWord();
                for(int i =0; i < a.length();i++){
                    for(char c : alphabet){
                        StringBuilder s = new StringBuilder(a);
                        s.setCharAt(i, c);
                        GraphNode g = nodes.get(s.toString());
                        if(g != null){
                            n.getValue().addNeighbour(g);
                        }
                        
                    }
                    
                }

            }
        }

    /**
     * Gets the input from stdin
     */

   private static void getInput() {
       boolean readingWordList = false;
       Scanner scan = new Scanner(System.in);
       while (scan.hasNextLine()) {
           String line = scan.nextLine();
           if (line.length() == 0 && readingWordList) {
               System.err.println("Empty Line");
               return;
           }
           
           else if (line.length() == 0) {
               readingWordList = true;
           }
           else if (!readingWordList) {
               try{
                
                   Scanner scanLine = new Scanner(line);
                   String word1 = scanLine.next(); 
                   String word2 = scanLine.next();
                   if(scanLine.hasNextInt()) {
                       int i = scanLine.nextInt();
                       chains.add(new Route(word1,word2,i));
                   }
                   else {
                       chains.add(new Route(word1,word2));
                   }
                   scanLine.close();
               
               }catch(Exception e){
                   System.err.println("Invalid input: "+line);
               }
           }
           
           else if (readingWordList) {
               Scanner scanLine = new Scanner(line);
               String word = "";
               try {
                    word = scanLine.next();
               }catch (Exception e) {
                   System.err.println("Invalid input: "+line);
               }
               if(scanLine.hasNext()){
                    System.err.println("Invalid input : "+line);
               }
               else{
                   nodes.put(word,new GraphNode(word));
               }
               scanLine.close();
              
           }

       }
       scan.close();
   }

     /**
      * Using breath first search to connect the word chains
      */

      private static GraphNode dfs(GraphNode n, Route target,int depth) {
        
        if(depth > target.getHops()){
            return null;
        }
        //System.out.println(n.getWord());
        if (n.getWord().equals(target.getTarget()) && depth == target.getHops()) {
            return n;
        }
        
        for (GraphNode i: n.getNeighbours()) {
                i.setPreviousNode(n);
                GraphNode search = dfs(i, target, depth+1 );
                if (search != null) {
                    return search;
                }
        }
        return null;
            
    }


     private static GraphNode bfs(GraphNode f, Route target) {
        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        queue.add(f);

        while(queue.peek() != null){
            GraphNode n = queue.poll();
            //System.out.println(n.getWord());
            if(n.getWord().equals(target.getTarget())){
                //System.out.println(n.getWord());
                //f.resetPreviousNode();
                return n;
            }
            //System.out.println(n);
            for (GraphNode i: n.getNeighbours()) {
                if(i.getPreviousNode() == null){
                    i.setPreviousNode(n);
                    queue.add(i);
                }
            }           
            
        }
        //f.resetPreviousNode();
        return null;
    }

     private static void find_valueNode() {

         for (Route r: chains) {
             boolean b = false;
             boolean a = false;
             
             for (Entry<String, GraphNode> n: nodes.entrySet()) {
                 // check chains
                 if (r.getValue().equals(n.getValue().getWord())) {
                     r.setValuenode(n.getValue());
                     b = true;
                 } else if (r.getTarget().equals(n.getValue().getWord())) {
                     a = true;
                 }
             
             }
             r.possible = b && a;
             
         }
     }
	 
     public static void main (String[] args) {
        getInput();
        
        link();
        
        find_valueNode();
        
        //for (GraphNode g: nodes) {
          //   System.out.print(g.getWord()+ " : ");
	    
            // for (int i =0; i< g.size();i++) {
            //System.out.print(g.getNeighbour(i).getWord()+ " | ");
            //}
            //System.out.println();
        //}
        //System.out.println();
		
        for(Route r:chains){
            //(r.getValue()+" "+ r.getTarget());
                for(Entry<String, GraphNode> n: nodes.entrySet()){
                    n.getValue().resetPreviousNode();
                }
                GraphNode path = null;
                r.getValueNode().setPreviousNode(r.getValueNode());
                if(r.isHopsSet()){
                    path = dfs(r.getValueNode(),r,1);
                }
                else{
                    //System.out.println(chains.get(0).getTarget() + chains.get(0).getValue());
                    path = bfs(r.getValueNode(),r);
                }
                r.getValueNode().resetPreviousNode();
                //System.out.println(path.getPreviousNode().getWord());
                //System.out.println(visited.size());
                
                if(path != null){
                    ArrayList<String> a= new ArrayList<String>();
                    a.add(path.getWord());
                    while(path.getPreviousNode() != null){
                        //System.out.println(path.getWord());
                        path = path.getPreviousNode();
                        a.add(path.getWord());
                    };
                    for(int j=a.size()-1; j >= 0;j--){
                        System.out.print(a.get(j)+ " ");
                    }
                    System.out.println();
                }
           else{
                System.out.print(r.getValue()+" "+r.getTarget()+" ");
                   if(r.isHopsSet()){
                       System.out.print(r.getHops());
                   }
                System.out.println(" impossible");
           }

        }

        
    }

 }
