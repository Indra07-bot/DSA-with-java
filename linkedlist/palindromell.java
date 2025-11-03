public class palindromell {
        public static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;

    // Add node at last
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // Print Linked List
    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    //find mid
    //slow fast approach
    public Node findMid(Node head){
        Node slow = head;
        Node fast = head;
        while (fast != null &&cycle_detection.java fast.next!= null) {
            slow = slow.next;//+1
            fast = fast.next.next;//+2

        }
        return slow;
    }
    public boolean checkPalindrome(){
        if(head == null || head.next ==null){
            return true;
        }
            //1-find mid
            Node miNode = findMid(head);

            //2-reverse 2nd half
            Node prev = null;
            Node curr = miNode;
            Node next;
            while(curr != null){
                next = curr.next;
                curr.next=prev;
                prev = curr;
                curr = next;

            }
            Node right =prev;//right half
            Node left = head;

            //3-check left half and right half
            while(right != null){
                if(left.data != right.data){
                    return false;
                }
                left = left.next;
                right = right.next;
            }
            return true;

    }



    public static void main(String[] args) {
        palindromell ll = new palindromell();

        // 🔹 Manually inserting data to form a palindrome
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(2);
        ll.addLast(1);
        ll.print();
        System.out.println(ll.checkPalindrome());
    }

}
