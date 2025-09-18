// Purpose: Deletes the nth node from the end of a singly linked list.

// Steps:

// Find size → Traverse once to count total nodes (sz).

// Special case → If n == sz, delete head (head = head.next).

// Find previous node of target:

// Target index = sz - n (1-based counting).

// Start with i = 1 at head.

// Move until i < target index.

// Delete node → prev.next = prev.next.next.

// Indexing style: 1-based counting (head = position 1, not 0).

// Complexity:

// Time: O(size) (two traversals in worst case: one for size, one for deletion).

// Space: O(1) (no extra memory).
public class removeNth {
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
    public static int size;


    public void addFirst(int data){
         //create new node
        Node newNode =new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        
        //newnode next->head ?(link)
        newNode.next =head;
        //head->newNode
        head = newNode;
    }
    public void print(){
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
    //remove Nth node from end
    public void deleteNthfromEnd(int n){
        //calculate size
        int sz=0;
        Node temp =head;
        while(temp !=null){
            temp=temp.next;
            sz++;
        }
        if(n== sz){
            head = head.next;
            return;
        }

        //sz-n
        int i= 1;
        int iToFind = sz-n;
        Node prev = head;
        while(i<iToFind){
            prev=prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;


    }

    public static void main(String[] args) {
        removeNth ll = new removeNth();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(4);
        ll.addFirst(5);
        ll.print();
        ll.deleteNthfromEnd(2);
        ll.print();

    }
    
}
