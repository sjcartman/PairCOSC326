/**
 *@author Sean Cartman
 *@author Susie Tay
 *
 */
 import java.util.*;

 public class Graph{
    private static ArrayList<GraphNode> nodes = new ArrayList<GraphNode>();
    private static ArrayList<Route> chains = new ArrayList<Route>();

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
                nodes.add(new GraphNode(line));
            }

        }
    }

    public static void main (String[] args){
        getInput();
        for(GraphNode g: nodes){
            System.out.println(g.getWord());
        }
        System.out.println();
        for(Route r:chains){
            System.out.println(r.getValue());
        }
    }

 }