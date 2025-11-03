public class zigzacll {
    public static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    // Insert at end
    public static void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Print linked list
    public static void print(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public void zigZag(){
        //find mid
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
            
        }
        Node mid = slow;

        //reverse the right mid
        Node next;
        Node prev =null;
        Node curr = mid.next;
        mid.next = null;
        while(curr != null){
                next = curr.next;
                curr.next=prev;
                prev = curr;
                curr = next;

            }
         Node right =prev;//right half
        Node left = head;
        Node nextL,nextR;
            //merged in zigzag format
            while (left != null && right != null) {
                nextL = left.next;
                left.next = right;
                nextR = right.next;
                right.next = nextL;

                left = nextL;
                right = nextR;
            }

    }


    public static void main(String[] args) {
        zigzacll ll = new zigzacll();
         ll.addLast(4);
        ll.addLast(3);
        ll.addLast(7);
        ll.addLast(8);
        ll.addLast(6);
        ll.addLast(2);
        ll.addLast(1);
        ll.print(head);
        ll.zigZag();
        ll.print(head);
    }
}
