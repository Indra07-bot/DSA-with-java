
public class createll {
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

    public void addLast(int data){
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head =tail= newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
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
    public void addmiddle(int idx,int data){
        if(idx==0){
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i=0;
        while(i<idx-1){
            temp = temp.next;
            i++;
        } 
        newNode.next = temp.next;
        temp.next = newNode;
    }
    public int removeFirst(){
        if(size==0){
            System.out.println("ll is empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val = head.data;
            head= tail=null;
            size=0;
            return val;

        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }
    public int removeLast(){
        if(size==0){
            System.out.println("ll is empty");
        }else if(size==1){
            int val = head.data;
            head = tail =null;
            size=0;
            return val;
        }
        Node prev = head;
        for(int i=0;i<size-2;i++){
            prev = prev.next;
        }
        int val = prev.next.data;
        prev.next =null;
        tail = prev;
        size--;
        return val;
    }
    public int itrSearch(int key){
        Node temp = head;
        int i = 0;
        while(temp !=null){
            if(temp.data== key){
                return i;
            }
            temp= temp.next;
            i++;
        }
        return -1;
    }
    public int helper(Node head,int key){
        if(head == null){
            return -1;
        }
        if(head.data== key){
            return 0;
        }
        int idx = helper(head.next, key);
        if(idx==-1){
            return -1;
        }
        return idx+1;
    }
    public int recSearch(int key){
        return helper(head,key);
    }
    //reverse
    public void reverse(){
        Node prev = null;
        Node curr = tail = head;
        Node next;

        while (curr!=null) {
            next = curr.next;
            curr.next=prev;
            prev = curr;
            curr = next;
            
        }
        head = prev;
    }
    public static void main(String[] args) {
        createll ll = new createll();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addmiddle(2, 8);
        ll.print();
        ll.removeFirst();
        ll.removeLast();
        ll.print();
        System.out.println(ll.size);
        ll.reverse();
        ll.print();
        // System.out.println(ll.itrSearch(8));
        // System.out.println(ll.recSearch(1));


    }
    
}
