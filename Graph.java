/**
 *@author Sean Cartman
 *@author Susie Tay
 *
 */
 import java.util.*;

 public class Graph{
    private static ArrayList<GraphNode> nodes = new ArrayList<GraphNode>();
    private static ArrayList<Route> chains = new ArrayList<Route>();

    private static void link(){
        for(GraphNode i :nodes){
            if(!i.getNeighboursSet())
            {
                for(GraphNode j:nodes){
                    int totalDifferneces = 0;
                    char[] jChars = j.getWord().toCharArray();
                    char[] iChars = i.getWord().toCharArray();
                    if(iChars.length == jChars.length){
                        for(int k =0; k< jChars.length;k++){
                            if(jChars[k] != iChars[k]){
                                totalDifferneces++;
                            }
                            
                        }
                        if (totalDifferneces == 1){
                            i.addNeighbour(j);
                        }   
                    }
                    
                }
            }
            i.setNeighboursSet();
        }
    }


    private static void getInput(){
        boolean readingWordList = false;
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(line.length() == 0){
                readingWordList = true;
            }
            else if(!readingWordList){
                Scanner scanLine = new Scanner(line);
                String word1 = scanLine.next(); 
                String world2 = scanLine.next();
                if(scanLine.hasNextInt()){
                    int i = scanLine.nextInt();
                    chains.add(new Route(word1,world2,i));
                }
                else{
                    chains.add(new Route(word1,world2));
                }

            }
            else if(readingWordList){
                Scanner scanLine = new Scanner(line);
                String s = scanLine.next();
                nodes.add(new GraphNode(s));
            }

        }
    }

    public static void main (String[] args){
        getInput();
        link();
        for(GraphNode g: nodes){
            System.out.print(g.getWord()+ " : ");
            for(GraphNode i:g.getNeighbours()){
                System.out.print(i.getWord()+ " | ");
            }
            System.out.println();
        }
        System.out.println();
        //for(Route r:chains){
          //  System.out.println(r.getValue()+" "+ r.getTarget());
        //}
    }

 }