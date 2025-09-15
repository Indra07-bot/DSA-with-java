public class friend_pairing {
    public static int friendsPair(int n){
        if(n==1 || n==2){
            return n;
        }
        //single
        int fnm1 = friendsPair(n-1);

        //pair
        int fnm2 = friendsPair(n-2);
        int pairway =(n-1)*fnm2;
        
        //toways
        int toWays = fnm1+pairway;
        return toWays;
    }
    
    public static void main(String[] args) {
        System.out.println(friendsPair(3));
        
    }
}
