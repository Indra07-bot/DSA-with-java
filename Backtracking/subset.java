import java.util.*;
public class subset {
    public static void findSubset(String str,int i,String ans){
        //base case
        if(i==str.length()){
            if(ans.length()==0){
                 System.out.println("null");
            }
           
        else{
            System.out.println(ans);
        }
        return;
    }
    //recursive function
    //if not want to pair
    findSubset(str,i+1,ans);
    //want to pair
    findSubset(str,i+1,ans+str.charAt(i));
}
    public static void main(String[] args) {
        String str = "abc";
        findSubset(str, 0, " ");
        
    }
    

}