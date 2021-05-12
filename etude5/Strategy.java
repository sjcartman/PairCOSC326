import java.util.*;

public class Strategy {
    final int CAPACITY = 4;
  final int NO_OF_FLOORS = 10;
    final int TIME_BETWEEN_FLOORS = 5;
    final int DOOR_OPEN_CLOSE = 10;
    
     int curF;
     int desF;
     ArrayList<Integer> listofFloors;
     int pass;
     int[] destinationFloors = new int[NO_OF_FLOORS +1];

    public static void main(String[] args) {

	Strategy elevator = new Strategy();
	elevator.getInput();

	//Strategy elevator = new Strategy();
	//getInput();
	
	//Strategy elevator = new Strategy();
	//getInput();
    }

    public void getInput() {
        Random rand = new Random();

		// generate random current floor
        curF = rand.nextInt(NO_OF_FLOORS + 1);
        println("current floor " + curF);
        
        // generate random persons
        pass = rand.nextInt(CAPACITY + 1);  
        println("no of pp " + pass);
        
    	// generate destination floor depending on how many persons
        for (int i=1; i <= pass && i < destinationFloors.length; i++) {
		        
        desF = rand.nextInt(NO_OF_FLOORS + 1);
        destinationFloors[i] = desF;
        println("to where " + desF);
        
        }
        
        
        println(curF + "F | Number of passengers: " + pass);

        if (pass > 0 && pass < CAPACITY) {

            listofFloors = new ArrayList<>();
            for (int i = 0; i <= pass; i++) {
                int floor = passFloor(i);
                if (!listofFloors.contains(floor)) {
println("here to add floor");
                    listofFloors.add(floor);
                }
            }

        }

        move_elevator();
    }

    public  void move_elevator() {
    
        for (int i =0; i < listofFloors.size(); i++) {
            int shortest_path = findShortest();

            println("To destination " + shortest_path + "F (" + destinationFloors[shortest_path-1] + ") Passengers");

            while(curF < shortest_path) {
                goUp();
            }

            while (curF > shortest_path) {
                goDown();
            }

            while (destinationFloors[shortest_path+1] > 0) {
                println("Getting off ... " + destinationFloors[shortest_path-1]-- + " ) Passengers at " + curF + "F.");
                delay(2000);  //getting on and off
                
            }
            
        }
    }


public  int passFloor(int f) {
        Random prand = new Random();
        boolean validFloor = false;

        int floor = 0;
	 while (!validFloor) {
	     floor = prand.nextInt(NO_OF_FLOORS + 1);

	     if (floor == curF) {
	         println("You are in " + curF + "F.");
         } else {
	         destinationFloors[floor + 1]++;
	         validFloor = true;
         }
     }
     return floor;
        }




    public  void goUp() {
	println(curF++ + "F | Going up ...");
	delay(5000);
    }

    public  void goDown() {
	println(curF-- + "F | Going down ...");
	delay(5000);
    }

    public  void println(Object o) {
	System.out.println(o);
    }

    public  void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {}
    }

    public  double getTransitTime(int destination) {
	int diff = Math.abs(curF - desF);
	return diff * TIME_BETWEEN_FLOORS;
    }

    public  double getWaitTime(int destination) {
        return 1.0;
        // not sure yet
    }

    public  int findShortest() {
        //int shortest = Math.abs(curF -listofFloors.get(0));
        
        int shortest = listofFloors.get(0);
        println("show lstofFloors " + listofFloors.get(0));
        println("Shortest " + shortest);
        
        int temp = 0;

        for (int i = 0; i < listofFloors.size(); i++) {
            if (shortest > Math.abs(curF - listofFloors.get(i))) {
                shortest = Math.abs(curF - listofFloors.get(i));
                temp = i;
            }
        }
        shortest = listofFloors.get(temp);
        println("Shortest " + shortest);
        listofFloors.set(temp, 11);
        return shortest;
    }
}