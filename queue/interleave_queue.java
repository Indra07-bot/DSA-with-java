import java.util.*;
public class interleave_queue {
    public static void interleaveQueue(Queue<Integer>q){
        Queue<Integer> first = new LinkedList<>();
        int size = q.size()/2;
        for(int i=0;i<size;i++){
            first.add(q.remove());
        }
        while (!first.isEmpty()) {
            q.add(first.remove());
            q.add(q.remove());
            
        }
    }
    
    public static void main(String[] args) {
        Queue <Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7);
        q.add(8);
        q.add(9);
        q.add(10);

        interleaveQueue(q);
        while (!q.isEmpty()) {
            System.out.print(q.remove()+" ");
        }
    }
}
