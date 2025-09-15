import java.util.*;
public class decimal_to_bineary {
    public static void decTobin(int num){
        int number = num;
        int bin = 0, pow =0;
        while (number>0) {
            int rem =number%2;
            bin = bin+(rem* (int) Math.pow(10, pow));
            pow++;
            number=number/2;
            
        }
        System.out.println(bin);
    }

    public static void main(String args[]){
        decTobin(5);

    }
    
}