import java.util.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;    
    private int N; // last element of the array


    public RandomizedQueue() {                 // construct an empty randomized queue
        items = (Item[]) new Object[4];
        N = -1;       
    }
   
    public boolean isEmpty() {                // is the queue empty?
        return (N == -1);
    }    
   
    public int size() {                       // return the number of items on the queue
        return N + 1;
    }
   
    private void resize() {
        if (N == items.length-1) {
            Item[] newItems = (Item[]) new Object[N*2];
            for (int i = 0; i <= N; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        else if (N <= 0) {}
        else if (N < (int) 0.25*items.length) {
            Item[] newItems = (Item[]) new Object[(int)N/2];
            for (int i = 0; i <= N; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
    }
    public void enqueue(Item item) {          // add the item to the end
        if (item == null) {
            throw new NullPointerException();
        }
        
        resize();
        N += 1;
        items[N] = item;
    }
   
    public Item dequeue() {                   // delete and return a random item
        if (N == -1) {
            throw new java.util.NoSuchElementException();
        }
        else {
            int number = StdRandom.uniform(N+1);
            Item output = (Item)items[number];
            items[number] = items[N];
            items[N] = null;
            N -= 1;
            resize();
            return output;
        }
    }
   
    public Item sample() {                    // return (but do not delete) a random item
        if (N == -1) {
            throw new java.util.NoSuchElementException();
        }
        else {
            int number = StdRandom.uniform(N+1);
            Item output = items[number];
            return output;
        }    
    }
   
    @Override
    public Iterator<Item> iterator() {        //return an iterator over items in order from front to end
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        int left; // number of items left to iterate through
        Item[] itemsCopy;
        
        public ListIterator() {
            left = N; 
            itemsCopy = (Item[]) Arrays.copyOf(items, items.length);
        }
        
        @Override
        public boolean hasNext()  { 
            return (left >= 0);         
        }
        
        @Override
        public void remove() { 
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            else {
                int number = StdRandom.uniform(left+1);
                Item current = itemsCopy[number];    
                itemsCopy[number] = itemsCopy[left];
                itemsCopy[left] = current;
                left--;
                return current;
            }
        }
    }
   
    public static void main(String[] args) {   // unit testing
        RandomizedQueue<Integer> intsDeque = new RandomizedQueue<>();
        int TESTS_NUMBER = 5;
        String result = "";
        
        for (int i = 0; i < TESTS_NUMBER; i++) {
            System.out.println("..enqueing " + i);
            intsDeque.enqueue(i);
            //System.out.println("..pushing " + PUSHED_VALUE);
            //intsDeque.addFirst(PUSHED_VALUE);
            result = result + " " + i;
        }
        int M = intsDeque.N + 1;
        System.out.println("Resulting deque: " + result);
        System.out.println("Its size: " + M);
        System.out.println("Resulting deque: ");
        for (Integer i : intsDeque) {
            System.out.println("In first iterator: ");
            System.out.println(i + " ");
            System.out.println("Starting another iterator: ");
            for (Integer j : intsDeque) {
                System.out.print(j + " ");
            }
        }
        System.out.println();
        System.out.println("..dequeing:");
        while (!intsDeque.isEmpty()) {
            System.out.println(intsDeque.dequeue());
        }
//        int S = 5;
//        for (int i = 0; i < 100; i++) {
//            System.out.print(StdRandom.uniform(S+1) + " ");
//        }
//        System.out.println();   
    }
}