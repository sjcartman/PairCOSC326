//https://www.geeksforgeeks.org/schedule-elevator-to-reduce-the-total-time-taken/
// https://www.geeksforgeeks.org/scan-elevator-disk-scheduling-algorithms/
// https://www.w3schools.com/java/java_abstract.asp

import java.util.*;

public abstract class Strategy {

    // create an elevator object??

    abstract void move_elevator();

}

class GFG extends Strategy {

    final int CAPACITY = 4;
    final int NO_OF_FLOORS = 10;
    final int TIME_BETWEEN_FLOORS = 5;
    final int DOOR_OPEN_CLOSE = 10;

    int curF;
    int desF;
    ArrayList<Passengers> passengers = new ArrayList();


    public static void main(String[] args) {


        Strategy elevator = new Strategy();

        for (int i = 0; i <; i++) {
            Random rand = new Random();
            arrive = rand.nextInt(TIME_BETWEEN_FLOORS);
            curF = rand.nextInt(NO_OF_FLOORS);
            desF = rand.nextInt(NO_OF_FLOORS);

            Passengers p = new Passengers(arrive, curF, desF);
            this.passengers.add(p);
        }

        println("----- Starting simulation -----");

        elevator.move_elevator();

        println("------ Ending simulation ------");

    }


    // move up or down depending on its current direction.

    //Given an integer k and an array arr[] representing the destination floors for N people waiting currently at the
    //ground floor and k is the capacity of the elevator i.e. maximum number of people it can hold at the same time. It
    //takes 1 unit time for the elevator to reach any consecutive floor from the current floor. The task is to schedule
    //the elevator in a way to minimize the total time taken to get all the people to their destination floor and then
    //return back to the ground floor.

    public  void move_elevator() {

        // Sort in descending order

        int temp;
        int n = passengers.size();






    }



    public  void goUp() {
        println(curF++ + "F | Going up ...");
        delay(5);
    }

    public  void goDown() {
        println(curF-- + "F | Going down ...");
        delay(5);
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

    public double getWaitTime(int destination) {
        return int waitTime += Math.abs(passengers.getEndTime - passengers.getStartTime); //??
    }

}