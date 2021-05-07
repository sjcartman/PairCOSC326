import java.util.*;
public class Cube{
    public static TreeSet<String> cubes = new TreeSet<String>();
    public static TreeSet<String> roCubes = new TreeSet<String>();
    public static TreeSet<String> testCube = new TreeSet<String>();
    public static boolean found = false;

    public static void proveRots(String s, int depth){
        if(depth == 4){
            return;
        }
        String a = x(s);
        String b = y(s);
        String c = z(s);
        boolean d =  testCube.add(a);
      
        boolean e =testCube.add(b);
      
        boolean f =testCube.add(c);
       
        proveRots(a, depth+1);
        proveRots(b, depth+1);
        proveRots(c, depth+1);
    }
    
    public static void findCube(String s, int depth){
        if(depth == 8){
            cubes.add(s);
            return;
        }
        findCube(s+"b", depth+1);
        findCube(s+'y', depth+1);
    }
    public static boolean dorots(String s, int depth){
        if(roCubes.contains(s)){
            return false;
        }
        if(depth == 5){
            roCubes.add(s);
            return false;
        }
        boolean a = dorots(x(s), depth+1);
        if(a){
            boolean b = dorots(y(s), depth+1);
            if(b ){
                dorots(z(s), depth+1);
            }
        }
        return true;

        }
    public static void main(String args[]){
        findCube("", 0);
    
        //proveRots("bbbbbbbb", 0);
        //System.out.println(testCube.size());

     
        for(String s: cubes){
            proveRots(s, 0);
            System.out.println(s + " " +testCube.size());
            boolean b = true;
            for(String i: testCube){
                if(roCubes.contains(i)){
                    b = false;
                }
            }
            if(b){
                roCubes.add(s);
            }
            //testCube = new TreeSet<String>();

        }
        System.out.println(roCubes.size());

        
    }
    public static String z(String cube){
        String out = "";
        out += cube.charAt(1);//0
        out += cube.charAt(5);//1
        out += cube.charAt(3);//2
        out += cube.charAt(7);//3
        out += cube.charAt(0);//4
        out += cube.charAt(4);//5
        out += cube.charAt(2);//6
        out += cube.charAt(6);//7
        return out;
    }
    public static String y(String cube){
        String out = "";
        out += cube.charAt(2); //0
        out += cube.charAt(3); //1
        out += cube.charAt(6); //2
        out += cube.charAt(7); //3 
        out += cube.charAt(0); //4
        out += cube.charAt(1); //5
        out += cube.charAt(4); //6 
        out += cube.charAt(5); //7
        return out;
    }

    public static String x(String cube){
        String out = "";
        out += cube.charAt(2); //0
        out += cube.charAt(0); //1
        out += cube.charAt(3); //2
        out += cube.charAt(1); //3 
        out += cube.charAt(6); //4
        out += cube.charAt(4); //5
        out += cube.charAt(7); //6 
        out += cube.charAt(5); //7
        return out;

    }
    
    // public static String left(String cube){
    //     for(int i =0; i <3; i++){
    //         cube = right(cube);    
    //     }
    //     return cube;
    // }
    
    // public static String bottom(String cube){
    //     for(int i =0; i <3; i++){
    //         cube = right(cube);    
    //     }
    //     return cube;
    // }
    
}