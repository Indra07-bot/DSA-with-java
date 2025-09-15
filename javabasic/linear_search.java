import java.util.*;
public class linear_search {
    public static int linearSearch ( int num[], int key){
        for(int i=0;i<num.length;i++){
            if(num[i]==key){
                return i;
            }
        }
        return -1;
    }
    public static void main(String args[]){
        int num[]={2,4,6,7,10,14,26};
        int key = 10;
        int index = linearSearch(num,key);
        if(index==-1){
            System.out.println("number not found");
        }else{
            System.out.println("number found at :"+index);
        }
    }
    
}
