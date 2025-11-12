import java.util.*;
public class first_non_repeat {
    public static void printNonRepeating(String str){
        int ferq[] = new int[26];
        Queue <Character> q = new LinkedList<>();

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            q.add(ch);
            ferq[ch-'a']++;

            while (!q.isEmpty() && ferq[q.peek()-'a']>1 ) {
                q.remove();
                
            }
            if(q.isEmpty()){
                System.out.print("-1"+" ");
            }else{
                System.out.print(q.peek()+" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String str = "aabccxb";
        printNonRepeating(str);
    }
    
}
