import java.util.*;
import java.lang.Math;
public class Harm{
    public static int divs(int n){
        int sum = 0;
        int i;
        // Loop to find the proper
        // divisor of every number
        // from 1 to N
        for (i = 1; i <= n/2; ++i)
            if(n % i ==0){
                sum += i;
            }
      
        return sum;
    } 
    public static void main(String[] args){
        for(int i =0; i <= 2000000; i++){
            int x1 = divs(i);
            int x2 = 0;
            if(x1 > i){
                x2 = divs(x1);
            }   
            if((i == x2)){
                System.out.println(i +" "+ x1);
            }
            //System.out.println(i +" "+ x1 +" "+x2);
        }
    }
}