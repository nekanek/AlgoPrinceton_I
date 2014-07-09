
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int N;         // number of elements on deque
    private Node first;    
    private Node last;
    
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }
    
    private class Node {
        private Item item;
        private Node next;
        private Node previous;  // in order to achieve constant time, should use doubly linked lists
        Node() {
            this(null);
        }
        Node (Item item) {
            if (item == null) {
                throw new NullPointerException();
            }
            else {            
                this.item = item;
                this.next = null;
                this.previous = null;
            }
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return this.N;
    }

    // add to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        else {
            Node input = new Node(item);
            if (this.N == 0) {
                this.first = input;
                this.last = input;
            }
            else {
                this.last.next = input;
                input.previous = this.last; //
                this.last = input;
            }
            this.N++;        
        }
    }
    
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        else {        
            Node input = new Node(item);
            if (this.N == 0) {
                this.first = input;
                this.last = input;
            }
            else {
                input.next = this.first;
                this.first.previous = input; //
                this.first = input;
            }
            this.N++;          
        }
    }
    
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        else {
            Node output = first;
            if (N == 1) {
                first = null;
                last = null;
            }
            else {
                first.next.previous = null;
                first = first.next;
            }
            this.N--;
            return output.item;
        }
    }
    
    // remove from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        else {
            Node output = last;
            if (N == 1) {
                this.last = null;
                this.first = null;
            }
            else {
                last.previous.next = null;
                last = last.previous;
            }
            this.N--;
            return output.item;
        }
    }

    @Override
    public Iterator<Item> iterator() {        //return an iterator over items in order from front to end
        return new ListIterator();
    }


    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext()  { 
            return current != null;         
        }
        
        @Override
        public void remove() { 
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


    public static void main(String[] args) {
        Deque<Integer> intsDeque = new Deque<>();
        int TESTS_NUMBER = 5;
        int PUSHED_VALUE = 100;
        String result = "";
        
        for (int i = 0; i < TESTS_NUMBER; i++) {
            System.out.println("..enqueing " + i);
            intsDeque.addLast(i);
            System.out.println("..pushing " + PUSHED_VALUE);
            intsDeque.addFirst(PUSHED_VALUE);
        //    result = PUSHED_VALUE + " " + result + " " + i;
        }
        // int N = intsDeque.N;
        //System.out.println("Resulting deque: " + result);
        System.out.println("Resulting deque: ");
        for (Integer i : intsDeque) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Popping:");
        for (int i = 0; i < TESTS_NUMBER+1; i++) {
            System.out.println(intsDeque.removeFirst());
        }
        System.out.println("..dequeing:");
        while (!intsDeque.isEmpty()) {
            System.out.println(intsDeque.removeLast());
        }
            
    }
    
}
