// The stack stores indexes of potential "next greater" elements.

// Traversing from right to left ensures we always look ahead.

// Smaller elements are popped because they are useless (blocked by bigger ones).
import java.util.*;
public class nextGreaterElement {
    public static void main(String[] args) {
        int arr[] = {6,8,0,1,3};
        Stack<Integer>s = new Stack<>(); //a stack to keep indexes of elements.
        int nextGreater[] = new int[arr.length];

        for(int i = arr.length-1;i>=0;i--){
            while (!s.isEmpty()&& arr[s.peek()] <= arr[i] ) {
                s.pop();
            }

            if(s.isEmpty()){
                nextGreater[i]=-1;
            }else{
                nextGreater[i]=arr[s.peek()];
            }
            s.push(i);
        }
        for(int i=0;i<nextGreater.length;i++){
            System.out.print(nextGreater[i]+" ");
        }
        System.out.println();
    }
    
}
