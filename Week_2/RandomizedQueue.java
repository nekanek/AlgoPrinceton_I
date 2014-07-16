import java.util.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size; // last element of the array
    private static final int MIN_SIZE = 4;

    public RandomizedQueue() { // construct an empty randomized queue
        items = (Item[]) new Object[MIN_SIZE];
        size = 0;
    }
   
    public boolean isEmpty() { // is the queue empty?
        return (size == 0);
    }
   
    public int size() { // return the number of items on the queue
        return size;
    }
   
    private void resize() {
        if (size == items.length) {
            items = Arrays.copyOf(items, items.length*2);
            // Item[] newItems = (Item[]) new Object[N*2];
            // for (int i = 0; i <= N; i++) {
            //     newItems[i] = items[i];
            // }
            // items = newItems;
        }
        else if (size < MIN_SIZE) {}
        else if (size < (int) 0.25*items.length) {
            items = Arrays.copyOf(items, items.length/2);
            // Item[] newItems = (Item[]) new Object[(int)N/2];
            // for (int i = 0; i <= N; i++) {
            //     newItems[i] = items[i];
            // }
            // items = newItems;
        }
    }
    public void enqueue(Item item) { // add the item to the end
        if (item == null) {
            throw new NullPointerException();
        }
        
        resize();
        items[size++] = item;
    }
   
    public Item dequeue() { // delete and return a random item
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        else {
            int number = StdRandom.uniform(size);
            Item output = (Item)items[number];
            items[number] = items[size - 1];
            items[--size] = null;
            resize();
            return output;
        }
    }
   
    public Item sample() { // return (but do not delete) a random item
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        else {
            int number = StdRandom.uniform(size);
            Item output = items[number];
            return output;
        }
    }
   
    @Override
    public Iterator<Item> iterator() { //return an iterator over items in order from front to end
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        int left; // number of items left to iterate through
        Item[] itemsCopy;
        
        public ListIterator() {
            left = size;
            itemsCopy = (Item[]) Arrays.copyOf(items, items.length);
        }
        
        @Override
        public boolean hasNext() {
            return (left > 0);
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            else {
                int number = StdRandom.uniform(left);
                Item current = itemsCopy[number];
                itemsCopy[number] = itemsCopy[left - 1];
                // itemsCopy[left] = current;
                left--;
                return current;
            }
        }
    }
   
    public static void main(String[] args) { // unit testing
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
        int M = intsDeque.size();
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
// int S = 5;
// for (int i = 0; i < 100; i++) {
// System.out.print(StdRandom.uniform(S+1) + " ");
// }
// System.out.println();
    }
}