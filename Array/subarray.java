import java.util.*;
public class subarray {
    public static void subArr(int arr[]){
        int arrr[] =arr;
        for(int i=0;i<arrr.length;i++){
            int start = i;
            for(int j=i;j<arrr.length;j++){
                int end = j;
                System.out.print("{");
                for(int k=start;k<=end;k++){
                    System.out.print(+arr[k]+" ");
                }
                System.out.print("} ");
            }
            System.out.println();
        }

    }
    public static void main(String args[]){
        int arr[]={1,2,4,5,6};
        subArr(arr);

    }
}
