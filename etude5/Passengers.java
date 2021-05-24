import java.util.*;

public class Passengers(){

		int start_time;
		int end_time;
		int floor_start;
		int floor_end;

public Passengers(int arrive, int curF, int desF){
		this.start_time = arrive;
		this.floor_start = curF;
		this.floor_end = desF;
		}

public int getStartTime(){
		return start_time;

		}

public int getEndTime(){
		return end_time;

		}

public int getFloor_start(){
		return floor_start;
		}

public int getFloor_end(){
		return floor_end;
		}
		}
