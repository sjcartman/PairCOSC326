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
    int[] destinationFloors = new int[NO_OF_FLOORS];

    public static void main(String[] args) {

        Strategy elevator = new Strategy();
        elevator.getInput();
    }

    public void getInput() {
        Random rand = new Random();

        // generate random current floor
        curF = rand.nextInt(NO_OF_FLOORS) ;
        println("current floor " + curF);

        // generate random persons
        pass = rand.nextInt(CAPACITY);
        println("no of pp " + pass);

        // generate destination floor depending on how many persons
    //    for (int i=1; i <= pass && i < destinationFloors.length; i++) {

     //        desF = rand.nextInt(NO_OF_FLOORS) + 1;
     //        destinationFloors[i] = desF;
     //        println("to where " + desF);
      //  }

        println(curF + "F | Number of passengers: " + pass);

        //if (pass > 0 && pass <= CAPACITY) {

            listofFloors = new ArrayList<>();
            for (int i = 0; i < pass; i++) {
                int floor = desFloor(i);

                println("des floor " + floor);
                if (!listofFloors.contains(floor)) {
                    println("here to add floor");
                    listofFloors.add(floor);
                }
            }

        //}

        move_elevator();
    }

    public  void move_elevator() {

        for (int i : listofFloors) {
            int shortest_path = findShortest(i);

            //println("To destination " + shortest_path + "F (" + destinationFloors[shortest_path-1] + ") Passengers");
            println("To destination " + shortest_path + "F.");

            while(curF < shortest_path) {
                goUp();
            }

            while (curF > shortest_path) {
                goDown();
            }

            if(curF == shortest_path){
                println("arrived at floor: " + curF );
            }
            // while (destinationFloors[shortest_path-1] > 0) {
            //     println(destinationFloors[shortest_path-1]);
            //     println("Getting off ... " + destinationFloors[shortest_path-1]-- + " passenger(s) at " + curF + "F.");
            //     delay(2);  //getting on and off
            //     println("xx");

            // }

        }
    }


    public  int desFloor(int f) {
        Random prand = new Random();
        boolean validFloor = false;

        int floor = 0;

        while (!validFloor) {
            floor = prand.nextInt(NO_OF_FLOORS);
            println("curF " + curF );
            println("floor " + floor);
            if (floor == curF) {
                println("You are in " + curF + "F.");
            } else {
               destinationFloors[floor]++;
               validFloor = true;
           }
       }
        return floor;
    }




    public  void goUp() {
        println(curF++ + "F | Going up ...");
        delay(2);
    }

    public  void goDown() {
        println(curF-- + "F | Going down ...");
        delay(3);
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

    public  int findShortest(int floor) {

        println("size " + listofFloors.size());

        int destination = floor;
        int shortest = 0;
        if(curF > destination){
            shortest = curF - Math.abs(curF-destination);
        }
        else if (curF < destination){
            shortest = curF + Math.abs(curF-destination);
            
        }


        println("show lstofFloors " + listofFloors.get(0));
        println("Shortest " + shortest);

        //int temp = 0;

        // for (int i = 1; i < listofFloors.size(); i++) {
        //     if (shortest > Math.abs(curF - floor)) {
        //         shortest = Math.abs(curF - floor);
        //         temp = i;
        //     }
        // }
        //shortest = listofFloors.get(temp);
        println("Shortest " + shortest);

        //listofFloors.set(temp, 10);
        return shortest;
    }
}