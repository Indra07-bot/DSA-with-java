public class quicksort {
    public static void quickShot(int arr[],int si,int ei){
        if(si>=ei){
            return;
        }
        //last element
        int pIdx = partition(arr,si,ei);
        quickShot(arr,si,pIdx-1);//left part
        quickShot(arr, pIdx+1, ei);//right part
    }
    public static int partition(int arr[],int si,int ei){
        int pivot = arr[ei];
        int i =si-1;//to make place for smaller element

        for(int j=si;j<ei;j++){
            if(arr[j]<pivot){
                i++;
                //swap
                int temp = arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
               
            }
        }
        i++;
        int temp = pivot;
        arr[ei] = arr[i];
        arr[i]= temp;
         return i;
        
    }

    public static void printArr(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }




    public static void main(String[] args) {
        int arr[]={6,3,9,5,2,8}; 
        quickShot(arr, 0, arr.length-1);
        printArr(arr);
    }
    
}
