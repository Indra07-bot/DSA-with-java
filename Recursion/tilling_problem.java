public class tilling_problem {
    public static int tilesNeed(int n){
        if(n==0 || n==1){
            return 1;
        }
        //vertical
        int fnm1 = tilesNeed(n-1);
        //horizontal
        int fnm2 = tilesNeed(n-2);

        int toWays = fnm1+fnm2;
        return toWays;
    }
    public static void main(String[] args) {
        System.out.println(tilesNeed(3));
    
    }
    
}
