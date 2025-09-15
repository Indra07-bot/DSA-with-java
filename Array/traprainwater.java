public class traprainwater {
    public static int trappedRainwater(int height[]){
        //left max boundary
        int leftMax[]=new int[height.length];
        leftMax[0]=height[0];
        for(int i=1;i<height.length;i++){
            leftMax[i]=Math.max(height[i], leftMax[i-1]);
        }
        //right most hight
        int rightMax[]= new int[height.length];
        rightMax[height.length-1]=height[height.length-1];
        for(int j=height.length-2;j>=0;j--){
            rightMax[j]=Math.max(height[j],rightMax[j+1]);

        }
        int trappedWater = 0;
        for(int i=0;i<height.length;i++){
            int waterLevel = Math.min(leftMax[i],rightMax[i]);

            trappedWater+=waterLevel-height[i];
        }
        return trappedWater;
    }
    public static void main(String args[]){
        int height[]={4,2,0,6,3,2,5};
        System.out.println(trappedRainwater(height));
    }
    
}
