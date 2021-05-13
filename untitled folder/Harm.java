import java.util.*;
import java.lang.Math;
public class Harm{
    public static int divs(int n){
        int sum = 0;
        //double val = Math.sqrt(n);
        // Loop to find the proper
        // divisor of every number
        // from 1 to N
        //System.out.println(f);
        double f = Math.sqrt(n);
        for (int i = 2; i <= f; i++)
            if(n % i ==0){
                if(n/i ==i){
                    sum += i;
                }
                else{
                    sum +=(i+n/i);
                }
                
            }
            
        return sum;
    } 
    public static void main(String[] args){
        for(int i =1; i <= 2000000; i++){
            int x1 = divs(i);
            int x2 = 0;
            if(x1 > i){
                x2 = divs(x1);
                if((i == x2)){
                    System.out.println(x2 +" "+ x1);
                }
            }   
           
            //System.out.println(i +" "+ x1 +" "+x2);
        }
    }
}