
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;         // number of elements on queue
    private Node first;    
    private Node last;


    public RandomizedQueue() {                 // construct an empty randomized queue
        first = null;
        last = null;
        N = 0;       
    }
   
    private class Node {
        private Item item;
        private Node next;
   
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
            }
        }
    }    

       
    public boolean isEmpty() {                // is the queue empty?
        return (first == null && last == null);
    }    
   
    public int size() {                       // return the number of items on the queue
        return N;
    }
   
    public void enqueue(Item item) {          // add the item
        if (N == 0) {
            first = new Node(item);
            last  = first;
        }
        else if (N==1) {
            last = new Node(item);
            first.next = last;
        }
        else {
            last.next = new Node(item);
            last = last.next;
        }
        N++;
    }
   
    public Item dequeue() {                   // delete and return a random item
        if (N == 0) {
            throw new java.util.NoSuchElementException();
        }
        else if (N == 1) {
            Item result = first.item;
            first = null;
            last = null;
            N = 0;
            return result;
        }
        else {
            int number = StdRandom.uniform(N);
            Node current = this.first;
            for (int i = 0; i < number-1; i++) {
                current = current.next;
            }
            Item result = current.next.item;
            current.next = current.next.next; // can b null if we return last element
            N--;
            return result;
        }
    }
   
    public Item sample() {                    // return (but do not delete) a random item
        if (N == 0) {
            throw new java.util.NoSuchElementException();
        }
        else {
            int number = StdRandom.uniform(N);
            Node current = this.first;
            for (int i = 0; i < number; i++) {
                current = current.next;
            }
            return current.item;   
        }    
    }
   
    public Iterator<Item> iterator() {         // return an independent iterator over items in random order
   
    }
   
    public static void main(String[] args) {   // unit testing
   
    }
}