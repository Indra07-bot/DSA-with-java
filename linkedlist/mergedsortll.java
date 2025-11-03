import java.util.LinkedList;

public class mergedsortll {
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;
    
    public Node getMid(Node head){
        Node slow =head;
        Node fast = head.next;
        while (fast!= null && fast.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node merge(Node lh, Node rh){
        Node mergeLL = new Node(-1);
        Node temp = mergeLL;

        while (lh !=null && rh!=null) {
            if(lh.data<=rh.data){
                temp.next = lh;
                lh = lh.next;
            }else{
                temp.next = rh;
                rh = rh.next;
            }
            temp = temp.next;
        }
        while (lh!=null) {
            temp.next = lh;
            lh = lh.next;
            temp = temp.next;
        }
        while (rh!=null) {
            temp.next = rh;
            rh = rh.next;
            temp = temp.next;
        }

        return mergeLL.next;
    }

    public Node mergeSort(Node head){
        if(head ==null || head.next ==null){
            return head;
        }
        //find mid
        Node mid = getMid(head);
        //left & right ms
        Node rightHead = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);

        //merged
        return merge(newLeft,newRight);

    }
    public static void print(Node head){
        if(head == null){
            System.out.println("ll is empty");
            return;
        }
        Node temp = head;
        while(temp !=null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("Null");
    }
       public void addLast(int data){
        Node newNode = new Node(data);
        if(head == null){
            head =tail= newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public static void main(String[] args) {
        mergedsortll ll = new mergedsortll();
        ll.addLast(1);
        ll.addLast(3);
        ll.addLast(2);
        ll.addLast(5);

        ll.print(head);
        ll.mergeSort(head);
        ll.print(head);

    }
    
}
