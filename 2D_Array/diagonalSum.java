import java.util.*;
public class diagonalSum {
    public static int diagonallSum(int arr[][]){
        int sum=0;
        //bruteForce approach
        // for(int i=0;i<arr.length;i++){
        //     for(int j=0;j<arr[0].length;j++){
        //         if(i==j){
        //             sum+=arr[i][j];
        //         }
        //         else if(i+j==arr.length-1){
        //             sum+=arr[i][j];
        //         }
        //     }
        // }
        for(int i=0;i<arr.length;i++){
            sum+=arr[i][i];

            if(i !=arr.length-1-i) //i+j=arr.length-1; so j = arr.length-1-i
            sum+=arr[i][arr.length-1-i];
        }
        return sum;
    }

    public static void main(String args[]){
        int arr[][]={{1,2,3,4},
                     {5,6,7,8},
                     {9,10,11,12},
                     {13,14,15,16}};
        System.out.println(diagonallSum(arr));
    }
    
}
