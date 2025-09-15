import java.util.*;
public class bineary_search {
    public static int binearySearch(int num[],int key){
        int start = 0, end = num.length-1;
       while(start<= end){
         int mid = ((start+end)/2);
        if(num[mid]== key){
            return mid;
        }
        if(num[mid]<key){
            start= mid+1;
        }else{
            end = mid-1;
        }
       }
       return -1;
    }
    public static void main(String args[]){
        int number[] ={2,4,6,8,10,12,14};
        int key = 10; 
        int index = binearySearch(number, key);
        if(index==-1){
            System.out.println("number not Found");

        }else{
            System.out.println("found at :"+index);
        }
    }
    
}
