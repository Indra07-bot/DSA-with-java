
public class Lastoccurence {
    public static int LastOccurence(int arr[],int key,int i){
        if(i==arr.length){
            return -1;
        }
        int isFound = LastOccurence(arr,key,i+1);
        if(isFound== -1 && arr[i]==key){
            return i;
        }
        return isFound;
    }

    public static void main(String[] args) {
        int arr[]={3,5,4,5};
        System.out.println(LastOccurence(arr, 5, 0));

    }
    
}
