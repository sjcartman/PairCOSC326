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
     private static void dfs(GraphNode n) {
         ArrayList<n> visited = new ArrayList<>();
	 boolean[] visited = new boolean[];

	 for (int i = 0; i < n.length(); i++) {
	     if (!visited[i]) {
		 dfs(n);
	     }
	 }
     }
	 
    public static void main (String[] args) {
        getInput();
        link();
        for (GraphNode g: nodes) {
            System.out.print(g.getWord()+ " : ");
	    
            for (GraphNode i: g.getNeighbours()) {
                System.out.print(i.getWord()+ " | ");
            }
            System.out.println();
        }
        System.out.println();
	//	dfs(GraphNode g);
        //for(Route r:chains){
        //   System.out.println(r.getValue()+" "+ r.getTarget());
        //}
    }

 }
