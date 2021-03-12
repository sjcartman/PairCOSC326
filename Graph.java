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
     private static ArrayList<String> visited = new ArrayList<String>();
     private static ArrayList<String> list = new ArrayList<String>();

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

     /**
      * Gets the input from stdin
      */

    private static void getInput() {
        boolean readingWordList = false;
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.length() == 0) {
                readingWordList = true;
            }
            else if (!readingWordList) {
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

            }
            else if (readingWordList) {
                Scanner scanLine = new Scanner(line);
                String s = scanLine.next();
                nodes.add(new GraphNode(s));
            }

        }
    }
     
     /**
      * Using depth first search to connect the word chains
      */
     private static boolean  dfs(GraphNode n, Route target, int depth) {
         
         for (String s: list) {
             // if node has been visited
             if (s.equals(n.getWord())) {
                 return false;
             }
         }

         //System.out.print(n.getWord());
         
        if (n.getWord().equals(target.getTarget())) {
            //System.out.println(depth);
            if(!target.isHopsSet() || target.getHops() == depth){
                visited = new ArrayList<String>();
                visited.add(n.getWord());
                return true;
            }
            
        }

       

        list.add(n.getWord());

         
         
        for (int i = 0; i < n.size(); i++) {
            boolean search = dfs(n.getNeighbour(i), target,depth++);

            if (search) {
                visited.add(n.getWord());
                return true;
            }
        }
        return false;
            
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
            if(r.possible){
                list = new ArrayList<String>();
               if(dfs(r.getValueNode(), r,2)){
                   //System.out.println(visited.size());
                   for (int i = visited.size()-1; i> -1; i--) {
                       System.out.print(visited.get(i) + " ");
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

         
