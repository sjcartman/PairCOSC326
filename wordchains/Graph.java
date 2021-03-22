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
               System.err.println("Unexpected empty line found, Stopping input read");
               return;
           }
           
           else if (line.length() == 0) {
               readingWordList = true;
           }
           else if (!readingWordList) {
               Route z = new Route(line);
               try{
                  
                   Scanner scanLine = new Scanner(line);
                   String word1 = scanLine.next(); 
                   String word2 = scanLine.next();
                   z.setValues(word1, word2);
                   if(scanLine.hasNextInt()) {
                        z.setHops(scanLine.nextInt());
                   }
                   chains.add(z);
                   scanLine.close();
               
               }catch(Exception e){
                   z.impossible("Invalid : ");
               }
           }
           
           else if (readingWordList) {
               Scanner scanLine = new Scanner(line);
               String word = "";
               try {
                    word = scanLine.next();
               }catch (Exception e) {
                   System.err.println("Invalid :"+line);
               }
               if(scanLine.hasNext()){
                    System.err.println("Invalid : "+line);
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

      private static LinkedList<String> dfs(GraphNode n, Route target) {

        Stack<LinkedList<GraphNode>> s = new Stack<LinkedList< GraphNode>>();
        LinkedList<GraphNode> l = new LinkedList<GraphNode>();
        l.add(n);
        s.push(l);
        while(!s.empty()){
            l = s.pop();
            n = l.getLast();
            if(l.size() < target.getHops()){
                for(GraphNode a : n.getNeighbours()){
                    if(!l.contains(a)){
                        LinkedList<GraphNode> newList = new LinkedList<GraphNode>(l);
                        newList.add(a);
                        s.push(newList);
                    }
                }
            }else if(n.getWord().equals(target.getTarget())) {
                LinkedList<String> rL = new LinkedList<String>();
                while(!l.isEmpty()){
                    rL.add(l.removeLast().getWord());
                }
                return rL;
                
            }   
        }
        return null;   
    }


     private static GraphNode bfs(GraphNode f, Route target) {
        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        queue.add(f);

        while(queue.peek() != null){
            GraphNode n = queue.poll();
            if(n.getWord().equals(target.getTarget())){
               
                return n;
            }
            
            for (GraphNode i: n.getNeighbours()) {
                if(i.getPreviousNode() == null){
                    i.setPreviousNode(n);
                    queue.add(i);
                }
            }           
            
        }
        return null;
    }

     private static void find_valueNode() {

         for (Route r: chains) {
            GraphNode a = nodes.get(r.getValue());
            GraphNode b = nodes.get(r.getTarget());
            if(a != null && b != null){
                r.setValuenode(a);
            }
            else{
                r.impossible("Invalid(start or target word is not a known word) : ");
            }
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
            if(r.isPossible()){
                
                GraphNode path = null;
                r.getValueNode().setPreviousNode(r.getValueNode());
                LinkedList<String> a= null;
                if(r.isHopsSet()){
                    a = dfs(r.getValueNode(),r);
                }
                else{
                    for(Entry<String, GraphNode> n: nodes.entrySet()){
                        n.getValue().resetPreviousNode();
                    }
                    path = bfs(r.getValueNode(),r);
                    a = new LinkedList<String>();
                    r.getValueNode().resetPreviousNode();
                    if(path != null){
                        a.add(path.getWord());
                        while(path.getPreviousNode() != null){
                            path = path.getPreviousNode();
                            a.add(path.getWord());
                        }
                    }
                }
                if(a != null){
                    StringBuilder b = new StringBuilder();
                    while(!a.isEmpty()){
                        b.append(a.removeFirst());
                        b.append(" ");
                    }
                    System.out.println(b.toString());
                }
                else{
                        System.out.println(r);
                }
            }else{
                System.out.println(r);
            }

        }

        
    }

 }
