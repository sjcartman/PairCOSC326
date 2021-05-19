import java.util.*;

public class Passengers(){

    int start_time;
    int end_time;
    int floor_start;
    int floor_end;

    public Passengers(int time,int start,int end){
	this.start_time = time;
	this.floor_start = start;
	this.floor_end = end;
    }

    public int getStart_time(){
	return start_time;

    }

    public int getEnd_time(){
	return end_time;

    }

    public int getFloor_start(){
	return floor_start;
    }

    public int getFloor_end(){
	return floor_end;
    }
}
