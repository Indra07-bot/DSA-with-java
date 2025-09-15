import java.util.*;
public class bineary_to_decimal {
    public static void binTodec(int binnum){
        int count =0, decnum =0;
        while (binnum>0) {
            int lastdigit = binnum%10;
            decnum = decnum+(lastdigit*(int)Math.pow(2,count));
            count++;
            binnum=binnum/10;
            
        }
        System.out.println(decnum);
    }
    public static void main(String args[]){
        binTodec(101);

    }

    
}
