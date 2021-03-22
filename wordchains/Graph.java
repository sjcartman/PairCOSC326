package wordchains;
/**
 * Etude02 Word Chains
 * Filename: Graph.java
 * COSC326 Semester 1
 * @author Sean Cartman 3157705
 * @author Susie Tay 5717090
 */
import java.util.*;


 public class Graph {
     // declaration for input
    private static ArrayList<GraphNode> nodes = new ArrayList<GraphNode>();
     // delcaration for the chain
    private static ArrayList<Route> chains = new ArrayList<Route>();
   

     private static void link() {
         for(GraphNode i :nodes){
             if (!i.getNeighboursSet()) {
                 for (GraphNode j:nodes){
    	    // convert string to character
                   int totalDifferences = 0;
                   char[] jChars = j.getWord().toCharArray();
                    char[] iChars = i.getWord().toCharArray();
                    if (iChars.length == jChars.length) {
                        for (int k = 0; k < jChars.length;k++) {
                             if (jChars[k] != iChars[k]) {
                               totalDifferences++;
                             }
                         }
     			// Add to counter if there is a change in character
                         if (totalDifferences == 1) {
                             i.addNeighbour(j);
                         }   
                     }
                    
                 }
             }
             i.setNeighboursSet();
         }
    }

        // private static void link2() {
        // TreeSet<String> ts = new TreeSet<String>();
        // int totalDifferences = 0;

        // assertTrue(ts.add(getNeighboursSet()));

        // Iterator<String> itr = ts.iterator();
        // while (itr.hasNext()) {
        //     String exists = itr.next();
        //     if (exists.equals(nodes)) {
        //     itr.remove();
        //     }
        //     char[] iChars = itr.getWord().toCharArray();
        //     char[] jChars = nodes.getWord().toCharArray();

        //     if (iChars.length == jChars.length) {
        //     for (int k=0; k <jChars.length; k++) {
        //         totalDifferences++;
        //     }
        //     if (totalDifferences == 1) {
        //         assertTrue(ts.add(nodes));
        //     }
        //     }
        // }
        // }

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
               try {
                   String s = scanLine.next();
                   boolean set = true; ;
                   for(GraphNode n:nodes){
                       if(n.getWord().equals(s)){
                       System.err.println("Invalid input: "+line);
                       set = false;
                       }
                   }
                   if(set){
                       nodes.add(new GraphNode(s));
                   }
               
               } catch (Exception e) {
                   System.err.println("Invalid input: "+line);
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
        if (n.getWord().equals(target.getTarget()) && depth == target.getHops()) {
            return n;
        }
        
        for (GraphNode i: n.getNeighbours()) {
                i.setPreviousNode(n);
                GraphNode search = dfs(i, target, depth+1 );
                if (search != null) {
                    return n;
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
             
             for (GraphNode n: nodes) {
                 // check chains
                 if (r.getValue().equals(n.getWord())) {
                     r.setValuenode(n);
                     b = true;
                 } else if (r.getTarget().equals(n.getWord())) {
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
                for(GraphNode i: nodes){
                    i.resetPreviousNode();
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
